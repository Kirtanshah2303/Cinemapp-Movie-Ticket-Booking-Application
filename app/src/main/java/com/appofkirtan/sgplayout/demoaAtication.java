package com.appofkirtan.sgplayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.slice.Slice;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.xml.sax.helpers.XMLReaderAdapter;

import java.util.ArrayList;
import java.util.List;

import www.sanju.motiontoast.MotionToast;

public class demoaAtication extends AppCompatActivity {

    public static Activity fa;

    //Movie selection list for Recycler View Hooking..
    private RecyclerView mRecyclerView;
    private Movie_Adapter mAdepter;
//    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager HorizontalLayout;

    //Firebase,DrawerLayout Declaration
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    MenuView.ItemView logout;
    FirebaseAuth mauth;
    Button ratebtn;
    RatingBar ratingStars;
    TextView emailheader,headingMovie;

   public static String currentuser;
    //FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

    //Automated
    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoa_atication);

        fa =this;

        SeatBookingActivity.seatnumber.clear();
        SeatBookingActivity.demo.clear();

        //Defining variables with their IDs.
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_id);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        logout = (MenuView.ItemView)findViewById(R.id.logout_drawer);

        headingMovie = findViewById(R.id.heading_movie);

//        headingMovie.setText("Current version is = "+UpdateHelperClass.appVersion);

        mauth=FirebaseAuth.getInstance();
        currentuser = mauth.getCurrentUser().getEmail();


        View headerview = navigationView.getHeaderView(0);
        emailheader = headerview.findViewById(R.id.email_header);
        emailheader.setText(currentuser);




      /* emailheader.setText(Fragment_Signin.emailuser);
            String emailuser = currentuser.getEmail();
            emailheader.setText(emailuser);*/

        setSupportActionBar(toolbar);

        //Giving functionality to the Hamburgur Icon to open Navigation drawer.
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Setting up Image slider (Automated) with the help of 3rd party Dependency added in manifest
        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.joblo.com/assets/images/joblo/posters/2020/03/2EF9FAE7-B888-4DBA-868D-B4E289BAE769.jpeg","Black Widow"));
        slideModels.add(new SlideModel("https://www.mauvais-genres.com/26247/avengers-endgame-original-movie-poster-15x21-in-2019-anthony-russo-robert-downey-jr.jpg","Avengers Endgame"));
        slideModels.add(new SlideModel("https://www.arthipo.com/image/cache/catalog/genel-tasarim/all-posters/sinema-cinema-film-postersleri/yabanci-filmler/1/pfilm1491-hacker_7cf19a97-poster-movie-film-1000x1000.jpg","Hacker"));
        imageSlider.setImageList(slideModels,false);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.logout_drawer: {
                        Toast.makeText(demoaAtication.this, "Logged out", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(demoaAtication.this,MainActivity.class));
                        return true;
                    }
                    case R.id.aboutUs:{
                        Toast.makeText(demoaAtication.this,"Aboout Us Clicked",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(demoaAtication.this,AboutUs_activity.class));
                        return  true;
                    }
                    case  R.id.RateUs:{
                        Dialog dialog =new Dialog(demoaAtication.this);
                        dialog.setContentView(R.layout.activity_rate_us);
                        dialog.setCanceledOnTouchOutside(false);

                        ratebtn = dialog.findViewById(R.id.ratingbtn);
                        ratingStars = dialog.findViewById(R.id.ratingBar);
                        //set dialog width and height
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        //Set transparent background
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        //set Animation
                        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                        dialog.show();

                        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                                int rating = (int)v;
                                String message = null;

                                switch (rating){
                                    case 1:
                                        message = "Sorry to hear that";
                                        break;
                                    case 2:
                                        message = "We always accept suggestions!!";
                                        break;
                                    case 3:
                                        message = "Good enough";
                                        break;
                                    case 4:
                                        message = "Great!! Thank you";
                                        break;
                                    case 5:
                                        message = "Awesome!! Thank you..";
                                        break;

                                }
                                Toast.makeText(demoaAtication.this,message,Toast.LENGTH_SHORT).show();
                            }
                        });
                        return true;
                    }
                }
                return true;
            }
        });

        ArrayList<MovieItem> movielist = new ArrayList<>();
        movielist.add(new MovieItem(R.drawable.movie1,"Avengers Endgame"));
        movielist.add(new MovieItem(R.drawable.movie2,"Hacker"));
        movielist.add(new MovieItem(R.drawable.movie4,"Harry Potter"));
        movielist.add(new MovieItem(R.drawable.movie3,"WAR"));

        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        /*HorizontalLayout
                = new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);*/
        HorizontalLayout = new LinearLayoutManager(demoaAtication.this,LinearLayoutManager.HORIZONTAL,false);
       // mLayoutManager = new LinearLayoutManager(this);
        mAdepter = new Movie_Adapter(movielist,this);
        mRecyclerView.setLayoutManager(HorizontalLayout);
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdepter);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.Movie_search);
        SearchView searchView  = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdepter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}