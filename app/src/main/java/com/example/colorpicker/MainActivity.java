package com.example.colorpicker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ColorPickerDialog.OnColorPickedListener {

    View pickedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickedColor = findViewById(R.id.picked_color);
        pickedColor.setBackgroundColor(this.getColor(R.color.defaultColor));

        ColorPickerDialog dialog = new ColorPickerDialog(this);
        dialog.setTitle(getString(R.string.pick_color));
        dialog.setCancelable(false);
        dialog.setButton(Dialog.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogI, int which) {
                dialog.returnColor();
            }
        });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogI, int which) {
            }
        });
        findViewById(R.id.button_pick).setOnClickListener((View v) -> dialog.show());

        dialog.setOnColorPickedListener(this);

    }



    @Override
    public void onColorPicked(ColorPickerDialog colorPickerDialog, int color) {
        pickedColor.setBackgroundColor(color);
    }
}
