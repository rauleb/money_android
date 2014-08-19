package com.example.money;

import java.util.ArrayList;

import com.example.clases.Gasto;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listado extends ActionBarActivity {

	private ArrayList<Gasto> gastos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado);
		gastos = new ArrayList<Gasto>();
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "money", null, 1);
		SQLiteDatabase db = admin.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from gastos", null);
		Gasto g;
		int total = 0;
		while (cursor.moveToNext()) {
			g = new Gasto();
			g.setId(cursor.getInt(0));
			g.setMonto(cursor.getInt(1));
			g.setFecha(cursor.getString(2));
			g.setInfo(cursor.getString(3));
			total+=g.getMonto();
			gastos.add(g);
		}
		g = new Gasto();
		g.setMonto(total);
		g.setInfo("TOTAL");
		gastos.add(g);
		ArrayAdapter<Gasto> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,gastos);
		ListView lista = (ListView)findViewById(R.id.listView1);
		lista.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listado, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
