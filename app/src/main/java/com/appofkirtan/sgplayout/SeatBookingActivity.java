package com.appofkirtan.sgplayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class SeatBookingActivity extends AppCompatActivity {

    ToggleButton seat1,seat2,seat3,seat4,seat5;
    ToggleButton seat6,seat7,seat8,seat9,seat10;
    ToggleButton seat11,seat12,seat13,seat14,seat15;
    ToggleButton seat16,seat17,seat18,seat19,seat20;
    ToggleButton seat21,seat22,seat23,seat24,seat25;
    Button button;
    FirebaseDatabase realtimedatabase;
    DatabaseReference realtimereference;

    // Booked Seat ArrayList
    public static  ArrayList demo = new ArrayList();

    //Selected Seat Array List
    public static ArrayList seatnumber = new ArrayList();

    int Price;


    int temp=0;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_booking);


        final ProgressDialog progressDialog;

        progressDialog = new ProgressDialog(SeatBookingActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },4000);


        seat1 = findViewById(R.id.seat1);
        seat2 = findViewById(R.id.seat2);
        seat3 = findViewById(R.id.seat3);
        seat4 = findViewById(R.id.seat4);
        seat5 = findViewById(R.id.seat5);
        seat6 = findViewById(R.id.seat6);
        seat7 = findViewById(R.id.seat7);
        seat8 = findViewById(R.id.seat8);
        seat9 = findViewById(R.id.seat9);
        seat10 = findViewById(R.id.seat10);
        seat11 = findViewById(R.id.seat11);
        seat12 = findViewById(R.id.seat12);
        seat13 = findViewById(R.id.seat13);
        seat14 = findViewById(R.id.seat14);
        seat15 = findViewById(R.id.seat15);
        seat16 = findViewById(R.id.seat16);
        seat17 = findViewById(R.id.seat17);
        seat18 = findViewById(R.id.seat18);
        seat19 = findViewById(R.id.seat19);
        seat20 = findViewById(R.id.seat20);
        seat21 = findViewById(R.id.seat21);
        seat22 = findViewById(R.id.seat22);
        seat23 = findViewById(R.id.seat23);
        seat24 = findViewById(R.id.seat24);
        seat25 = findViewById(R.id.seat25);
        realtimedatabase=FirebaseDatabase.getInstance();
        button = findViewById(R.id.button);


        realtimereference = realtimedatabase.getReference(demothirdActivity.moviename).child(demothirdActivity.databasedate).child(demothirdActivity.databasetime);
        realtimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //demo.add(Integer.parseInt(snapshot.getValue().toString()));
                    int temp = Integer.parseInt(snapshot.getValue().toString());
                    demo.add(temp);
                }
                if (demo.contains(1)){
                    temp++;
                    seat1.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(1)){
                    seat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked) {
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            } else {
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });

                }
                if (demo.contains(2)){
                    seat2.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(2)){
                    seat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(3)){
                    temp++;
                    seat3.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(3)){
                    seat3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(4)){
                    temp++;
                    seat4.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(4)){
                    seat4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(5)){
                    temp++;
                    seat5.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(5)){
                    seat5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(6)){
                    seat6.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(6)){
                    seat6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(7)){
                    seat7.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(7)){
                    seat7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(8)){
                    temp++;
                    seat8.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }
                if (!demo.contains(8)){
                    seat8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(9)){
                    seat9.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(10)){
                    seat10.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(11)){
                    seat11.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(12)){
                    seat12.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(13)){
                    seat13.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(14)){
                    seat14.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(15)){
                    seat15.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(16)){
                    seat16.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(17)){
                    seat17.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else {
                    seat17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(18)){
                    seat18.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(19)){
                    seat19.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(20)){
                    seat20.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(21)){
                    seat21.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(22)){
                    seat22.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(23)){
                    seat23.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(24)){
                    seat24.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
                if (demo.contains(25)){
                    seat25.setBackground(getResources().getDrawable(R.drawable.ic_seats_booked));
                    // seat1.isClickable();
                }else{
                    seat25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @SuppressLint("UseCompatLoadingForDrawables")
                        @Override
                        public void onCheckedChanged(CompoundButton buttonview, boolean ischecked) {
                            if (ischecked){
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seats_selected));
                                seatnumber.add(buttonview.getText());
                            }else{
                                buttonview.setBackground(getResources().getDrawable(R.drawable.ic_seat));
                                seatnumber.remove(buttonview.getText());
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SeatBookingActivity.this,SeatBookingActivity.this.toString(),Toast.LENGTH_SHORT).show();
            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatBookingActivity.this,ConfirmationActivity.class);
                intent.putExtra("SeatNumber",seatnumber);
                intent.putExtra("BookedSeat",demo);
                startActivity(intent);
                Toast.makeText(SeatBookingActivity.this,""+demo+"  "+temp,Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
     //   demo = null;
        demo.clear();
        seatnumber.clear();
        super.onBackPressed();
    }
}