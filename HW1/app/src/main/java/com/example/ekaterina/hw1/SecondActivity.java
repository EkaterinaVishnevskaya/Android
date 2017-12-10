package com.example.ekaterina.hw1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class SecondActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, SecondBlankFragment.OnFragmentInteractionListener {

    private ImageView mImageView;
    private Button tp;
    private Button button;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mImageView= (ImageView) findViewById(R.id.photo);
        tp = (Button) findViewById(R.id.take_photo);
        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        /*button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondBlankFragment fragment2 =new SecondBlankFragment();

                getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment2).commit();
            }
        });*/

        BlankFragment fragment =new BlankFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.button_fragment, fragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //SecondBlankFragment fragment =new SecondBlankFragment();

       // getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment).commit();
    }
}
