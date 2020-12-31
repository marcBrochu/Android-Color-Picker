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

public class HueSeekBar extends AppCompatSeekBar {

    private static SeekBar seekH;

    public HueSeekBar(Context context) {
        super(context);
        init();
    }

    public HueSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HueSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        seekH = (SeekBar) findViewById(R.id.seekH);
        LinearGradient linearGradient = new LinearGradient(0.f, 0.f, 940f, 0.0f,

                new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(linearGradient);

        seekH.setProgressDrawable(shape);
        setOnSeekBarChangeListener(listener);
    }

    OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateHueValue(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private static void updateHueValue(int progress) {
        ColorPickerDialog.setHueValue(progress);
    }

    public static void updateHueSeekBar(int hueProgress) {
        seekH.setProgress(hueProgress);
        updateHueValue(hueProgress);
    }
}
