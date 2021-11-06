package com.onethreefourtwo.gamegagner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LanguageChoose extends AppCompatActivity {
    private AdView mAdView,mAdView1;
    public TextView math,xo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choose);
        Appodeal.setTesting(false);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.BANNER_TOP);
        Appodeal.show(this,Appodeal.BANNER_TOP);
        // DatabaseReference db11 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        //                    db11.child("win").setValue(Double.valueOf(HistoryWin + 1));
        //                    db11.child("level").setValue(Double.valueOf(HistoryLevel + 1));

        //Ad2 end

        math = (TextView)findViewById(R.id.math);
        xo = (TextView)findViewById(R.id.xo);

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                db1.child("language").setValue("tur");
                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                startActivity(i);
                finish();
            }
        });
        xo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                db1.child("language").setValue("aze");
                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                startActivity(i);
                finish();
            }
        });
    }
}