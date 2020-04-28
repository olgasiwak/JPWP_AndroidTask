package com.example.hellofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.hellofragment.api.NetworkClient;
import com.example.hellofragment.api.WeatherApi;
import com.example.hellofragment.model.WeatherResult;
import java.util.zip.Inflater;


//TODO odkomentuj poniższy kod po wykonanu zadania 4
import com.example.hellofragment.api.NetworkClient;
import com.example.hellofragment.api.WeatherApi;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HelloFragmentA extends Fragment {

    private TextView cityName;                                      //Podany kod
    private String cityNameString;                                  //Podany kod
    private TextView forecast;                                      //Podany kod
    private ImageView weatherIcon;                                  //Podany kod

    private String UNITS = "metric";                                //Podany kod
    private String API_KEY = "eccf917310f1c6acbf2acb9e85bf1a0d";    //Podany kod


    //TODO zad. 3
    public interface HelloFragmentAListener{
    //TODO tutaj zadeklaruj metodę, która posłuży do przesłania tekstu do fragmentu B
        void sendData(String text);
    }

    private HelloFragmentAListener helloFragmentAListener;          //Podany kod

    //TODO zad. 3
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //TODO tutaj sprawdź czy interfejs HelloFragmentALlistener jest zaimplementowany przez
        //     MainActivity, w tym celu użyj "instanceof", context to w pewnym sensie referencja
        //     do MainActivity. Jeżeli jest, to dołącz helloFragmentAListener do tej instancji, w
        //     tym celu musisz rzutować context na HelloFragmentAListener.
        //     Rzuć wyjątkiem RuntimeException jeśli nie jest.
        if (context instanceof HelloFragmentAListener){
            helloFragmentAListener = (HelloFragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement HelloFragmentAListener");
        }
    }



    //TODO zadanie 4
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO zad 2.1 użyj wyglądu z pliku hello_fragment_a.xml, plik ten wskazywany jest
        //     przez id  - nazwa pliku bez rozszeżenia, poszczególen id layoutów przechowywane przez R.layout..
        //     wstaw ten layout do poniższego kodu

        View view = inflater.inflate(R.layout.hello_fragment_a_final, container, false);

        // TODO zad2.1 Następnym krokiem jest "znalezienie" poszczególnych elementów zdefiniowanych w wyglądzie.
        //    W celu znalezienia poszczególnych elementów użyj metody view.findViewById(R.id.nazwa_elementu)
        //     nazwe elementu możesz znaleść w pliku app/res/layout/hello_fragment_a.xml, w którym zdefiniowany jest wygląd danego fragmentu. Znajdź
        //     kawałek kodu odpowiedzialny za dany element (textView), jego id definiowane jest w polu android:id (id danego elementu to nazwa
        //     umieszczona po znaczniku @+id/)
        cityName = view.findViewById(R.id.city_name);
        //TODO zad. 4 należy zmienić wygląd na hello_fragment_a_final.xml, znaleźć nowe elementy (tak samo jak w zadaniu 2.1)
        //     oraz za pomocą metody setText() ustawić tekst wyświetlany przez cityName na ten
        //     przechowywany w zmiennej cityNameString

//        odkomętuj poniższy kod po wykonaniu zadania 4
        if(cityNameString != null) {
            fetchWeatherDetails();
        }
        cityName.setText(cityNameString);
        return view;                                                                                //Podany kod
    }

    //TODO zad. 3
    @Override
    public void onPause() {
        super.onPause();
        //TODO wywołaj na helloFragmentAListener zadeklarowaną przez Ciebie metodę.
        helloFragmentAListener.sendData("text");
    }


    //TODO zad. 3
    @Override
    public void onDetach() {
        super.onDetach();
        //TODO nadpisz zmienną helloFragmentAListener w taki sposób, aby garbage collector mógł ją usunąć
        helloFragmentAListener = null;
    }

    //TODO zad. 4
    void updateCity(String city){
        //TODO przypisz zawarotść zmiennej city do cityNameString
        cityNameString = city;
    }

//TODO odkometuj poniższy kod po wykonaniu zadania 4
    private void fetchWeatherDetails() {
       //TODO Stworzyć instancję retrofit przy użyciu metody statycznej z klasy NetworkClient

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        //Utworzono instancję interfejsu WeatherApi z wykorzystaniem obiektu retrofit
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
       //TODO wywołać metodę z interfejsu WeatherApi, jako nazwę miasta przekaż zmienną cityNameString,
       //     klucz API_KEY jak i jednostka UNITS są zdefiniowana na początku klasy
       Call call = weatherApi.getWeatherByCity(cityNameString,UNITS,API_KEY);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    WeatherResult weatherResult = (WeatherResult) response.body();
                    //TODO Wypisać temperaturę, cisnienie i pędkość wiatru. Do konkretnych elementów
                    //     odpowiedzi możemy się dostać przy pomocy stworzonych wcześniej getterów i
                    //     setterów(np weatherResult.getName())
                    //     W textView nie należy dokonywać konkatenacji, dlatego w res.strings.xml
                  //     przygotowano odpowiednie wyrażenie - "weather_information".
                   //     W celu jego wykorzystania należy posłużyć się metodą
                   //     getString(R.string.weather_information, weatherResponse.getMain().getTemp(), weatherResult.getWind().getSpeed(), weatherResult.getMain().getPressure())

                    getString(R.string.weather_information, weatherResult.getMain().getTemp(), weatherResult.getWind().getSpeed(), weatherResult.getMain().getPressure());
                   //TODO Do wyświetlenia ikony odpowiadającej danej pogodzie użyjemy biblioteki picasso.
                   //     W tym celu z odpowiedzi wydobywamy informację o ikonce (weatherResult.getWeather.get(0).getIcon)
                   //     i przygotowywujemy URL z którego ściągniemy obrazek.
                  //     To adresu https://openweathermap.org/img/w/ + icon + .png
                   //     Teraz możemy załadować obrazek:
                        //Picasso.get.load("https://openweathermap.org/img/w/").into(imageView w których chcemy wyświetlić ikonkę)

                    String icon = weatherResult.getWeather().get(0).getIcon();
                    Picasso.get().load("https://openweathermap.org/img/w/" + icon + ".png").into(weatherIcon);

               } else {
                  Toast.makeText(getContext(), "wrong city name", Toast.LENGTH_LONG).show();
                   cityName.setText("wrong city name");
               }
            }
           @Override
           public void onFailure(Call call, Throwable t){
            }
        });
    }
}
