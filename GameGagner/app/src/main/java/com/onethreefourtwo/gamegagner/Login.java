package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    private EditText Name;
    private EditText Email;
    private EditText Password;
    private EditText SecondPassword;
    private EditText Surname;
    private AdView mAdView,mAdView1;
    private Button Submit;
    private String NameString,EmailString,PasswordString,SecondPasswordString,SurnameString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText)findViewById(R.id.name);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        SecondPassword = (EditText)findViewById(R.id.secondpassword);
        Submit = (Button)findViewById(R.id.Submit);
        Surname = (EditText)findViewById(R.id.surname);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameString = Name.getText().toString();
                EmailString = Email.getText().toString();
                PasswordString = Password.getText().toString();
                SecondPasswordString = SecondPassword.getText().toString();
                SurnameString = Surname.getText().toString();

                if(NameString.matches("")){
                    Toast.makeText(getApplicationContext(),"Adınız qeyd edilməmişdir!",Toast.LENGTH_LONG).show();
                }
                else if(EmailString.matches("")){
                    Toast.makeText(getApplicationContext(),"Emailiniz qeyd edilməmişdir!",Toast.LENGTH_LONG).show();
                }
                else if(PasswordString.matches("")){
                    Toast.makeText(getApplicationContext(),"Şifrəniz qeyd edilməmişdir!",Toast.LENGTH_LONG).show();
                }
                else if(SecondPasswordString.matches("")){
                    Toast.makeText(getApplicationContext(),"Təhlükəsizlik şifrəniz qeyd edilməmişdir!",Toast.LENGTH_LONG).show();
                }
                else{
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    Toast.makeText(getApplicationContext(),EmailString+PasswordString,Toast.LENGTH_LONG).show();
                    firebaseAuth.createUserWithEmailAndPassword(EmailString,PasswordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //Burada esas ekrana getmelidir
                                String MyId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users");
                                db1.child(MyId).child("id").setValue(MyId);
                                db1.child(MyId).child("name").setValue(NameString);
                                db1.child(MyId).child("email").setValue(EmailString);
                                db1.child(MyId).child("password").setValue(PasswordString);
                                db1.child(MyId).child("secondpassword").setValue(SecondPasswordString);
                                db1.child(MyId).child("surname").setValue(SurnameString);
                                db1.child(MyId).child("level").setValue(0);
                                db1.child(MyId).child("lose").setValue(0);
                                db1.child(MyId).child("win").setValue(0);
                                db1.child(MyId).child("price").setValue(0);
                                Intent i = new Intent(getApplicationContext(),LanguageChoose.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Qeydiyyatda problem yarandı!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                final TextView LoginButton = (TextView) findViewById(R.id.loginbtn);
                LoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent  i = new Intent(getApplicationContext(),Register_Activity.class);
                        startActivity(i);
                    }
                });

            }
        });

    }
}