package ahmed.adel.sleeem.clowyy.carapp.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import org.jetbrains.annotations.NotNull;

import ahmed.adel.sleeem.clowyy.carapp.model.Car;

public class CarDataSourceFactory extends DataSource.Factory<Integer, Car> {


    public MutableLiveData<CarDataSource> carLiveDataSource = new MutableLiveData<>();

    @NotNull
    @Override
    public DataSource<Integer, Car> create() {
        CarDataSource dataSource = new CarDataSource();
        carLiveDataSource.postValue(dataSource);

       return dataSource;
    }
}
