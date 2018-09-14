package proj.kolot.com.discountatb.model;

import java.util.ArrayList;
import java.util.List;

import proj.kolot.com.discountatb.repository.retrofit.Category;

public class Categories {


    private List<ProductCategory> values = new ArrayList<>();

    public Categories() {
        for (Category category : Category.values()) {
            values.add(new ProductCategory(category.getValue(), category.getDescription()));
        }
    }

    public List<ProductCategory> getValues() {
        return values;
    }
}
