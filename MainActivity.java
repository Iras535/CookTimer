package com.example.android.cooktimer;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    Button btnStart, btnStop;
    TextView txtTimerHour, txtTimerMinute, txtTimerSecond;
    LinearLayout linearLayout1, linearLayout2;
    Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        initUI();

        final Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                countDownStart();
            }
        });

    }

    @SuppressLint("SimpleDateFormat")
    private void initUI() {
        linearLayout1 = (LinearLayout) findViewById(R.id.ll1);
        linearLayout2 = (LinearLayout) findViewById(R.id.ll2);
        txtTimerHour = (TextView) findViewById(R.id.txtTimerHour);
        txtTimerMinute = (TextView) findViewById(R.id.txtTimerMinute);
        txtTimerSecond = (TextView) findViewById(R.id.txtTimerSecond);

    }

    // //////////////COUNT DOWN START/////////////////////////
    public void countDownStart() {

        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                String h = txtTimerHour.getText().toString();
                String m = txtTimerMinute.getText().toString();
                String s = txtTimerSecond.getText().toString();
                long hur = Long.valueOf(h);
                long min = Long.valueOf(m);
                long sec = Long.valueOf(s);
                long diff = hur + min + sec;
                try {


//                    String h = txtTimerHour.getText().toString();
//                    String m = txtTimerMinute.getText().toString();
//                    String s = txtTimerSecond.getText().toString();
//
//                    long hur = Long.valueOf(h);
//                    long min = Long.valueOf(m);
//                    long sec = Long.valueOf(s);
//                    long diff = hur + min + sec;

                    long hours = diff / (60 * 60 * 1000);
                    diff -= hours * (60 * 60 * 1000);
                    long minutes = diff / (60 * 1000);
                    diff -= minutes * (60 * 1000);
                    long seconds = diff / 1000;
                    txtTimerHour.setText("" + String.format("%02d", hours));
                    txtTimerMinute.setText("" + String.format("%02d", minutes));
                    txtTimerSecond.setText("" + String.format("%02d", seconds));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (diff <= 0) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    handler.removeCallbacks(runnable);
                    handler.removeMessages(0);
                }


                // handler.postDelayed(runnable, 0);
            }
        };


        ///COUNT DOWN END/////////
    }
}


