package com.example.stellarplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.stellarplayer.Model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView titleTv,currentTimeTv,totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay,nextBtn,previousBtn,musicCover;
    ArrayList<Song> songsList;
    Song currentSong;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);

        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicCover = findViewById(R.id.music_icon_big);

        titleTv.setSelected(true);

        songsList = (ArrayList<Song>) getIntent().getSerializableExtra("LIST");

        try {
            setResourcesWithMusic();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.pause);
                        musicCover.setRotation(0);
                    }else{
                        pausePlay.setImageResource(R.drawable.pausesong);
                        musicCover.setRotation(0);
                    }

                }
                new Handler().postDelayed(this,100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void setResourcesWithMusic() throws IOException {
        currentSong = songsList.get(MyMediaPlayer.currentIndex);

        titleTv.setText(currentSong.getTitle());

        totalTimeTv.setText(convertToMMSS(currentSong.getDuration()));

        // Get album cover art
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(currentSong.getPath());
        byte[] coverArt = retriever.getEmbeddedPicture();

        if (coverArt != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(coverArt, 0, coverArt.length);
            musicCover.setImageBitmap(bitmap);
        } else {
            musicCover.setImageResource(R.drawable.note_music); // Đặt ảnh mặc định nếu không có ảnh bìa album
        }

        retriever.release();

        pausePlay.setOnClickListener(v-> pausePlay());
        nextBtn.setOnClickListener(v-> {
            try {
                playNextSong();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        previousBtn.setOnClickListener(v-> {
            try {
                playPreviousSong();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        playMusic();
    }


    private void playMusic(){

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void playNextSong() throws IOException {
        if(MyMediaPlayer.currentIndex == songsList.size()-1)
            MyMediaPlayer.currentIndex = 0; // Reset to the first song if the last song is reached
        else
            MyMediaPlayer.currentIndex +=1;

        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void playPreviousSong() throws IOException {
        if(MyMediaPlayer.currentIndex== 0)
            return;
        MyMediaPlayer.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }


    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }


}
