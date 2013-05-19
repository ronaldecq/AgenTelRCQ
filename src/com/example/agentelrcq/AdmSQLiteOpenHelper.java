package com.example.agentelrcq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdmSQLiteOpenHelper extends SQLiteOpenHelper{

	public AdmSQLiteOpenHelper(Context context, String nombre,
			CursorFactory factory, int version) {
		super(context, nombre, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table contactos (numero integer primary key, "
				+ "nombre text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versAnter, int verNuev) {
		// TODO Auto-generated method stub
		db.execSQL("droop table if exits contactos");
		db.execSQL("create table contactos (numero integer primary key, "
				+ "nombre text)");
	}

}
