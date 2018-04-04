package fernanda.heartthisapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Artist implements Parcelable {

    @SerializedName("track_count")
    public String numberOfTracks;
    @SerializedName("followers_count")
    public String numberOfFollowers;
    public User user;

    protected Artist(Parcel in) {
        numberOfTracks = in.readString();
        numberOfFollowers = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numberOfTracks);
        dest.writeString(numberOfFollowers);
    }
}
