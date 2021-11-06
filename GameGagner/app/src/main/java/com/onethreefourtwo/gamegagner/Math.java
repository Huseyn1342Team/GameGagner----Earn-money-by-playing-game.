package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.logging.Level;

public class Math extends AppCompatActivity {
    public String s1 = null, s2 = null, s3 = null, s4 = null, s5 = null, s6 = null;
    public TextView Num1,Num2,Res0,Res1,Res2;
    public CardView Result1,Result2,Result3;
    public int Place;
    public DatabaseReference db100;
    public int GameNumber = 0;
    public AdView mAdView,mAdView1;
    public Long level,lose,win;
    public int Answer =0;
    private RewardedAd rewardedAd;
    public boolean condition1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        Toast.makeText(getApplicationContext(),"Math",Toast.LENGTH_LONG).show();
        MobileAds.initialize(getApplicationContext(),String.valueOf(R.string.app_id));


        //Rewarded ad
        rewardedAd = new RewardedAd(this,"ca-app-pub-3940256099942544/5224354917");
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback(){
            @Override
            public void onRewardedAdLoaded(){

            }
            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError){

            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(),adLoadCallback);


        //Ad1 start
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //Ad1 end
        //Ad2 start
        mAdView1 = findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);
        //Ad2 end

        DatabaseReference db10 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String language = snapshot.child("language").getValue().toString();
                if(language.equals("tur")){
                    condition1 = true;
                    final TextView title1 = (TextView)findViewById(R.id.title1);
                    final TextView title2 = (TextView)findViewById(R.id.title2);
                    final TextView title3 = (TextView)findViewById(R.id.title3);

                    title1.setText("Matematik Oyunu");
                    title2.setText("Soruları Çöz");
                    title3.setText("Cevabı Seçin");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
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

        final ImageView Back = (ImageView)findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                startActivity(i);
            }
        });
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                level = (Long) snapshot.child("level").getValue();
                lose = (Long)snapshot.child("lose").getValue();
                win = (Long)snapshot.child("win").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Declaring variables and places
        Num1 = (TextView)findViewById(R.id.number1);
        Num2 = (TextView)findViewById(R.id.number2);
        Res0 = (TextView)findViewById(R.id.res0);
        Res1 = (TextView)findViewById(R.id.res1);
        Res2 = (TextView)findViewById(R.id.res2);
        Result1 = (CardView)findViewById(R.id.Result1);
        Result2 = (CardView)findViewById(R.id.Result2);
        Result3 = (CardView)findViewById(R.id.Result3);

        db100 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Random r = new Random();
        Place = r.nextInt(3);
        play();
    }
    public void play(){
        Random r = new Random();
        int Number1 = r.nextInt(101);
        int Number2 = r.nextInt(101);

        int ResMine = r.nextInt(20);
        int ResMine2 = r.nextInt(20);
        int Result = Number1+Number2;
        Place = r.nextInt(3);
        Num1.setText(String.valueOf(Number1));
        Num2.setText(String.valueOf(Number2));
        if(Place == 0){
            Res0.setText(String.valueOf(Result));
            Res2.setText(String.valueOf(Result+ResMine));
            Res1.setText(String.valueOf(Result-ResMine2));
        }
        else if(Place == 1){
            Res1.setText(String.valueOf(Result));
            Res2.setText(String.valueOf(Result+ResMine));
            Res0.setText(String.valueOf(Result-ResMine2));
        }
        else if(Place == 2){
            Res2.setText(String.valueOf(Result));
            Res0.setText(String.valueOf(Result+ResMine));
            Res1.setText(String.valueOf(Result-ResMine2));
        }
        Result1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Place == 0){
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("win").setValue(win);
                    Answer = Answer+1;
                    win = win +1;
                    GameNumber = GameNumber+1;
                    Toast.makeText(getApplicationContext(),"Doğru!",Toast.LENGTH_LONG).show();
                    play();
                }
                else{
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lose").setValue(lose);
                    lose = lose +1;
                    GameNumber = GameNumber+1;
                    Toast.makeText(getApplicationContext(),"Yanlış!",Toast.LENGTH_LONG).show();
                    play();
                }

            }
        });
        Result2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Place == 1){
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("win").setValue(win);
                    Answer = Answer+1;
                    win = win +1;
                    GameNumber = GameNumber+1;
                    Toast.makeText(getApplicationContext(),"Doğru!",Toast.LENGTH_LONG).show();
                    play();
                }
                else{
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lose").setValue(lose);
                    lose = lose +1;
                    GameNumber = GameNumber+1;
                    Toast.makeText(getApplicationContext(),"Yanlış!",Toast.LENGTH_LONG).show();
                    play();
                }

            }
        });
        Result3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Place == 2){
                    Answer = Answer+1;
                    win = win +1;
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("win").setValue(win);
                    GameNumber = GameNumber+1;
                    Toast.makeText(getApplicationContext(),"Doğru!",Toast.LENGTH_LONG).show();
                    play();
                }
                else{
                    lose = lose +1;
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lose").setValue(lose);
                    GameNumber = GameNumber+1;
                    Toast.makeText(getApplicationContext(),"Yanlış!",Toast.LENGTH_LONG).show();
                    play();
                }
            }
        });
        if(GameNumber == 3){
            if(condition1){
                s1 = "Yükleniyor...";
                s2 = "Reklam yükleniyor...";
                s3 = "Tebrikler!";
                s4 = "Siz cevabı doğru cevapllandırdınız! 3 doğru cevap 1 puan veriyor!";
                s5 = "Eve Git";
                s6 = "Oyunu Oyna";
            }
            else{
                s1 = "Yüklənir..";
                s2 = "Gözləyin, reklam yüklənir.";
                s3 = "Təbriklər!";
                s4 = "Siz cavabı uğurla cavabladınız! Misalın cavabı doğrudur! 3 doğru bir bal verir sizə!";
                s5 = "Evə get";
                s6 = "Oyunu oyna";

            }
            final ProgressDialog progressDialog = new ProgressDialog(Math.this);
            if(Answer == 3){
                progressDialog.setTitle(s1);
                progressDialog.setMessage(s2);
                progressDialog.show();
                //Ad show
                if(rewardedAd.isLoaded()){
                    RewardedAdCallback rewardedAdCallback =  new RewardedAdCallback() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            level = level +1;
                            DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            db1.child("level").setValue(level);
                            final ProgressDialog myDialog = new ProgressDialog(Math.this);
                            myDialog.setIcon(R.drawable.yes);
                            myDialog.setTitle(s3);
                            myDialog.setMessage(s4);
                            myDialog.setCancelable(false);
                            myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                            myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent i2 = new Intent(getApplicationContext(),Math.class);
                                    startActivity(i2);
                                    finish();
                                }
                            });
                            myDialog.show();
                        }
                        @Override
                        public void onRewardedAdOpened() {
                            super.onRewardedAdOpened();
                        }
                        @Override
                        public void onRewardedAdClosed() {
                            level = level +1;
                            DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            db1.child("level").setValue(level);
                            final ProgressDialog myDialog = new ProgressDialog(Math.this);
                            myDialog.setIcon(R.drawable.yes);
                            myDialog.setTitle(s3);
                            myDialog.setMessage(s4);
                            myDialog.setCancelable(false);
                            myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                            myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent i2 = new Intent(getApplicationContext(),Math.class);
                                    startActivity(i2);
                                    finish();
                                }
                            });
                            myDialog.show();
                        }
                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                            super.onRewardedAdFailedToShow(adError);
                        }
                    };
                    rewardedAd.show(this,rewardedAdCallback);
                }
                else{
                    Toast.makeText(this,"Reklam yüklənmir!", Toast.LENGTH_LONG).show();
                }
                //End ad show


            }
            else{
                progressDialog.setIcon(R.drawable.no);
                progressDialog.setTitle(s3);
                progressDialog.setMessage(s4);
                progressDialog.setCancelable(false);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                        startActivity(i);
                        finish();
                    }
                });
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent b = new Intent(getApplicationContext(),Math.class);
                        startActivity(b);
                        finish();
                    }
                });
                progressDialog.show();
                if(rewardedAd.isLoaded()){
                    RewardedAdCallback rewardedAdCallback = new RewardedAdCallback() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                        }
                    };
                    rewardedAd.show(Math.this,rewardedAdCallback);
                }
                else{
                    if (condition1){
                        Toast.makeText(getApplicationContext(), "Reklam Yüklenmiyor!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Reklam Yüklənmir!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}