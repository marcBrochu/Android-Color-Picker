package com.example.colorpicker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.colorpicker.SeekBarManager.BlueSeekBar;
import com.example.colorpicker.SeekBarManager.GreenSeekBar;
import com.example.colorpicker.SeekBarManager.HueSeekBar;
import com.example.colorpicker.SeekBarManager.RedSeekBar;
import com.example.colorpicker.Views.AreaPicker;

import java.util.Arrays;


public class ColorPickerDialog extends AlertDialog implements ColorVariables {
    private static AreaPicker seekSV;
    private static SaturationValueGradient saturationValueGradient;

    // Représentation/stockage interne de la couleur présentement sélectionnée par le Dialog.
    private static int r = Color.red(Color.BLACK);
    private static int g = Color.green(Color.BLACK);
    private static int b = Color.blue(Color.BLACK);
    private static int hue = 0;
    private static float alpha = 0;
    OnColorPickedListener callbackColor;

    ColorPickerDialog(Context context) {
        super(context);
        init(context);
    }

    ColorPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    ColorPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        // Initialize dialog
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_picker, null);
        setView(v);

        // Initialize SV gradient
        seekSV = v.findViewById(R.id.seekSV);
        saturationValueGradient = new SaturationValueGradient();
        seekSV.setInsetDrawable(saturationValueGradient);


        // Exemple pour afficher un gradient SV centré sur du rouge pur.
        saturationValueGradient.setColor(Color.RED);

        // Default color
        setColor(getContext().getColor(R.color.defaultColor));
    }


    @ColorInt
    int getColor() {
        return Color.rgb(r, g, b);
    }

    public void setColor(@ColorInt int newColor) {
        r = Color.red(newColor);
        g = Color.green(newColor);
        b = Color.blue(newColor);
    }

    static private int[] HSVtoRGB(int h, int s, int v) {
        float hPrime, sPrime, vPrime;
        float c, delta, x;
        float r, g, b;

        hPrime = (float) h / 60;
        sPrime = (float) s / MAX_SV_VALUE;
        vPrime = (float) v / MAX_SV_VALUE;

        c = sPrime * vPrime;
        delta = vPrime - c;
        x = 1 - Math.abs(((hPrime % 2) - 1));

        //Calcul R'
        if ((0 <= hPrime && hPrime <= 1) || (5 < hPrime && hPrime <= 6)) {
            r = 1;
        } else if ((1 < hPrime && hPrime <= 2) || (4 < hPrime && hPrime <= 5)) {
            r = x;
        } else {
            r = 0;
        }

        //Calcul G'
        if ((0 <= hPrime && hPrime <= 1) || (3 < hPrime && hPrime <= 4)) {
            g = x;
        } else if (1 < hPrime && hPrime <= 3) {
            g = 1;
        } else {
            g = 0;
        }

        //Calcul B'
        if (0 <= hPrime && hPrime <= 2) {
            b = 0;
        } else if ((2 < hPrime && hPrime <= 3) || (5 < hPrime && hPrime <= 6)) {
            b =  x;
        } else {
            b = 1;
        }

        r = (MAX_RGB_VALUE * (c * r + delta));
        g = (MAX_RGB_VALUE * (c * g + delta));
        b = (MAX_RGB_VALUE * (c * b + delta));

        return new int[]{(int)r, (int)g, (int)b};
    }

    static private int[] RGBtoHSV(int r, int g, int b) {
        float h, s, v;

        // Calcul du delta
        float maxColor = Math.max(Math.max(r, g), b);
        float minColor = Math.min(Math.min(r, g), b);
        float delta = maxColor - minColor;

        //Calcul de H'
        //Gestion des valeurs indéterminées
        if (delta == 0) {
            h = 0;
            s = 0;
            v = MAX_SV_VALUE;
            return new int[]{(int)h, (int)s, (int)v};
        }

        if (maxColor == r) {
            h = (g - b) / delta;
        } else if (maxColor == g) {
            h = 2 + ((b - r) / delta);
        } else {
            h = 4 + ((r - g) / delta);
        }

        //Calcul de H
        if (h >= 0) {
            h = 60 * h;
        } else {
            h = 60 * (h + 6);
        }

        //Calcul de S
        if (maxColor == 0) {
            s = 0;
        } else {
            s = 100 * (delta / maxColor);
        }

        //Calul de v
        v = 100 * (maxColor / MAX_RGB_VALUE);

        return new int[]{(int)h, (int)s, (int)v};
    }

    public static void updateRGBBars(int s, int v) {
        int[] rgb = HSVtoRGB(hue, s, v);
        RedSeekBar.updateRedSeekBar(rgb[0]);
        GreenSeekBar.updateGreenSeekBar(rgb[1]);
        BlueSeekBar.updateBlueSeekBar(rgb[2]);
    }

    private static void updateSquareColorByHue() {
        int newColor = Color.HSVToColor(new float[]{hue, STANDARD_SATURATION, STANDARD_VALUE});
        saturationValueGradient.setColor(newColor);
    }


    private static void updateHSV() {
        int[] hsv = RGBtoHSV(r, g, b);
        HueSeekBar.updateHueSeekBar(hsv[0]);
        seekSV.rePick(hsv[1]/100.f,   Math.abs(1-(hsv[2])/100.f));
    }

    public static void setRedValue(int progress) {
        r = progress;
        updateHSV();
    }

    static public void setGreenValue(int progress) {
        g = progress;
        updateHSV();
    }

    static public void setBlueValue(int progress) {
        b = progress;
        updateHSV();
    }

    public static void setHueValue(int progress) {
        hue = progress;
        updateSquareColorByHue();
    }

    public static void setAlphaValue(float progress) {
        alpha = progress;
    }


    public void setOnColorPickedListener(OnColorPickedListener onColorPickedListener) {
        callbackColor = onColorPickedListener;
    }

    public interface OnColorPickedListener {
        void onColorPicked(ColorPickerDialog colorPickerDialog, @ColorInt int color);
    }


    public void returnColor() {
        callbackColor.onColorPicked(this, getColor());
    }
}
