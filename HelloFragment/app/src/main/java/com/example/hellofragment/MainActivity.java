package com.example.hellofragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//TODO użyj tej linii do zadania 2.1 oraz 2.2 (potem zakomentuj i odkomentuj kolejne)
//public class MainActivity extends AppCompatActivity {

//TODO użyj tej linii do zadania 3 (potem zakomentuj i odkomentuj kolejne)
//public class MainActivity extends AppCompatActivity implements HelloFragmentA.HelloFragmentAListener {

//TODO użyj tej linii do zadania 4 i wzwyż
public class MainActivity extends AppCompatActivity implements HelloFragmentA.HelloFragmentAListener,
        HelloFragmentB.SendCityNameListener {



    private HelloFragmentB fragmentB;   //podany kod
    private HelloFragmentA fragmentA;   //podany kod

    BottomNavigationView navbar;        //podany kod

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                     //podany kod

        if (fragmentA == null) {                                    //podany kod
            fragmentA = new HelloFragmentA();                       //podany kod
        }

        if (fragmentB == null) {                                    //podany kod
            fragmentB = new HelloFragmentB();                       //podany kod
        }

        navbar = findViewById(R.id.bottom_nav_bar);                 //podany kod
        navbar.setOnNavigationItemSelectedListener(navListener);    //podany kod
        //TODO zad. 2.2 tutaj dowiąż "startowy" fragment. Na początek przypisz wynik metody getSupportFragmentManager() do
        // obiektu klasy FragmentManager (nazwij do fragmentManager). Następnie na obiekcie fragmentManager wywołaj funkcję
        // beginTransaction() i jej wynik przypisz do obiektu klasy FragmentTransaction (nazwij go fragmentTransaction).
        // Ostatnim krokiem będzie wywołanie metody replace (jako parametry przyjmuje ona R.id.fragment_container, fragmentA)
        // na obiekcie fragmentTransaction oraz metody commit().
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragmentA).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =                         //podany kod
            new BottomNavigationView.OnNavigationItemSelectedListener() {                               //podany kod
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {                       //podany kod
                    Fragment selectedFragment = null;                                                   //podany kod
                    switch (item.getItemId()) {                                                         //podany kod
                        case R.id.fragment_a:                                                           //podany kod
                            selectedFragment = fragmentA;                                               //podany kod
                            break;                                                                      //podany kod
                        case R.id.fragment_b:                                                           //podany kod
                            selectedFragment = fragmentB;                                               //podany kod
                            break;                                                                      //podany kod
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();                      //podany kod
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();       //podany kod
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment).commit();    //podany kod
                    return true;
                }
            };

// odkomentuj poniższy kod w trakcie wykonywania zadania 3
    @Override
    public void sendData(String text) {
       //TODO wywołaj uzupełnioną przez Ciebie metodę updateData(String text) {...}
        fragmentB.updateData(text);
    }

// odkomentuj poniższy kod w trakcie wykonywania zadania 4
    @Override
    public void newCity(String city) {
        fragmentA.updateCity(city);
        navbar.setSelectedItemId(R.id.fragment_a);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentA)
                .commit();
    }

}
