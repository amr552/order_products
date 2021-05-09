package com.example.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class an_UrunAdapter extends ArrayAdapter<an_urun> {
    Context context;
    public an_UrunAdapter(@NonNull Context context, int resource, @NonNull List<an_urun> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.an_urun,parent,false);

        an_urun urun=getItem(position);
        TextView an_isim=convertView.findViewById(R.id.an_iv_txt_urunismi_li);
        TextView an_fiyat=convertView.findViewById(R.id.an_iv_txt_urunfiyat_li);
        TextView an_aciklama=convertView.findViewById(R.id.an_iv_txt_urunaciklama_li);

        Button an_siparis=convertView.findViewById(R.id.an_iv_btn_Begen_li);

        an_isim.setText(urun.getAn_Urun_ismi());
        an_fiyat.setText(String.valueOf(urun.getAn_fiyat()));
        an_aciklama.setText(urun.getAn_urun_aciklama());
        an_siparis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,urun.getAn_Urun_ismi()+"siparisini alinmistir",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //return super.getView(position, convertView, parent);
    return  convertView;
    }
}
