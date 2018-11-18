package me.zuichu.emojilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.zuichu.emoji.EmojiView;

public class MainActivity extends AppCompatActivity {
    private EmojiView ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ev = findViewById(R.id.ev);
    }
}
