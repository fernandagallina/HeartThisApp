package fernanda.heartthisapp.model.component;

import android.app.Activity;

import dagger.Component;
import fernanda.heartthisapp.model.CustomScope;
import fernanda.heartthisapp.model.module.TrackFragmentModule;
import fernanda.heartthisapp.ui.TrackFragment;

/**
 * Created by Fernanda on 08/12/2016.
 */


@CustomScope
@Component(dependencies = NetComponent.class, modules = TrackFragmentModule.class)
public interface TrackComponent {

    void inject(TrackFragment fragment);

    void inject(Activity activity);
}
