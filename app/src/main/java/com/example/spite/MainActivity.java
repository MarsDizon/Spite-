package com.example.spite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private UserDBHandler dbh = new UserDBHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        User user = new User( "user01", "Chloe", "chloeEmail", "chloe", 55.0, "Kyle", "user02" );
        User user2 = new User( "user02", "Jack", "PumpkinKing", "box", 206.0, "Oogie-Boogie", "user03");
        User user3 = new User( "user03", "Kelini", "waywardsRest", "stab", 64.3, "Emotions", "user01");
        dbh.addUser( db, user );
        dbh.addUser( db, user2 );
        dbh.addUser( db, user3 );
*/

        /* Display the Bottom Navigation Bar*/
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        /*Switch statement to display specific fragments outside of the navigation tab*/
        Intent whichView = getIntent();
        String source = whichView.getStringExtra("TabView");


        if (source != null) {
            Log.d("TabView", source);
            changeFragmentView(source);
        }
        else {
            /*Display FragmentHome by default upon opening*/
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    new FragmentHome()).commit();
        }
    }

    /*Set up menu in the Bottom Navigation Bar and change fragments upon clicking*/
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    /*Set up menu as a switch*/
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new FragmentHome();
                            break;
                        case R.id.nav_progress:
                            selectedFragment = new FragmentProgress();
                            break;
                        case R.id.nav_kyle_progress:
                            selectedFragment = new FragmentKyleProgress();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new FragmentProfile();
                            break;
                    }

                    /*Get selectedFragment and display selectedFragment in the switch*/
                    if(selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                    }

                    return true;
                }
            };

    /*TODO: Change FragmentView is successful. However, navBar icons do not change*/
    public void changeFragmentView(String source){
        Fragment selectedFragment = null;

        if (source.equals("EndWorkoutToProgress")) {
            selectedFragment = new FragmentProgress();
            Log.d("TabView", "Stage 2");
        }

        /*Get selectedFragment and display selectedFragment in the switch*/
        if(selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            Log.d("TabView", "Stage 3");
        }
    }
}