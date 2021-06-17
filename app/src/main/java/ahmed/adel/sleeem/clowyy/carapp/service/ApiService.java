package ahmed.adel.sleeem.clowyy.carapp.service;

import ahmed.adel.sleeem.clowyy.carapp.model.CarResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cars")
    Call<CarResponse> getCars (@Query("page") int page);
}
