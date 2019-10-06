package com.example.fudbalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Controler controler;
    EditText brojDresa;
    EditText ime;
    EditText prezime;
    EditText pozicija;
    EditText klub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.prikazIgraca);
        controler = new Controler(this,"",null,1);
        brojDresa=findViewById(R.id.editDres);
        ime=findViewById(R.id.editIme);
        prezime=findViewById(R.id.editPrezime);
        pozicija=findViewById(R.id.editPozicija);
        klub=findViewById(R.id.editKlub);
    }

    public void getAll(View view) {
        controler. list_all_igrac(textView);
    }

    public void dodaj(View view) {
        try{
            String value= brojDresa.getText().toString();
            int finalValue=Integer.parseInt(value);
            controler.insert_igrac(finalValue,ime.getText().toString(),prezime.getText().toString(),pozicija.getText().toString(),klub.getText().toString());
        }catch (SQLiteException e){
            Toast.makeText(MainActivity.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
        }
    }

    public void obrisi(View view) {
        controler.delete_igrac(ime.getText().toString());
    }
}
