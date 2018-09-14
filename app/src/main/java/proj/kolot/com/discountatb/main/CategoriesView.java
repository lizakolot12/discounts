package proj.kolot.com.discountatb.main;

import java.util.List;

import proj.kolot.com.discountatb.model.ProductCategory;

public interface CategoriesView {
    void showCategories(List<ProductCategory> list);
    void showCategoryContent(ProductCategory category);
    void setPresenter(CategoriesPresenter presenter);
}
