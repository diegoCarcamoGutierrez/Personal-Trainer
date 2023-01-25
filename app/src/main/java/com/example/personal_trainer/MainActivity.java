package com.example.personal_trainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.personal_trainer.addExercise.AddExerciseFragment;
import com.example.personal_trainer.history.HistoryFragment;
import com.example.personal_trainer.home.HomeFragment;
import com.example.personal_trainer.stats.StatsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout); //Funcionamiento del DrawerLayout
        navigationView=findViewById(R.id.navigation_view); //Funcionamiento del NavigationView
        Fragment fragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,fragment).commit();
        //Selección de Fragment según se seleccione un item del NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=new HomeFragment();
                int id=item.getItemId();
                if (id==R.id.homeFragment){
                    selectedFragment=new HomeFragment();
                }
                if (item.getItemId() == R.id.addExerciseFragment) {
                    selectedFragment=new AddExerciseFragment();
                }
                if (item.getItemId() == R.id.stats) {
                    selectedFragment= new StatsFragment();
                }
                if (item.getItemId() == R.id.exerciseHistory) {
                    selectedFragment=new HistoryFragment();
                }
                if (selectedFragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,selectedFragment).commit();
                }


                return true;
            }
        });
    }
}