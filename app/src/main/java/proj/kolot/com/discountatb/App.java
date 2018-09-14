package proj.kolot.com.discountatb;

import android.app.Application;

import proj.kolot.com.discountatb.repository.GeneralRepository;
import proj.kolot.com.discountatb.repository.RepositoryFactory;
import proj.kolot.com.discountatb.repository.retrofit.ProductApi;
import proj.kolot.com.discountatb.repository.retrofit.RetrofitClientInstance;

public class App extends Application {
    private static App instance;
    private ProductApi productApi;
    private GeneralRepository repository;
    private PresenterFactory presenterFactory = new PresenterFactory();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        productApi = RetrofitClientInstance.getRetrofitInstance().create(ProductApi.class);
        repository = new GeneralRepository(RepositoryFactory.getLocalRepository(), RepositoryFactory.getRemoteRepository());
    }

    public ProductApi getProductApi() {
        return productApi;
    }

    public static App getInstance() {
        return instance;
    }

    public GeneralRepository getRepository() {
        return repository;
    }

    public PresenterFactory getPresenterFactory() {
        return presenterFactory;
    }

}
