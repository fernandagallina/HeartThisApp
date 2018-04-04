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
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private final IBinder musicBind = new MusicBinder();

    private MediaPlayer player = new MediaPlayer();
    private int resumePosition = 0;

    public void onCreate() {
        super.onCreate();
        initMusicPlayer();
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
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    private void initMusicPlayer() {
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
    }

    public void playMedia() {
        if(!player.isPlaying()) {
            player.start();
        }
    }

    public void pauseOrResumeMedia() {
        if(player.isPlaying()) {
            player.pause();
            resumePosition = player.getCurrentPosition();
        } else {
            player.seekTo(resumePosition);
            player.start();
        }

    }

    public void playSong(String song) {
        player.reset();
        Uri streamUri = Uri.parse(song);

        try {
            player.setDataSource(getApplicationContext(), streamUri);
        } catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.prepareAsync();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
