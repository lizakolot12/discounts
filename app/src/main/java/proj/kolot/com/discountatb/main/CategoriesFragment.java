package proj.kolot.com.discountatb.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import proj.kolot.com.discountatb.R;
import proj.kolot.com.discountatb.list.ProductListActivity;
import proj.kolot.com.discountatb.model.ProductCategory;


public class CategoriesFragment extends Fragment implements CategoriesView {

    private static final int SPAN_COUNT = 2;
    private CategoryViewAdapter adapter;
    private CategoriesPresenter presenter;
    private List<ProductCategory> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.categories);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        adapter = new CategoryViewAdapter(new ArrayList<ProductCategory>());
        adapter.setListener(new CategoryViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ProductCategory category) {
                presenter.showCategoryContent(category);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void showCategories(List<ProductCategory> list) {
        categories = list;
        adapter.setValues(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCategoryContent(ProductCategory category) {
        Intent intent = ProductListActivity.newIntent(this.getContext(), category, categories);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setPresenter(CategoriesPresenter presenter) {
        this.presenter = presenter;
    }
}
