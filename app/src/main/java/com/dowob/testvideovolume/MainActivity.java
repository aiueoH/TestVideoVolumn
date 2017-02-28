package com.dowob.testvideovolume;

import android.app.AlarmManager;
import android.content.Context;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
    private Button mPlayAlarmAndVideoButton;
    private Button mPrintSystemVolumeButton;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });
        mPlayAlarmAndVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
                playAlarm();
            }
        });
        mPrintSystemVolumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printSystemVolume();
            }
        });
    }

    private void printSystemVolume() {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int music = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        int alarm = am.getStreamVolume(AudioManager.STREAM_ALARM);
        int ring = am.getStreamVolume(AudioManager.STREAM_RING);
        String s;
        s = String.format("Music: %d", music);
        Log.d("MainActivity", s);
        s = String.format("alarm: %d", alarm);
        Log.d("MainActivity", s);
        s = String.format("ring: %d", ring);
        Log.d("MainActivity", s);
    }

    private void findViews() {
        mPlayButton = (Button) findViewById(R.id.button_1);
        mPlayAlarmAndVideoButton = (Button) findViewById(R.id.button_2);
        mPrintSystemVolumeButton = (Button)  findViewById(R.id.button_3);
        mVideoView = (VideoView) findViewById(R.id.videoView_1);
    }

    private void playAlarm() {
        String path = "android.resource://" + getPackageName() + File.separator + R.raw.rrr;
        Uri uri = Uri.parse(path);
        Context context = getApplicationContext();
        Ringtone r = RingtoneManager.getRingtone(context, uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                    .build();
            r.setAudioAttributes(aa);
        } else {
            r.setStreamType(AudioManager.STREAM_ALARM);
        }
        r.play();
    }

    private void playVideo() {
        String path = "android.resource://" + getPackageName() + File.separator + R.raw.rrr;
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(0f, 0f);
            }
        });
        mVideoView.setVideoPath(path);
        mVideoView.start();
    }
}
