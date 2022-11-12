package trishie.cs.byu.lovetank;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import redis.clients.jedis.Jedis;
import trishie.cs.byu.lovetank.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    TextView loveLanguageDropdown;
    TextView loveLanguageDropdown_husband;
    boolean[] selectedLoveLanguages;
    boolean[] selectedLoveLanguages_husband;
    ArrayList<Integer> loveLanguagesList = new ArrayList<>();
    ArrayList<Integer> loveLanguagesList_husband = new ArrayList<>();
    String[] loveLangArray = {"Quality Time", "Acts of Services",
            "Physical Touch", "Words of Affirmations", "Gifts" };

    private EditText wife_firstName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //assign variable
        loveLanguageDropdown = findViewById(R.id.loveLanguageDropdown);
        //assign husbands variables
        loveLanguageDropdown_husband = findViewById(R.id.loveLanguageDropdown2);

        wife_firstName = findViewById(R.id.wifeNameField);
        System.out.println("I AM GEREEEE");
        System.out.println(wife_firstName.getText().toString());

        //------------------------------------

        //initialize love language array
        selectedLoveLanguages = new boolean[loveLangArray.length];
        selectedLoveLanguages_husband = new boolean[loveLangArray.length];

        loveLanguageDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this
                );
                //set title
                builder.setTitle("Select Love Language");
                //set dialog non-cancellable
                builder.setCancelable(false);
                builder.setMultiChoiceItems(loveLangArray, selectedLoveLanguages, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check condition -- whether box is checked
                        if (isChecked){
                            loveLanguagesList.add(which);
                            //sort lang list
                            Collections.sort(loveLanguagesList);
                        }
                        else{
                            //when box is unselected
                            loveLanguagesList.remove(which);
                        }
                    }
                });

                builder.setPositiveButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();

                        //for every one the user has chosen
                        for(int j = 0; j < loveLanguagesList.size(); j++){
                            //use that particular index of the list to get actual love lang name
                            stringBuilder.append(loveLangArray[loveLanguagesList.get(j)]);
                            //check conditon
                            if (j != loveLanguagesList.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        loveLanguageDropdown.setText(stringBuilder.toString());
                    }
                });

                builder.show();
            }
        });
        //-------------------------------------------------------------------------------------------
        loveLanguageDropdown_husband.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this
                );
                //set title
                builder.setTitle("Select Love Language");
                //set dialog non-cancellable
                builder.setCancelable(false);
                builder.setMultiChoiceItems(loveLangArray, selectedLoveLanguages_husband, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check condition -- whether box is checked
                        if (isChecked){
                            loveLanguagesList_husband.add(which);
                            //sort lang list
                            Collections.sort(loveLanguagesList_husband);
                        }
                        else{
                            //when box is unselected
                            loveLanguagesList_husband.remove(which);
                        }
                    }
                });

                builder.setPositiveButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();

                        //for every one the user has chosen
                        for(int j = 0; j < loveLanguagesList_husband.size(); j++){
                            //use that particular index of the list to get actual love lang name
                            stringBuilder.append(loveLangArray[loveLanguagesList_husband.get(j)]);
                            //check conditon
                            if (j != loveLanguagesList_husband.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        loveLanguageDropdown_husband.setText(stringBuilder.toString());
                    }
                });

                builder.show();
            }
        });

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Jedis jedis = new Jedis("localhost", 6379);
////                System.out.println("connected to reddis");
//
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}