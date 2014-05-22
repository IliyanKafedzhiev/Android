package com.veli.androidsu_09.accessingdb;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

	private static SQLiteDatabase db;
	private static final String TABLE_PERSON = "Person";
	private static final String COLUMN_PERSON_NAME = "Name";
	private static final String DATABASE_NAME = "accessingdatabase";
	private static final String PRIMARY_KEY = "_id";

	private static final String DB_CREATE_TABLE_PERSON = "CREATE TABLE "
			+ TABLE_PERSON + " ( " + PRIMARY_KEY
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PERSON_NAME
			+ " TEXT NOT NULL);";

	private static final String SELECT_ALL_PERSONS = "select * from "
			+ TABLE_PERSON;

	private static final int DATABASE_VERSION = 1;

	public static DbHelper dbHelper = null;

	protected DbAdapter() {
    }
	 
	public static void initialize(Context context) {
		
		if (dbHelper == null) {
		dbHelper = new DbHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
		}
	}

	public static SQLiteDatabase openDatabase() {
		db = dbHelper.getWritableDatabase();
		return db;
	}

	public static void closeDatabase() {
		db.close();
	}

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			try {
				db.execSQL(DB_CREATE_TABLE_PERSON);
			} catch (Exception ex) {

			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			// recreate it
			db.execSQL("drop table if exists " + COLUMN_PERSON_NAME);
			this.onCreate(db);
		}
	}

	public static long createPerson(String name) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_PERSON_NAME, name);

		return db.insert(TABLE_PERSON, null, values);
	}

	public static Person getPersonByName(String name) {
		Person person = new Person();
		Cursor c = fetchPersonByName(name);

		if (c != null) {
			c.moveToFirst();
		}
		cursor.close();

		return person;
	}

	private static Cursor fetchPersonByName(String name) {
		return null;
	}

	public static List<Person> getAllPersons() {
		List<Person> personList = new ArrayList<Person>();

		Cursor cursor = db.rawQuery(SELECT_ALL_PERSONS, null);

		if (cursor.moveToFirst()) {
			do {
				Person person = new Person();
				person.setId(Integer.parseInt(cursor.getString(0)));
				person.setName(cursor.getString(1));

				personList.add(person);
			} while (cursor.moveToNext());
		}
		cursor.close();

		return personList;
	}
}
