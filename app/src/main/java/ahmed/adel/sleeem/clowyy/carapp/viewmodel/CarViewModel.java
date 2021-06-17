package ahmed.adel.sleeem.clowyy.carapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import ahmed.adel.sleeem.clowyy.carapp.datasource.CarDataSource;
import ahmed.adel.sleeem.clowyy.carapp.datasource.CarDataSourceFactory;

import ahmed.adel.sleeem.clowyy.carapp.model.Car;

public class CarViewModel extends ViewModel {

      public   LiveData<PagedList<Car>> pagedListLiveData ;

    public CarViewModel() {
        CarDataSourceFactory carDataSourceFactory = new CarDataSourceFactory();
        userDataSource = carDataSourceFactory.carLiveDataSource;

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CarDataSource.PAGE_SIZE)
                .build();

        pagedListLiveData = new LivePagedListBuilder(carDataSourceFactory,config).build();
    }

    private final LiveData<CarDataSource> userDataSource;
}
