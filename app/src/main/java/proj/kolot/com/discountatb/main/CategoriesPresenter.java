package proj.kolot.com.discountatb.main;


import java.util.List;

import proj.kolot.com.discountatb.model.Categories;
import proj.kolot.com.discountatb.model.ProductCategory;

public class CategoriesPresenter {
    private CategoriesView categoriesView;
    private List<ProductCategory> categories;

    public CategoriesPresenter(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;
        categories = new Categories().getValues();

    }

    public void start() {
        if (categoriesView != null) {
            categoriesView.showCategories(categories);
        }
    }

    public void attachView(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;

    }

    public void detachView() {
        this.categoriesView = null;
    }

    public void showCategoryContent(ProductCategory category) {
        if (categoriesView != null) {
            categoriesView.showCategoryContent(category);
        }
    }
}
