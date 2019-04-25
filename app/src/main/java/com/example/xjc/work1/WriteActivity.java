package com.example.xjc.work1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_write_memo;
    private Button btn_write_bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
    }

    private void initView() {
        et_write_memo = (EditText) findViewById(R.id.et_write_memo);
        btn_write_bc = (Button) findViewById(R.id.btn_write_bc);

        btn_write_bc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write_bc:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String memo = et_write_memo.getText().toString().trim();
        if (TextUtils.isEmpty(memo)) {
            return;
        }

        // TODO validate success, do something
        ConfigUtils.saveMemo(this, memo, ConfigUtils.getTime());
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(WriteActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(WriteActivity.this, MainActivity.class));
        finish();
    }
}
