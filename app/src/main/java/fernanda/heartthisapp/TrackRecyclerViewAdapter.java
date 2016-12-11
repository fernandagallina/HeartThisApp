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
import fernanda.heartthisapp.model.Track;

/**
 * Created by Fernanda on 08/12/2016.
 */

public class TrackRecyclerViewAdapter extends RecyclerView.Adapter<TrackRecyclerViewAdapter.TrackHolders>{
    private List<Track> trackList = new ArrayList<>();
    private Callback callback;
    private Context context;

    public TrackRecyclerViewAdapter(List<Track> track, Context context) {
        trackList = track;
        this.context = context;
    }

    @Override
    public TrackHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_track_item, parent, false);
        final TrackHolders trackHolders = new TrackHolders(view);
        trackHolders.contentLayoutTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null) {
                    callback.onItemClick(trackHolders.track);
                }
            }
        });
        return  trackHolders;
    }

    @Override
    public void onBindViewHolder(TrackHolders holder, int position) {
        holder.track = trackList.get(position);
        holder.trackName.setText(trackList.get(position).getTitle());
        holder.duration.setText(trackList.get(position).getDuration());
//        holder.trackDescription.setText(trackList.get(position).getDescription());
        holder.trackGenre.setText(trackList.get(position).getGenre());
        Picasso.with(context).load(trackList.get(position).getArtwork_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public static class TrackHolders extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout_track)
        public View contentLayoutTrack;

        public Track track;

        @BindView(R.id.track_image)
        public ImageView imageView;

        @BindView(R.id.track_name)
        public TextView trackName;

        @BindView(R.id.duration)
        public TextView duration;

//        @BindView(R.id.track_description)
//        public TextView trackDescription;

        @BindView(R.id.track_genre)
        public TextView trackGenre;


        public TrackHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback {
        void onItemClick(Track track);
    }
}


