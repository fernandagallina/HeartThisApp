package fernanda.heartthisapp.model.injection.module;

import dagger.Module;
import dagger.Provides;
import fernanda.heartthisapp.model.injection.CustomScope;
import fernanda.heartthisapp.presenter.ArtistContract;
import fernanda.heartthisapp.presenter.ArtistPresenter;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 06/12/2016.
 */

@Module
public class ArtistModule {
    private final ArtistContract.View view;

    public ArtistModule(ArtistContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    ArtistPresenter providesArtistPresenter(Retrofit retrofit) {
        return new ArtistPresenter(retrofit, view);
    }
}
