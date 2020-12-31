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

public class GreenSeekBar extends AppCompatSeekBar {
    private static SeekBar seekG;
    public static int greenValue;

    public GreenSeekBar(Context context) {
        super(context);
        init();
    }

    public GreenSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GreenSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        seekG = (SeekBar) findViewById(R.id.seekG);
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, 940f, 0.0f,

                new int[]{Color.BLACK, Color.GREEN},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);

        seekG.setProgressDrawable(shape);
        setOnSeekBarChangeListener(listener);
    }

    OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateGreenValue(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private static void updateGreenValue(int progress) {
        ColorPickerDialog.setGreenValue(progress);
        greenValue = progress;
        BlueSeekBar.updateBlueShade();
        RedSeekBar.updateRedShade();
    }

    public static void updateGreenSeekBar(int greenProgress) {
        seekG.setProgress(greenProgress);
        updateGreenValue(greenProgress);
    }

    public static void updateGreenShade() {
        @ColorInt int lowerGradient = rgbGradientController.getLowerGreenGradient();
        @ColorInt int upperGradient = rgbGradientController.getUpperGreenGradient();
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, seekG.getWidth(), 0.0f,

                new int[]{lowerGradient, upperGradient},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);
        seekG.setProgressDrawable(shape);
    }

}
