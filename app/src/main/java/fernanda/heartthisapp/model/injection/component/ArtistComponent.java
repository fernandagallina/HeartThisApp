package fernanda.heartthisapp.model.injection.component;

import android.app.Activity;

import dagger.Component;
import fernanda.heartthisapp.model.injection.CustomScope;
import fernanda.heartthisapp.model.injection.module.ArtistModule;
import fernanda.heartthisapp.ui.ArtistFragment;

/**
 * Created by Fernanda on 06/12/2016.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = ArtistModule.class)
public interface ArtistComponent {

    void inject(ArtistFragment fragment);

    void inject(Activity activity);
}
