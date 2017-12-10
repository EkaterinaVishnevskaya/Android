package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends Activity {
    Button btn;
    EditText name;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = getApplicationContext();
        btn =(Button) findViewById(R.id.save);
        Intent intent = getIntent();
        name = (EditText)findViewById(R.id.name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference companiesRef = database.getReference("companies/"+firebaseAuth.getCurrentUser().getUid());
                companiesRef.setValue(new InsuranceCompany(firebaseAuth.getCurrentUser().getEmail(), name.getText().toString()));
                database = FirebaseDatabase.getInstance();
                DatabaseReference accountsRef = database.getReference("accounts/"+firebaseAuth.getCurrentUser().getUid());
                accountsRef.setValue(new CompanyAccount());
                Intent intent = new Intent(context, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
