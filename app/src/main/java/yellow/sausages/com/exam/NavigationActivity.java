package yellow.sausages.com.exam;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView textFAB;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        textFAB = findViewById(R.id.textFAB);
        fab = findViewById(R.id.FABBY);
        toolbar = findViewById(R.id.navitoolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textFAB.setVisibility(View.VISIBLE);
            }
        });

        //drawer stuff
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuPowerOff:
                Toast.makeText(this, "Power Off", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuSettings:
                Toast.makeText(this, "Open Settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cat1:
                Toast.makeText(this, "THIS 1st CAT", Toast.LENGTH_LONG).show();
                break;
            case R.id.cat2:
                Toast.makeText(this, "THIS 2nd CAT", Toast.LENGTH_LONG).show();
                break;
            case R.id.cat3:
                Toast.makeText(this, "THIS 3rd CAT", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
