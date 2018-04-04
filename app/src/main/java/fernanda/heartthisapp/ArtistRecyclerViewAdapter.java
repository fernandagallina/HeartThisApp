package fernanda.heartthisapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.presenter.ArtistPresenter;

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<ArtistRecyclerViewAdapter.ArtistItemViewHolder> {

    private final ArtistItemListener listener;
    private final Context context;

    private List<Artist> artists = new ArrayList<>();

    public interface ArtistItemListener {
        void onItemClick(Artist artist);
    }

    public ArtistRecyclerViewAdapter(ArtistPresenter presenter, Context context) {
        listener = presenter;
        this.context = context;
    }

    @Override
    public ArtistItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_artist, parent, false);

        return new ArtistItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistItemViewHolder holder, int position) {
        holder.onBind(artists.get(position), context, listener);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void setArtistList(List<Artist> artistList) {
        artists = artistList;
    }


    static class ArtistItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout)
        View contentLayout;
        @BindView(R.id.artist_image)
        ImageView imageView;
        @BindView(R.id.artist_name)
        TextView artistName;
        @BindView(R.id.followers)
        TextView followers;
        @BindView(R.id.numberOfTracks)
        TextView tracks_count;

        ArtistItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(final Artist artist, Context context, final ArtistItemListener listener) {
            artistName.setText(artist.user.username);
            tracks_count.setText(String.format("Tracks: %s", artist.numberOfTracks));
            followers.setText(String.format("Followers: %s", artist.numberOfFollowers));
            Picasso.with(context).load(artist.user.avatar_url).into(imageView);

            contentLayout.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(artist);
                }
            });
        }
    }
}
