package proj.kolot.com.discountatb.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import proj.kolot.com.discountatb.R;
import proj.kolot.com.discountatb.model.Product;
import proj.kolot.com.discountatb.model.ProductCategory;


public class ListFragment extends Fragment implements ProductListView {
    private static final String ARGS_CATEGORY = "args_category";

    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private TextView message;
    private ProductListPresenter presenter;
    private ProductCategory productCategory;


    public static ListFragment newInstance(ProductCategory category) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setProductCategory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        progressBar = view.findViewById(R.id.progress);
        message = view.findViewById(R.id.message);

        recyclerView = view.findViewById(R.id.products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemRecyclerViewAdapter(getContext(), new ArrayList<Product>()));
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        setProductCategory();
        if (presenter != null) {
            presenter.start(productCategory);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showError(String error) {
        message.setVisibility(View.VISIBLE);
        message.setText(error);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<Product> list) {
        recyclerView.setVisibility(View.VISIBLE);
        message.setVisibility(View.GONE);
        ItemRecyclerViewAdapter adapter = (ItemRecyclerViewAdapter) recyclerView.getAdapter();
        adapter.updateData(list);
    }

    @Override
    public void hideData() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(ProductListPresenter presenter) {
        this.presenter = presenter;
        if (isShowedView()) {
            setProductCategory();
            presenter.start(productCategory);
        }

    }

    public void refreshData() {
        presenter.update();
    }

    private void setProductCategory() {
        productCategory = getArguments().getParcelable(ARGS_CATEGORY);
    }

    private boolean isShowedView() {
        return isAdded();
    }
}
