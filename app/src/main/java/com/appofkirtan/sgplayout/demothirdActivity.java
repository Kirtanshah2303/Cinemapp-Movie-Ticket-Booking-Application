package com.appofkirtan.sgplayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class demothirdActivity extends AppCompatActivity {

    TextView nameMovie;
    ImageView ImageMovie;
    Button bookbtn;
    Calendar calendar;
    SimpleDateFormat DateFormat;
    int currentTime;
    public static String moviename;
    String Date,Date2,Date3,Date4;
    String currnttime;
    public static String databasetime,databasedate;

    FirebaseDatabase realtimedatabase;
    DatabaseReference realtimereference1;
    DatabaseReference realtimereference2;
    DatabaseReference realtimereference3;
    DatabaseReference realtimereference4;

    int Seatnumber;

    ArrayList list1 = new ArrayList();
    ArrayList list2 = new ArrayList();
    ArrayList list3 = new ArrayList();
    ArrayList list4 = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demothird);

        nameMovie = findViewById(R.id.MovieName);
        ImageMovie = findViewById(R.id.MovieImage);
        bookbtn = findViewById(R.id.Bookingbtn);

        realtimedatabase=FirebaseDatabase.getInstance();
//
//        realtimereference1 = realtimedatabase.getReference(demothirdActivity.moviename).child(demothirdActivity.databasedate).child("09:00");
//        realtimereference1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //demo.add(Integer.parseInt(snapshot.getValue().toString()));
//                    int temp = Integer.parseInt(snapshot.getValue().toString());
//                    list1.add(temp);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        realtimereference2 = realtimedatabase.getReference(demothirdActivity.moviename).child(demothirdActivity.databasedate).child("12:00");
//        realtimereference2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //demo.add(Integer.parseInt(snapshot.getValue().toString()));
//                    int temp = Integer.parseInt(snapshot.getValue().toString());
//                    list2.add(temp);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        realtimereference3 = realtimedatabase.getReference(demothirdActivity.moviename).child(demothirdActivity.databasedate).child("15:00");
//        realtimereference3.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //demo.add(Integer.parseInt(snapshot.getValue().toString()));
//                    int temp = Integer.parseInt(snapshot.getValue().toString());
//                    list3.add(temp);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        realtimereference4 = realtimedatabase.getReference(demothirdActivity.moviename).child(demothirdActivity.databasedate).child("18:00");
//        realtimereference4.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //demo.add(Integer.parseInt(snapshot.getValue().toString()));
//                    int temp = Integer.parseInt(snapshot.getValue().toString());
//                    list4.add(temp);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        calendar = Calendar.getInstance();


        if (getIntent().hasExtra("MovieName") && getIntent().hasExtra("MovieImage")){
            moviename = getIntent().getStringExtra("MovieName");
            int movieimg = getIntent().getIntExtra("MovieImage",0);

            nameMovie.setText(moviename);
            ImageMovie.setImageResource(movieimg);

        }
        DateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date = DateFormat.format(calendar.getTime());
      //  date1.setText(Date);

        calendar.add(Calendar.DAY_OF_MONTH,1);
         Date2 = DateFormat.format(calendar.getTime());
       // date2.setText(Date2);


        calendar.add(Calendar.DAY_OF_MONTH,1);
        Date3 = DateFormat.format(calendar.getTime());
       // date3.setText(Date3);

        calendar.add(Calendar.DAY_OF_MONTH,1);
        Date4 = DateFormat.format(calendar.getTime());
       // date4.setText(Date4);


        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog =new Dialog(demothirdActivity.this);
                dialog.setContentView(R.layout.date_picker_dialog);
                dialog.setCanceledOnTouchOutside(true);
                //set dialog width and height
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                //Set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //set Animation
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                Button date1 = dialog.findViewById(R.id.date1);
                Button date2 = dialog.findViewById(R.id.date2);
                Button date3 = dialog.findViewById(R.id.date3);
                Button date4 = dialog.findViewById(R.id.date4);

                SimpleDateFormat currntTime = new SimpleDateFormat("HH:mm ");
                currnttime = currntTime.format(calendar.getTime());
                //databasetime=currnttime;
                for(int i=0;i<2;i++){
                    int temp;
                    temp = Integer.parseInt(String.valueOf(currnttime.charAt(i)));
                    if (i==0){
                        currentTime = temp*10;
                    }else{
                        currentTime = currentTime+temp;
                    }
                }

               /* DateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String Date;
                Date = DateFormat.format(calendar.getTime());
                date1.setText(Date);

                calendar.add(Calendar.DAY_OF_MONTH,1);
                String Date2 = DateFormat.format(calendar.getTime());
                date2.setText(Date2);


                calendar.add(Calendar.DAY_OF_MONTH,1);
                String Date3 = DateFormat.format(calendar.getTime());
                date3.setText(Date3);

                calendar.add(Calendar.DAY_OF_MONTH,1);
                String Date4 = DateFormat.format(calendar.getTime());
                date4.setText(Date4);*/
               date1.setText(Date);
               date2.setText(Date2);
               date3.setText(Date3);
               date4.setText(Date4);

