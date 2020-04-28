package com.example.hellofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HelloFragmentB extends Fragment {

    public interface SendCityNameListener {
        void newCity(String city);
    }

    private SendCityNameListener sendCityNameListener;  //Podany kod
    private TextView textView;                          //Podany kod
    private String textViewString;                      //Podany kod

    private EditText searchCity;                        //Podany kod

    //TODO zad. 3
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //TODO tutaj sprawdź czy interfejs HelloFragmentBLlistener jest zaimplementowany przez
        //     MainActivity, w tym celu użyj "instanceof", context to w pewnym sensie referencja
        //     do MainActivity. Jeżeli jest, to dołącz helloFragmentBListener do tej instancji, w
        //     tym celu musisz rzutować context na HelloFragmentBListener.
        //     Rzuć wyjątkiem RuntimeException jeśli nie jest.
        if (context instanceof SendCityNameListener){
                sendCityNameListener = (SendCityNameListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement HelloFragmentBListener");
        }
    }



    //TODO zadanie 3:   komunikacja
    //TODO zadanie 4:   przygowtowanie do API
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO zad 2.1 użyj wyglądu z pliku hello_fragment_b.xml, plik ten wskazywany jest
        //     przez id - nazwa pliku bez rozszeżenia, poszczególen id layoutów przechowywane przez R.layout..
        //     wstaw ten layout do poniższego kodu
        View view = inflater.inflate(R.layout.hello_fragment_b_final, container, false);
        // TODO zad2.1 Następnym krokiem jest "znalezienie" poszczególnych elementów (textView) zdefiniowanych w wyglądzie.
        //     W celu znalezienia poszczególnych elementów użyj metody view.findViewById(R.id.nazwa_elementu)
        //     nazwe elementu możesz znaleść w pliku app/res/layout/hello_fragment_b.xml, w którym zdefiniowany jest wygląd danego fragmentu. Znajdź
        //     kawałek kodu odpowiedzialny za dany element (textView), jego id definiowane jest w polu android:id (id danego elementu to nazwa
        //     umieszczona po znaczniku @+id/)
        textView = view.findViewById(R.id.textB);

        //TODO zad. 3 za pomocą metody setText() zmień tekst wyświetlany przez textView na ten przechowywany w textViewString
        if (textViewString != null && !textViewString.isEmpty()){
            textView.setText(textViewString);
        }
        //TODO zad. 4 należy zmienić wygląd na hello_fragment_b_final.xml, znaleźć nowe elementy (tak samo jak w zadaniu 2.1)


// odkomentuj poniższy kod w trakcie wykonywania zadania 4
        textView.setOnEditorActionListener(editorActionListener);
        return view;                                                                                //podany kod
    }

// odkomentuj poniższy kod w trakcie wykonywania zadania 4
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String obtainedText = String.valueOf(searchCity.getText());
                if (!obtainedText.isEmpty()) {
                    sendCityNameListener.newCity(obtainedText);
                    searchCity.setText("");
               } else {
                    Toast toast = Toast.makeText(getContext(), "Please enter city name",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 200);
                   toast.show();
                }
            }
            return false;
        }
    };


    //TODO zad. 3
    void updateData(String text) {
        //TODO przypisz zawarotść zmiennej text do textViewString
        if (textView != null){
            textViewString = text;
        }
    }

    //TODO zad. 4
    @Override
    public void onDetach() {
        super.onDetach();
        //TODO nadpisz zmienną helloFragmentBListener w taki sposób, aby garbage collector mógł ją usunąć
        sendCityNameListener = null;

    }

}
