package com.example.administrator.ourdays;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    TextView begin_tx = null;
    TextView over_tx = null;
    TextView end_tx = null;
    TextView over_day_tx = null;
    TextView marry_days_tx = null;
    Timer timer = new Timer();
    MediaPlayer mediaPlayer = null;
    private static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1, "棉");
        map.put(2, "皮");
        map.put(3, "麦");
        map.put(4, "蜡");
        map.put(5, "木");
        map.put(6, "铜");
        map.put(7, "羊毛");
        map.put(8, "虞美人");
        map.put(9, "陶");
        map.put(10, "锡");
        map.put(11, "珊瑚");
        map.put(12, "丝");
        map.put(13, "铃兰");
        map.put(14, "铅");
        map.put(15, "水晶");
        map.put(16, "蓝宝石");
        map.put(17, "玫瑰");
        map.put(18, "绿松石");
        map.put(19, "印花");
        map.put(20, "瓷");
        map.put(21, "乳白石");
        map.put(22, "青铜");
        map.put(23, "绿玉");
        map.put(24, "萨丁");
        map.put(25, "银");
        map.put(26, "玉");
        map.put(27, "桃花心木");
        map.put(28, "镍");
        map.put(29, "绒");
        map.put(30, "珍珠");
        map.put(31, "羊皮");
        map.put(32, "紫铜");
        map.put(33, "斑岩");
        map.put(34, "琥珀");
        map.put(35, "琥珀");
        map.put(36, "梅斯林");
        map.put(37, "纸");
        map.put(38, "水银");
        map.put(39, "绉纱");
        map.put(40, "祖母绿");
        map.put(41, "铁");
        map.put(42, "珠质");
        map.put(43, "法兰绒");
        map.put(44, "黄玉");
        map.put(45, "朱红");
        map.put(46, "薰衣草");
        map.put(47, "开斯米");
        map.put(48, "紫晶");
        map.put(49, "雪松");
        map.put(50, "金");
        map.put(60, "砖石");
        map.put(70, "白金");
        map.put(75, "白石");
        map.put(80, "橡树");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Date now = new Date();
            Calendar ca = Calendar.getInstance();
            ca.set(2015,2,12,13,14,0);
            Date begin = ca.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.i("begin",sdf.format(begin));
            begin_tx.setText(sdf.format(begin));
            end_tx.setText(sdf.format(now));
            Log.i("now",sdf.format(now));
            String string = MyDateHelper.getDateDiff(begin);
            over_tx.setText(string);
            Log.i("time", string);
            over_day_tx.setText("小曦曦和大军军在一起：" + (now.getTime() - begin.getTime())/(1000*60*60*24) + "天");
            Calendar marryBegin = Calendar.getInstance();
            marryBegin.set(2018,7,20,0,0,0);;
            double yearDiff = MyDateHelper.getYearDiff(marryBegin.getTime(), now);
            marry_days_tx.setText("结婚：" + yearDiff + "年(" + getMarryType(yearDiff) + "婚)");
        }
    };

    private String getMarryType(Double years) {
        int intYears = years.intValue();
        if (map.containsKey(intYears)) {
            return map.get(intYears);
        }
        while (intYears > 0) {
            if (map.containsKey(intYears--)) {
                return map.get(intYears);
            }
        }
        return map.get(1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("onCreate", "begin:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin_tx = findViewById(R.id.beginTimeText);
        over_tx = findViewById(R.id.overtimeText);
        end_tx = findViewById(R.id.endTimeText);
        over_day_tx = findViewById(R.id.overDaysText);
        marry_days_tx = findViewById(R.id.marryDays);
        Thread musicThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    AssetManager am = getAssets();
                    AssetFileDescriptor afd = am.openFd("gb.mp3");
                    Log.i("Music", "" + afd.getLength());
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mediaPlayer.setLooping(true);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        musicThread.start();
        timer.schedule(task, 1000, 1000);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onStart() {
        Log.i("onStart", "begin");
        super.onStart();
//        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        Log.i("onStop", "begin");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.i("onResume", "begin");
        super.onResume();
//        mediaPlayer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("onKeyDown", "begin");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mediaPlayer.pause();
            finish();
//            mediaPlayer.release();
        }
        return false;
    }
}
