package com.hemangkumar.cagrcalculator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Hemang on 14/06/16.
 */
public class ConvFrag extends Fragment {

    EditText months;
    EditText days;

    TextView res1;
    TextView res2;
    Button convertMonths;
    Button convertDays;
    public ConvFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.conv_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        months = (EditText) getView().findViewById(R.id.editText4);
        days = (EditText) getView().findViewById(R.id.editText5);

        convertMonths = (Button) getView().findViewById(R.id.button2);
        convertDays = (Button) getView().findViewById(R.id.button3);

        res1 = (TextView) getView().findViewById(R.id.textView5);
        res2 = (TextView) getView().findViewById(R.id.textView7);

        convertMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res1.setText("YEARS = " + String.valueOf(Float.parseFloat(months.getText().toString())/12));

            }
        });

        convertDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float val = Float.parseFloat(days.getText().toString());
                res2.setText("YEARS = " + String.valueOf(val/365));
            }
        });

    }
}
