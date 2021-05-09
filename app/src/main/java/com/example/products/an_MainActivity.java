package com.example.products;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class an_MainActivity extends AppCompatActivity {
 TextView an_urunismi,an_urunfiyat,an_urunaciklama;
 Spinner an_urunmiktar  ;
 ArrayList<an_urun> an_urunler ;
 Button an_liste ,an_fotocek;
 ImageView an_fotoyeri;
 Boolean an_isPermitted;
     private static  final  int an_CAMERA_REQUEST  =6060;
 private  static  final int  an_MY_CAMERA_PERMISSION_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        an_urunismi=findViewById(R.id.an_etxt_Urunismi);
        an_urunfiyat=findViewById(R.id.an_etxt_Urun_Fiyat);
        an_urunaciklama=findViewById(R.id.an_etxt_Urun_Aciklama);
        an_urunmiktar=findViewById(R.id.an_spinner);
            an_fotocek=findViewById(R.id.an_btn_iv_fotocek);
            an_fotoyeri=findViewById(R.id.an_iv_fotodan);

        an_urunler=new ArrayList<>();

        an_liste=findViewById(R.id.an_btn_listview_goster);
        an_liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent an_intent=new Intent(an_MainActivity.this, an_ListeAktivity.class);
               // an_intent.putExtra("Urunleri ",an_urunler);
                an_veritabani.an_myurunler= an_urunler;
                startActivity(an_intent);
            }
        });


        //internitte veri tabanli baglainiz orada dinamik olarak miktar tipleri cekiniz
        List<String> an_SpinnerElemenleri=new ArrayList();
        an_SpinnerElemenleri.add("kutu");
        an_SpinnerElemenleri.add("adet");
        an_SpinnerElemenleri.add("kilo");

        ArrayAdapter<String> an_adapter =new ArrayAdapter(an_MainActivity.this,
                android.R.layout.simple_spinner_item,
                an_SpinnerElemenleri);
            an_urunmiktar.setAdapter(an_adapter);//arayuz elemenleri  Adaptir ile baglayacagiz


        an_fotocek.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
              if (ActivityCompat.checkSelfPermission(an_MainActivity.this,Manifest.permission.CAMERA)//ACCESS_FINE_LOCATION)
              ==PackageManager.PERMISSION_GRANTED/**&&ActivityCompat.checkSelfPermission(an_MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)
              ==PackageManager.PERMISSION_GRANTED**/){
                    Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent,100);
              }
              else
              {
                  ActivityCompat.requestPermissions(an_MainActivity.this,
                          new String[]{Manifest.permission.CAMERA},100);
              }

            }
        });
    }


    public void onRequestPermissionResults(int requestcode,@NotNull String[] permissions ,@NotNull int[] grantResults){
        if (requestcode==100 && grantResults.length > 0 && grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED){
            Intent takePictureIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent,100);
        }
        else
        {
            Toast.makeText(an_MainActivity.this ,"izin verilmedi " ,Toast.LENGTH_LONG).show();
            Log.d("Hata" ,"izin verilmedi ");
        }

    }
/**
 *
 * android:entries="@android:array/phoneTypes"
 *         android:prompt="@string/urun_miktar"
 * ****/



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            an_fotoyeri.setImageBitmap(photo);
        }
    }
    public void am_UrunOlustur(View view) {
        an_urun an_urunu=new an_urun(an_MainActivity.this,an_urunismi.getText().toString(),
                an_urunaciklama.getText().toString(),
                an_urunmiktar.getSelectedItem().toString(),
                Double.parseDouble(an_urunfiyat.getText().toString()));
                an_urunu.setAn_fiyat(Double.parseDouble(an_urunfiyat.getText().toString()));
                if (an_urunu.getAn_fiyat()>0)
                an_urunler.add(an_urunu);

        Toast.makeText(getApplicationContext(),"urun sayisi "+an_urunler.size()+
                 "eklenen " +an_urunler.get(an_urunler.size()-1 ).getAn_Urun_ismi(),
                Toast.LENGTH_LONG).show();
    }
}
/**
 *   public void onRequestPermissionResults(int requestcode,@NotNull String[] permissions ,@NotNull int[] grantResults){
 *             super.onRequestPermissionsResult(requestcode,permissions,grantResults);
 *             if (requestcode==an_MY_CAMERA_PERMISSION_CODE)
 *             {
 *                 if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
 *                 {
 *                     Toast.makeText(this, "camera permission granted " ,Toast.LENGTH_SHORT).show();
 *                     Intent an_cameraIntent =new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
 *                     startActivityForResult(an_cameraIntent,an_CAMERA_REQUEST);
 *                 }
 *                 else
 *                 {
 *                     Toast.makeText(this,"camera permission denied",Toast.LENGTH_LONG).show();
 *                 }
 *             }
 *         }
 *
 *
 *
 *
 *     else {
 *                     Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
 *                     startActivityForResult(cameraIntent,an_CAMERA_REQUEST);
 *                 }
 *
 * */


                                                    /*private void checkRunTimePermission(){
                                                        String[] permissionArray =new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                                            requestPermissions(permissionArray,111);

                                                        }else {
                                                                ActivityCompat.requestPermissions(an_MainActivity.this,new String[] {Manifest.permission.CAMERA
                                                                },100);
                                                        }
                                                    }*/


                                                   /* @Override
                                                    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                                                        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                                                        boolean openActivityOnce = true;
                                                        boolean openDialogOnce = true;
                                                        if (requestCode == 11111) {
                                                            for (int i = 0; i < grantResults.length; i++) {
                                                                String permission = permissions[i];

                                                                an_isPermitted = grantResults[i] == PackageManager.PERMISSION_GRANTED;

                                                                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                                                                    // user rejected the permission
                                                                    boolean showRationale = shouldShowRequestPermissionRationale(permission);
                                                                    if (!showRationale) {
                                                                        //execute when 'never Ask Again' tick and permission dialog not show
                                                                    } else {
                                                                        if (openDialogOnce) {
                                                                            //alertView();
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            if (an_isPermitted)
                                                            {
                                                                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                                      startActivityForResult(cameraIntent,an_CAMERA_REQUEST);
                                                            }
                                                        }
                                                    }*/

