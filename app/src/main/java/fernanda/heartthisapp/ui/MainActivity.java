package fernanda.heartthisapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fernanda.heartthisapp.R;
import fernanda.heartthisapp.model.MusicService;

public class MainActivity extends AppCompatActivity {

    @BindDrawable(R.drawable.ic_play_arrow_black_24dp)
    Drawable playImage;
    @BindDrawable(R.drawable.ic_pause_black_24dp)
    Drawable pauseImage;


    private Intent playIntent;
    MusicService musicService = new MusicService();
    boolean isPlaying;
    private MenuItem playItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new ArtistFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        playItem = menu.findItem(R.id.actionPlay);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionPlay) {
            isPlaying = !isPlaying;
            if (isPlaying) {
                playItem.setIcon(pauseImage);
//                play.setBackground(playImage);
            } else {
                playItem.setIcon(playImage);
//                play.setBackground(pauseImage);
            }

            musicService.pauseOrResumeMedia();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicService = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

   private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void playSong(String stream_url) {
        playItem.setIcon(pauseImage);
        musicService.playSong(stream_url);
    }
}
