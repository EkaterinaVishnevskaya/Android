package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends Activity {
    private TextView money;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        money = (TextView)findViewById(R.id.money);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = database.getReference("accounts/"+firebaseAuth.getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CompanyAccount account = new CompanyAccount();
                account.setAmountOfMoney(dataSnapshot.getValue(CompanyAccount.class).getAmountOfMoney());
                money.setText(String.valueOf(account.getAmountOfMoney()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
