package com.eujoh.uoeapp.User;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eujoh.uoeapp.Common.LoginSignUp.Login;
import com.eujoh.uoeapp.Common.LoginSignUp.StudentStartUpScreen;
import com.eujoh.uoeapp.Common.LoginSignUp.User;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.CampusBuzzMvAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.CampusBuzzMvHelperClass;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.ClubsSocietiesAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.ClubsSocietiesHelperClass;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.RecentAnnouncementsAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.RecentAnnouncementsHelperClass;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.UpcomingEventsAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.UpcomingEventsHelperClass;
import com.eujoh.uoeapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;

    RecyclerView recentAnnouncementsRecycler, campusBuzzMvRecycler, clubsSocietiesRecycler, upcomingEventsRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, getGradient3, getGradient4;
    ImageView drawerMenuIcon, menuPop;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        recentAnnouncementsRecycler = findViewById(R.id.recent_announcements_recycler);
        campusBuzzMvRecycler = findViewById(R.id.campus_buzz_mv_recycler);
        clubsSocietiesRecycler = findViewById(R.id.clubs_societies_recycler);
        upcomingEventsRecycler = findViewById(R.id.upcoming_events_recycler);
        drawerMenuIcon = findViewById(R.id.drawer_menu_icon);
        contentView = findViewById(R.id.content);
        menuPop = findViewById(R.id.menu_imgv);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav_view);

        navigationDrawer();

        recentAnnouncementsRecycler();
        campusBuzzMvRecycler();
        clubsSocietiesRecycler();
        upcomingEventsRecycler();

        menuPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDashboard.this, "You clicked", Toast.LENGTH_SHORT).show();
                final PopupMenu popupMenu = new PopupMenu(UserDashboard.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popmenu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.log_out_menu:
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                finish();
                            return true;
                            case R.id.my_profile_menu:
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                return true;
                        }
                        popupMenu.show();
                        return false;
                    }
                });
            }
        });
    }

    //Navigation Drawer functions
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_drawer_home);

        drawerMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                 else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimaryDark));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offSetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offSetScale);
                contentView.setScaleY(offSetScale);

                //Translate the view, accounting for the scaled idth
                final float xOffSet = drawerView.getWidth() * slideOffset;
                final float xOffSetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffSet - xOffSetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_drawer_home:
                startActivity(new Intent(this,UserDashboard.class));
                break;
            case R.id.nav_drawer_contacts:
                startActivity(new Intent(this,contacts.class));
                break;
            case R.id.nav_drawer_announcements:
                startActivity(new Intent(this,announcements.class));
                break;
            case R.id.nav_drawer_lost_found:
                startActivity(new Intent(this,LostFound.class));
                break;
            case R.id.nav_drawer_my_profile:
                startActivity(new Intent(this,UserProfile.class));
                break;
            case R.id.nav_drawer_log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, Login.class));
                finish();
        }
        return true;
    }

    //Recyclerview functions
    private void upcomingEventsRecycler() {
        upcomingEventsRecycler.setHasFixedSize(true);
        upcomingEventsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<UpcomingEventsHelperClass> upcomingEvents = new ArrayList<>();

        upcomingEvents.add(new UpcomingEventsHelperClass(R.drawable.announcements,"Fee Deadline", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        upcomingEvents.add(new UpcomingEventsHelperClass(R.drawable.faqs,"Semester Resumption", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        upcomingEvents.add(new UpcomingEventsHelperClass(R.drawable.profile,"Exam Dates", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        upcomingEvents.add(new UpcomingEventsHelperClass(R.drawable.lost_and_found1,"Council Elections", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));

        adapter = new UpcomingEventsAdapter(upcomingEvents);
        upcomingEventsRecycler.setAdapter(adapter);
    }
    private void clubsSocietiesRecycler() {
        clubsSocietiesRecycler.setHasFixedSize(true);
        clubsSocietiesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<ClubsSocietiesHelperClass> clubsSocieties = new ArrayList<>();

        clubsSocieties.add(new ClubsSocietiesHelperClass(R.drawable.announcements, "Games"));
        clubsSocieties.add(new ClubsSocietiesHelperClass(R.drawable.lost_and_found1, "Politics"));
        clubsSocieties.add(new ClubsSocietiesHelperClass(R.drawable.faqs, "Taekwondo"));

        adapter = new ClubsSocietiesAdapter(clubsSocieties);
        clubsSocietiesRecycler.setAdapter(adapter);
    }
    private void campusBuzzMvRecycler() {
        campusBuzzMvRecycler.setHasFixedSize(true);
        campusBuzzMvRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<CampusBuzzMvHelperClass> mostViewedCampusBuzz = new ArrayList<>();

        mostViewedCampusBuzz.add(new CampusBuzzMvHelperClass(R.drawable.announcements,"Fee Deadline", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        mostViewedCampusBuzz.add(new CampusBuzzMvHelperClass(R.drawable.faqs,"Semester Resumption", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        mostViewedCampusBuzz.add(new CampusBuzzMvHelperClass(R.drawable.profile,"Exam Dates", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        mostViewedCampusBuzz.add(new CampusBuzzMvHelperClass(R.drawable.lost_and_found1,"Council Elections", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));

        adapter = new CampusBuzzMvAdapter(mostViewedCampusBuzz);
        campusBuzzMvRecycler.setAdapter(adapter);
    }
    private void recentAnnouncementsRecycler() {
        recentAnnouncementsRecycler.setHasFixedSize(true);
        recentAnnouncementsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<RecentAnnouncementsHelperClass> recentAnnouncements = new ArrayList<>();

        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.announcements,"Fee Deadline", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.faqs,"Semester Resumption", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.profile,"Exam Dates", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.lost_and_found1,"Council Elections", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));

        adapter = new RecentAnnouncementsAdapter(recentAnnouncements);
        recentAnnouncementsRecycler.setAdapter(adapter);
    }

    public void startupscreen(View view) {
        startActivity(new Intent(this, StudentStartUpScreen.class));
    }

    public void schoolmapscreen(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void studentportalscreen(View view) {
//        startActivity(new Intent(this, StudentPortal.class));
        Toast.makeText(UserDashboard.this, "You clicked", Toast.LENGTH_SHORT).show();
        final PopupMenu popupMenu = new PopupMenu(UserDashboard.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popmenu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.log_out_menu:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                        return true;
                    case R.id.my_profile_menu:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        return true;
                }
                popupMenu.show();
                return false;
            }
        });
    }

    public void schoolwebscreen(View view) {
        Uri uri = Uri.parse("https://www.uoeld.ac.ke/");
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}