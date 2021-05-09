package com.example.products;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;

public class an_urun implements Serializable {
    private  String an_Urun_ismi ;
    private String an_urun_aciklama;
    private String an_urun_miktar;
    private Double an_fiyat;
    private  int an_imageID;
    Context context;


    public an_urun(){}

   public void setContext(Context context) {
      this.context = context;
 }

    public an_urun(Context context,String an_Urun_ismi, String an_urun_aciklama, String an_urun_miktar, Double an_fiyat) {
        this.an_Urun_ismi = an_Urun_ismi;
        this.an_urun_aciklama = an_urun_aciklama;
        this.an_urun_miktar = an_urun_miktar;
        this.an_fiyat = an_fiyat;
        this.context=context;

    }

    public String getAn_Urun_ismi() {
        return an_Urun_ismi;
    }

    public void setAn_Urun_ismi(String an_Urun_ismi) {
        this.an_Urun_ismi = an_Urun_ismi;
    }

    public String getAn_urun_aciklama() {
        return an_urun_aciklama;
    }

    public void setAn_urun_aciklama(String an_urun_aciklama) {
        this.an_urun_aciklama = an_urun_aciklama;
    }

    public String getAn_urun_miktar() {
        return an_urun_miktar;
    }

    public void setAn_urun_miktar(String an_urun_miktar) {
        this.an_urun_miktar = an_urun_miktar;
    }

    public Double getAn_fiyat() {
        return an_fiyat;
    }

    public void setAn_fiyat(Double an_fiyat) {
        if(an_fiyat<0){
            if (context!=null) {
                Toast.makeText(context, "nigative fiyati olamaz", Toast.LENGTH_SHORT).show();

            }
            this.an_fiyat=0.0;
        }else
        this.an_fiyat = an_fiyat;
    }
}
