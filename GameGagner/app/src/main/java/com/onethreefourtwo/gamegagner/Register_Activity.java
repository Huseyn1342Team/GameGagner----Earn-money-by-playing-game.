package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_Activity extends AppCompatActivity {
    private EditText Email,Password;
    private String EmailText,PasswordText;
    private Button Submit;
    private AdView mAdView,mAdView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        Submit = (Button)findViewById(R.id.submit);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmailText = Email.getText().toString();
                PasswordText = Password.getText().toString();
                if(EmailText.matches("")){
                    Toast.makeText(getApplicationContext(),"Emailinizi qeyd edin!",Toast.LENGTH_LONG).show();
                }
                else if(PasswordText.matches("")){
                    Toast.makeText(getApplicationContext(),"Şifrənizi qeyd edin!",Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(EmailText,PasswordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.hasChild("language")){
                                            Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                            startActivity(i);
                                            finish();
                                        }
                                        else{
                                            Intent i = new Intent(getApplicationContext(),LanguageChoose.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Daxil olmaq prosesində problem yarandı!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


        final TextView RegisterBtn = (TextView)findViewById(R.id.registerbtn);


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
}