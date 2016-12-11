package fernanda.heartthisapp.model.module;

import dagger.Module;
import dagger.Provides;
import fernanda.heartthisapp.model.CustomScope;
import fernanda.heartthisapp.ui.TrackView;

/**
 * Created by Fernanda on 08/12/2016.
 */

@Module
public class TrackFragmentModule {
    private final TrackView view;

    public TrackFragmentModule(TrackView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    TrackView providesTrackView() {
        return view;
    }
}
