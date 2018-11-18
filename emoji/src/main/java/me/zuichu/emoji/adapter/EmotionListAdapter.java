package me.zuichu.emoji.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.zuichu.emoji.Emotion;
import me.zuichu.emoji.R;

public class EmotionListAdapter extends BaseAdapter {
    private Context context;
    private onEmotionClick onEmotionClick;
    private ArrayList<Emotion> list;

    public EmotionListAdapter(Context context, ArrayList<Emotion> data) {
        this.context = context;
        this.list = data;
    }

    @Override
    public int getCount() {
        if (list == null || list.size() < 1) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (list == null || list.size() < 1) {
            return 0;
        }
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_emoji, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Emotion emotion = list.get(position);
        viewHolder.tv_emotion.setText(emotion.getSurrogates() + "\n" + emotion.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEmotionClick != null) {
                    onEmotionClick.onEmotionClick(emotion);
                }
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onEmotionClick != null) {
                    onEmotionClick.onEmotionLongClick(emotion);
                }
                return false;
            }
        });
        return convertView;
    }

    public void setOnEmotionClickListener(onEmotionClick click) {
        onEmotionClick = click;
    }

    public interface onEmotionClick {
        void onEmotionClick(Emotion emotion);

        void onEmotionLongClick(Emotion emotion);
    }

    class ViewHolder {
        public final TextView tv_emotion;

        public ViewHolder(View v) {
            tv_emotion = (TextView) v.findViewById(R.id.tv_emotion);
        }
    }
}
