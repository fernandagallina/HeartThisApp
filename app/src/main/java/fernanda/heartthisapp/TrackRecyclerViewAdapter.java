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
import fernanda.heartthisapp.presenter.TrackPresenter;

public class TrackRecyclerViewAdapter extends RecyclerView.Adapter<TrackRecyclerViewAdapter.TrackItemViewHolder> {

    private final TrackItemListener listener;
    private final Context context;

    private List<Track> tracks = new ArrayList<>();

    public interface TrackItemListener {
        void onItemClick(Track track);
    }

    public TrackRecyclerViewAdapter(TrackPresenter presenter, Context context) {
        listener = presenter;
        this.context = context;
    }

    @Override
    public TrackItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_track, parent, false);

        return new TrackItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackItemViewHolder holder, int position) {
        holder.onBind(tracks.get(position), context, listener);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }
    public void setTrackList(List<Track> trackList) {
        tracks = trackList;
    }


    public static class TrackItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout_track)
        View contentLayoutTrack;
        @BindView(R.id.track_image)
        ImageView imageView;
        @BindView(R.id.track_name)
        TextView trackName;
        @BindView(R.id.duration)
        TextView duration;
        @BindView(R.id.track_genre)
        TextView trackGenre;

        TrackItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(Track track, Context context, TrackItemListener listener) {
            trackName.setText(track.title);
            duration.setText(track.getDuration());
            trackGenre.setText(track.genre);
            Picasso.with(context).load(track.artwork_url).into(imageView);
            contentLayoutTrack.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(track);
                }
            });
        }
    }
}


