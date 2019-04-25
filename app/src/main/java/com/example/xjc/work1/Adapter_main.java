package com.example.xjc.work1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Adapter_main extends BaseAdapter {

    private List<Memo> objects;

    private Context context;
    private CallBack callBack;
    private LayoutInflater layoutInflater;

    public Adapter_main(Context context, List<Memo> objects, CallBack callBack) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Memo getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_main, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Memo) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final Memo object, ViewHolder holder) {
        Log.d("TAG", "initializeViews: " + object.getTitle());
        holder.tvItemMainTitle.setText(object.getTitle());
        holder.tvItemMainTime.setText(object.getTime());
        holder.ll_item_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.look(object);
            }
        });
    }

    public interface CallBack {
        void look(Memo memo);
    }

    protected class ViewHolder {
        private TextView tvItemMainTitle;
        private TextView tvItemMainTime;
        private LinearLayout ll_item_main;

        public ViewHolder(View view) {
            tvItemMainTitle = (TextView) view.findViewById(R.id.tv_item_main_title);
            tvItemMainTime = (TextView) view.findViewById(R.id.tv_item_main_time);
            ll_item_main = view.findViewById(R.id.ll_item_main);
        }
    }
}
