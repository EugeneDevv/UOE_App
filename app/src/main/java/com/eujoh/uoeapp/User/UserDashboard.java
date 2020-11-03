package com.eujoh.uoeapp.User;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eujoh.uoeapp.Common.LoginSignUp.Login;
import com.eujoh.uoeapp.Common.LoginSignUp.StudentStartUpScreen;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.AnnouncemetsAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.ClubsSocietiesAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.ClubsSocietiesHelperClass;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.GenUserInterfaceAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.UpcomingEventsAdapter;
import com.eujoh.uoeapp.HelperClasses.HomeAdapter.UpcomingEventsHelperClass;
import com.eujoh.uoeapp.R;
import com.eujoh.uoeapp.models.uploadsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton profileFab,logoutFab;
    private TextView profilefabName, logoutfabName;
    DatabaseReference databaseReference;

    //Variables
    static final float END_SCALE = 0.7f;

    RecyclerView recentAnnouncementsRecycler, campusBuzzMvRecycler, upcomingEventsRecycler;
    RecyclerView.Adapter adapter;
    private AnnouncemetsAdapter recyclerAdapter;
    private GenUserInterfaceAdapter genrecyclerAdpter;
    private GradientDrawable gradient1, gradient2, getGradient3, getGradient4;
    ImageView drawerMenuIcon, menuPop;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        profileFab = findViewById(R.id.floatingActionButton);
        logoutFab = findViewById(R.id.floatingActionButton_logout);
        profilefabName = findViewById(R.id.textView);
        logoutfabName = findViewById(R.id.textView_logout);

        isOpen = false;

        recentAnnouncementsRecycler = findViewById(R.id.recent_announcements_recycler);
        campusBuzzMvRecycler = findViewById(R.id.campus_buzz_mv_recycler);
        upcomingEventsRecycler = findViewById(R.id.upcoming_events_recycler);
        drawerMenuIcon = findViewById(R.id.drawer_menu_icon);
        contentView = findViewById(R.id.content);
        menuPop = findViewById(R.id.menu_imgv);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav_view);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        navigationDrawer();

        recentAnnouncementsRecycler();
        campusBuzzMvRecycler();
        upcomingEventsRecycler();

        menuPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    menuPop.setImageResource(R.drawable.more_icon);
                    profileFab.setVisibility(View.INVISIBLE);
                    profilefabName.setVisibility(View.INVISIBLE);
                    logoutFab.setVisibility(View.INVISIBLE);
                    logoutfabName.setVisibility(View.INVISIBLE);

                    isOpen= false;
                } else {
                    menuPop.setImageResource(R.drawable.ic_icone_close);
                    profileFab.setVisibility(View.VISIBLE);
                    profilefabName.setVisibility(View.VISIBLE);
                    logoutFab.setVisibility(View.VISIBLE);
                    logoutfabName.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
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

        final ArrayList<uploadsModel> recentAnnouncements = new ArrayList<>();

        Query query = databaseReference.child("Events");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    uploadsModel uploadsModel = new uploadsModel();
                    uploadsModel.setImageURL(snapshot.child("imageURL").getValue().toString());
                    uploadsModel.setTitle(snapshot.child("itemName").getValue().toString());
                    uploadsModel.setDescription(snapshot.child("itemDesc").getValue().toString());
                    recentAnnouncements.add(uploadsModel);
                }
                genrecyclerAdpter = new GenUserInterfaceAdapter(UserDashboard.this, recentAnnouncements);
                upcomingEventsRecycler.setAdapter(genrecyclerAdpter);
                genrecyclerAdpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void campusBuzzMvRecycler() {
        campusBuzzMvRecycler.setHasFixedSize(true);
        campusBuzzMvRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        final ArrayList<uploadsModel> recentAnnouncements = new ArrayList<>();

        Query query = databaseReference.child("CampusBuzz");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    uploadsModel uploadsModel = new uploadsModel();
                    uploadsModel.setImageURL(snapshot.child("imageURL").getValue().toString());
                    uploadsModel.setTitle(snapshot.child("itemName").getValue().toString());
                    uploadsModel.setDescription(snapshot.child("itemDesc").getValue().toString());
                    recentAnnouncements.add(uploadsModel);
                }
                genrecyclerAdpter = new GenUserInterfaceAdapter(UserDashboard.this, recentAnnouncements);
                campusBuzzMvRecycler.setAdapter(genrecyclerAdpter);
                genrecyclerAdpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void recentAnnouncementsRecycler() {
        recentAnnouncementsRecycler.setHasFixedSize(true);
        recentAnnouncementsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        final ArrayList<uploadsModel> recentAnnouncements = new ArrayList<>();

        Query query = databaseReference.child("Announcements");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    uploadsModel uploadsModel = new uploadsModel();
                    uploadsModel.setImageURL(snapshot.child("imageURL").getValue().toString());
                    uploadsModel.setTitle(snapshot.child("itemName").getValue().toString());
                    uploadsModel.setDescription(snapshot.child("itemDesc").getValue().toString());
                    recentAnnouncements.add(uploadsModel);
                }
                recyclerAdapter = new AnnouncemetsAdapter(UserDashboard.this, recentAnnouncements);
                recentAnnouncementsRecycler.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.announcements,"Fee Deadline", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
//        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.faqs,"Semester Resumption", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
//        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.profile,"Exam Dates", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
//        recentAnnouncements.add(new RecentAnnouncementsHelperClass(R.drawable.lost_and_found1,"Council Elections", "nknkn jbnk jbjbjn jbjbnk jbjjn jbjbhbf jbjbjj bjbjnj jbjbjn"));
//
//        adapter = new RecentAnnouncementsAdapter(recentAnnouncements);
//        recentAnnouncementsRecycler.setAdapter(adapter);
//    }

    public void startupscreen(View view) {
        startActivity(new Intent(this, StudentStartUpScreen.class));
    }

    public void schoolmapscreen(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void studentportalscreen(View view) {
        startActivity(new Intent(this, StudentPortal.class));
    }

    public void logoutUser(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void CallUserProfle(View view) {
        startActivity(new Intent(UserDashboard.this, UserProfile.class));
    }

    public void OpenEmergencyContacts(View view) {
        startActivity(new Intent(getApplicationContext(), contacts.class));
        finish();
    }

    public void openschoolWeb(View view) {
        String url = "https://www.uoeld.ac.ke/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}