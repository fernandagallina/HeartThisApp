package fernanda.heartthisapp;

import android.app.Application;

import fernanda.heartthisapp.model.component.DaggerNetComponent;
import fernanda.heartthisapp.model.component.NetComponent;
import fernanda.heartthisapp.model.module.AppModule;
import fernanda.heartthisapp.model.module.NetModule;

/**
 * Created by Fernanda on 06/12/2016.
 */

public class App extends Application {

    private static final String API_BASE_URL = "https://api-v2.hearthis.at/";
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(API_BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
