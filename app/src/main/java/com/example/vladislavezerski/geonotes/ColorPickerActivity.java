package com.example.vladislavezerski.geonotes;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.skydoves.colorpickerview.ColorPickerView;

public class ColorPickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker);


        final ColorPickerView colorPicker = (ColorPickerView) findViewById(R.id.colorPickerView);
        final View btn = findViewById(R.id.btnColorPick);

        colorPicker.setColorListener(new ColorPickerView.ColorListener() {
            @Override
            public void onColorSelected(int color) {
                btn.getBackground().setColorFilter(colorPicker.getColor(), PorterDuff.Mode.SRC_ATOP);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("color", colorPicker.getColor());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
