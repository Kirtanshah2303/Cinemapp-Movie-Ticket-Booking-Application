package com.appofkirtan.sgplayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ConfirmationActivity extends AppCompatActivity {

    ArrayList<Integer> SeatNumber = new ArrayList<Integer>();
    ArrayList<Integer> bookedseat = new ArrayList<Integer>();

    TextView movienameP,selecetedseatP,timeP,dateP,amountP;

   public static int Price=0;
    int Goldseat=0,SilverSeat=0;

    Button confirmationBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        movienameP = findViewById(R.id.textView7);
        dateP = findViewById(R.id.textView8);
        timeP = findViewById(R.id.textView9);
        selecetedseatP = findViewById(R.id.textview10);
        amountP = findViewById(R.id.textview11);
        confirmationBtn = findViewById(R.id.confirmationBtn);


        movienameP.setText("Movie name :"+demothirdActivity.moviename);
        dateP.setText("Date :"+demothirdActivity.databasedate);
        timeP.setText("Time :"+demothirdActivity.databasetime);


        SeatNumber = (ArrayList<Integer>)getIntent().getSerializableExtra("SeatNumber");
        bookedseat = (ArrayList<Integer>)getIntent().getSerializableExtra("BookedSeat");

        selecetedseatP.setText("Seleceted seats :"+SeatNumber+"");

        for (int i=0;i<SeatNumber.size();i++){

            int temp=0;
            temp = Integer.parseInt(String.valueOf(SeatNumber.get(i)));

            if (temp<=10){
                SilverSeat++;
            }else{
                Goldseat++;
            }

        }


        Price = (SilverSeat*100) + (Goldseat*250);

        amountP.setText("Total  Amount = " + Price);

        confirmationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmationActivity.this,PaymentActivity.class);
                intent.putExtra("SeatNumber",SeatNumber);
                intent.putExtra("BookedSeat",bookedseat);
                intent.putExtra("Price",Price);
                startActivity(intent);
            }
        });

    }
}