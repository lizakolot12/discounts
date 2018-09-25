package proj.kolot.com.discountatb.repository;


import java.util.List;

import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;

public interface Repository {

    void getProductByCategory(ProductCategory category, LoadDataCallback listener);



    interface LoadDataCallback {
        void onDataReceive(List<Product> list);

        void onError(String error);
    }
}



