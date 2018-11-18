package me.zuichu.emoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import me.zuichu.emoji.adapter.EmotionListAdapter;

public class EmojiListView extends FrameLayout implements EmotionListAdapter.onEmotionClick {
    private View rootView;
    GridView gridView;
    private EmotionListAdapter adapter;
    private EmotionUtils utils;

    public EmojiListView(@NonNull Context context) {
        super(context);
        init();
    }

    public EmojiListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_emojilist, this);
        initView();
    }

    private void initView() {
        gridView = rootView.findViewById(R.id.gv);
        utils = new EmotionUtils(getContext());
        adapter = new EmotionListAdapter(getContext(), utils.getEmotionList());
        gridView.setAdapter(adapter);
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
