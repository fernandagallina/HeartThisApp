package fernanda.heartthisapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fernanda.heartthisapp.model.MusicService;
import fernanda.heartthisapp.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.playButton)
    Button play;

    @BindView(R.id.pauseButton)
    Button pause;

    private Intent playIntent;
    private boolean musicBound = false;
    MusicService musicService = new MusicService();

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
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    public void replaceFragments(String permalink) {

        if(!isFinishing()) {

            TrackFragment fragment = new TrackFragment();
            fragment.setPermalink(permalink);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_artists, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

   private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    public void playSong(String stream_url) {
        play.setVisibility(View.VISIBLE);
        pause.setVisibility(View.VISIBLE);
        musicService.playSong(stream_url);
    }

    @OnClick(R.id.playButton)
    public void playMedia() {
        musicService.playMedia();
    }

    @OnClick(R.id.pauseButton)
    public void pauseOrResumeMedia() {
        musicService.pauseOrResumeMedia();
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicService = null;
        super.onDestroy();
    }
}
