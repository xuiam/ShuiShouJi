package com.example.xjc.work1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.xjc.work1.MainActivity.memo;

public class LookActivity extends AppCompatActivity {

    private TextView tv_look_time;
    private TextView tv_look_memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        initView();
    }

    private void initView() {
        tv_look_time = (TextView) findViewById(R.id.tv_look_time);
        tv_look_memo = (TextView) findViewById(R.id.tv_look_memo);
        if (memo != null) {
            tv_look_memo.setText(memo.getTitle());
            tv_look_time.setText(memo.getTime());
        }
    }
}
