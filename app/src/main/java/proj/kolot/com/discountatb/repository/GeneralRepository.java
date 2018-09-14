package proj.kolot.com.discountatb.repository;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;


public class GeneralRepository implements Repository {
    private static final String TAG = GeneralRepository.class.getName();

    private Repository localRepository;
    private Repository remoteRepository;

    public GeneralRepository(Repository localRepository, Repository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    private Map<ProductCategory, List<Product>> cache = new HashMap<>();


    @Override
    public void getProductByCategory(final ProductCategory category, final LoadDataCallback listener) {
             List<Product> result = cache.get(category);
        if (result != null) {
            Log.i(TAG, " get data from cache");
            listener.onDataReceive(result);
            return;
        }

        localRepository.getProductByCategory(category, new LoadDataCallback() {
            @Override
            public void onDataReceive(List<Product> list) {
                Log.i(TAG, " get data from local storage");
                cache.put(category, list);
                listener.onDataReceive(list);
            }

            @Override
            public void onError(String error) {
                Log.i(TAG, "get data from internet");
                getTasksFromRemoteDataSource(category, listener);
            }
        });
    }
    @Override
    public void refresh(final ProductCategory category, final LoadDataCallback listener) {
        remoteRepository.getProductByCategory(category, new LoadDataCallback() {
            @Override
            public void onDataReceive(List<Product>result) {
                localRepository.updateRepository(category, result);
                cache.put(category, result);
                listener.onDataReceive(result);
            }

            @Override
            public void onError(String error) {
                cache = new HashMap<>();
                listener.onError(error);
            }
        });
    }

    private void getTasksFromRemoteDataSource(final ProductCategory category, final LoadDataCallback listener) {
        remoteRepository.getProductByCategory(category, new LoadDataCallback() {
            @Override
            public void onDataReceive(List<Product> list) {
                cache.put(category, list);
                localRepository.updateRepository(category, list);
                listener.onDataReceive(list);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void updateRepository(final ProductCategory category, List<Product> l) {
        cache = new HashMap<>();
        remoteRepository.getProductByCategory(category, new LoadDataCallback() {
            @Override
            public void onDataReceive(List<Product> result) {
                localRepository.updateRepository(category, result);
                cache.put(category, result);
            }

            @Override
            public void onError(String error) {
                cache = new HashMap<>();
            }
        });
    }
}
