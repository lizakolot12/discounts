package proj.kolot.com.discountatb.list;

import java.util.List;

import proj.kolot.com.discountatb.model.Product;


public interface ProductListView {
    void showError(String error);
    void showProgress();
    void hideProgress();
    void showData(List<Product> list);
    void hideData();
    void setPresenter(ProductListPresenter presenter);
}
