package proj.kolot.com.discountatb.repository;

import java.util.List;

import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;
import proj.kolot.com.discountatb.repository.db.ProductDao;
import proj.kolot.com.discountatb.util.AppExecutors;


public class LocalRepository implements EditableRepository {

    private ProductDao productDao;
    private AppExecutors appExecutors;


    public LocalRepository(ProductDao productDao, AppExecutors executors) {
        this.productDao = productDao;
        this.appExecutors = executors;

    }

    @Override
    public void getProductByCategory(final ProductCategory category, final LoadDataCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Product> list = productDao.getAllProductsByCategory(category.getValue());
                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (list.isEmpty()) {
                            callback.onError("");
                        } else {
                            callback.onDataReceive(list);
                        }
                    }
                });
            }
        };

        appExecutors.getDiskIO().execute(runnable);

    }

    @Override
    public void saveData(final ProductCategory category, final List<Product> list, final CompleteCallback completeCallback) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                for (Product pr : list) {
                    pr.setCategory(category.getValue());
                }
                productDao.insert(list);
                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        completeCallback.onComplete(true);
                    }
                });
            }
        };
        appExecutors.getDiskIO().execute(saveRunnable);
    }

    @Override
    public void clear(final ProductCategory category, final CompleteCallback completeCallback) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                productDao.deleteByCategory(category.getValue());
                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        completeCallback.onComplete(true);
                    }
                });
            }
        };
        appExecutors.getDiskIO().execute(deleteRunnable);

    }
}
