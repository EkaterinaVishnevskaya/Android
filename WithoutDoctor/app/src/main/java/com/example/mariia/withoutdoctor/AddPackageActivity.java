package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPackageActivity extends Activity {

    private Button btn;
    private EditText name;
    private EditText price;
    private EditText description;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference newPackagesRef;
    private DatabaseReference packagesRef;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package);
        context = getApplicationContext();
        btn = (Button)findViewById(R.id.add);
        name = (EditText) findViewById(R.id.name);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                database = FirebaseDatabase.getInstance();
                packagesRef = database.getReference("packages");
                newPackagesRef = packagesRef.push();
                final String[] companyName = new String[1];

                DatabaseReference company = database.getReference("companies/"+firebaseAuth.getCurrentUser().getUid());
                company.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        InsuranceCompany comp = new InsuranceCompany();
                        comp.setName(dataSnapshot.getValue(InsuranceCompany.class).getName());
                        companyName[0] = comp.getName();
                        Log.d("debug", companyName[0]);
                        newPackagesRef.setValue(new InsurancePackage(name.getText().toString(),
                                firebaseAuth.getCurrentUser().getUid(),
                                companyName[0],
                                description.getText().toString(),
                                Integer.parseInt(price.getText().toString())));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Toast.makeText(AddPackageActivity.this, "Пакет добавлен!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
