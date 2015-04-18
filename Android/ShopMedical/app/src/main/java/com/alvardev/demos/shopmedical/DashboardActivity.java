package com.alvardev.demos.shopmedical;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.adapter.MedicamentosAdapter;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.util.PedidoDialogFragment;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.fragment.SearchResultFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DashboardActivity extends BaseActionBarActivity {

    private static final String TAG = "DashboardActivity";

    private SearchResultFragment searchResultFragment = SearchResultFragment.newInstance(null,null);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Buscar...");
        new ChangeFragmentsTask(null).execute(StaticData.SEARCH_RESULT);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String q = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(getApplicationContext(), "search: "+q, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_search || super.onOptionsItemSelected(item);
    }


    private class ChangeFragmentsTask extends AsyncTask<Integer, Integer, Boolean> {
        private Bundle bundle;
        private int position;

        public ChangeFragmentsTask(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        protected Boolean doInBackground(Integer... itemAction) {
            position = itemAction[0];
            changeScreen(bundle, position);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            //closeDrawer(position);
        }

    }

    private void changeScreen(Bundle bundle, int option) {

        switch (option){
            case StaticData.SEARCH_RESULT:
                changeFragment(bundle, searchResultFragment);
                break;

            default:
                break;
        }
    }
    private void changeFragment(Bundle args, Fragment fragment) {
        if (args != null) fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
    }



}
