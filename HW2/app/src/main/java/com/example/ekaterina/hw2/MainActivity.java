package com.example.ekaterina.hw2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View container = findViewById(R.id.fragment_container);
        if (container != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            A_Fragment firstFragment = new A_Fragment();
            B_Fragment secondFragment = new B_Fragment();
            C1_Fragment thirdFragment = new C1_Fragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());
            if(container instanceof FrameLayout) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            } else if (container instanceof LinearLayout)
            {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_a, firstFragment).commit();
                getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_b, secondFragment).commit();
                getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_c, thirdFragment).commit();
            }
        }
    }

}
