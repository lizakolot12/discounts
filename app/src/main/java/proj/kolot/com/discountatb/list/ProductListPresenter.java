package proj.kolot.com.discountatb.list;


import java.util.List;

import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;
import proj.kolot.com.discountatb.repository.Repository;

public class ProductListPresenter {

    private final Repository repository;
    private ProductListView productListView;
    private ProductCategory category;
    private boolean loading = false;
    private List<Product> list;

    public ProductListPresenter(ProductListView productListView, Repository repository) {
        this.productListView = productListView;
        this.repository = repository;
    }

    public void start(ProductCategory category) {
        this.category = category;
        showData();
    }

    public void update() {
        loading = true;
        list = null;
        repository.refresh(category, getRepositoryCallback());
    }

    private Repository.LoadDataCallback getRepositoryCallback() {
        return new Repository.LoadDataCallback() {
            @Override
            public void onDataReceive(List<Product> result) {
                list = result;
                loading = false;
                showProducts();
            }

            @Override
            public void onError(String error) {
                list = null;
                loading = false;
                showError(error);
            }
        };
    }

    private void showData() {
        if (list == null) {
            if (!loading) {
                showLoading();
                loading = true;
                repository.getProductByCategory(category, getRepositoryCallback());
            } else {
                showLoading();
            }
        } else {
            showProducts();
        }
    }


    private void showLoading() {
        if (productListView != null) {
            productListView.hideData();
            productListView.showProgress();
        }
    }

    private void showProducts() {
        if (productListView != null) {
            productListView.hideProgress();
            productListView.showData(list);
        }
    }

    private void showError(String error) {
        if (productListView != null) {
            productListView.hideProgress();
            productListView.showError(error);
        }
    }


    public void attachView(ProductListView productListView) {
        this.productListView = productListView;

    }

    public void detachView() {
        this.productListView = null;
    }
}
