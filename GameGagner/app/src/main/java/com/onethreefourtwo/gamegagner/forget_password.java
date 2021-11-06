package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class forget_password extends AppCompatActivity {
    private Button Submit;
    private AdView mAdView,mAdView1;
    private EditText SecondPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);



        //Declaring Variables
        Submit = (Button)findViewById(R.id.submit);
        SecondPassword = ( EditText)findViewById(R.id.secondpassword);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String SecondPasswordString = SecondPassword.getText().toString();
                if(SecondPasswordString.matches("")){
                    Toast.makeText(getApplicationContext(),"Siz təhlükəsizlik şifrənizi daxil etməmisiniz!",Toast.LENGTH_LONG).show();
                }
                else{
                    DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users");
                    db1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                if(snapshot1.child("secondpassword").getValue().toString().matches(SecondPasswordString)){
                                    Toast.makeText(getApplicationContext(),snapshot1.child("password").getValue().toString(),Toast.LENGTH_LONG).show();
                                    String EmailText = snapshot1.child("email").getValue().toString();
                                    String PaswordText = snapshot1.child("password").getValue().toString();
                                    FirebaseAuth.getInstance().signInWithEmailAndPassword(EmailText,PaswordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Intent i = new Intent(getApplicationContext(),EsasEkran.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Bu təhlükəsizlik şifrəsinə uyğun hesab tapılmadı!",Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}