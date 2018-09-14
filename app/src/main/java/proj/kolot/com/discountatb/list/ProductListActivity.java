package proj.kolot.com.discountatb.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import proj.kolot.com.discountatb.App;
import proj.kolot.com.discountatb.R;
import proj.kolot.com.discountatb.model.ProductCategory;

public class ProductListActivity extends AppCompatActivity {

    private static final String EXTRA_CATEGORY_CHOSEN = "extra_category_choosed_id";
    private static final String EXTRA_CATEGORY_LIST = "extra_category_list";

    private List<ProductCategory> categories;
    private ProductCategory currentCategory;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ListFragment currentFragment;


    public static Intent newIntent(Context context, ProductCategory category, List<ProductCategory> list) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_CHOSEN, category);
        ArrayList<ProductCategory> arrayList = new ArrayList<>(list);
        intent.putExtra(EXTRA_CATEGORY_LIST, arrayList);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setInitData();

        tabLayout = findViewById(R.id.tabs);
        for (ProductCategory category : categories) {
            tabLayout.addTab(tabLayout.newTab().setText(category.getDescription()));
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        selectPage(getCurrentItem());
    }

    private void setInitData() {
        currentCategory = getIntent().getParcelableExtra(EXTRA_CATEGORY_CHOSEN);
        categories = getIntent().getParcelableArrayListExtra(EXTRA_CATEGORY_LIST);
        if (categories == null) {
            categories = new ArrayList<>();
        }
    }

    private int getCurrentItem() {
        int position = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getValue() == currentCategory.getValue()) {
                position = i;
                break;
            }
        }
        return position;
    }

    private void selectPage(int pageIndex) {
        tabLayout.setScrollPosition(pageIndex, 0f, true);
        viewPager.setCurrentItem(pageIndex);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                currentFragment.refreshData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ProductCategory productCategory = categories.get(position);
            ListFragment fragment = (ListFragment) super.instantiateItem(container, position);
            fragment.setPresenter(App.getInstance().getPresenterFactory().getProductListPresenter(productCategory, fragment));
            return fragment;
        }

        @Override
        public Fragment getItem(int position) {
            ProductCategory productCategory = categories.get(position);
            ListFragment fragment = ListFragment.newInstance(productCategory);
            fragment.setPresenter(App.getInstance().getPresenterFactory().getProductListPresenter(productCategory, fragment));
            return fragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            currentFragment = (ListFragment) object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(position).getDescription();
        }

        @Override
        public int getCount() {
            return categories.size();
        }
    }

}
