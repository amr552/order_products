package com.example.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class an_ListeAktivity extends AppCompatActivity {
    ListView an_listem;
    ConstraintLayout an_constraintlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_aktivity);
        an_listem=findViewById(R.id.an_liste);
        an_constraintlayout=findViewById(R.id.an_linearLayout);
        ArrayList<an_urun> urunler= an_veritabani.an_myurunler;
                //(ArrayList<an_urun>) getIntent().getSerializableExtra("urunler");

        Toast.makeText(an_ListeAktivity.this,urunler.get(0).getAn_Urun_ismi(),Toast.LENGTH_SHORT);
            an_UrunAdapter an_urunadapter =new an_UrunAdapter(an_ListeAktivity.this,0,urunler);
            an_listem.setAdapter(an_urunadapter);

    }
}