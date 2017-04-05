package com.example.vladislavezerski.geonotes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.skydoves.colorpickerview.ColorPickerView;

public class ColorPicker extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker);
    }
       /* colorPickerView.setColorListener(new ColorPickerView.ColorListener() {
            @Override
            public void onColorSelected(int color) {

            }
        });
    }

    @Override
    public void onColorSelected(int color) {
        colorPickerView.getColor()
    }*/
}
