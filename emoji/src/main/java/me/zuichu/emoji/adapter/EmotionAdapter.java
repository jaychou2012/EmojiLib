package me.zuichu.emoji.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.zuichu.emoji.Emotion;
import me.zuichu.emoji.R;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.ViewHolder> {
    private onEmotionClick onEmotionClick;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tv_emotion;

        public ViewHolder(View v) {
            super(v);
            tv_emotion = (TextView) v.findViewById(R.id.tv_emotion);
        }
    }

    private ArrayList<Emotion> list;

    public EmotionAdapter(ArrayList<Emotion> data) {
        this.list = data;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Emotion emotion = list.get(position);
        holder.tv_emotion.setText(emotion.getSurrogates());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEmotionClick != null) {
                    onEmotionClick.onEmotionClick(emotion);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onEmotionClick != null) {
                    onEmotionClick.onEmotionLongClick(emotion);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() < 1) {
            return 0;
        }
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emoji, parent, false);
        return new ViewHolder(v);
    }

    public void setOnEmotionClickListener(onEmotionClick click) {
        this.onEmotionClick = click;
    }

    public interface onEmotionClick {
        void onEmotionClick(Emotion emotion);

        void onEmotionLongClick(Emotion emotion);
    }

}
