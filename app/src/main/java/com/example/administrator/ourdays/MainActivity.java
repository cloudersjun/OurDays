package com.example.administrator.ourdays;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.EOFException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    TextView begin_tx =null;
    TextView over_tx =null;
    TextView end_tx = null;
    TextView over_day_tx = null;
    Timer timer = new Timer();
    MediaPlayer mediaPlayer = null;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
             super.handleMessage(msg);
             Date now= new Date();
             Calendar ca = Calendar.getInstance();
             ca.set(2015,2,12,13,14,0);
             Date begin = ca.getTime();
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Log.i("begin",sdf.format(begin));
             begin_tx.setText(sdf.format(begin));
             end_tx.setText(sdf.format(now));
             Log.i("now",sdf.format(now));
             String string = MyDatehelper.getDateDiff(begin);
//             String string = DateUtils.formatDateRange(getApplicationContext(),begin.getTime(),now.getTime(),DateUtils.FORMAT_ABBREV_ALL).toString();
             over_tx.setText(string);
             Log.i("time",string);
             over_day_tx.setText("小曦曦和大军军在一起："+(now.getTime() - begin.getTime())/(1000*60*60*24) + "天");
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("onCreate","begin:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin_tx = (TextView) findViewById(R.id.begintimeText);
        over_tx = (TextView) findViewById(R.id.overtimeText);
        end_tx = (TextView) findViewById(R.id.endtimeText);
        over_day_tx = (TextView) findViewById(R.id.overdayText);
        Thread musicThread = new Thread(new Runnable() {
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        musicThread.start();
        timer.schedule(task,1000,1000);
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onStart(){
        Log.i("onStart","begin");
        super.onStart();
//        mediaPlayer.start();
    }

    @Override
    protected void onStop(){
        Log.i("onStop","begin");
        super.onStop();
    }
    @Override
    protected void onResume(){
        Log.i("onResume","begin");
        super.onResume();
//        mediaPlayer.start();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        Log.i("onKeyDown","begin");
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            mediaPlayer.pause();
            finish();
//            mediaPlayer.release();
        }
        return false;
    }
}
