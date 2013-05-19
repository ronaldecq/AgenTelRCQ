package com.example.agentelrcq;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgeTel extends Activity {

	private EditText edtxt_numero, edtxt_nombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_age_tel);

		edtxt_numero = (EditText) findViewById(R.id.edtxt_numero);
		edtxt_nombre = (EditText) findViewById(R.id.edtxt_nombre);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.age_tel, menu);
		return true;
	}

	public void almacenar(View v) {
		try {
			AdmSQLiteOpenHelper adm = new AdmSQLiteOpenHelper(this, "agendatel", null, 1);
			SQLiteDatabase bd = adm.getWritableDatabase();
			String numero = edtxt_numero.getText().toString();
			String nombre = edtxt_nombre.getText().toString();
			ContentValues registro = new ContentValues();
			registro.put("numero", numero);
			registro.put("nombre", nombre);
			bd.insert("contactos", null, registro);
			bd.close();
			edtxt_nombre.setText("");
			edtxt_numero.setText("");
			Toast.makeText(this, "El Contacto fue almacenado con éxito", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this,
					"No se pudo almacenar el contacto",
					Toast.LENGTH_LONG).show();
		}
	} 

	public void eliminar(View v) {
		AdmSQLiteOpenHelper adm = new AdmSQLiteOpenHelper(this, "agendatel", null, 1);
		SQLiteDatabase bd = adm.getWritableDatabase();
		String numero = edtxt_numero.getText().toString();
		int targ = bd.delete("contactos", "numero=" + numero + "", null);
		bd.close();
		edtxt_nombre.setText("");
		edtxt_numero.setText("");
		if (targ == 1)
			Toast.makeText(this, "Se borró el contacto al numero asociado",
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "No existe un contacto con ese numero",
					Toast.LENGTH_SHORT).show();
	}
	
	public void modificar(View v) {
		AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this,"agendatel", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String numero = edtxt_numero.getText().toString();
		String nombre = edtxt_nombre.getText().toString();
		ContentValues registro = new ContentValues();
		registro.put("numero", numero);
		registro.put("nombre", nombre);
		int targ = bd.update("contactos", registro, "numero=" + numero, null);
		bd.close();
		if (targ == 1)
			Toast.makeText(this, "Los Datos fueron modifcados", Toast.LENGTH_SHORT)
					.show();
		else
			Toast.makeText(this, "No existe un contacto con ese numero",
					Toast.LENGTH_SHORT).show();
	}

	public void consultar(View v) {
		AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this,"agendatel", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String numero = edtxt_numero.getText().toString();
		Cursor fila = bd.rawQuery(
				"select numero,nombre from contactos where numero=" + numero
						+ "", null);
		if (fila.moveToFirst()) {
			edtxt_numero.setText(fila.getString(0));
			edtxt_nombre.setText(fila.getString(1));
		} else
			Toast.makeText(this, "No existe un contacto con ese numero",
					Toast.LENGTH_SHORT).show();
		bd.close();
	}

}
