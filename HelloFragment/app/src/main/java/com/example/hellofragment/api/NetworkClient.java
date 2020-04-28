package com.example.hellofragment.api;
//TODO odkomentuj poniższy kod po wykonanu zadania 4
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//
public class NetworkClient {
    public static final String BASE_URL = "https://api.openweathermap.org";
    public static Retrofit retrofit;
//
    public static Retrofit getRetrofitClient() {
//
       if (retrofit == null) {
           retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
       return retrofit;
   }
}