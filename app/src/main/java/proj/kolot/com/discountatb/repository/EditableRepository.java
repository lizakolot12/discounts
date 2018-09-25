package proj.kolot.com.discountatb.repository;

import java.util.List;

import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;

public interface EditableRepository extends Repository {
    void saveData(ProductCategory category, List<Product> list, CompleteCallback completeCallback);

    void clear(ProductCategory category, CompleteCallback completeCallback);

    interface CompleteCallback {
        void onComplete(boolean success);
    }
}
