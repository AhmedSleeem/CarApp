package ahmed.adel.sleeem.clowyy.carapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceBuilder {

    //BASE_URL
    private  static final String BASE_URL = "https://demo1585915.mockable.io/api/v1/";

    private static Retrofit INSTANCE  ;







    public static synchronized  <T extends Object> T buildService(Class<T> serviceType){
            if (INSTANCE == null){
                //create Logger
             HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


                OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
                        .addInterceptor(logger);

                Gson gson = new GsonBuilder().setLenient().create();

              Retrofit.Builder builder =new  Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(okHttp.build());

               INSTANCE=  builder.build();

            }
          return (T) INSTANCE.create(serviceType);
    }

}
