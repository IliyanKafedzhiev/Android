package com.veli.androidsu_09.accessingdb;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

	private static final String SELECT_ALL_PEOPLE = "select * from "
			+ TABLE_PERSON;
	private static final String SELECT_PERSON_BY_NAME = "select * from "
			+ TABLE_PERSON + " where " + COLUMN_PERSON_NAME + " = ? LIMIT 1;";

	private static final String SELECT_TABLE_SIZE = "select count(*) from ";

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

			db.execSQL(DB_CREATE_TABLE_PERSON);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			// recreate it
			db.execSQL("drop table if exists " + COLUMN_PERSON_NAME);
			this.onCreate(db);
		}
	}

	public static long addPerson(Person person) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_PERSON_NAME, person.getName());

		return db.insert(TABLE_PERSON, null, values);
	}

	public static List<Person> getAllPeopleByName(String name) {

		List<Person> personList = null;
		Cursor cursor = db.rawQuery(SELECT_PERSON_BY_NAME,
				new String[] { name });

		if (cursor != null) {
			try {
				personList = getPersonListFromCursor(cursor);
			} finally {
				cursor.close();
			}
		}

		return personList;
	}

	public static Person getPersonByName(String name) {
		Person person = null;

		Cursor cursor = db.rawQuery(SELECT_PERSON_BY_NAME,
				new String[] { name });

		if (cursor != null) {
			try {
				if (cursor.moveToFirst()) {
					person = getPersonFromCursorRow(cursor);
				}
			} finally {
				cursor.close();
			}
		}

		return person;
	}

	public static List<Person> getAllPeople() {
		Cursor cursor = db.rawQuery(SELECT_ALL_PEOPLE, null);
		List<Person> personList = getPersonListFromCursor(cursor);
		return personList;
	}

	public static int getPeopleCount() {
		Cursor cursor = db.rawQuery(SELECT_TABLE_SIZE + TABLE_PERSON, null);
		cursor.moveToFirst();
		int rowCount = cursor.getInt(0);
		cursor.close();
		return rowCount;
	}

	private static Person getPersonFromCursorRow(Cursor cursor) {
		Person person = new Person();
		person.setId(Integer.parseInt(cursor.getString(0)));
		person.setName(cursor.getString(1));
		return person;
	}

	private static List<Person> getPersonListFromCursor(Cursor cursor) {
		List<Person> personList = new ArrayList<Person>();
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					Person person = getPersonFromCursorRow(cursor);
					personList.add(person);
				} while (cursor.moveToNext());
			}
		}
		return personList;
	}
}
