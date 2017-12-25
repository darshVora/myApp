package com.example.vora.darsh.socialmediaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleInstaActivity extends AppCompatActivity {

    private String post_key = null;
    private DatabaseReference mDatabase;
    private ImageView image;
    private TextView title;
    private TextView desc;
    private Button deleteButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_insta);

        post_key = getIntent().getExtras().getString("Postid");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Instaapp");

        image = findViewById(R.id.singleImageView);
        title = findViewById(R.id.singleTitle);
        desc = findViewById(R.id.singleDesc);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_title = dataSnapshot.child("title").getValue(String.class);
                String post_desc = dataSnapshot.child("desc").getValue(String.class);
                String post_image = dataSnapshot.child("image").getValue(String.class);
                String post_uid = dataSnapshot.child("uid").getValue(String.class);

                title.setText(post_title);
                title.setTextColor(Color.BLUE);
                desc.setText(post_desc);
                desc.setTextColor(Color.WHITE);
                Picasso.with(SingleInstaActivity.this).load(post_image).into(image);

                if(mAuth.getCurrentUser().getUid().equals(post_uid)){
                    deleteButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }

    public void deleteButtonClicked(View view){
        if(post_key != null) {
            mDatabase.child(post_key).removeValue();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

    }
}
