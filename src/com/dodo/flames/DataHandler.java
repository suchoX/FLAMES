package com.dodo.flames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DataHandler 
{
	FeedReaderDbHelper mDbHelper;
	SQLiteDatabase db;
	Context ctx;
	public DataHandler(Context ctx)
	{
		this.ctx=ctx;
		mDbHelper=new FeedReaderDbHelper(ctx);
	}
    public static final String TABLE_NAME = "History";
    public static final String COLUMN_NAME_NAME1 = "na1";
    public static final String COLUMN_NAME_NAME2 = "na2";
    public static final String COLUMN_NAME_RESULT = "result";
    private static final String CREATE_TABLE= "CREATE TABLE "+ TABLE_NAME + " ("+COLUMN_NAME_NAME1+ " TEXT NOT NULL,"+ COLUMN_NAME_NAME2 + " TEXT NOT NULL," + COLUMN_NAME_RESULT + " TEXT NOT NULL);";
    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + TABLE_NAME;
        
    public class FeedReaderDbHelper extends SQLiteOpenHelper 
    {
    	public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public FeedReaderDbHelper(Context context) 
        {
        	super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) 
        {
                db.execSQL(CREATE_TABLE);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
        	db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
    public DataHandler open()
	{
		db=mDbHelper.getWritableDatabase();
		return this;
	}
    
	public void close()
	{
		mDbHelper.close();
	}
    public long Insert(String n1,String n2,int r)
    {
    	ContentValues values = new ContentValues();
    	values.put(COLUMN_NAME_NAME1,n1);
    	values.put(COLUMN_NAME_NAME2, n2);
    	switch(r)
		{
			case 1:values.put(COLUMN_NAME_RESULT,"Friends");
					break;
			case 2:values.put(COLUMN_NAME_RESULT,"Lovers");
					break;
			case 3:values.put(COLUMN_NAME_RESULT,"Attraction");
					break;
			case 4:values.put(COLUMN_NAME_RESULT,"Married");
					break;
			case 5:values.put(COLUMN_NAME_RESULT,"Enemies");
					break;
			case 6:values.put(COLUMN_NAME_RESULT,"Siblings");
					break;
		}
    	return db.insertOrThrow(TABLE_NAME, null, values);
    }
    public Cursor returnData()
	{
		return db.query(TABLE_NAME, new String[]{COLUMN_NAME_NAME1, COLUMN_NAME_NAME2,COLUMN_NAME_RESULT}, null, null, null, null, null);
	}
    public void removeall()
    {
    	db.delete(TABLE_NAME, null, null);
    }
}
