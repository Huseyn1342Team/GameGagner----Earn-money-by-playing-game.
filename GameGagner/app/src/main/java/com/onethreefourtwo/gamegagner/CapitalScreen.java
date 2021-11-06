package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CapitalScreen extends AppCompatActivity {
    public List<Integer> Images = new ArrayList<Integer>();
    public List<String> Texts = new ArrayList<String>();
    public List<Integer> Answer = new ArrayList<Integer>();
    public List<String> VariantAs = new ArrayList<String>();
    public List<String> VariantBs = new ArrayList<String>();
    public List<String> VariantCs = new ArrayList<String>();
    public List<String> VariantDs = new ArrayList<String>();
    public int Place1,Place2,Place3;
    public AdView mAdView, mAdView1;
    public String AllResult,MyResult;
    public double HistoryWin,HistoryLose,HistoryLevel;
    public boolean condition = false;
    public RewardedAd rewardedAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital_screen);
        Appodeal.setTesting(false);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.BANNER_TOP);
        Appodeal.show(this,Appodeal.BANNER_TOP);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.REWARDED_VIDEO);
        Appodeal.cache(CapitalScreen.this,Appodeal.REWARDED_VIDEO);
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded(boolean b) {
                Appodeal.show(CapitalScreen.this,Appodeal.REWARDED_VIDEO);
                //Appodeal.show((Activity) getApplicationContext(),Appodeal.REWARDED_VIDEO);
            }

            @Override
            public void onRewardedVideoFailedToLoad() {
                Toast.makeText(getApplicationContext(),"Error 4041", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoShown() {
                Toast.makeText(getApplicationContext(),"Error 4043", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoShowFailed() {
                Toast.makeText(getApplicationContext(),"Error 4042", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoFinished(double v, String s) {

            }

            @Override
            public void onRewardedVideoClosed(boolean b) {

            }

            @Override
            public void onRewardedVideoExpired() {

            }

            @Override
            public void onRewardedVideoClicked() {

            }
        });
        Appodeal.show(CapitalScreen.this,Appodeal.REWARDED_VIDEO);

        //Ad1 start



        //Ad2 end
        //Delaring Variables in the layout:
        final TextView Title0 = (TextView)findViewById(R.id.title0);
        final TextView Title1 = (TextView)findViewById(R.id.title1);
        final TextView Title2 = (TextView)findViewById(R.id.title2);
        final ImageView Image0 = (ImageView) findViewById(R.id.Image1);
        final ImageView Image1 = (ImageView) findViewById(R.id.Image11);
        final ImageView Image2 = (ImageView) findViewById(R.id.Image21);
        final TextView Text1 = (TextView) findViewById(R.id.Text1);
        final TextView Text11 = (TextView) findViewById(R.id.Text11);
        final TextView Text12 = (TextView) findViewById(R.id.Text21);
        final CardView Card0 = (CardView) findViewById(R.id.card0);
        final CardView Card1 = (CardView) findViewById(R.id.card1);
        final CardView Card2 = (CardView) findViewById(R.id.card2);
        final CardView Card3 = (CardView) findViewById(R.id.card3);
        final CardView Card10 = (CardView)findViewById(R.id.card10);
        final CardView Card11 = (CardView)findViewById(R.id.card11);
        final CardView Card12 = (CardView)findViewById(R.id.card12);
        final CardView Card13 = (CardView)findViewById(R.id.card13);
        final CardView Card20 = (CardView)findViewById(R.id.card20);
        final CardView Card21 = (CardView)findViewById(R.id.card21);
        final CardView Card22 = (CardView)findViewById(R.id.card22);
        final CardView Card23 = (CardView)findViewById(R.id.card23);
        final TextView VariantAText = (TextView)findViewById(R.id.VariantAText);
        final TextView VariantBText = (TextView)findViewById(R.id.VariantBText);
        final TextView VariantCText = (TextView)findViewById(R.id.VariantCText);
        final TextView VariantDText = (TextView)findViewById(R.id.VariantDText);
        final TextView Variant1AText = (TextView)findViewById(R.id.Variant1AText);
        final TextView Variant1BText = (TextView)findViewById(R.id.Variant1BText);
        final TextView Variant1CText = (TextView)findViewById(R.id.Variant1CText);
        final TextView Variant1DText = (TextView)findViewById(R.id.Variant1DText);
        final TextView Variant2AText = (TextView)findViewById(R.id.Variant2AText);
        final TextView Variant2BText = (TextView)findViewById(R.id.Variant2BText);
        final TextView Variant2CText = (TextView)findViewById(R.id.Variant2CText);
        final TextView Variant2DText = (TextView)findViewById(R.id.Variant2DText);

        MyResult = "";
        //Translate to English and start play:
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Language = snapshot.child("language").getValue().toString();
                HistoryLevel = Double.valueOf(snapshot.child("level").getValue().toString());
                HistoryLose = Double.valueOf(snapshot.child("lose").getValue().toString());
                HistoryWin = Double.valueOf(snapshot.child("win").getValue().toString());

                if(Language.equals("tur")){
                    Title0.setText("Soru 1");
                    Title1.setText("Soru 2");
                    Title2.setText("Soru 3");
                    Texts.add("Azerbaycanın başkenti hangi şehirdin?");
                    Texts.add("Türkmenistanın başkenti hangi şehirdir?");
                    Texts.add("Belçikanın başkenti hangi şehirdir?");
                    Texts.add("Almanyanın başkenti hangi şehirdir?");
                    Texts.add("Macaristanın başkenti hangi şehirdir?");
                    Texts.add("Avustralyanın başkenti hangi şehirdir?");
                    Texts.add("Danimarkanın başkenti hangi şehirdir?");
                    Texts.add("Hindistanın başkenti hangi şehirdir?");
                    Texts.add("Finlandiyanın başkenti hangi şehirdir?");
                    Texts.add("Afqanistanın başkenti hangi şehirdir?");
                    Texts.add("Pakistanın başkenti hangi şehirdir?");
                    Texts.add("İspanyanın başkenti hangi şehirdir??");
                    Texts.add("Fransanın başkenti hangi şehirdir?");
                    Texts.add("Çinin başkenti hangi şehirdir?");
                    Texts.add("Çek Cumhuriyetinin başkenti hangi şehirdir?");
                    Texts.add("Şilinin başkenti hangi şehirdir?");
                    Texts.add("Estonyanın başkenti hangi şehirdir?");
                    Texts.add("Avusturyanın başkenti hangi şehirdir?");
                    //Variants for Baku:
                    VariantAs.add("A) Bakü");
                    VariantBs.add("B) Gence");
                    VariantCs.add("C) Sumqayıt");
                    VariantDs.add("D) Lenkeran");
                    //Variants for Ashgabat:
                    VariantAs.add("A) Türkmenibad");
                    VariantBs.add("B) Balkanabat");
                    VariantCs.add("C) Aşqabat");
                    VariantDs.add("D) Dasoğuz");
                    //Variables for Brussel:
                    VariantAs.add("A) London");
                    VariantBs.add("B) Brüssel");
                    VariantCs.add("C) Gent");
                    VariantDs.add("D) Kopenhag");
                    //Variables for Berlin:
                    VariantAs.add("A) Berlin");
                    VariantBs.add("B) Münih");
                    VariantCs.add("C) Frankfurt");
                    VariantDs.add("D) Hamburg");
                    //Variables for Budapest:
                    VariantAs.add("A) Szolnok");
                    VariantBs.add("B) Sopron");
                    VariantCs.add("C) Debrecen");
                    VariantDs.add("D) Budapeşt");
                    //Variables for Canberra:
                    VariantAs.add("A) Sidney");
                    VariantBs.add("B) Canberra");
                    VariantCs.add("C) Melborn");
                    VariantDs.add("D) Hobart");
                    //Variables for Copenhagen:
                    VariantAs.add("A) Kopenhag");
                    VariantBs.add("B) Odense");
                    VariantCs.add("C) Budapeşt");
                    VariantDs.add("D) London");
                    //Variables for Delhi:
                    VariantAs.add("A) Tokyo");
                    VariantBs.add("B) Pekin");
                    VariantCs.add("C) Kaşmir");
                    VariantDs.add("D) Delhi");
                    //Variables for Helsinki:
                    VariantAs.add("A) Turku");
                    VariantBs.add("B) Helsinki");
                    VariantCs.add("C) Tampere");
                    VariantDs.add("D) Kopenhag");
                    //Variables for Kabul:
                    VariantAs.add("A) Kabul");
                    VariantBs.add("B) Cəlalabat");
                    VariantCs.add("C) Kandahar");
                    VariantDs.add("D) Herat");
                    //Variables for İslamabad:
                    VariantAs.add("A) Kaşmir");
                    VariantBs.add("B) Lahor");
                    VariantCs.add("C) İslamabat");
                    VariantDs.add("D) Multan");
                    //Variables for Madrid:
                    VariantAs.add("A) Barcelona");
                    VariantBs.add("B) Qranada");
                    VariantCs.add("C) Madrid");
                    VariantDs.add("D) Valensiya");
                    //Variables for Paris:
                    VariantAs.add("A) Paris");
                    VariantBs.add("B) Marsel");
                    VariantCs.add("C) Lyon");
                    VariantDs.add("D) Bordeks");
                    //Variables for Pekin:
                    VariantAs.add("A) Şanqay");
                    VariantBs.add("B) Şenzen");
                    VariantCs.add("C) Vuhan");
                    VariantDs.add("D) Pekin");
                    //Variables for Praqa:
                    VariantAs.add("A) Ostrava");
                    VariantBs.add("B) Praqa");
                    VariantCs.add("C) Liberec");
                    VariantDs.add("D) Most");
                    //Variables for Santyago:
                    VariantAs.add("A) Santyago");
                    VariantBs.add("B) Brezilya");
                    VariantCs.add("C) Temuko");
                    VariantDs.add("D) Pukon");
                    //Variables for Talinn:
                    VariantAs.add("A) Narva");
                    VariantBs.add("B) Tartu");
                    VariantCs.add("C) Talin");
                    VariantDs.add("D) Valqa");
                    //Variables for Vyana:
                    VariantAs.add("A) Viyana");
                    VariantBs.add("B) Qraz");
                    VariantCs.add("C) Salzburq");
                    VariantDs.add("D) Linz");
                }
                else{
                    Texts.add("Azərbaycanın paytaxtı hansı şəhərdir?");
                    Texts.add("Türkmənistanın paytaxtı hansı şəhərdir?");
                    Texts.add("Belçikanın paytaxtı hansı şəhərdir?");
                    Texts.add("Almanyanın paytaxtı hansı şəhərdir?");
                    Texts.add("Macarıstanın paytaxtı hansı şəhərdir?");
                    Texts.add("Avstraliyanın paytaxtı hansı şəhərdir?");
                    Texts.add("Danimarkanın paytaxtı hansı şəhərdir?");
                    Texts.add("Hindistanın paytaxtı hansı şəhərdir?");
                    Texts.add("Finlandiyanın paytaxtı hansı şəhərdir?");
                    Texts.add("Əfqanıstanın paytaxtı hansı şəhərdir?");
                    Texts.add("Pakistanın paytaxtı hansı şəhərdir?");
                    Texts.add("İspanyanın paytaxtı hansı şəhərdir?");
                    Texts.add("Fransanın paytaxtı hansı şəhərdir?");
                    Texts.add("Çinin paytaxtı hansı şəhərdir?");
                    Texts.add("Şexiyanın paytaxtı hansı şəhərdir?");
                    Texts.add("Çilinin paytaxtı hansı şəhərdir?");
                    Texts.add("Estoniyanın paytaxtı hansı şəhərdir?");
                    Texts.add("Avstriyanın paytaxtı hansı şəhərdir?");
                    //Variants for Baku:
                    VariantAs.add("A) Bakı");
                    VariantBs.add("B) Gəncə");
                    VariantCs.add("C) Sumqayıt");
                    VariantDs.add("D) Lənkəran");
                    //Variants for Ashgabat:
                    VariantAs.add("A) Türkmənibad");
                    VariantBs.add("B) Balkanabad");
                    VariantCs.add("C) Aşqabad");
                    VariantDs.add("D) Daşoğuz");
                    //Variables for Brussel:
                    VariantAs.add("A) London");
                    VariantBs.add("B) Brüssel");
                    VariantCs.add("C) Gent");
                    VariantDs.add("D) Kopenhagen");
                    //Variables for Berlin:
                    VariantAs.add("A) Berlin");
                    VariantBs.add("B) Münix");
                    VariantCs.add("C) Frankfurt");
                    VariantDs.add("D) Hamburq");
                    //Variables for Budapest:
                    VariantAs.add("A) Szolnok");
                    VariantBs.add("B) Sopron");
                    VariantCs.add("C) Debrecen");
                    VariantDs.add("D) Budapeşt");
                    //Variables for Canberra:
                    VariantAs.add("A) Sidney");
                    VariantBs.add("B) Canberra");
                    VariantCs.add("C) Melborn");
                    VariantDs.add("D) Hobart");
                    //Variables for Copenhagen:
                    VariantAs.add("A) Kopenhagen");
                    VariantBs.add("B) Odense");
                    VariantCs.add("C) Budapeşt");
                    VariantDs.add("D) London");
                    //Variables for Delhi:
                    VariantAs.add("A) Tokyo");
                    VariantBs.add("B) Pekin");
                    VariantCs.add("C) Kaşmir");
                    VariantDs.add("D) Delhi");
                    //Variables for Helsinki:
                    VariantAs.add("A) Turku");
                    VariantBs.add("B) Helsinki");
                    VariantCs.add("C) Tampere");
                    VariantDs.add("D) Kopenhagen");
                    //Variables for Kabul:
                    VariantAs.add("A) Kabul");
                    VariantBs.add("B) Cəlalabad");
                    VariantCs.add("C) Kandahar");
                    VariantDs.add("D) Herat");
                    //Variables for İslamabad:
                    VariantAs.add("A) Kaşmir");
                    VariantBs.add("B) Lahor");
                    VariantCs.add("C) İslamabad");
                    VariantDs.add("D) Multan");
                    //Variables for Madrid:
                    VariantAs.add("A) Barcelona");
                    VariantBs.add("B) Qranada");
                    VariantCs.add("C) Madrid");
                    VariantDs.add("D) Valensiya");
                    //Variables for Paris:
                    VariantAs.add("A) Paris");
                    VariantBs.add("B) Marsel");
                    VariantCs.add("C) Lyon");
                    VariantDs.add("D) Bordeks");
                    //Variables for Pekin:
                    VariantAs.add("A) Şanqay");
                    VariantBs.add("B) Şenzen");
                    VariantCs.add("C) Vuhan");
                    VariantDs.add("D) Pekin");
                    //Variables for Praqa:
                    VariantAs.add("A) Ostrava");
                    VariantBs.add("B) Praqa");
                    VariantCs.add("C) Liberec");
                    VariantDs.add("D) Most");
                    //Variables for Santyago:
                    VariantAs.add("A) Santyago");
                    VariantBs.add("B) Brazilya");
                    VariantCs.add("C) Temuko");
                    VariantDs.add("D) Pukon");
                    //Variables for Talinn:
                    VariantAs.add("A) Narva");
                    VariantBs.add("B) Tartu");
                    VariantCs.add("C) Talin");
                    VariantDs.add("D) Valqa");
                    //Variables for Vyana:
                    VariantAs.add("A) Vyana");
                    VariantBs.add("B) Qraz");
                    VariantCs.add("C) Salzburq");
                    VariantDs.add("D) Linz");
                }
                Images.add(R.drawable.baku);
                Images.add(R.drawable.ashgabat);
                Images.add(R.drawable.brussel);
                Images.add(R.drawable.berlin);
                Images.add(R.drawable.budapest);
                Images.add(R.drawable.canberra);
                Images.add(R.drawable.copenhagen);
                Images.add(R.drawable.delhi);
                Images.add(R.drawable.helsinki);
                Images.add(R.drawable.kabul);
                Images.add(R.drawable.islamabad);
                Images.add(R.drawable.madrid);
                Images.add(R.drawable.paris);
                Images.add(R.drawable.pekin);
                Images.add(R.drawable.prague);
                Images.add(R.drawable.santyago);
                Images.add(R.drawable.talin);
                Images.add(R.drawable.vienna);



                //Prepare answer:
                //A,C,B,A,D,B,A,D,B,A,C,C,A,D,B,A,C,A
                Answer.add(0);// A
                Answer.add(2);// C
                Answer.add(1);// B
                Answer.add(0);// A
                Answer.add(3);// D
                Answer.add(1);// B
                Answer.add(0);// A
                Answer.add(3);// D
                Answer.add(1);// B
                Answer.add(0);// A
                Answer.add(2);// C
                Answer.add(2);// C
                Answer.add(0);// A
                Answer.add(3);// D
                Answer.add(1);// B
                Answer.add(0);// A
                Answer.add(2);// C
                Answer.add(0);// A

                Random r  = new Random();
                Place1 = r.nextInt(18);
                Place2 = r.nextInt(18);
                Place3 = r.nextInt(18);
                String ex1 = String.valueOf(Answer.get(Place1));
                String ex2 = String.valueOf(Answer.get(Place2));
                String ex3 = String.valueOf(Answer.get(Place3));
                AllResult = ex1+ex2+ex3;

                //Put Variant for 1:
                VariantAText.setText(VariantAs.get(Place1));
                VariantBText.setText(VariantBs.get(Place1));
                VariantCText.setText(VariantCs.get(Place1));
                VariantDText.setText(VariantDs.get(Place1));
                //Put Variant for 2:
                Variant1AText.setText(VariantAs.get(Place2));
                Variant1BText.setText(VariantBs.get(Place2));
                Variant1CText.setText(VariantCs.get(Place2));
                Variant1DText.setText(VariantDs.get(Place2));
                //Put Variant for 3:
                Variant2AText.setText(VariantAs.get(Place3));
                Variant2BText.setText(VariantBs.get(Place3));
                Variant2CText.setText(VariantCs.get(Place3));
                Variant2DText.setText(VariantDs.get(Place3));

                Text1.setText(Texts.get(Place1));
                Text11.setText(Texts.get(Place2));
                Text12.setText(Texts.get(Place3));

                Image0.setImageResource(Images.get(Place1));
                Image1.setImageResource(Images.get(Place2));
                Image2.setImageResource(Images.get(Place3));

                Card0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card0.getBackground().setTint(R.color.blue);
                        Card1.setEnabled(false);
                        Card2.setEnabled(false);
                        Card3.setEnabled(false);
                        Card0.setEnabled(false);
                        MyResult = MyResult+"0";
                    }
                });
                Card1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card1.getBackground().setTint(R.color.blue);
                        Card0.setEnabled(false);
                        Card2.setEnabled(false);
                        Card3.setEnabled(false);
                        Card1.setEnabled(false);
                        MyResult = MyResult +"1";
                    }
                });
                Card2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card2.getBackground().setTint(R.color.blue);
                        Card1.setEnabled(false);
                        Card0.setEnabled(false);
                        Card3.setEnabled(false);
                        Card2.setEnabled(false);
                        MyResult = MyResult +"2";
                    }
                });
                Card3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card3.getBackground().setTint(R.color.blue);
                        Card1.setEnabled(false);
                        Card2.setEnabled(false);
                        Card0.setEnabled(false);
                        Card3.setEnabled(false);
                        MyResult = MyResult +"3";
                    }
                });
                Card10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card10.getBackground().setTint(R.color.blue);
                        Card11.setEnabled(false);
                        Card12.setEnabled(false);
                        Card13.setEnabled(false);
                        Card10.setEnabled(false);
                        MyResult = MyResult +"0";
                    }
                });
                Card11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card11.getBackground().setTint(R.color.blue);
                        Card10.setEnabled(false);
                        Card12.setEnabled(false);
                        Card13.setEnabled(false);
                        Card11.setEnabled(false);
                        MyResult = MyResult +"1";
                    }
                });
                Card12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card12.getBackground().setTint(R.color.blue);
                        Card10.setEnabled(false);
                        Card11.setEnabled(false);
                        Card13.setEnabled(false);
                        Card12.setEnabled(false);
                        MyResult = MyResult +"2";
                    }
                });
                Card13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card13.getBackground().setTint(R.color.blue);
                        Card10.setEnabled(false);
                        Card12.setEnabled(false);
                        Card11.setEnabled(false);
                        Card13.setEnabled(false);
                        MyResult = MyResult +"3";
                    }
                });
                Card20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card20.getBackground().setTint(R.color.blue);
                        Card21.setEnabled(false);
                        Card22.setEnabled(false);
                        Card23.setEnabled(false);
                        Card20.setEnabled(false);
                        MyResult = MyResult +"0";
                        check();
                    }
                });
                Card21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card21.getBackground().setTint(R.color.blue);
                        Card20.setEnabled(false);
                        Card22.setEnabled(false);
                        Card23.setEnabled(false);
                        Card21.setEnabled(false);
                        MyResult = MyResult +"1";
                        check();
                    }
                });
                Card22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card22.getBackground().setTint(R.color.blue);
                        Card20.setEnabled(false);
                        Card21.setEnabled(false);
                        Card23.setEnabled(false);
                        Card22.setEnabled(false);
                        MyResult = MyResult +"2";
                        check();
                    }
                });
                Card23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card23.getBackground().setTint(R.color.blue);
                        Card20.setEnabled(false);
                        Card22.setEnabled(false);
                        Card21.setEnabled(false);
                        Card23.setEnabled(false);
                        MyResult = MyResult +"3";
                        check();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void check(){
        if (MyResult.equals(AllResult)) {
            final ProgressDialog progressDialog = new ProgressDialog(CapitalScreen.this);
            progressDialog.setIcon(R.drawable.yes);
            progressDialog.setTitle("Doğru!");
            progressDialog.setMessage("GameGagner");
            progressDialog.setCancelable(false);
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseReference db11 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    db11.child("win").setValue(Double.valueOf(HistoryWin + 1));
                    db11.child("level").setValue(Double.valueOf(HistoryLevel + 1));
                    Intent i = new Intent(getApplicationContext(), EsasEkran.class);
                    startActivity(i);
                    finish();
                }
            });
            progressDialog.show();
        }
        else{
            ProgressDialog progressDialog = new ProgressDialog(CapitalScreen.this);
            progressDialog.setIcon(R.drawable.no);
            progressDialog.setTitle("Yanlış!");
            progressDialog.setMessage("GameGagner");
            progressDialog.setCancelable(false);
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lose").setValue(HistoryLose+1);
                    Intent i = new Intent(getApplicationContext(), EsasEkran.class);
                    startActivity(i);
                    finish();
                }
            });
            progressDialog.show();
        }
    }
}