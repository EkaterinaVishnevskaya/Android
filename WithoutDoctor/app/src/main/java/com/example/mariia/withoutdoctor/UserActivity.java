package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends Activity{
    private Button btn;
    private Button btn2;
    private TextView name;
    private Context context;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = getApplicationContext();
        btn = (Button) findViewById(R.id.add);
        btn2 = (Button)findViewById(R.id.account);
        context = getApplicationContext();
        name = (TextView)findViewById(R.id.name);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = database.getReference("companies/"+firebaseAuth.getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                InsuranceCompany account = new InsuranceCompany();
                account.setName(dataSnapshot.getValue(InsuranceCompany.class).getName());
                name.setText(String.valueOf(account.getName()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddPackageActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

}
