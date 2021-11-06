package com.onethreefourtwo.gamegagner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {
    private String ReceiverUid;
    private ImageView Back;
    private ImageView SendButton;
    private EditText SendEdit;
    private FirebaseUser SenderUser;
    private MessageAdapter messageAdapter;
    private List<MessageModel> mChat;
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private TextView Name;
    private ImageView Call;
    private CircleImageView CircleImage;
    private String phonenumber;
    private String profil;
    private String c;
    private String msg;
    private int dad;
    private DatabaseReference db1,db2;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAbo21vCA:APA91bG0bpvsZAiLhEJPLq2MJPNGoKJhlCCkaIi9b_iKWxbI7f6p2Nz61vnEoYE4FrM2heomUoLa15T94y6p2YIVo7ts49fbr0uaverMa4_a8RH2NdoYxEZPbn4oS4R14QY_2iqolK7U";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;
    private boolean notify = false;
    public boolean condition1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        MobileAds.initialize(getApplicationContext(),String.valueOf(R.string.app_id));

        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String language = snapshot.child("language").getValue().toString();
               if(language.equals("tur")){
                   final TextView name = (TextView)findViewById(R.id.name);
                   name.setText("Bize Ulaş");
                   condition1 = true;
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        Call = (ImageView)findViewById(R.id.call);
        Name = (TextView)findViewById(R.id.name);

        recyclerView = (RecyclerView)findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        SendButton = (ImageView)findViewById(R.id.sendbutton);
        SendEdit = (EditText)findViewById(R.id.sendedittext);
        SenderUser = FirebaseAuth.getInstance().getCurrentUser();
        phonenumber = "0557567473";
        readMessages(SenderUser.getUid(),"user");
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(phonenumber);
            }
        });
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = SendEdit.getText().toString();
                if(!msg.equals("")){
                    SendMessage(SenderUser.getUid(),ReceiverUid,msg);
                    SendEdit.setText("");
                    return;
                }
                if(condition1){
                    Toast.makeText(Chat.this, "Boş mesaj yollaya bilmiyorsunuz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Chat.this, "Boş mesaj yollaya bilmərsiniz!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void SendMessage(String sender, String receiver, String message){
        receiver = "user";
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();

        String myid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        reference2.child("Chats").child(myid).push().setValue(hashMap);

        final String msg = message;

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    private void readMessages(final String myid, final String userid){

        mChat = new ArrayList<>();
        String myid2 = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        reference = FirebaseDatabase.getInstance().getReference().child("Chats").child(myid2);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    MessageModel chat =snapshot.getValue(MessageModel.class);
                    if(chat.getReceiver().equals(myid)&&chat.getSender().equals(userid)||chat.getReceiver().equals(userid)&&chat.getSender().equals(myid)){
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(Chat.this,mChat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
