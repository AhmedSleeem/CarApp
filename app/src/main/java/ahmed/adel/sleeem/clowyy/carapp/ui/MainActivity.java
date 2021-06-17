package ahmed.adel.sleeem.clowyy.carapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import ahmed.adel.sleeem.clowyy.carapp.R;
import ahmed.adel.sleeem.clowyy.carapp.ui.adapter.CarAdapter;

import ahmed.adel.sleeem.clowyy.carapp.viewmodel.CarViewModel;


public class MainActivity extends AppCompatActivity {
   private CarAdapter userAdapter;
   private RecyclerView recyclerView;
   private SwipeRefreshLayout swipeRefreshLayout;
   private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializedUIElement();

        observeCarData();

        // to reload the data after pull the screen down
        pullToRefresh();

        recyclerView.setAdapter(userAdapter);
    }

    private void pullToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {

            recyclerView.setAdapter(userAdapter);

                    swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void observeCarData() {

        CarViewModel userViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        userViewModel.pagedListLiveData.observe(this,cars -> {

            // to check whether data is fetched or not and after that hide the progress bar

            if (cars.size()>0) {
                setProgressBarIndeterminateVisibility(false);

                progressBar.setVisibility(View.GONE);
                userAdapter.submitList(cars);
            }
        });
    }

    private void initializedUIElement(){
        userAdapter = new CarAdapter(getBaseContext());
        recyclerView = findViewById(R.id.rv_user_list);
        progressBar = findViewById(R.id.progressbar);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
    }
}