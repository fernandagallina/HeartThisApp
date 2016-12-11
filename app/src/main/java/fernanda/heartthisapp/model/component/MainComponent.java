package fernanda.heartthisapp.model.component;

import android.app.Activity;

import dagger.Component;
import fernanda.heartthisapp.model.CustomScope;
import fernanda.heartthisapp.model.module.MainModule;
import fernanda.heartthisapp.ui.ArtistFragment;

/**
 * Created by Fernanda on 06/12/2016.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainModule.class) //dependencies = NetComponent.class,
public interface MainComponent {

    void inject(ArtistFragment fragment);

    void inject(Activity activity);
}
