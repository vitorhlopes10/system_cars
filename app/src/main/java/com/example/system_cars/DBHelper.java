package com.example.system_cars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "system_car";
    public static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_CARRO = "CREATE TABLE carro (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " marca TEXT NOT NULL, " +
            " modelo TEXT NOT NULL, " +
            " anoFab INTEGER NOT NULL);";
    private static final String DROP_TABLE_CARRO = " DROP TABLE carro";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CARRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CARRO);
        onCreate(db);
    }

    public long inserirCarro(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert("carro", null, cv);
        return id;
    }

    public boolean removerTotal() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM carro");
        return true;
    }

    public boolean removerCarro(String idLivro) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("carro", "id" + "=?", new String[]{idLivro}) > 0;
    }

    public List<ContentValues> pesquisarCarro() {
        String sql = "SELECT id, marca, modelo, anoFab FROM carro";
        String where[] = null;
        return pesquisarCarro(sql, where);
    }

    private List<ContentValues> pesquisarCarro(String sql, String[] where) {
        List<ContentValues> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, where);

        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put("id", c.getInt(c.getColumnIndex("id")));
                cv.put("marca", c.getString(c.getColumnIndex("marca")));
                cv.put("modelo", c.getString(c.getColumnIndex("modelo")));
                cv.put("anoFab", c.getInt(c.getColumnIndex("anoFab")));
                lista.add(cv);
            } while (c.moveToNext());
        }

        return lista;
    }
}
