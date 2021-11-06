package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myaccount extends AppCompatActivity {
    private TextView Win,Lose,Game,Revenue,BarText;
    private ProgressBar Bar;
    public AdView mAdView,mAdView1;
    public boolean condition1 = false;
    private int leveltext;
    private int wintext,losetext,gametext,revenuetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        Appodeal.setTesting(false);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.BANNER_TOP);
        Appodeal.show(this,Appodeal.BANNER_TOP);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
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
        //Ad2 end
        DatabaseReference db10 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String language = snapshot.child("language").getValue().toString();
                if(language.equals("tur")){
                    final TextView title1 = (TextView)findViewById(R.id.title1);
                    final TextView title2 = (TextView)findViewById(R.id.title2);
                    final TextView title3 = (TextView)findViewById(R.id.title3);
                    final TextView description1 = (TextView)findViewById(R.id.description1);
                    final TextView description2 = (TextView)findViewById(R.id.description2);
                    final TextView description3 = (TextView)findViewById(R.id.description3);
                    final TextView stat1 = (TextView)findViewById(R.id.stat1);
                    final TextView stat2 = (TextView)findViewById(R.id.stat2);
                    final TextView stat3 = (TextView)findViewById(R.id.stat3);
                    final TextView stat4 = (TextView)findViewById(R.id.stat4);

                    title1.setText("Benim Hesabım");
                    title2.setText("Statistikleriniz");
                    title3.setText("Seviyye göstericisi");
                    description1.setText("Kendi gelirlerinizi izleyin!");
                    description2.setText("Bilgirenizi daha yakından izleyin!");
                    description3.setText("Hangi seviyyede olduğunuzu bilin!");
                    stat1.setText("Oynadığınız oyunların sayı");
                    stat2.setText("Yenildiyiniz oyunların sayı");
                    stat3.setText("Yendiğiniz oyunların sayı");
                    stat4.setText("Ödülünüz");
                    condition1 = true;
                    bottomNavigationView.getMenu().clear();
                    bottomNavigationView.inflateMenu(R.menu.menu_tur);
                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //bottomNavigationView.setSelectedItemId(R.id.page_2);

        //Declaring TextViews:
        Win = (TextView)findViewById(R.id.win);
        Lose = (TextView)findViewById(R.id.lose);
        Game = (TextView)findViewById(R.id.game);
        Revenue = (TextView)findViewById(R.id.revenue);
        BarText = (TextView)findViewById(R.id.bartext);
        Bar = (ProgressBar) findViewById(R.id.bar);


        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wintext = Integer.parseInt(snapshot.child("win").getValue().toString());
                losetext = Integer.valueOf(snapshot.child("lose").getValue().toString());
                gametext = losetext+wintext;
                revenuetext = Integer.valueOf(snapshot.child("price").getValue().toString());
                leveltext = Integer.valueOf(snapshot.child("level").getValue().toString());
                final String email = snapshot.child("email").getValue().toString();

                Win.setText(String.valueOf(wintext));
                Lose.setText(String.valueOf(losetext));
                Game.setText(String.valueOf(gametext));
                if(condition1){
                    Revenue.setText(String.valueOf((wintext / 400)));
                }
                else {
                    Revenue.setText(String.valueOf((wintext / 400)*0.5));
                }
                if(condition1){
                    BarText.setText(String.valueOf(wintext / 400) + "seviyye");
                }
                else {
                    BarText.setText(String.valueOf(wintext / 400) + " səviyyə");
                }

                if(wintext%4000 == 0){
                    if(wintext != 0){
                        final ProgressDialog myDialog = new ProgressDialog(myaccount.this);
                        myDialog.setIcon(R.drawable.yes);
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Reward").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        database.child("email").setValue(email);
                        String s1 = null,s2=null,s3=null;
                        if(condition1){
                            s1 = "Tebrikler!";
                            s2 = "Siz 10 TL kazandınız! Siz ile appde ve ya email üzerinden ulaşacağız. Ama bunun için bize 5-9 gün vermelisiniz. Sabrınız için teşekkürler!";
                            s3 = "Tamam";
                        }
                        else {
                            s1 = "Təbriklər!";
                            s2 = "Siz artıq 5 AZN qazamısınız! Siz ilə mobil tətbiqdə və ya email vasitəsilə əlaqə qurulacaq növbəti 7-9 gün ərzində. Hörmətlə, Crecom Komandası";
                            s3 = "Oldu";
                        }

                        myDialog.setTitle(s1);
                        myDialog.setMessage(s2);
                        myDialog.setCancelable(false);
                        myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s3, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.show();
                    }

                }
                final TextView BarText2 = (TextView)findViewById(R.id.bartext2);
                if(wintext >= 400){
                    BarText2.setText(wintext%400+"/ 400");
                    int a = ((leveltext%400) / 4) %100;
                    Bar.setProgress(a);
                }
                else{
                    BarText2.setText(wintext + "/ 400");
                    int a = (leveltext / 4) % 100;
                    Bar.setProgress(a);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}