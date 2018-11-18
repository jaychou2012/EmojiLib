package me.zuichu.emoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import me.zuichu.emoji.adapter.EmotionAdapter;

public class EmojiView extends FrameLayout implements EmotionAdapter.onEmotionClick {
    private View rootView;
    RecyclerView recyclerView;
    private EmotionAdapter adapter;
    private EmotionUtils utils;

    public EmojiView(@NonNull Context context) {
        super(context);
        init();
    }

    public EmojiView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_emoji, this);
        initView();
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.rv);
        utils = new EmotionUtils(getContext());
        adapter = new EmotionAdapter(utils.getEmotionList());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 10);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnEmotionClickListener(this);
    }

    @Override
    public void onEmotionClick(Emotion emotion) {
        showToast(emotion.getName());
    }

    public void showEmotionView(boolean show) {
        setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onEmotionLongClick(Emotion emotion) {
        showToast("长按" + emotion.getName());
    }

    private void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
    }
}
