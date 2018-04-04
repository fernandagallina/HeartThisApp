package fernanda.heartthisapp.model.injection.module;

import dagger.Module;
import dagger.Provides;
import fernanda.heartthisapp.model.injection.CustomScope;
import fernanda.heartthisapp.presenter.TrackContract;

/**
 * Created by Fernanda on 08/12/2016.
 */

@Module
public class TrackModule {
    private final TrackContract.View view;

    public TrackModule(TrackContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    TrackContract.View providesTrackView() {
        return view;
    }
}
