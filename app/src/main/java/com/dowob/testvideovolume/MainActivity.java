package com.dowob.testvideovolume;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
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
    }

    private void findViews() {
        mPlayButton = (Button) findViewById(R.id.button_1);
        mVideoView = (VideoView) findViewById(R.id.videoView_1);
    }

    private void playVideo() {
        String path = "android.resource://" + getPackageName() + File.separator + R.raw.rrr;
        mVideoView.setVideoPath(path);
        mVideoView.requestFocus();
        mVideoView.start();
    }
}
