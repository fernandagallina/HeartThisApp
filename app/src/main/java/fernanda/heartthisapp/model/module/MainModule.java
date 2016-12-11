package fernanda.heartthisapp.model.module;

import dagger.Module;
import dagger.Provides;
import fernanda.heartthisapp.model.CustomScope;
import fernanda.heartthisapp.ui.MainView;

/**
 * Created by Fernanda on 06/12/2016.
 */

@Module
public class MainModule {

    private final MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MainView providesMainView() {
        return view;
    }
}
