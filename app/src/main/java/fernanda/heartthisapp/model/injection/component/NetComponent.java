package fernanda.heartthisapp.model.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import fernanda.heartthisapp.ui.MainActivity;
import fernanda.heartthisapp.model.injection.module.AppModule;
import fernanda.heartthisapp.model.injection.module.NetModule;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 06/12/2016.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();

    void inject(MainActivity activity);
}
