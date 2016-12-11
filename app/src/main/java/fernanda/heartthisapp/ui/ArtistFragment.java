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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fernanda.heartthisapp.App;
import fernanda.heartthisapp.ArtistRecyclerViewAdapter;
import fernanda.heartthisapp.R;
import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.component.DaggerMainComponent;
import fernanda.heartthisapp.model.module.MainModule;
import fernanda.heartthisapp.presenter.MainPresenterImpl;

/**
 * Created by Fernanda on 07/12/2016.
 */

public class ArtistFragment extends Fragment implements MainView{

    @Inject
    MainPresenterImpl presenter;

    ArtistRecyclerViewAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<Artist> artistList;
    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);

        artistList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
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

        presenter.loadArtists();
    }

    @Override
    public void showArtitsts(List<Artist> artists) {
        for(int i = 0; i < artists.size(); i++) {
            artistList.add(artists.get(i));
        }

        adapter = new ArtistRecyclerViewAdapter(artistList, this.getContext());
        adapter.setArtistList(artistList);
        adapter.setCallback(new ArtistRecyclerViewAdapter.Callback() {
            @Override
            public void onItemClick(Artist artist) {
                String permalink = artist.getUser().getPermalink();
                ((MainActivity)getActivity()).replaceFragments(permalink);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
