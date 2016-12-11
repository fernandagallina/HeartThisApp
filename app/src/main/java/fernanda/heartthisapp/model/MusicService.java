package fernanda.heartthisapp.model;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Fernanda on 10/12/2016.
 */
public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener{

    //media player
    private MediaPlayer player;
    //song list
    private ArrayList<Track> songs;
    //current position
    private int songPosn;
    private final IBinder musicBind = new MusicBinder();

    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        initMusicPlayer();
    }

    public void initMusicPlayer() {
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    public void setList(ArrayList<Track> tracks) {
        songs = tracks;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
    }

    public void playMedia() {
        if(!player.isPlaying()) {
            player.start();
        }
    }

    int resumePosition;
    public void pauseOrResumeMedia() {
        if(player.isPlaying()) {
            player.pause();
            resumePosition = player.getCurrentPosition();
        } else {
            player.seekTo(resumePosition);
            player.start();
        }

    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void playSong(String song) {
        player.reset();
        Uri streamUri = Uri.parse(song);

        try {
            player.setDataSource(getApplicationContext(), streamUri);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.prepareAsync();
    }
}
