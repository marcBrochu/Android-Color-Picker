package com.example.colorpicker.SeekBarManager;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.example.colorpicker.R;

public class rgbGradientController {

    static int getUpperGreenGradient() {
        @ColorInt int gradient = Color.rgb(RedSeekBar.redValue, 255, BlueSeekBar.blueValue);
        return gradient;
    }

    static int getUpperBlueGradient() {
        @ColorInt int gradient = Color.rgb(RedSeekBar.redValue, GreenSeekBar.greenValue, 255);
        return gradient;
    }

    static int getUpperRedGradient() {
        @ColorInt int gradient = Color.rgb(255, GreenSeekBar.greenValue, BlueSeekBar.blueValue);
        return gradient;
    }

    static int getLowerGreenGradient() {
        @ColorInt int gradient = Color.rgb(RedSeekBar.redValue, 0, BlueSeekBar.blueValue);
        return gradient;
    }

    static int getLowerRedGradient() {
        @ColorInt int gradient = Color.rgb(0, GreenSeekBar.greenValue, BlueSeekBar.blueValue);
        return gradient;
    }

    static int getLowerBlueGradient() {
        @ColorInt int gradient = Color.rgb(RedSeekBar.redValue, GreenSeekBar.greenValue, 0);
        return gradient;
    }
}
