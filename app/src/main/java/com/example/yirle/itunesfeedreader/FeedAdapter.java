package com.example.yirle.itunesfeedreader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedAdapter extends ArrayAdapter {
    private List<FeedEntry> list;
    private int resource;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    public FeedAdapter(@NonNull Context context, int resource, List<FeedEntry> list) {
        super(context, resource);
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FeedEntry currentApp = list.get(position);

        viewHolder.tvTitle.setText(currentApp.getTitle());
        viewHolder.tvSummary.setText(currentApp.getSummary());
        viewHolder.tvPrice.setText(currentApp.getTitle());
        viewHolder.tvRights.setText(currentApp.getRights());
        viewHolder.tvPrice.setText(currentApp.getIm_price());

        return convertView;
    }

    private class ViewHolder {
        private TextView tvTitle;
        private TextView tvSummary;
        private TextView tvPrice;
        private TextView tvRights;

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvSummary() {
            return tvSummary;
        }

        public TextView getTvPrice() {
            return tvPrice;
        }

        public TextView getTvRights() {
            return tvRights;
        }

        public ViewHolder(View v) {
            tvTitle = v.findViewById(R.id.tvTitle);
            tvSummary = v.findViewById(R.id.tvSummary);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvRights = v.findViewById(R.id.tvRights);
        }
    }
}