//               if (list1.size()==25){
//                   date1.setClickable(false);
//               }
//                if (list2.size()==25){
//                    date1.setClickable(false);
//                }
//                if (list3.size()==25){
//                    date1.setClickable(false);
//                }
//                if (list4.size()==25){
//                    date1.setClickable(false);
//                }

               date1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       gettime(Date,currentTime,Date);
                       databasedate=Date;

                   }
               });
                date2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gettime(Date2,currentTime,Date);
                        databasedate=Date2;
                       // databasedate=Date2;
//                        gettime(Date2,currentTime,Date);
                    }
                });
                date3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gettime(Date3,currentTime,Date);
                            databasedate=Date3;

                           // databasedate=Date3;
                        }
                });

                date4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gettime(Date4,currentTime,Date);

                        databasedate=Date4;
                       // databasedate=Date4;
                    }
                });


                dialog.show();

            }
        });

    }



            @SuppressLint("SetTextI18n")
    public void gettime(String date,int Time,String currentDate){

        int a=9;
        int b=12;
        int c=15;
        int d=18;



       // heading.setText("Please Select the Time for "+ date +"from here");

        Dialog dialog =new Dialog(demothirdActivity.this);
        dialog.setContentView(R.layout.time_picker_dialog);
        dialog.setCanceledOnTouchOutside(true);
        //set dialog width and height
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        //Set transparent background
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //set Animation
        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

        Button time1 = dialog.findViewById(R.id.time1);
        Button time2 = dialog.findViewById(R.id.time2);
        Button time3 = dialog.findViewById(R.id.time3);
        Button time4 = dialog.findViewById(R.id.time4);

        if (Time >= d && date.equals(currentDate)){
           /* time1.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);*/
            time1.setAlpha(.5f);
            time2.setAlpha(.5f);
            time3.setAlpha(.5f);
            time4.setAlpha(.5f);
            time1.setClickable(false);
            time2.setClickable(false);
            time3.setClickable(false);
            time4.setClickable(false);
        }else if(Time >= c && Time <d && date.equals(currentDate)){
            /*time1.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);*/
            time1.setAlpha(.5f);
            time2.setAlpha(.5f);
            time3.setAlpha(.5f);
            time1.setClickable(false);
            time2.setClickable(false);
            time3.setClickable(false);

                time4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databasetime = "18:00";
                        startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                    }
                });

        }else if(Time >= b && Time <c && date.equals(currentDate)){
           /* time1.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);*/
            time1.setAlpha(.5f);
            time2.setAlpha(.5f);
            time1.setClickable(false);
            time2.setClickable(false);
            time3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "15:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
            time4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "18:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
        }else if(Time >= a && Time < b && date.equals(currentDate)){
            //time2.setVisibility(View.INVISIBLE);
            time1.setAlpha(.5f);
            time1.setClickable(false);
            time2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "12:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
            time3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "15:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
            time4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "18:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
        }else{
            time1.setClickable(true);
            time2.setClickable(true);
            time3.setClickable(true);
            time4.setClickable(true);
            time1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "09:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
            time2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "12:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
            time3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "15:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
            time4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databasetime = "18:00";
                    startActivity(new Intent(demothirdActivity.this,SeatBookingActivity.class));
                }
            });
        }

        /*time1.setText("09:00");
        time2.setText("12:00");
        time3.setText("15:00");
        time4.setText("18:00");*/

        TextView heading;
        heading = dialog.findViewById(R.id.TimePickerHeading);

        heading.setText("Please Select the Time for "+ date +" from here");

        dialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}