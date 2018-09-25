package proj.kolot.com.discountatb.repository;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;


public class GeneralRepository implements EditableRepository {
    private static final String TAG = GeneralRepository.class.getName();

    private EditableRepository localRepository;
    private Repository remoteRepository;

    public GeneralRepository(EditableRepository localRepository, Repository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    private Map<ProductCategory, List<Product>> cache = new HashMap<>();


    @Override
    public void getProductByCategory(final ProductCategory category, final LoadDataCallback listener) {
        List<Product> result = cache.get(category);
        if (result != null) {
            Log.e(TAG, " get data from cache");
            listener.onDataReceive(result);
            return;
        }

        localRepository.getProductByCategory(category, new LoadDataCallback() {
            @Override
            public void onDataReceive(List<Product> list) {
                Log.e(TAG, " get data from local storage");
                cache.put(category, list);
                listener.onDataReceive(list);
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "get data from internet");
                getTasksFromRemoteDataSource(category, listener);
            }
        });
    }

    private void getTasksFromRemoteDataSource(final ProductCategory category, final LoadDataCallback listener) {
        remoteRepository.getProductByCategory(category, new LoadDataCallback() {
            @Override
            public void onDataReceive(final List<Product> list) {
                saveData(category, list, new CompleteCallback() {
                    @Override
                    public void onComplete(boolean success) {
                        listener.onDataReceive(list);
                    }
                });
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }


    @Override
    public void saveData(final ProductCategory category, List<Product> list, CompleteCallback completeCallback) {
        localRepository.saveData(category, list, completeCallback);
        cache.put(category, list);

    }

    @Override
    public void clear(ProductCategory category, CompleteCallback completeCallback) {
        cache = new HashMap<>();
        localRepository.clear(category, completeCallback);
    }
}
