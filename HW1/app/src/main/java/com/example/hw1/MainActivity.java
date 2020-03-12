package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText initBill;
    TextView tenPTip, fifPTip, twenPTip, tenPTot,
            fifPTot, twenPTot, custTip, custTot, custTipDisplay;
    SeekBar tipProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBill = (EditText)findViewById(R.id.iniBill);
        tenPTip = (TextView)findViewById(R.id.tip10);
        fifPTip = (TextView)findViewById(R.id.tip15);
        twenPTip = (TextView)findViewById(R.id.tip20);
        tenPTot = (TextView)findViewById(R.id.tot10);
        fifPTot = (TextView)findViewById(R.id.tot15);
        twenPTot = (TextView)findViewById(R.id.tot20);
        custTipDisplay = (TextView)findViewById(R.id.tipPercentage);
        custTip = (TextView)findViewById(R.id.totaltip);
        custTot = (TextView)findViewById(R.id.totalprice);

        tipProgress = (SeekBar)findViewById(R.id.customseek);

        initBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    double bill = Double.parseDouble(initBill.getText().toString());
                    double tenPer = 1.10;
                    double fifPer = 1.15;
                    double twenPer = 1.20;

                    double tip10 = CalcTip(bill, tenPer);
                    double tip15 = CalcTip(bill, fifPer);
                    double tip20 = CalcTip(bill, twenPer);

                    double tot10 = CalcTot(bill, tenPer);
                    double tot15 = CalcTot(bill, fifPer);
                    double tot20 = CalcTot(bill, twenPer);

                    tenPTip.setText(String.format("%.2f", tip10));
                    fifPTip.setText(String.format("%.2f", tip15));
                    twenPTip.setText(String.format("%.2f", tip20));

                    tenPTot.setText(String.format("%.2f", tot10));
                    fifPTot.setText(String.format("%.2f", tot15));
                    twenPTot.setText(String.format("%.2f", tot20));

                    double tip = ((double) tipProgress.getProgress())/100 + 1.00;
                    double check = Double.parseDouble(initBill.getText().toString());
                    double customTip = CalcTip(check, tip);
                    custTip.setText(String.format("%.2f", customTip));

                    double customTotal = CalcTot(check, tip);
                    custTot.setText(String.format("%.2f", customTotal));

                }
                catch(NumberFormatException ex){
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    double bill = Double.parseDouble(initBill.getText().toString());
                    double tenPer = 1.10;
                    double fifPer = 1.15;
                    double twenPer = 1.20;

                    double tip10 = CalcTip(bill, tenPer);
                    double tip15 = CalcTip(bill, fifPer);
                    double tip20 = CalcTip(bill, twenPer);

                    double tot10 = CalcTot(bill, tenPer);
                    double tot15 = CalcTot(bill, fifPer);
                    double tot20 = CalcTot(bill, twenPer);

                    tenPTip.setText(String.format("%.2f", tip10));
                    fifPTip.setText(String.format("%.2f", tip15));
                    twenPTip.setText(String.format("%.2f", tip20));

                    tenPTot.setText(String.format("%.2f", tot10));
                    fifPTot.setText(String.format("%.2f", tot15));
                    twenPTot.setText(String.format("%.2f", tot20));

                    double tip = ((double) tipProgress.getProgress())/100 + 1.00;
                    double check = Double.parseDouble(initBill.getText().toString());
                    double customTip = CalcTip(check, tip);
                    custTip.setText(String.format("%.2f", customTip));

                    double customTotal = CalcTot(check, tip);
                    custTot.setText(String.format("%.2f", customTotal));

                }
                catch(NumberFormatException ex){
                    return;
                }

            }
        });

        tipProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int percent = seekBar.getProgress();
                custTipDisplay.setText(percent + "%");



                try{
                    double tip = ((double) tipProgress.getProgress())/100 + 1.00;
                    double check = Double.parseDouble(initBill.getText().toString());
                    double customTip = CalcTip(check, tip);
                    custTip.setText(String.format("%.2f", customTip));

                    double customTotal = CalcTot(check, tip);
                    custTot.setText(String.format("%.2f", customTotal));

                }

                catch(NumberFormatException ex){
                    return;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int percent = seekBar.getProgress();
                custTipDisplay.setText(percent + "%");

                double tip = ((double) tipProgress.getProgress())/100 + 1.00;

                try{
                    double check = Double.parseDouble(initBill.getText().toString());
                    double customTip = CalcTip(check, tip);
                    custTip.setText(String.format("%.2f", customTip));

                    double customTotal = CalcTot(check, tip);
                    custTot.setText(String.format("%.2f", customTotal));

                }

                catch(NumberFormatException ex){
                    return;
                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int percent = seekBar.getProgress();
                custTipDisplay.setText(percent + "%");

                double tip = ((double) tipProgress.getProgress())/100 + 1.00;

                try{
                    double check = Double.parseDouble(initBill.getText().toString());
                    double customTip = CalcTip(check, tip);
                    custTip.setText(String.format("%.2f", customTip));

                    double customTotal = CalcTot(check, tip);
                    custTot.setText(String.format("%.2f", customTotal));

                }

                catch(NumberFormatException ex){
                    return;
                }

            }
        });
    }//end OnCreate

    public double CalcTip(double price, double tip){
        double total;
        double tipPrice;


        total = (price * tip);
        tipPrice = (total - price);

        return tipPrice;




    }

    public double CalcTot(double price, double percent){

        double total;

        total = price * percent;

        return total;

    }

}
