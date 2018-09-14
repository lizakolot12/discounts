package proj.kolot.com.discountatb.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import proj.kolot.com.discountatb.App;
import proj.kolot.com.discountatb.R;

public class MainActivity extends AppCompatActivity {

    private static final String NAME_FRAGMENT_CATEGORIES = "categories_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        CategoriesFragment fragment = (CategoriesFragment) fragmentManager.findFragmentByTag(NAME_FRAGMENT_CATEGORIES);
        if (fragment == null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragment = new CategoriesFragment();
            ft.replace(R.id.fragment, fragment, NAME_FRAGMENT_CATEGORIES);
            ft.commit();
        }
        fragment.setPresenter(App.getInstance().getPresenterFactory().getCategoriesPresenter(fragment));
    }

}
