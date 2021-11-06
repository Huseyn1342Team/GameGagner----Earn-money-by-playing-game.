package com.onethreefourtwo.gamegagner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.UUID;

public class Profil extends AppCompatActivity {
    public TextView Name,Name1,Surname,Email;
    private ImageView imageView;
    private Button AddImage;
    private  String uid;
    private Button ChooseImage,UploadImage;
    private Uri filePath;
    private TextView ChangePassword;
    FirebaseStorage storage;
    private ImageView Profilimage;
    StorageReference storageReference;
    private Button Upload;
    private TextView ChangeEmail;
    private String url;
    private TextView Phone;
    private TextView ChangePhone;
    public boolean condition1 = false;
    private TextView ShowScore;
    private final int PICK_IMAGE_REQUEST = 71;
    private TextView Contact;
    private AdView mAdView,mAdView1;
    private TextView LogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Appodeal.setTesting(false);
        Appodeal.initialize(this,"89abe9c6aae21dbe28f1cca6794ab8900839e61f4d9cfdfa",Appodeal.BANNER_TOP);
        Appodeal.show(this,Appodeal.BANNER_TOP);



        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
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
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //bottomNavigationView.setSelectedItemId(R.id.page_3);

        DatabaseReference db10 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("profil")) {
                    String image = snapshot.child("profil").getValue().toString();
                    Picasso.get().load(image).into(Profilimage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Profilimage = (ImageView)findViewById(R.id.profilimage);
        AddImage = (Button)findViewById(R.id.addImage);

        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
            }
        });
        Profilimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseImage();
            }
        });

        Name = (TextView)findViewById(R.id.name);
        Name1 = (TextView)findViewById(R.id.name2);
        Surname = (TextView)findViewById(R.id.surname);
        Email = (TextView)findViewById(R.id.email);
        DatabaseReference db11 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String language = snapshot.child("language").getValue().toString();
                if(language.equals("tur")){
                    bottomNavigationView.getMenu().clear();
                    bottomNavigationView.inflateMenu(R.menu.menu_tur);
                    condition1=true;
                    AddImage.setText("Resimi Yükle");
                    bottomNavigationView.getMenu().getItem(2).setChecked(true);
                    final TextView text1 = (TextView)findViewById(R.id.text1);
                    text1.setText("Profil resiminizi yüklemek için yukarıda bulunan profil resiminize tıklayıb kendi yeni profil resiminizi seçmelisiniz. Sonra ise yukarıda bulunan butonu tıklamalısınız.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        final String myId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String NameText = snapshot.child(myId).child("name").getValue().toString();
                String SurnameText = snapshot.child(myId).child("surname").getValue().toString();
                String EmailText = snapshot.child(myId).child("email").getValue().toString();

                Name.setText(NameText);
                Name1.setText(NameText);
                Surname.setText(SurnameText);
                Email.setText(EmailText);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ChooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Profilimage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void UploadImage(){
        if(filePath != null)
        {
            String s1 = "Yüklənir...";
            if(condition1){
                s1 = "Yükleniyor...";
            }
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(s1);
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // getting image uri and converting into string
                                    Uri downloadUrl = uri;
                                    String image = downloadUrl.toString();
                                    String mid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(mid);
                                    reference.child("profil").setValue(image);
                                    String s1 = "Yükləndi...";
                                    if(condition1){
                                        s1 = "Yükleme bitdi...";
                                    }
                                    Toast.makeText(Profil.this, s1, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Profil.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Yuklenir "+(int)progress+"%");
                        }
                    });
        }
    }
}