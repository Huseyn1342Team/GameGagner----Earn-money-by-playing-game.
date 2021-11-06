package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.google.android.gms.ads.AdError;
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

import java.util.Random;

import javax.xml.transform.Result;

public class MathScreen extends AppCompatActivity {
    public String Language;
    public RewardedAd rewardedAd;
    public AdView mAdView, mAdView1;
    public boolean condition = false;
    public String AllResult, MyResult;
    public Button Submit;
    public int Nomre1, Nomre2, Nomre3, Nomre4, Nomre5, Nomre6, Nomre1Mine, Nomre2Mine, Nomre3Mine, Nomre4Mine, Nomre5Mine, Nomre6Mine, Result1, Result2, Result3;
    public double HistoryWin, HistoryLose, HistoryLevel;
    public int Place, Place2, Place3;
    public ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_screen);
        Appodeal.setTesting(false);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.BANNER_TOP);
        Appodeal.show(this,Appodeal.BANNER_TOP);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.REWARDED_VIDEO);
        Appodeal.cache(MathScreen.this,Appodeal.REWARDED_VIDEO);
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded(boolean b) {
                Appodeal.show(MathScreen.this,Appodeal.REWARDED_VIDEO);
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
        Appodeal.show(MathScreen.this,Appodeal.REWARDED_VIDEO);

        //Game Play
        Submit = (Button)findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyResult.equals(AllResult)) {
                    final ProgressDialog progressDialog = new ProgressDialog(MathScreen.this);
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
                        }
                    });
                }
                else{
                    ProgressDialog progressDialog = new ProgressDialog(MathScreen.this);
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
                        }
                    });
                    progressDialog.show();
                }
            }
        });
        DatabaseReference database1 = FirebaseDatabase.getInstance().getReference();
        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Language = snapshot.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("language").getValue().toString();
                HistoryLose = Double.valueOf(snapshot.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lose").getValue().toString());
                HistoryWin = Double.valueOf(snapshot.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("win").getValue().toString());
                HistoryLevel = Double.valueOf(snapshot.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("level").getValue().toString());
                Random r = new Random();
                Nomre1 = r.nextInt(101);
                Nomre2 = r.nextInt(101);
                Nomre3 = r.nextInt(101);
                Nomre4 = r.nextInt(101);
                Nomre5 = r.nextInt(101);
                Nomre6 = r.nextInt(101);
                Nomre1Mine = r.nextInt(20);
                Nomre2Mine = r.nextInt(20);
                Nomre3Mine = r.nextInt(20);
                Nomre4Mine = r.nextInt(20);
                Nomre5Mine = r.nextInt(20);
                Nomre6Mine = r.nextInt(20);
                Result1 = Nomre1 + Nomre2;
                Result2 = Nomre3 + Nomre4;
                Result3 = Nomre5 + Nomre6;
                Place = r.nextInt(3);
                Place2 = r.nextInt(3);
                Place3 = r.nextInt(3);
                if (Language.equals("tur")) {
                    //Translate page
                    final TextView Title1 = (TextView) findViewById(R.id.title1);
                    final TextView Title2 = (TextView) findViewById(R.id.title2);
                    final TextView Title3 = (TextView) findViewById(R.id.title3);
                    final TextView Title22 = (TextView) findViewById(R.id.title22);
                    final TextView Title23 = (TextView) findViewById(R.id.title23);
                    final TextView Title32 = (TextView) findViewById(R.id.title32);
                    final TextView Title33 = (TextView) findViewById(R.id.title33);
                    final TextView Number1 = (TextView) findViewById(R.id.number1);
                    final TextView Number2 = (TextView) findViewById(R.id.number2);
                    final TextView Number21 = (TextView) findViewById(R.id.number21);
                    final TextView Number22 = (TextView) findViewById(R.id.number22);
                    final TextView Number31 = (TextView) findViewById(R.id.number31);
                    final TextView Number32 = (TextView) findViewById(R.id.number32);
                    final TextView Res0 = (TextView) findViewById(R.id.res0);
                    final TextView Res1 = (TextView) findViewById(R.id.res1);
                    final TextView Res2 = (TextView) findViewById(R.id.res2);
                    final TextView Res20 = (TextView) findViewById(R.id.res20);
                    final TextView Res21 = (TextView) findViewById(R.id.res21);
                    final TextView Res22 = (TextView) findViewById(R.id.res22);
                    final TextView Res30 = (TextView) findViewById(R.id.res30);
                    final TextView Res31 = (TextView) findViewById(R.id.res31);
                    final TextView Res32 = (TextView) findViewById(R.id.res32);
                    final CardView Result11 = (CardView) findViewById(R.id.Result1);
                    final CardView Result12 = (CardView) findViewById(R.id.Result2);
                    final CardView Result13 = (CardView) findViewById(R.id.Result3);
                    final CardView Result21 = (CardView) findViewById(R.id.Result21);
                    final CardView Result22 = (CardView) findViewById(R.id.Result22);
                    final CardView Result23 = (CardView) findViewById(R.id.Result23);
                    final CardView Result31 = (CardView) findViewById(R.id.Result31);
                    final CardView Result32 = (CardView) findViewById(R.id.Result32);
                    final CardView Result33 = (CardView) findViewById(R.id.Result33);

                    //Set the variables:
                    Title1.setText("Matematik Oyunu");
                    Title2.setText("1-ci Soru");
                    Title3.setText("Cevabı Seçin");
                    Title22.setText("2-ci Soru");
                    Title23.setText("Cevabı Seçin");
                    Title32.setText("3-cü Soru");
                    Title33.setText("Cevabı Seçin");

                    //Set the numbers to the number place
                    Number1.setText(String.valueOf(Nomre1));
                    Number2.setText(String.valueOf(Nomre2));
                    Number21.setText(String.valueOf(Nomre3));
                    Number22.setText(String.valueOf(Nomre4));
                    Number31.setText(String.valueOf(Nomre5));
                    Number32.setText(String.valueOf(Nomre6));

                    //Set the number of variants in the first question:
                    if (Place == 0) {
                        AllResult = AllResult + "1";
                        Res0.setText(String.valueOf(Result1));
                        Res1.setText(String.valueOf(Result1 + Nomre1Mine));
                        Res1.setText(String.valueOf(Result1 - Nomre2Mine));
                    } else if (Place == 1) {
                        AllResult = AllResult + "2";
                        Res1.setText(String.valueOf(Result1));
                        Res0.setText(String.valueOf(Result1 + Nomre1Mine));
                        Res2.setText(String.valueOf(Result1 - Nomre2Mine));
                    } else if (Place == 2) {
                        AllResult = AllResult + "3";
                        Res2.setText(String.valueOf(Result1));
                        Res1.setText(String.valueOf(Result1 + Nomre1Mine));
                        Res0.setText(String.valueOf(Result1 - Nomre2Mine));
                    }

                    //Set the number of variant in the second question:
                    if (Place2 == 0) {
                        AllResult = AllResult + "4";
                        Res20.setText(String.valueOf(Result2));
                        Res21.setText(String.valueOf(Result2 + Nomre3Mine));
                        Res22.setText(String.valueOf(Result2 - Nomre4Mine));
                    } else if (Place2 == 1) {
                        AllResult = AllResult + "5";
                        Res21.setText(String.valueOf(Result2));
                        Res20.setText(String.valueOf(Result2 + Nomre3Mine));
                        Res22.setText(String.valueOf(Result2 - Nomre4Mine));
                    } else if (Place2 == 2) {
                        AllResult = AllResult + "6";
                        Res22.setText(String.valueOf(Result2));
                        Res20.setText(String.valueOf(Result2 + Nomre3Mine));
                        Res21.setText(String.valueOf(Result2 - Nomre4Mine));
                    }

                    //Set the number of variant in third question:
                    if (Place3 == 0) {
                        AllResult = AllResult + "7";
                        Res30.setText(String.valueOf(Result3));
                        Res31.setText(String.valueOf(Result3 + Nomre5Mine));
                        Res32.setText(String.valueOf(Result3 - Nomre6Mine));
                    } else if (Place3 == 1) {
                        AllResult = AllResult + "8";
                        Res31.setText(String.valueOf(Result3));
                        Res32.setText(String.valueOf(Result3 + Nomre5Mine));
                        Res30.setText(String.valueOf(Result3 - Nomre6Mine));
                    } else if (Place3 == 2) {
                        AllResult = AllResult + "9";
                        Res32.setText(String.valueOf(Result3));
                        Res31.setText(String.valueOf(Result3 + Nomre5Mine));
                        Res30.setText(String.valueOf(Result3 - Nomre6Mine));
                    }
                    Res0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "1";
                            Result11.getBackground().setTint(Color.BLUE);
                            Result11.setEnabled(false);
                            Result12.setEnabled(false);
                            Result13.setEnabled(false);
                            Toast.makeText(getApplicationContext(),"Hey clicked", Toast.LENGTH_LONG).show();
                        }
                    });
                    Result11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "1";
                            Result12.setEnabled(false);
                            Result13.setEnabled(false);
                            Result11.getBackground().setTint(Color.BLUE);
                            Result11.setEnabled(false);
                        }
                    });
                    Result12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "2";
                            Result12.setCardBackgroundColor(Color.BLUE);
                            Result11.setEnabled(false);
                            Result13.setEnabled(false);
                            Result12.getBackground().setTint(Color.BLUE);
                            Result12.setEnabled(false);
                        }
                    });
                    Result13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "3";
                            Result13.setCardBackgroundColor(Color.BLUE);
                            Result12.setEnabled(false);
                            Result11.setEnabled(false);
                            Result13.getBackground().setTint(Color.BLUE);
                            Result13.setEnabled(false);
                        }
                    });
                    Result21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "4";
                            Result21.setCardBackgroundColor(Color.BLUE);
                            Result22.setEnabled(false);
                            Result23.setEnabled(false);
                            Result21.getBackground().setTint(Color.BLUE);
                            Result21.setEnabled(false);
                        }
                    });
                    Result22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "5";
                            Result22.setCardBackgroundColor(Color.BLUE);
                            Result21.setEnabled(false);
                            Result23.setEnabled(false);
                            Result22.getBackground().setTint(Color.BLUE);
                            Result22.setEnabled(false);
                        }
                    });
                    Result23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "6";
                            Result23.setCardBackgroundColor(Color.BLUE);
                            Result22.setEnabled(false);
                            Result21.setEnabled(false);
                            Result23.getBackground().setTint(Color.BLUE);
                            Result23.setEnabled(false);
                        }
                    });
                    Result31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "7";
                            Result31.getBackground().setTint(Color.BLUE);
                            Result32.setEnabled(false);
                            Result33.setEnabled(false);
                            Result31.setEnabled(false);
                            check();
                        }
                    });
                    Result32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "8";
                            Result32.setCardBackgroundColor(Color.BLUE);
                            Result31.setEnabled(false);
                            Result33.setEnabled(false);
                            Result32.getBackground().setTint(Color.BLUE);
                            Result32.setEnabled(false);
                            check();
                        }
                    });
                    Result33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "9";
                            Result33.setCardBackgroundColor(Color.BLUE);
                            Result32.setEnabled(false);
                            Result31.setEnabled(false);
                            Result33.getBackground().setTint(Color.BLUE);
                            Result33.setEnabled(false);
                            check();
                        }
                    });
                }
                else{
                    final TextView Title1 = (TextView) findViewById(R.id.title1);
                    final TextView Title2 = (TextView) findViewById(R.id.title2);
                    final TextView Title3 = (TextView) findViewById(R.id.title3);
                    final TextView Title22 = (TextView) findViewById(R.id.title22);
                    final TextView Title23 = (TextView) findViewById(R.id.title23);
                    final TextView Title32 = (TextView) findViewById(R.id.title32);
                    final TextView Title33 = (TextView) findViewById(R.id.title33);
                    final TextView Number1 = (TextView) findViewById(R.id.number1);
                    final TextView Number2 = (TextView) findViewById(R.id.number2);
                    final TextView Number21 = (TextView) findViewById(R.id.number21);
                    final TextView Number22 = (TextView) findViewById(R.id.number22);
                    final TextView Number31 = (TextView) findViewById(R.id.number31);
                    final TextView Number32 = (TextView) findViewById(R.id.number32);
                    final TextView Res0 = (TextView) findViewById(R.id.res0);
                    final TextView Res1 = (TextView) findViewById(R.id.res1);
                    final TextView Res2 = (TextView) findViewById(R.id.res2);
                    final TextView Res20 = (TextView) findViewById(R.id.res20);
                    final TextView Res21 = (TextView) findViewById(R.id.res21);
                    final TextView Res22 = (TextView) findViewById(R.id.res22);
                    final TextView Res30 = (TextView) findViewById(R.id.res30);
                    final TextView Res31 = (TextView) findViewById(R.id.res31);
                    final TextView Res32 = (TextView) findViewById(R.id.res32);
                    final CardView Result11 = (CardView) findViewById(R.id.Result1);
                    final CardView Result12 = (CardView) findViewById(R.id.Result2);
                    final CardView Result13 = (CardView) findViewById(R.id.Result3);
                    final CardView Result21 = (CardView) findViewById(R.id.Result21);
                    final CardView Result22 = (CardView) findViewById(R.id.Result22);
                    final CardView Result23 = (CardView) findViewById(R.id.Result23);
                    final CardView Result31 = (CardView) findViewById(R.id.Result31);
                    final CardView Result32 = (CardView) findViewById(R.id.Result32);
                    final CardView Result33 = (CardView) findViewById(R.id.Result33);

                    //Set the variables:


                    //Set the numbers to the number place
                    Number1.setText(String.valueOf(Nomre1));
                    Number2.setText(String.valueOf(Nomre2));
                    Number21.setText(String.valueOf(Nomre3));
                    Number22.setText(String.valueOf(Nomre4));
                    Number31.setText(String.valueOf(Nomre5));
                    Number32.setText(String.valueOf(Nomre6));

                    //Set the number of variants in the first question:
                    if (Place == 0) {
                        AllResult = AllResult + "1";
                        Res0.setText(String.valueOf(Result1));
                        Res1.setText(String.valueOf(Result1 + Nomre1Mine));
                        Res1.setText(String.valueOf(Result1 - Nomre2Mine));
                    } else if (Place == 1) {
                        AllResult = AllResult + "2";
                        Res1.setText(String.valueOf(Result1));
                        Res0.setText(String.valueOf(Result1 + Nomre1Mine));
                        Res2.setText(String.valueOf(Result1 - Nomre2Mine));
                    } else if (Place == 2) {
                        AllResult = AllResult + "3";
                        Res2.setText(String.valueOf(Result1));
                        Res1.setText(String.valueOf(Result1 + Nomre1Mine));
                        Res0.setText(String.valueOf(Result1 - Nomre2Mine));
                    }

                    //Set the number of variant in the second question:
                    if (Place2 == 0) {
                        AllResult = AllResult + "4";
                        Res20.setText(String.valueOf(Result2));
                        Res21.setText(String.valueOf(Result2 + Nomre3Mine));
                        Res22.setText(String.valueOf(Result2 - Nomre4Mine));
                    } else if (Place2 == 1) {
                        AllResult = AllResult + "5";
                        Res21.setText(String.valueOf(Result2));
                        Res20.setText(String.valueOf(Result2 + Nomre3Mine));
                        Res22.setText(String.valueOf(Result2 - Nomre4Mine));
                    } else if (Place2 == 2) {
                        AllResult = AllResult + "6";
                        Res22.setText(String.valueOf(Result2));
                        Res20.setText(String.valueOf(Result2 + Nomre3Mine));
                        Res21.setText(String.valueOf(Result2 - Nomre4Mine));
                    }

                    //Set the number of variant in third question:
                    if (Place3 == 0) {
                        AllResult = AllResult + "7";
                        Res30.setText(String.valueOf(Result3));
                        Res31.setText(String.valueOf(Result3 + Nomre5Mine));
                        Res32.setText(String.valueOf(Result3 - Nomre6Mine));
                    } else if (Place3 == 1) {
                        AllResult = AllResult + "8";
                        Res31.setText(String.valueOf(Result3));
                        Res32.setText(String.valueOf(Result3 + Nomre5Mine));
                        Res30.setText(String.valueOf(Result3 - Nomre6Mine));
                    } else if (Place3 == 2) {
                        AllResult = AllResult + "9";
                        Res32.setText(String.valueOf(Result3));
                        Res31.setText(String.valueOf(Result3 + Nomre5Mine));
                        Res30.setText(String.valueOf(Result3 - Nomre6Mine));
                    }
                    Res0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "1";
                            Result11.getBackground().setTint(Color.BLUE);
                            Result11.setEnabled(false);
                            Result12.setEnabled(false);
                            Result13.setEnabled(false);
                            Toast.makeText(getApplicationContext(),"Hey clicked", Toast.LENGTH_LONG).show();
                        }
                    });
                    Result11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "1";
                            Result12.setEnabled(false);
                            Result13.setEnabled(false);
                            Result11.getBackground().setTint(Color.BLUE);
                            Result11.setEnabled(false);
                        }
                    });
                    Result12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "2";
                            Result12.setCardBackgroundColor(Color.BLUE);
                            Result11.setEnabled(false);
                            Result13.setEnabled(false);
                            Result12.getBackground().setTint(Color.BLUE);
                            Result12.setEnabled(false);
                        }
                    });
                    Result13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "3";
                            Result13.setCardBackgroundColor(Color.BLUE);
                            Result12.setEnabled(false);
                            Result11.setEnabled(false);
                            Result13.getBackground().setTint(Color.BLUE);
                            Result13.setEnabled(false);
                        }
                    });
                    Result21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "4";
                            Result21.setCardBackgroundColor(Color.BLUE);
                            Result22.setEnabled(false);
                            Result23.setEnabled(false);
                            Result21.getBackground().setTint(Color.BLUE);
                            Result21.setEnabled(false);
                        }
                    });
                    Result22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "5";
                            Result22.setCardBackgroundColor(Color.BLUE);
                            Result21.setEnabled(false);
                            Result23.setEnabled(false);
                            Result22.getBackground().setTint(Color.BLUE);
                            Result22.setEnabled(false);
                        }
                    });
                    Result23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "6";
                            Result23.setCardBackgroundColor(Color.BLUE);
                            Result22.setEnabled(false);
                            Result21.setEnabled(false);
                            Result23.getBackground().setTint(Color.BLUE);
                            Result23.setEnabled(false);
                        }
                    });
                    Result31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "7";
                            Result31.getBackground().setTint(Color.BLUE);
                            Result32.setEnabled(false);
                            Result33.setEnabled(false);
                            Result31.setEnabled(false);
                            check();
                        }
                    });
                    Result32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "8";
                            Result32.setCardBackgroundColor(Color.BLUE);
                            Result31.setEnabled(false);
                            Result33.setEnabled(false);
                            Result32.getBackground().setTint(Color.BLUE);
                            Result32.setEnabled(false);
                            check();
                        }
                    });
                    Result33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyResult = MyResult + "9";
                            Result33.setCardBackgroundColor(Color.BLUE);
                            Result32.setEnabled(false);
                            Result31.setEnabled(false);
                            Result33.getBackground().setTint(Color.BLUE);
                            Result33.setEnabled(false);
                            check();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void check(){
        if (MyResult.equals(AllResult)) {
            final ProgressDialog progressDialog = new ProgressDialog(MathScreen.this);
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
            ProgressDialog progressDialog = new ProgressDialog(MathScreen.this);
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
