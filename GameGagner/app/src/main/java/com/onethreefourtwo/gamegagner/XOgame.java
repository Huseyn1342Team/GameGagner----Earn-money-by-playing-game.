package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.onethreefourtwo.gamegagner.R.drawable.close;
import static com.onethreefourtwo.gamegagner.R.drawable.info;
import static com.onethreefourtwo.gamegagner.R.drawable.islamabad;
import static com.onethreefourtwo.gamegagner.R.drawable.madrid;

public class XOgame extends AppCompatActivity {
    public List<Integer> Images = new ArrayList<Integer>();
    public List<String> Texts = new ArrayList<String>();
    public List<Integer> Answer = new ArrayList<Integer>();
    public List<String> VariantAs = new ArrayList<String>();
    public List<String> VariantBs = new ArrayList<String>();
    public List<String> VariantCs = new ArrayList<String>();
    public List<String> VariantDs = new ArrayList<String>();
    public String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12;
    public ImageView Image1;
    public String Language;
    public Long level;
    public int answer = 0;
    public ProgressDialog progressDialog;
    public AdView mAdView,mAdView1;
    public int randomplace;
    private RewardedAd rewardedAd;
    private Long lose,win;
    public TextView Text1,VariantAText,VariantBText,VariantCText,VariantDText;
    public LinearLayout VariantA,VariantB,VariantC,VariantD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_ogame);
        MobileAds.initialize(getApplicationContext(),String.valueOf(R.string.app_id));

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

        DatabaseReference db100 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db100.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Language = snapshot.child("language").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(Language.equals("tur")){
            s1 = "Y??kleniyor...";
            s2 = "Bir ka?? saniye bekleyin!";
            s3 = "Maalesef!";
            s4 = "Yanl???? bildiniz! Bu ??lkenin ba??kenti se??diyiniz ??ehir de??il! ??yi ??anslar!";
            s5 = "Eve Git";
            s6 = "Oyunu Oyna";
            s7 = "Tebrikler!";
            s8 = "Do??ru! Tebrikler! Siz 1 puan kazand??n??z!";
            s9 = "Eve Git";
            s10 = "Oyunu Oyna";
            s11 = "Reklam Y??klenmiyor! Puan verilemiyor!";

            //Add text to images:
            //Categories --> Baku,Ashgabat,Brussel,Berlin,Budapest,Canberra,Copenhagen,Delhi,Helsinki,Kabul,Islamabad,Madrid,Paris,Pekin
            //Categories --> Prague,Santyago,Talin,Vienna

            Texts.add("Azerbaycan??n ba??kenti hangi ??ehirdin?");
            Texts.add("T??rkmenistan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Bel??ikan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Almanyan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Macaristan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Avustralyan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Danimarkan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Hindistan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Finlandiyan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Afqanistan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Pakistan??n ba??kenti hangi ??ehirdir?");
            Texts.add("??spanyan??n ba??kenti hangi ??ehirdir??");
            Texts.add("Fransan??n ba??kenti hangi ??ehirdir?");
            Texts.add("??inin ba??kenti hangi ??ehirdir?");
            Texts.add("??ek Cumhuriyetinin ba??kenti hangi ??ehirdir?");
            Texts.add("??ilinin ba??kenti hangi ??ehirdir?");
            Texts.add("Estonyan??n ba??kenti hangi ??ehirdir?");
            Texts.add("Avusturyan??n ba??kenti hangi ??ehirdir?");


            //Categories --> Baku,Ashgabat,Brussel,Berlin,Budapest,Canberra,Copenhagen,Delhi,Helsinki,Kabul,Islamabad,Madrid,Paris,Pekin
            //Categories --> Prague,Santyago,Talin,Vienna

            //Variants for Baku:
            VariantAs.add("A) Bak??");
            VariantBs.add("B) Gence");
            VariantCs.add("C) Sumqay??t");
            VariantDs.add("D) Lenkeran");

            //Variants for Ashgabat:
            VariantAs.add("A) T??rkmenibad");
            VariantBs.add("B) Balkanabat");
            VariantCs.add("C) A??qabat");
            VariantDs.add("D) Daso??uz");

            //Variables for Brussel:
            VariantAs.add("A) London");
            VariantBs.add("B) Br??ssel");
            VariantCs.add("C) Gent");
            VariantDs.add("D) Kopenhag");

            //Variables for Berlin:
            VariantAs.add("A) Berlin");
            VariantBs.add("B) M??nih");
            VariantCs.add("C) Frankfurt");
            VariantDs.add("D) Hamburg");

            //Variables for Budapest:
            VariantAs.add("A) Szolnok");
            VariantBs.add("B) Sopron");
            VariantCs.add("C) Debrecen");
            VariantDs.add("D) Budape??t");

            //Variables for Canberra:
            VariantAs.add("A) Sidney");
            VariantBs.add("B) Canberra");
            VariantCs.add("C) Melborn");
            VariantDs.add("D) Hobart");

            //Variables for Copenhagen:
            VariantAs.add("A) Kopenhag");
            VariantBs.add("B) Odense");
            VariantCs.add("C) Budape??t");
            VariantDs.add("D) London");

            //Variables for Delhi:
            VariantAs.add("A) Tokyo");
            VariantBs.add("B) Pekin");
            VariantCs.add("C) Ka??mir");
            VariantDs.add("D) Delhi");

            //Variables for Helsinki:
            VariantAs.add("A) Turku");
            VariantBs.add("B) Helsinki");
            VariantCs.add("C) Tampere");
            VariantDs.add("D) Kopenhag");

            //Variables for Kabul:
            VariantAs.add("A) Kabul");
            VariantBs.add("B) C??lalabat");
            VariantCs.add("C) Kandahar");
            VariantDs.add("D) Herat");

            //Variables for ??slamabad:
            VariantAs.add("A) Ka??mir");
            VariantBs.add("B) Lahor");
            VariantCs.add("C) ??slamabat");
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
            VariantAs.add("A) ??anqay");
            VariantBs.add("B) ??enzen");
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
            s1 = "Y??kl??nir!";
            s2 ="Bir ne???? saniy?? g??zl??yin!";
            s3 = "T????s??f!";
            s4 = "S??hv bildiniz! Bu ??lk??nin paytax?? bu deyil! U??urlar!";
            s5 = "Ev?? Get";
            s6 = "Oyunu Oyna";
            s7 = "T??brikl??r!";
            s8 = "Siz sual?? u??urla cavabland??rd??n??z! T??brikl??r! Siz ???? ard??c??l suala d??zg??n cavab ver??r??k 1 xal qazanm??s??n??z!";
            s9 = "Ev?? Get";
            s10 = "Oyunu Oyna";
            s11 = "Reklam Y??kl??nmir!";
            //Add text to images:
            //Categories --> Baku,Ashgabat,Brussel,Berlin,Budapest,Canberra,Copenhagen,Delhi,Helsinki,Kabul,Islamabad,Madrid,Paris,Pekin
            //Categories --> Prague,Santyago,Talin,Vienna
            Texts.add("Az??rbaycan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("T??rkm??nistan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Bel??ikan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Almanyan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Macar??stan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Avstraliyan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Danimarkan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Hindistan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Finlandiyan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("??fqan??stan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Pakistan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("??spanyan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Fransan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("??inin paytaxt?? hans?? ????h??rdir?");
            Texts.add("??exiyan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("??ilinin paytaxt?? hans?? ????h??rdir?");
            Texts.add("Estoniyan??n paytaxt?? hans?? ????h??rdir?");
            Texts.add("Avstriyan??n paytaxt?? hans?? ????h??rdir?");


            //Categories --> Baku,Ashgabat,Brussel,Berlin,Budapest,Canberra,Copenhagen,Delhi,Helsinki,Kabul,Islamabad,Madrid,Paris,Pekin
            //Categories --> Prague,Santyago,Talin,Vienna

            //Variants for Baku:
            VariantAs.add("A) Bak??");
            VariantBs.add("B) G??nc??");
            VariantCs.add("C) Sumqay??t");
            VariantDs.add("D) L??nk??ran");

            //Variants for Ashgabat:
            VariantAs.add("A) T??rkm??nibad");
            VariantBs.add("B) Balkanabad");
            VariantCs.add("C) A??qabad");
            VariantDs.add("D) Da??o??uz");

            //Variables for Brussel:
            VariantAs.add("A) London");
            VariantBs.add("B) Br??ssel");
            VariantCs.add("C) Gent");
            VariantDs.add("D) Kopenhagen");

            //Variables for Berlin:
            VariantAs.add("A) Berlin");
            VariantBs.add("B) M??nix");
            VariantCs.add("C) Frankfurt");
            VariantDs.add("D) Hamburq");

            //Variables for Budapest:
            VariantAs.add("A) Szolnok");
            VariantBs.add("B) Sopron");
            VariantCs.add("C) Debrecen");
            VariantDs.add("D) Budape??t");

            //Variables for Canberra:
            VariantAs.add("A) Sidney");
            VariantBs.add("B) Canberra");
            VariantCs.add("C) Melborn");
            VariantDs.add("D) Hobart");

            //Variables for Copenhagen:
            VariantAs.add("A) Kopenhagen");
            VariantBs.add("B) Odense");
            VariantCs.add("C) Budape??t");
            VariantDs.add("D) London");

            //Variables for Delhi:
            VariantAs.add("A) Tokyo");
            VariantBs.add("B) Pekin");
            VariantCs.add("C) Ka??mir");
            VariantDs.add("D) Delhi");

            //Variables for Helsinki:
            VariantAs.add("A) Turku");
            VariantBs.add("B) Helsinki");
            VariantCs.add("C) Tampere");
            VariantDs.add("D) Kopenhagen");

            //Variables for Kabul:
            VariantAs.add("A) Kabul");
            VariantBs.add("B) C??lalabad");
            VariantCs.add("C) Kandahar");
            VariantDs.add("D) Herat");

            //Variables for ??slamabad:
            VariantAs.add("A) Ka??mir");
            VariantBs.add("B) Lahor");
            VariantCs.add("C) ??slamabad");
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
            VariantAs.add("A) ??anqay");
            VariantBs.add("B) ??enzen");
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
        //Declaring ImageView:
        final ImageView Back = (ImageView)findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                startActivity(i);
            }
        });
        Image1 = (ImageView) findViewById(R.id.Image1);
        //Declaring TextView:
        Text1 = (TextView) findViewById(R.id.Text1);
        VariantAText = (TextView) findViewById(R.id.VariantAText);
        VariantBText = (TextView) findViewById(R.id.VariantBText);
        VariantCText = (TextView) findViewById(R.id.VariantCText);
        VariantDText = (TextView) findViewById(R.id.VariantDText);

        //Declaring Linear Layouts:
        VariantA = (LinearLayout) findViewById(R.id.VariantA);
        VariantB = (LinearLayout) findViewById(R.id.VariantB);
        VariantC = (LinearLayout) findViewById(R.id.VariantC);
        VariantD = (LinearLayout) findViewById(R.id.VariantD);
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

        //Choose A random Question Among 31 Example
        Random r = new Random();
        randomplace = r.nextInt(18);

        Image1.setImageResource(Images.get(randomplace));
        Text1.setText(Texts.get(randomplace));
        VariantAText.setText(VariantAs.get(randomplace));
        VariantBText.setText(VariantBs.get(randomplace));
        VariantCText.setText(VariantCs.get(randomplace));
        VariantDText.setText(VariantDs.get(randomplace));

        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                level = (Long) snapshot.child("level").getValue();
                lose = (Long) snapshot.child("lose").getValue();
                win = (Long) snapshot.child("win").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        final CardView card0 = (CardView)findViewById(R.id.card0);
        final CardView card1 = (CardView)findViewById(R.id.card1);
        final CardView card2 = (CardView)findViewById(R.id.card2);
        final CardView card3 = (CardView)findViewById(R.id.card3);
        progressDialog = new ProgressDialog(XOgame.this);
        progressDialog.setTitle(s1);
        progressDialog.setMessage(s2);
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });

        card0.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                card0.setBackgroundColor(R.color.blue);
                if(Answer.get(randomplace) == 0){
                    answer = answer + 1;
                    win = win + 1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("win").setValue(win);
                    progressDialog.show();
                    chart();
                }
                else {
                    answer = 0;
                    lose = lose+1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("lose").setValue(lose);
                    final ProgressDialog myDialog = new ProgressDialog(XOgame.this);
                    myDialog.setIcon(R.drawable.no);
                    myDialog.setTitle(s3);
                    myDialog.setMessage(s4);
                    myDialog.setCancelable(false);
                    myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                            startActivity(i);
                        }
                    });
                    myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent b = new Intent(getApplicationContext(),XOgame.class);
                            startActivity(b);
                        }
                    });
                    myDialog.show();
                }
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                card1.setBackgroundColor(R.color.blue);
                if(Answer.get(randomplace) == 1){
                    answer = answer + 1;
                    win = win + 1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("win").setValue(win);
                    progressDialog.show();
                    chart();

                }
                else {
                    answer = 0;
                    lose = lose +1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("lose").setValue(lose);
                    final ProgressDialog myDialog = new ProgressDialog(XOgame.this);
                    myDialog.setIcon(R.drawable.no);
                    myDialog.setTitle(s3);
                    myDialog.setMessage(s4);
                    myDialog.setCancelable(false);
                    myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                            startActivity(i);
                        }
                    });
                    myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent b = new Intent(getApplicationContext(),XOgame.class);
                            startActivity(b);
                        }
                    });
                    myDialog.show();

                }
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                card2.setBackgroundColor(R.color.blue);
                if(Answer.get(randomplace) == 2){
                    answer = answer + 1;
                    win = win + 1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("win").setValue(win);
                    progressDialog.show();
                    chart();

                }
                else {
                    answer = 0;
                    lose = lose +1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("lose").setValue(lose);
                    final ProgressDialog myDialog = new ProgressDialog(XOgame.this);
                    myDialog.setIcon(R.drawable.no);
                    myDialog.setTitle(s3);
                    myDialog.setMessage(s4);
                    myDialog.setCancelable(false);
                    myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                            startActivity(i);
                        }
                    });
                    myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent b = new Intent(getApplicationContext(),XOgame.class);
                            startActivity(b);
                        }
                    });
                    myDialog.show();

                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                card3.setBackgroundColor(R.color.blue);
                if(Answer.get(randomplace) == 3){
                    answer = answer + 1;
                    win = win + 1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("win").setValue(win);
                    progressDialog.show();
                    chart();
                }
                else {
                    answer = 0;
                    lose = lose +1;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("lose").setValue(lose);
                    final ProgressDialog myDialog = new ProgressDialog(XOgame.this);
                    myDialog.setIcon(R.drawable.no);
                    myDialog.setTitle(s3);
                    myDialog.setMessage(s4);
                    myDialog.setCancelable(false);
                    myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                            startActivity(i);
                        }
                    });
                    myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s6, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent b = new Intent(getApplicationContext(),XOgame.class);
                            startActivity(b);
                        }
                    });
                    myDialog.show();
                }
            }
        });

    }
    public void chart(){
        Random r = new Random();
        randomplace = r.nextInt(18);

        Image1.setImageResource(Images.get(randomplace));
        Text1.setText(Texts.get(randomplace));
        VariantAText.setText(VariantAs.get(randomplace));
        VariantBText.setText(VariantBs.get(randomplace));
        VariantCText.setText(VariantCs.get(randomplace));
        VariantDText.setText(VariantDs.get(randomplace));


        if(answer == 3){
            progressDialog.show();
            if(rewardedAd.isLoaded()){
                RewardedAdCallback rewardedAdCallback = new RewardedAdCallback() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        level = level +1;
                        win = win +1;
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReference.child("level").setValue(level);
                        databaseReference.child("win").setValue(win);
                        final ProgressDialog myDialog = new ProgressDialog(XOgame.this);
                        myDialog.setIcon(R.drawable.yes);
                        myDialog.setTitle(s7);
                        myDialog.setMessage(s8);
                        myDialog.setCancelable(false);
                        myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s9, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                startActivity(i);
                            }
                        });
                        myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s10, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent b = new Intent(getApplicationContext(),XOgame.class);
                                startActivity(b);
                            }
                        });
                        myDialog.show();
                    }
                    @Override
                    public void onRewardedAdClosed(){
                        level = level +1;
                        win = win +1;
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReference.child("level").setValue(level);
                        databaseReference.child("win").setValue(win);
                        final ProgressDialog myDialog = new ProgressDialog(XOgame.this);
                        myDialog.setIcon(R.drawable.yes);
                        myDialog.setTitle(s7);
                        myDialog.setMessage(s8);
                        myDialog.setCancelable(false);
                        myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, s9, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                startActivity(i);
                            }
                        });
                        myDialog.setButton(DialogInterface.BUTTON_POSITIVE, s10, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent b = new Intent(getApplicationContext(),XOgame.class);
                                startActivity(b);
                            }
                        });
                        myDialog.show();
                    }
                };
                rewardedAd.show(XOgame.this,rewardedAdCallback);
                progressDialog.dismiss();
            }
            else{
                Toast.makeText(XOgame.this,s11, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
            answer = 0;
        }
    }
}