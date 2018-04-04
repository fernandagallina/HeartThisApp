package fernanda.heartthisapp.model.injection.component;

import android.app.Activity;

import dagger.Component;
import fernanda.heartthisapp.model.injection.CustomScope;
import fernanda.heartthisapp.model.injection.module.TrackModule;
import fernanda.heartthisapp.ui.TrackFragment;

/**
 * Created by Fernanda on 08/12/2016.
 */


@CustomScope
@Component(dependencies = NetComponent.class, modules = TrackModule.class)
public interface TrackComponent {

    void inject(TrackFragment fragment);

    void inject(Activity activity);
}
