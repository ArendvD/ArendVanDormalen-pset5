package arend.arendvandormalen_pset5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


/*
 - Seperation of Concerns
 - Model & Utility Classes
 + Models contain data, getters + setters
 + Utility managet data, doet andere functies met data
 - Singleton
 + Single instance in je application, private constructor (zodat er maar 1 kan bestaan)
 en public getInstance method (die je overal kan aanroepen)
 + Gebruik voor overzicht van lijsten
 + Singleton handiger bij lokale opslag ipv database.
 - Fragments (niet verplicht)
 + Expand UI in Activity
 + Maakt UI flexibeler
 + Hergebruikbaar

- Android Storage (Internal Storage)

 CODE DEMO:




*/

