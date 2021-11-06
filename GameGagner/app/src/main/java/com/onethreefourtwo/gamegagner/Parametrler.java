package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parametrler extends AppCompatActivity {
    private LinearLayout Haqqimizda,Komanda,BizNeEdirik,YeniNeVar,NeceQosulum,Elaqe,Bloq,Cixis;
    public AdView mAdView,mAdView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrler);
        Appodeal.setTesting(false);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.BANNER_TOP);
        Appodeal.show(this,Appodeal.BANNER_TOP);
        //Ad2 end
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_1:
                        Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                        startActivity(i);
                        return true;
                    case R.id.page_2:
                        Intent i2 = new Intent(getApplicationContext(),myaccount.class);
                        startActivity(i2);
                        return true;
                    case R.id.page_3:
                        Intent i3 = new Intent(getApplicationContext(),Profil.class);
                        startActivity(i3);
                        return true;
                    case R.id.page_4:
                        Intent i4 = new Intent(getApplicationContext(),Parametrler.class);
                        startActivity(i4);
                        return true;
                    case R.id.page_5:
                        Intent i5 = new Intent(getApplicationContext(),Chat.class);
                        startActivity(i5);
                        return true;
                }
                return true;
            }
        });

        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String language = snapshot.child("language").getValue().toString();
                if(language.equals("tur")){
                    final TextView text1 = (TextView)findViewById(R.id.text1);
                    final TextView text2 = (TextView)findViewById(R.id.text2);
                    final TextView text3 = (TextView)findViewById(R.id.text3);
                    final TextView text4 = (TextView)findViewById(R.id.text4);
                    final TextView text5 = (TextView)findViewById(R.id.text5);
                    final TextView text6 = (TextView)findViewById(R.id.text6);
                    text1.setText("Hakkımızda");
                    text2.setText("Takımımız");
                    text3.setText("Biz napıyoruz?");
                    text4.setText("Yeni ne var?");
                    text5.setText("Bize katıl");
                    text6.setText("Çıkış");
                    bottomNavigationView.getMenu().clear();
                    bottomNavigationView.inflateMenu(R.menu.menu_tur);
                    bottomNavigationView.getMenu().getItem(3).setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //bottomNavigationView.setSelectedItemId(R.id.page_4);


        Haqqimizda = (LinearLayout)findViewById(R.id.about);
        Komanda = (LinearLayout)findViewById(R.id.team);
        BizNeEdirik = (LinearLayout)findViewById(R.id.whatwedo);
        YeniNeVar = (LinearLayout)findViewById(R.id.whatisnew);
        NeceQosulum = (LinearLayout)findViewById(R.id.join);
        Elaqe = (LinearLayout)findViewById(R.id.contact);
        Cixis = (LinearLayout)findViewById(R.id.logout);

        //https://crecomtechnologies.com/#about
        //https://crecomtechnologies.com/#wedoing
        //https://crecomtechnologies.com/#teams
        //https://crecomtechnologies.com/#contact
        //https://crecomtechnologies.com/#new
        //https://crecomtechnologies.com/#join
        //https://crecomtechnologies.com/#blog
        //Haqqimizda,Komanda,BizNeEdirik,YeniNeVar,NeceQosulum,Elaqe,Bloq,Cixis
        Haqqimizda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crecomtechnologies.com/#about"));
                startActivity(browserIntent);
            }
        });
        Komanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crecomtechnologies.com/#teams"));
                startActivity(browserIntent);
            }
        });
        BizNeEdirik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crecomtechnologies.com/#wedoing"));
                startActivity(browserIntent);
            }
        });
        YeniNeVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crecomtechnologies.com/#new"));
                startActivity(browserIntent);
            }
        });
        NeceQosulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crecomtechnologies.com/#join"));
                startActivity(browserIntent);
            }
        });
        Elaqe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crecomtechnologies.com/#contact"));
                startActivity(browserIntent);
            }
        });
        Cixis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i1 = new Intent(getApplicationContext(),Register_Activity.class);
                startActivity(i1);
                finish();
            }
        });
    }
}