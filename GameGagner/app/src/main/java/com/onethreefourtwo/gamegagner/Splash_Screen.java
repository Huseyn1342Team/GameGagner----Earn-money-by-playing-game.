package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash_Screen extends AppCompatActivity {
    public String s1,s2,s3,s4,s5,s6;
    public PackageInfo pInfo;
    public String RealVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("version");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RealVersionName = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        MobileAds.initialize(getApplicationContext(),String.valueOf(R.string.app_id));
        if(!isNetworkAvailable()){
            final ProgressDialog myDialog = new ProgressDialog(Splash_Screen.this);
            myDialog.setIcon(R.drawable.no);
            myDialog.setTitle("No Internet");
            myDialog.setCancelable(false);
            myDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            myDialog.show();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user == null){
                    if(!isNetworkAvailable()){
                        final ProgressDialog myDialog = new ProgressDialog(Splash_Screen.this);
                        myDialog.setIcon(R.drawable.no);
                        myDialog.setTitle("No Internet");
                        myDialog.setCancelable(false);
                        myDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        myDialog.show();
                        return;
                    }
                    Intent i = new Intent(getApplicationContext(),Register_Activity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild("language")){
                                String language  = snapshot.child("language").getValue().toString();
                                if(language.equals("tur")){
                                    s1 = "??nternet ba??lant??s?? yok!";
                                    s2 = "L??tfen, internete ba??land??kdan sonra tekrar deneyin!";
                                    s3 = "GameGagnerde update var!";
                                    s4 = "Yeni ??zelliklerimiz ile sizi mutlu etmek istiyoruz! O y??zden appimizi g??ncelleyin!";
                                    s5 = "G??ncelle";
                                    s6 = "Tamam";
                                }
                                else if(language.equals("aze")){
                                    s1 = "??nternet ??laq??si yoxdur!";
                                    s2 = "Z??hm??t olmasa, internet?? qo??ulduqdan sonra t??krar yoxlay??n!";
                                    s3 = "GameGagnerd?? update var!";
                                    s4 = "Yeni x??susiyyetl??rimiz ile sizi xo??b??xt etm??k ist??yirik! Ona g??r?? GameGagneri g??nc??ll??yin!";
                                    s5 = "G??nc??ll??";
                                    s6 = "Oldu";
                                }
                                if(!isNetworkAvailable()){
                                    final ProgressDialog myDialog = new ProgressDialog(Splash_Screen.this);
                                    myDialog.setIcon(R.drawable.no);
                                    myDialog.setTitle(s1);
                                    myDialog.setMessage(s2);
                                    myDialog.setCancelable(false);
                                    myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    });
                                    myDialog.show();
                                    return;
                                }
                                String version = null;
                                try {
                                    pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
                                    version = pInfo.versionName;
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                String MyVersionName = pInfo.versionName;
                                if(version.equals(RealVersionName)){
                                }
                                else{
                                    final ProgressDialog myDialog = new ProgressDialog(Splash_Screen.this);
                                    myDialog.setIcon(R.drawable.yes);
                                    myDialog.setTitle(s3);
                                    myDialog.setMessage(s4);
                                    myDialog.setCancelable(false);
                                    myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s5, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse("https://play.google.com/store/apps/details?id=com.onethreefourtwo.gamegagner") );
                                            startActivity( browse );
                                        }
                                    });
                                    myDialog.show();
                                    return;
                                }
                                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Intent i = new Intent(getApplicationContext(),LanguageChoose.class);
                                startActivity(i);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        }, 4000);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}