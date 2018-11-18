package me.zuichu.emoji;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmotionUtils {
    private Context context;
    private SharedPreferences sp;
    private static String emotionString = "";
    private ArrayList<Emotion> list;

    public EmotionUtils(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("emotion", Context.MODE_PRIVATE);
        initEmotionList();
    }

    private void initEmotionList() {
        list = new ArrayList<>();
        Resources res = context.getResources();
        String[] city = res.getStringArray(R.array.unicode);
        for (int i = 0; i < city.length; i++) {
            list.add(new Emotion.Builder().
                    name("smile")
                    .unicode(city[i])
                    .surrogates(getSurrogates(city[i]))
                    .type("1").build());
        }
    }

    public ArrayList<Emotion> getEmotionList() {
        return list;
    }

    public String getSurrogates(String string) {
        emotionString = new String(Character.toChars(Integer.parseInt(string, 16)));
        return emotionString;
    }

    public ArrayList<Emotion> getListByType(String type) {
        ArrayList<Emotion> emotions = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            emotions.add(list.get(i));
        }
        return emotions;
    }

    public ArrayList<Emotion> putEmotionClick(Emotion emotion) {
        String emotions = sp.getString("recent", "[]");
        ArrayList<Emotion> emotionlist = new ArrayList<>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(emotions);
            JSONObject object = new JSONObject();
            object.put("name", emotion.getName());
            object.put("surrogates", emotion.getSurrogates());
            object.put("type", emotion.getType());
            object.put("unicode", emotion.getUnicode());
            jsonArray.put(0, object);
            sp.edit().putString("recent", jsonArray.toString()).commit();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject emotionObject = jsonArray.getJSONObject(i);
                emotionlist.add(new Emotion.Builder()
                        .unicode(emotionObject.getString("unicode"))
                        .surrogates(emotionObject.getString("surrogates"))
                        .type(emotionObject.getString("type"))
                        .name(emotionObject.getString("name")).build());
            }
            return emotionlist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emotionlist;
    }

    public ArrayList<Emotion> getRecentList() {
        ArrayList<Emotion> emotionlist = new ArrayList<>();
        String emotions = sp.getString("recent", "[]");
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(emotions);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                emotionlist.add(new Emotion.Builder()
                        .unicode(object.getString("unicode"))
                        .surrogates(object.getString("surrogates"))
                        .type(object.getString("type"))
                        .name(object.getString("name")).build());
            }
            return emotionlist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emotionlist;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
