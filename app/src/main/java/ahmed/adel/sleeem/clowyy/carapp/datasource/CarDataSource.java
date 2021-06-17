package ahmed.adel.sleeem.clowyy.carapp.datasource;

import android.util.Log;

import androidx.paging.PageKeyedDataSource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ahmed.adel.sleeem.clowyy.carapp.model.Car;
import ahmed.adel.sleeem.clowyy.carapp.model.CarResponse;
import ahmed.adel.sleeem.clowyy.carapp.service.ApiService;
import ahmed.adel.sleeem.clowyy.carapp.service.ApiServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDataSource extends PageKeyedDataSource<Integer, Car> {

    public static final int  PAGE_SIZE = 6;
    private static final int  FIRST_SIZE = 1;

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Integer> loadInitialParams, @NotNull LoadInitialCallback<Integer, Car> loadInitialCallback) {

        ApiService service = ApiServiceBuilder.buildService(ApiService.class);

        Call<CarResponse> call = service.getCars(FIRST_SIZE);

        //handle service Response CallBack
        call.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                if (response.isSuccessful()){
                    CarResponse responseBody = response.body();
                    List<Car> cars = responseBody.getData();


                    loadInitialCallback.onResult(cars,null,FIRST_SIZE+1);
                }else{
                    switch (response.code()){
                        case 404:
                            Log.e( "onResponse: ", "Server Return Error : No data found");
                            break;
                        case 500:
                            Log.e( "onResponse: ", "Server Return Error : Server is Broken");
                            break;
                        default:
                            Log.e( "onResponse: ", "Unknown Error");

                    }
                }

            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {

                Log.e( "onFailure: ",t.getMessage() );
            }
        });
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Car> loadCallback) {

    }

    @Override
    public void loadAfter(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Car> loadCallback) {

        ApiService service = ApiServiceBuilder.buildService(ApiService.class);

        Call<CarResponse> call = service.getCars(loadParams.key);

        int newKey = loadParams.key + 1 ;

        //handle service Response CallBack
        call.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                if (response.isSuccessful()){
                    CarResponse responseBody = response.body();
                    List<Car> cars = responseBody.getData();

                    loadCallback.onResult(cars,newKey );
                }

            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {

            }
        });
    }


}
