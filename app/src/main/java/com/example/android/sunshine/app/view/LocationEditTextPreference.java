package com.example.android.sunshine.app.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.sunshine.app.R;

/**
 * Created by jam on 17/6/25.
 */

public class LocationEditTextPreference extends EditTextPreference {

    private int minLength = DEFAULT_MIN_LOCATION_LENGTH;
    private final String TAG = "LocationEditTextPreference";
    private static final int DEFAULT_MIN_LOCATION_LENGTH = 2;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LocationEditText,
                0, 0);

        try {
            minLength = a.getInt(R.styleable.LocationEditText_minLength, DEFAULT_MIN_LOCATION_LENGTH);
            Log.d(TAG, "minLength = " + minLength);
        } finally {
            a.recycle();
        }
    }

    public LocationEditTextPreference(Context context) {
        this(context, null);
    }

    public void setMinLength(int length) {
        if (length < 6) {
            return;
        }
        minLength = length;
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        EditText editText = getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Dialog dialog = getDialog();
                if (dialog instanceof AlertDialog) {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    Button postiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    postiveButton.setEnabled(s.length() < DEFAULT_MIN_LOCATION_LENGTH ? false : true);
                }
            }
        });
    }
}
