package com.example.colorpicker.SeekBarManager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.example.colorpicker.ColorPickerDialog;
import com.example.colorpicker.R;

public class BlueSeekBar extends AppCompatSeekBar {
    private static SeekBar seekB;
    public static int blueValue;

    public BlueSeekBar(Context context) {
        super(context);
        init();
    }

    public BlueSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlueSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        seekB = findViewById(R.id.seekB);
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, 940f, 0.0f,

                new int[]{Color.BLACK, Color.BLUE},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);

        seekB.setProgressDrawable(shape);
        setOnSeekBarChangeListener(listener);
    }

    OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateBlueValue(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private static void updateBlueValue(int progress) {
        ColorPickerDialog.setBlueValue(progress);
        blueValue = progress;
        GreenSeekBar.updateGreenShade();
        RedSeekBar.updateRedShade();
    }

    public static void updateBlueSeekBar(int blueProgress) {
        seekB.setProgress(blueProgress);
        updateBlueValue(blueProgress);
    }

    public static void updateBlueShade() {
        @ColorInt int lowerGradient = rgbGradientController.getLowerBlueGradient();
        @ColorInt int upperGradient = rgbGradientController.getUpperBlueGradient();
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, seekB.getWidth(), 0.0f,

                new int[]{lowerGradient, upperGradient},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);
        seekB.setProgressDrawable(shape);
    }

}
