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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fernanda.heartthisapp.App;
import fernanda.heartthisapp.R;
import fernanda.heartthisapp.TrackRecyclerViewAdapter;
import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.Track;
import fernanda.heartthisapp.model.injection.component.DaggerTrackComponent;
import fernanda.heartthisapp.model.injection.module.TrackModule;
import fernanda.heartthisapp.presenter.TrackContract;
import fernanda.heartthisapp.presenter.TrackPresenter;

public class TrackFragment extends Fragment implements TrackContract.View {

    private static final String ARTIST_OBJECT = "ARTIST_OBJECT";

    @Inject
    TrackPresenter presenter;

    @BindView(R.id.recycler_view_track)
    RecyclerView recyclerView;

    private TrackRecyclerViewAdapter adapter;

    public static TrackFragment newInstance(Artist artist) {
        TrackFragment fragment = new TrackFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARTIST_OBJECT, artist);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent();
        readExtras();
    }

    private void readExtras() {
        if (getArguments().getParcelable(ARTIST_OBJECT) != null) {
            presenter.setArtist(getArguments().getParcelable(ARTIST_OBJECT));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracks, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadTrack();
    }

    @Override
    public void showTracks(List<Track> tracks) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new TrackRecyclerViewAdapter(presenter, this.getContext());
        adapter.setTrackList(tracks);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void playTrack(String stream_url) {
        ((MainActivity) getContext()).playSong(stream_url);
    }

    public void getComponent() {
        DaggerTrackComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .trackModule(new TrackModule(this))
                .build().inject(this);
    }
}
