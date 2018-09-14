package proj.kolot.com.discountatb.repository.retrofit;


import io.reactivex.Observable;
import proj.kolot.com.discountatb.model.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {
    @GET("api2/catalogue/product-list/?type=2")
    Observable<Result> getAllProducts();

    @GET("api2/catalogue/action-products/?type=2")
    Observable<Result> getAllProductsByCategory(@Query("id")int id);

}
