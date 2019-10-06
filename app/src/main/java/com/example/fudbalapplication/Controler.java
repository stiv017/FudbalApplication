package com.example.fudbalapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class Controler extends SQLiteOpenHelper {
    public Controler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "fudbal.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE igrac (brojDresa INTEGER PRIMARY KEY , ime TEXT , prezime TEXT,pozicija TEXT,klub TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS igrac");
        onCreate(sqLiteDatabase);
    }

    public void insert_igrac(int brDresa,String imeIgraca, String prezimeIgraca,String pozicijaIgraca,String nazivKluba){
        ContentValues contentValues = new ContentValues();
        contentValues.put("brojDresa", brDresa);
        contentValues.put("ime", imeIgraca);
        contentValues.put("prezime", prezimeIgraca);
        contentValues.put("pozicija", pozicijaIgraca);
        contentValues.put("klub",nazivKluba);
        this.getWritableDatabase().insertOrThrow("igrac", "", contentValues);
    }

    public  void delete_igrac(String imeIgraca){
        this.getWritableDatabase().delete("igrac", "ime='" +imeIgraca+"'",null);
    }

    public void update_igrac(String staro_ime, String novo_ime,String staroPrezime,String novoPrezime){
        this.getWritableDatabase().execSQL("UPDATE igrac SET ime='"+novo_ime+"'prezime='"+novoPrezime+"'WHERE ime='"+staro_ime+"'prezime='"+staroPrezime+"'");
    }

    public  void  list_all_igrac(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM igrac", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0)+"\n"+cursor.getString(1)+ "\n"+cursor.getString(2)+ "\n"+cursor.getString(3)+ "\n"+cursor.getString(4));
        }
    }
}
