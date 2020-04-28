package com.example.hellofragment.api;
//TODO odkomentuj poniższy kod po wykonanu zadania 4
import com.example.hellofragment.model.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
//
public interface WeatherApi {

   //TODO Zadeklaruj metodę HTTP do pobierania danych. Wykorzystaj przykład przedstawiony na prezentacji (twój endpoint to "/data/2.5/weather/")
    @GET("/data/2.5/weather/")
    Call<WeatherResult> getWeatherByCity(@Query("q") String city, @Query("units") String units, @Query("appid") String apiKey);
}
