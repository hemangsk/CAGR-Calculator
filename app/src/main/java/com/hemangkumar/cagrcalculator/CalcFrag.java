package com.hemangkumar.cagrcalculator;


import android.content.Context;
import android.graphics.Typeface;
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
import android.widget.Toast;

/**
 * Created by Hemang on 14/06/16.
 */
public class CalcFrag extends Fragment {
    EditText pv;
    EditText fv;
    EditText y;
    EditText c;
    Float presVal, futVal, years, cagr;

    TextView result;

    Double res;



    public CalcFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calc_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pv = (EditText) getView().findViewById(R.id.editText);
        fv = (EditText) getView().findViewById(R.id.editText2);
        y = (EditText) getView().findViewById(R.id.editText3);
        c = (EditText) getView().findViewById(R.id.editText4);

        final Button calculate = (Button) getView().findViewById(R.id.button);

        result = (TextView) getView().findViewById(R.id.textView3);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTypefaces();
                getValues();


                if(presVal != null && futVal!= null && years!=null){
                    calculateCagr();
                    roundResult();
                    c.setText(String.valueOf(res) + " %");
                    c.setTypeface(c.getTypeface(), Typeface.BOLD);

                }

                else if(presVal!=null && futVal!=null && cagr!=null){
                    calculateYears();
                    roundResult();
                    y.setText(String.valueOf(res) + " years");
                    y.setTypeface(c.getTypeface(), Typeface.BOLD);

                }

                else if(presVal!=null && years!=null && cagr!=null){
                    calculateFuture();
                    roundResult();
                    fv.setText(String.valueOf(res) + " Rs");
                    fv.setTypeface(c.getTypeface(), Typeface.BOLD);
                }

                else if(years!=null && futVal !=null && cagr!=null){
                    calculatePresent();
                    roundResult();
                    pv.setText(String.valueOf(res) + " Rs");
                    pv.setTypeface(c.getTypeface(), Typeface.BOLD);
                }

                else {
                    Context context = getActivity();
                 Toast.makeText(context, "Please recheck the entered values! 3 out of 4 fields should be filled.", Toast.LENGTH_LONG).show();
                }

                //result.setText(String.valueOf(res*100) + "%");
                refreshValues();
            }
        });
    }

    public void roundResult(){
        Log.e("RESULT RESULT RESULT", String.valueOf(res));
        res = Math.round(res * 100.0) / 100.0;
    }

    public void getValues(){

        String pS,fS,yS,cS;
        CharSequence rs = "Rs";
        CharSequence yr = "years";
        CharSequence per = "%";
        pS = pv.getText().toString().trim();

        fS = fv.getText().toString().trim();
        yS = y.getText().toString().trim();

        if(pS.contains(rs) || fS.contains(rs)){
            Log.e("First Trimmer", "");
            pS = pS.split("Rs")[0].trim();
            fS = fS.split("Rs")[0].trim();
        }
        if(yS.contains(yr)){
            Log.e("Sec Trimmer", "");
            yS = yS.split("years")[0].trim();
        }
        cS = c.getText().toString().trim();

        if(cS.contains(per)){
            Log.e("Third Trimmer", "");
            cS = cS.split("%")[0].trim();
        }
        try {
            if (!pS.equals("")) {
                presVal = Float.parseFloat(pS);
            }

            if (!cS.equals("")) {
                cagr = Float.parseFloat(cS);

            }

            if (!yS.equals("")) {
                years = Float.parseFloat(yS);
            }

            if (!fS.equals("")) {
                futVal = Float.parseFloat(fS);
            }
        }catch (NumberFormatException n){
            n.printStackTrace();
            Context context = getActivity();
            Toast.makeText(context, "Please enter correct values in fields", Toast.LENGTH_LONG).show();
        }


        Log.e("CAGR", String.valueOf(cagr));
    }

    public void calculateCagr(){
        Log.e("h",String.valueOf(Math.pow(presVal / futVal, 1.0 / years)));
        res = (Math.pow(futVal / presVal, 1.0 / years) - 1.0 ) * 100;
        Log.e("h2",String.valueOf(res));
    }

    public void calculateYears(){
        try {
            res = Math.log(futVal / presVal) / Math.log((cagr + 100)/100);
        }catch (Exception e){
            e.printStackTrace();
            Context context = getActivity();
            Toast.makeText(context, "There is a calculation error! Please recheck the entered values", Toast.LENGTH_LONG).show();
        }
    }

    public void calculateFuture(){
        try {
            res = presVal * (Math.exp(years* Math.log((cagr + 100)/100)));
        }catch (Exception e){
            e.printStackTrace();
            Context context = getActivity();
            Toast.makeText(context, "There is a calculation error! Please recheck the entered values", Toast.LENGTH_LONG).show();
        }
    }

    public void calculatePresent(){
        try {
            res = futVal / Math.exp(years* Math.log((cagr +100)/100));
        }catch (Exception e){
            e.printStackTrace();
            Context context = getActivity();
            Toast.makeText(context, "There is a calculation error! Please recheck the entered values. Please do not enter UNITS.", Toast.LENGTH_LONG).show();
        }
    }

    public void refreshValues(){
        futVal = null;
        presVal = null;
        years = null;
        cagr = null;
    }

    public void resetTypefaces(){
        c.setTypeface(null,Typeface.NORMAL);
        pv.setTypeface(null, Typeface.NORMAL);
        fv.setTypeface(null, Typeface.NORMAL);
        y.setTypeface(null, Typeface.NORMAL);

    }
}
