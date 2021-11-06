package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.api.App;
import com.explorestack.consent.Consent;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EsasEkran extends AppCompatActivity {
    private AdView mAdView,mAdView1;
    public boolean condition = false;
    public String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esas_ekran);
        Appodeal.initialize(this, "89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa", Appodeal.BANNER_TOP);
        Appodeal.setTesting(false);
        if(!Appodeal.isInitialized(Appodeal.BANNER_TOP) || !Appodeal.isLoaded(Appodeal.BANNER_TOP)){
            Appodeal.initialize(this, "89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa", Appodeal.BANNER_TOP);
        }
        Appodeal.show(this,Appodeal.BANNER_TOP);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.INTERSTITIAL);
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean b) {
                Toast.makeText(getApplicationContext(),String.valueOf(b), Toast.LENGTH_LONG).show();
                Appodeal.show(EsasEkran.this,Appodeal.INTERSTITIAL);
            }

            @Override
            public void onInterstitialFailedToLoad() {
                Toast.makeText(getApplicationContext(),"Error 4041", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterstitialShown() {
                Toast.makeText(getApplicationContext(),"Error 4042", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterstitialShowFailed() {
                Toast.makeText(getApplicationContext(),"Error 4043", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterstitialClicked() {
                Toast.makeText(getApplicationContext(),"Error 4044", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterstitialClosed() {
                Toast.makeText(getApplicationContext(),"Error 4045", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterstitialExpired() {
                Toast.makeText(getApplicationContext(),"Error 4046", Toast.LENGTH_LONG).show();
            }
        });
        Appodeal.show(EsasEkran.this,Appodeal.INTERSTITIAL);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
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
                String Language = snapshot.child("language").getValue().toString();
                if(Language.equals("tur")){
                    final TextView Title = (TextView)findViewById(R.id.title);
                    final TextView Description1 = (TextView)findViewById(R.id.description1);
                    final TextView Elan1Title = (TextView)findViewById(R.id.elan1title);
                    final TextView Elan2Title = (TextView)findViewById(R.id.elan2title);
                    final TextView Elan1Description = (TextView)findViewById(R.id.elan1description);
                    final TextView Elan2Description = (TextView)findViewById(R.id.elan2description);
                    Title.setText("Ana Sayfa");
                    Description1.setText("Bu ekrandan istediyiniz oyunu seçerek oynaya bilirsiniz. Tamamladığınız her 10 seviyə için size 10 TL ödül verilecek. Biz siz ile 10-cu seviyeye ulaşdıkda mail üzerinden size ulaşacağız!");
                    Elan1Title.setText("Başkent Oyunu");
                    Elan2Title.setText("Matematik Oyunu");
                    Elan1Description.setText("Bu oyunda siz sorulan ülkelerin başkentlerini bulmalısınız.");
                    Elan2Description.setText("Bu oyunda siz sorulan 2 rakamın toplamını bulmalısınız.");
                    bottomNavigationView.getMenu().clear();
                    bottomNavigationView.inflateMenu(R.menu.menu_tur);
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final TextView Math = (TextView)findViewById(R.id.math);
        final TextView XO =(TextView)findViewById(R.id.xo);
        Math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MathScreen.class);
                startActivity(i);

            }
        });
        XO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CapitalScreen.class);
                startActivity(i);

            }
        });
    }
}