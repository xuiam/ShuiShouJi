package com.example.xjc.work1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Memo memo = null;
    private TextView tv_main_time;
    private TextView tv_main_start;
    private ListView lv_main;
    private List<Memo> mList;
    private Adapter_main mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mList = new ArrayList<>();
        mList.add(new Memo("近日，唐嫣过安检的画面引发争议。可以看到唐嫣穿着一件银色羽绒服，戴着帽子和墨镜，后来应海关人员要求摘掉了帽子，而摘帽子的同时唐嫣连忙捂住头发。此视频一公布，引发了很多网友的质疑：“她为什么过关时候不用脱去外套？”还有网友表示：“我怀着孕过安检的时候都被要求脱外套呢”“果然明星和我们这些平头老百姓待遇都不一样，墨镜和外套都不用脱。”“明星是有特权吗？”", "2018-9-10"));
        mList.add(new Memo("张大仙赔偿300万 一年内不得直播\n" +
                "王者荣耀主播张大仙与企鹅电竞的官司一审宣判：认定张大仙违约，要求其立即停止在企鹅电竞以外平台的主播活动，并向腾讯支付40余万元违约金及300万元赔偿金", "2018-11-11"));
        mAdapter = new Adapter_main(this, mList, new Adapter_main.CallBack() {
            @Override
            public void look(Memo memo) {
                MainActivity.memo = memo;
                startActivity(new Intent(MainActivity.this, LookActivity.class));
            }
        });
        lv_main.setAdapter(mAdapter);
        readMemo();
    }

    private void readMemo() {
        try {
            JSONArray ja = ConfigUtils.readMemo(this);
            JSONObject jo;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                mList.add(new Memo(jo.getString("title"), jo.getString("time")));
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tv_main_time = (TextView) findViewById(R.id.tv_main_time);
        tv_main_start = (TextView) findViewById(R.id.tv_main_start);
        lv_main = (ListView) findViewById(R.id.lv_main);
        tv_main_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WriteActivity.class));
                finish();
            }
        });
        tv_main_time.setText(ConfigUtils.getTime());
    }
}
