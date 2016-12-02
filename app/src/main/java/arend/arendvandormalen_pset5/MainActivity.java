package arend.arendvandormalen_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String masterListTitle = "master";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addList(View view) {

        EditText listTitleBox = (EditText)findViewById(R.id.add_bar_master);
        String listTitle = listTitleBox.getText().toString();

        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra("listTitle", listTitle);
        startActivity(intent);

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

-Singleton (new singleton in menu)

public class StringManager{

    private static StringManager instance = new StringManager();
    private static String usefulString;

    public static StringManager getInstance() = return instance;

    // constructor
    private StringManager(){
        usefulString = "this is the initial String";
    }

    public void setUsefulString(String newString){
        usefulString = newString;
    }

    public String getUsefulString(){
        return usefulString;
    }

}


MainActivity:


manager = usefulString.getInstance();
TextView stringTv = (TextView)...
manager.setUsefulSting(newString);



*/

