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

/**
 * Created by Fernanda on 07/12/2016.
 */

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<ArtistRecyclerViewAdapter.ArtistsHolders>{

    private List<Artist> artistList = new ArrayList<>();
    private Callback callback;
    private Context context;

    public ArtistRecyclerViewAdapter(List<Artist> artists, Context context) {
        artistList = artists;
        this.context = context;
    }

    @Override
    public ArtistsHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artist_item, parent, false);
        final ArtistsHolders artistsHolders = new ArtistsHolders(view);
        artistsHolders.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null) {
                    callback.onItemClick(artistsHolders.artist);
                }
            }
        });
        return  artistsHolders;
    }

    @Override
    public void onBindViewHolder(ArtistsHolders holder, int position) {
        holder.artist = artistList.get(position);
        holder.artistName.setText(artistList.get(position).getUser().getUsername());
        holder.tracks_count.setText("Tracks: " + artistList.get(position).getTrack_count());
        holder.followers.setText("Followers: " + artistList.get(position).getFollowers_count());
        Picasso.with(context).load(artistList.get(position).getUser().getAvatar_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public static class ArtistsHolders extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout)
        public View contentLayout;

        public Artist artist;

        @BindView(R.id.artist_image)
        public ImageView imageView;

        @BindView(R.id.artist_name)
        public TextView artistName;

        @BindView(R.id.followers)
        public TextView followers;

        @BindView(R.id.track_count)
        public TextView tracks_count;

        public ArtistsHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback {
        void onItemClick(Artist artist);
    }
}
