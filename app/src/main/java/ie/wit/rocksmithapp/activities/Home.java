package ie.wit.rocksmithapp.activities;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ie.wit.rocksmithapp.Main.RocksmithApp;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public RocksmithApp app = RocksmithApp.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
