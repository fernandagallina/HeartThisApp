package fernanda.heartthisapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fernanda.heartthisapp.App;
import fernanda.heartthisapp.ArtistRecyclerViewAdapter;
import fernanda.heartthisapp.R;
import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.injection.component.DaggerArtistComponent;
import fernanda.heartthisapp.model.injection.module.ArtistModule;
import fernanda.heartthisapp.presenter.ArtistContract;
import fernanda.heartthisapp.presenter.ArtistPresenter;

/**
 * Created by Fernanda on 07/12/2016.
 */

public class ArtistFragment extends Fragment implements ArtistContract.View {

    @Inject
    public ArtistPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ArtistRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.loadArtists();
    }

    @Override
    public void showArtists(List<Artist> artists) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new ArtistRecyclerViewAdapter(presenter, this.getContext());
        adapter.setArtistList(artists);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showArtistTracks(Artist artist) {
        TrackFragment trackFragment = TrackFragment.newInstance(artist);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, trackFragment, trackFragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(trackFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void updateItemList(int position) {
        adapter.notifyItemChanged(position);
    }

    private void getComponent() {
        DaggerArtistComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .artistModule(new ArtistModule(this))
                .build().inject(this);
    }
}
