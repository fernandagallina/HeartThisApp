package fernanda.heartthisapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fernanda.heartthisapp.App;
import fernanda.heartthisapp.R;
import fernanda.heartthisapp.TrackRecyclerViewAdapter;
import fernanda.heartthisapp.model.Track;
import fernanda.heartthisapp.model.component.DaggerTrackComponent;
import fernanda.heartthisapp.model.module.TrackFragmentModule;
import fernanda.heartthisapp.presenter.TrackPresenterImpl;

/**
 * Created by Fernanda on 08/12/2016.
 */

public class TrackFragment extends Fragment implements TrackView {

    @Inject
    TrackPresenterImpl presenter;

    String permalink;

    TrackRecyclerViewAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<Track> artistTrackList;
    private Unbinder unbinder;

    @BindView(R.id.recycler_view_track)
    RecyclerView recyclerView;

    @BindView(R.id.track_list_title)
    TextView trackTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            permalink = savedInstanceState.getString("permalink");
        }
        DaggerTrackComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .trackFragmentModule(new TrackFragmentModule(this))
                .build().inject(this);

        artistTrackList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracks, container, false);
        unbinder = ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trackTitle.setText(permalink+" tracks");
        presenter.loadTrack(permalink);
    }

    @Override
    public void showTracks(List<Track> tracks) {
        for(int i = 0; i < tracks.size(); i++) {
            artistTrackList.add(tracks.get(i));
        }

        adapter = new TrackRecyclerViewAdapter(artistTrackList, this.getContext());
        adapter.setTrackList(artistTrackList);
        adapter.setCallback(new TrackRecyclerViewAdapter.Callback() {
            @Override
            public void onItemClick(Track track) {
                ((MainActivity)getContext()).playSong(track.getStream_url());
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("permalink", permalink);
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

}
