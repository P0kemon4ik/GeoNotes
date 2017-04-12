package com.example.vladislavezerski.geonotes;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.skydoves.colorpickerview.ColorPickerView;

public class NotesAddActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private ImageView newNote;
    private ImageView changeColor;
    private EditText txtBody;
    private TextView otmena;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_add);
        newNote = (ImageView) findViewById(R.id.imageView2);
        changeColor = (ImageView) findViewById(R.id.imgChangeColor);
        txtBody = (EditText) findViewById(R.id.txt_notes_body);
        otmena = (TextView) findViewById(R.id.txt_otmena);

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ColorPickerActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        findViewById(R.id.txt_gotovo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("text",txtBody.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        otmena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            newNote.getBackground().setColorFilter(data.getIntExtra("color", Color.BLACK), PorterDuff.Mode.SRC_ATOP);
            changeColor.getBackground().setColorFilter(data.getIntExtra("color", Color.BLACK), PorterDuff.Mode.SRC_ATOP);
        }

    }

}