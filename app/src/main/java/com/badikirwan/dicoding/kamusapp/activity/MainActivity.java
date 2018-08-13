package com.badikirwan.dicoding.kamusapp.activity;

import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.badikirwan.dicoding.kamusapp.R;
import com.badikirwan.dicoding.kamusapp.adapter.KamusAdapter;
import com.badikirwan.dicoding.kamusapp.helper.KamusHelper;
import com.badikirwan.dicoding.kamusapp.model.KamusModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    @BindView(R.id.edt_cari)
    EditText mEdt_cari;

    @BindView(R.id.btn_cari)
    Button mBtn_cari;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private KamusHelper kamusHelper;
    private KamusAdapter adapter;
    String lang_type;
    private ArrayList<KamusModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        ButterKnife.bind(this);

        mBtn_cari.setOnClickListener(myListener);
        setupList();
        lang_type = "ENG";
        getData(lang_type, "");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_eng_to_indo:
                lang_type = "ENG";
                getData(lang_type, "");
                break;
            case R.id.nav_indo_to_eng:
                lang_type = "IND";
                getData(lang_type, "");
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String kota = mEdt_cari.getText().toString();
            getData(lang_type, kota);
        }
    };

    private void setupList() {
        adapter = new KamusAdapter(this);
        kamusHelper = new KamusHelper(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
    }

    public void getData(String lang, String search) {
        try {
            kamusHelper.open();

            if (search.isEmpty()) {
                list = kamusHelper.getAllData(lang);
            } else {
                list = kamusHelper.getDataByName(search, lang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusHelper.close();
        }

        adapter.replaceAll(list);
    }
}
