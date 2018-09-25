package proj.kolot.com.discountatb.repository;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import proj.kolot.com.discountatb.App;
import proj.kolot.com.discountatb.model.ProductCategory;
import proj.kolot.com.discountatb.model.Result;


public class RemoteRepository implements Repository {

    @Override
    public void getProductByCategory(final ProductCategory category, final LoadDataCallback callback) {
        Observable<Result> disposable;
        if (category.getValue() == 0) {
            disposable = App.getInstance().getProductApi().getAllProducts();
        } else {
            disposable = App.getInstance().getProductApi().getAllProductsByCategory(category.getValue());
        }
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) {
                        callback.onDataReceive(result.getProducts());
                        Log.e("for test", " received data from internet " + category.getDescription());
                    }
                }));
    }

}
