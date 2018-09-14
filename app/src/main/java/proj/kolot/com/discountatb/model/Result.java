package proj.kolot.com.discountatb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Result {

    @SerializedName("result")
    private Boolean result;

    @SerializedName("lastId")
    private String lastId;

    @SerializedName("products")
    private List<Product> products = null;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}