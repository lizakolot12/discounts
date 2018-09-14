package proj.kolot.com.discountatb;

import java.util.HashMap;
import java.util.Map;

import proj.kolot.com.discountatb.list.ProductListPresenter;
import proj.kolot.com.discountatb.list.ProductListView;
import proj.kolot.com.discountatb.main.CategoriesPresenter;
import proj.kolot.com.discountatb.main.CategoriesView;
import proj.kolot.com.discountatb.model.ProductCategory;


public class PresenterFactory {
    private Map<ProductCategory, ProductListPresenter> productListPresenters = new HashMap<>();
    private CategoriesPresenter categoriesPresenter;

    public ProductListPresenter getProductListPresenter(ProductCategory category, ProductListView view) {
        ProductListPresenter presenter = productListPresenters.get(category);
        if (presenter == null) {
            presenter = new ProductListPresenter(view, App.getInstance().getRepository());
            productListPresenters.put(category, presenter);
        } else {
            presenter.attachView(view);
        }
        return presenter;
    }


    public CategoriesPresenter getCategoriesPresenter(CategoriesView view) {
        if (categoriesPresenter == null) {
            categoriesPresenter = new CategoriesPresenter(view);
        } else {
            categoriesPresenter.attachView(view);
        }
        return categoriesPresenter;
    }
}
