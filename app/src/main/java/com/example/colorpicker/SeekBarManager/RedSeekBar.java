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

public class RedSeekBar extends AppCompatSeekBar {
    private static SeekBar seekR;
    public static int redValue;

    public RedSeekBar(Context context) {
        super(context);
        init();
    }

    public RedSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RedSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        seekR = findViewById(R.id.seekR);
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, 940f, 0.0f,
                new int[]{Color.BLACK, Color.RED},
                null, Shader.TileMode.CLAMP);

        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);

        seekR.setProgressDrawable(shape);
        setOnSeekBarChangeListener(listener);
    }

    OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateRedValue(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private static void updateRedValue(int progress) {
        ColorPickerDialog.setRedValue(progress);
        redValue = progress;
        GreenSeekBar.updateGreenShade();
        BlueSeekBar.updateBlueShade();
    }

    public static void updateRedSeekBar(int redProgress) {
        seekR.setProgress(redProgress);
        updateRedValue(redProgress);
    }

    public static void updateRedShade() {
        @ColorInt int lowerGradient = rgbGradientController.getLowerRedGradient();
        @ColorInt int upperGradient = rgbGradientController.getUpperRedGradient();
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, seekR.getWidth(), 0.0f,

                new int[]{lowerGradient, upperGradient},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);
        seekR.setProgressDrawable(shape);
    }
}
