package com.example.ekaterina.hw1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.ekaterina.hw1.R.id.second;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    public int a = 0;
    public int b = 0;
    private Button dec = (Button) findViewById(R.id.dec);
    private Button inc = (Button) findViewById(R.id.inc);
    private Button count = (Button) findViewById(R.id.calc);
    private TextView first = (TextView)findViewById(R.id.first);
    private EditText second = (EditText)findViewById(R.id.second);
    private TextView result = (TextView)findViewById(R.id.result);
    private Button change = (Button) findViewById(R.id.change_activity);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a--;
                first.setText("" + a);
            }
        });
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a++;
                first.setText("" + a);
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = parseInt(second.getText().toString());
                int calc = a*b;
                result.setText("Result="+calc);
            }
        });

        final Context cont = this;
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(cont, SecondActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("a", a);
        outState.putString("first", first.getText().toString());
        outState.putString("res", result.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        result.setText(savedInstanceState.getString("res"));
        first.setText(savedInstanceState.getString("first"));
        a = savedInstanceState.getInt("a");
    }
}
