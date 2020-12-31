package com.example.colorpicker.SeekBarManager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.example.colorpicker.ColorPickerDialog;
import com.example.colorpicker.R;

public class AlphaSeekBar extends AppCompatSeekBar {
    private SeekBar seekA;

    public AlphaSeekBar(Context context) {
        super(context);
        init();
    }

    public AlphaSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlphaSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        seekA = (SeekBar) findViewById(R.id.seekA);
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, 940f, 0.0f,

                new int[]{Color.TRANSPARENT, Color.BLACK},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);

        seekA.setProgressDrawable(shape);
        setOnSeekBarChangeListener(listener);
    }

    OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateAlphaValue(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateAlphaValue(int progress) {
        ColorPickerDialog.setAlphaValue(progress / 100.0f);
    }
}
