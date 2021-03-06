package com.appofkirtan.sgplayout;
//"Movie : "+demothirdActivity.moviename + "\n" + "Date :" + demothirdActivity.databasedate + "\n" + "Time :" + demothirdActivity.databasetime + "\n" + "Booked Seats : " + seattemp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class PaymentActivity extends AppCompatActivity {
    private static final int STORAGE_CODE = 1000;

    //  private long backpressedtime;

    String mail="";

    CardView axisbank,UPI;
    Button savebtn,homebtn;

    String Seats="Seats";
    String currentUser = demoaAtication.currentuser;

//    ImageButton homebtn;

    Bitmap bitmap;

    FirebaseDatabase realtimedatabase;
    DatabaseReference realtimereference;

   public static ArrayList<Integer> SeatNumber = new ArrayList<Integer>();
   public static ArrayList<Integer> bookedseat = new ArrayList<Integer>();

   ArrayList UserBookedSeat = new ArrayList();
    //Combined storage Arraylist
   public static ArrayList seattemp = new ArrayList();

    TextView amount;

    String validation ;

    ImageView mimage;


    //    String sEmail = "malavkirtan@gmail.com";
//    String sPasssword = "AnroidAppSgp";
    String subject = "Booked Tickets";
    String message1= "Hii this is for testing purpose only thanks for visiting " +
            "From" +
            "Malav Sukhadia" +
            "Kirtan Shah";
    
    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        ArrayList list = new ArrayList();
        for (int i=0;i<currentUser.length();i++){
            list.add(currentUser.charAt(i));
        }
        for (int i=0;i<currentUser.length();i++){
            if (currentUser.charAt(i)=='.'){
                list.set(i,"-");
            }
        }
        for (int i=0;i<list.size();i++){
            mail = mail+list.get(i);
        }



        demoaAtication.fa.finish();
        demothirdActivity.fb.finish();
        SeatBookingActivity.fc.finish();
        ConfirmationActivity.fd.finish();

        axisbank = findViewById(R.id.AxisBank);
        UPI = findViewById(R.id.UPI);


        savebtn = findViewById(R.id.save);
        homebtn = findViewById(R.id.home);
//        mimage = findViewById(R.id.imageView);
        Intent intent = getIntent();
        int price = intent.getIntExtra("Price",1);

        amount = findViewById(R.id.amount);
        amount.setText("Total Amount to pay : "+price+"");

       // icicibank = findViewById(R.id.ICICIBank);

       /* movienameP = findViewById(R.id.textView7);
        dateP = findViewById(R.id.textView8);
        timeP = findViewById(R.id.textView9);
        selecetedseatP = findViewById(R.id.textview10);
        amountP = findViewById(R.id.textview11);

        movienameP.setText("Movie name :"+demothirdActivity.moviename);
        dateP.setText("Date :"+demothirdActivity.databasedate);
        timeP.setText("Time :"+demothirdActivity.databasetime);*/


        realtimedatabase=FirebaseDatabase.getInstance();


        realtimereference =realtimedatabase.getReference(demothirdActivity.moviename).child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child("User").child(mail);
        realtimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //demo.add(Integer.parseInt(snapshot.getValue().toString()));
                    int temp = Integer.parseInt(snapshot.getValue().toString());
                    UserBookedSeat.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Intent intent = getIntent();
       // SeatNumber.add(intent.getIntExtra("SeatNumber",0));
        SeatNumber = (ArrayList<Integer>)getIntent().getSerializableExtra("SeatNumber");
        bookedseat = (ArrayList<Integer>)getIntent().getSerializableExtra("BookedSeat");

        validation = "Valid tickets for "+demothirdActivity.moviename +" On "+ demothirdActivity.databasedate +" and "+ demothirdActivity.databasetime + " and seat :"+ SeatNumber;

        /*selecetedseatP.setText("Seleceted seats :"+SeatNumber);

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

        amountP.setText("Total  Amount = " + Price);*/

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seattemp.clear();
                SeatNumber.clear();
                bookedseat.clear();
                startActivity(new Intent(PaymentActivity.this,demoaAtication.class));
                finish();
            }
        });


        UPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog =new Dialog(PaymentActivity.this);
                dialog.setContentView(R.layout.upi_information_dialog);
                dialog.setCanceledOnTouchOutside(true);
                //set dialog width and height
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                //Set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //set Animation
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                Button proceed = dialog.findViewById(R.id.proceed_upi);
                final EditText UPINumber = dialog.findViewById(R.id.upiNumber);

                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number = UPINumber.getText().toString().trim();
                        UPITesting testing = new UPITesting();

                        boolean   reciver =  testing.validateUPINumber(number);

                        if (reciver == true){
                            seattemp = (ArrayList) SeatNumber.clone();
                            seattemp.addAll((ArrayList)bookedseat);
                            UserBookedSeat.addAll(SeatNumber);
                            // UserHelperclass helperclass=new UserHelperclass(SeatNumber);
                            realtimereference=realtimedatabase.getReference(demothirdActivity.moviename);
                            realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child(Seats).setValue(seattemp);
                            realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child("User").child(mail).setValue(UserBookedSeat);

                            if (seattemp.size()==25){
//                                realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).setValue("done");
                                seattemp.add(26);
                                realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child(Seats).setValue(seattemp);
                            }

                            Dialog dialog =new Dialog(PaymentActivity.this);
                            dialog.setContentView(R.layout.success_dialog);
                            dialog.setCanceledOnTouchOutside(false);
                            //set dialog width and height
                            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                            //Set transparent background
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            //set Animation
                            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                            dialog.show();

                            savebtn.setVisibility(View.VISIBLE);
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            try{
                                BitMatrix bitMatrix = multiFormatWriter.encode(validation, BarcodeFormat.QR_CODE,400,400);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                bitmap = barcodeEncoder.createBitmap(bitMatrix);
//                                mimage.setImageBitmap(bitmap);
                            }catch (WriterException e){
                                e.printStackTrace();
                            }


                            if (savebtn.getVisibility() == View.VISIBLE){
                                savebtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                                            // System OS >= Marshmallow(6.0), check If permission is enabled or not
                                            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                                                //permission was not  granted , request it.
                                                String[] permissoins = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                                requestPermissions(permissoins,STORAGE_CODE);
                                            }else {
                                                // permission already granted , call pdf save method
                                                savepdf();
                                                savebtn.setClickable(false);
                                                savebtn.setAlpha(.5f);
                                                savebtn.setText("Saved");
                                            }
                                        }else {
                                            //system OS < Marshmallow  no required to check runtime permission , call savepdf method
                                            savepdf();
                                            savebtn.setClickable(false);
                                            savebtn.setAlpha(.5f);
                                            savebtn.setText("Saved");
                                        }
                                    }
                                });
                            }




                        }else {
                            Toast.makeText(PaymentActivity.this,"Something Went wrong try again",Toast.LENGTH_SHORT).show();
                            Dialog dialog =new Dialog(PaymentActivity.this);
                            dialog.setContentView(R.layout.upi_error_dialog);
                            dialog.setCanceledOnTouchOutside(false);
                            //set dialog width and height
                            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                            //Set transparent background
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            //set Animation
                            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                            dialog.show();
                        }

                    }
                });
                dialog.show();
            }
        });

        axisbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this,"Payment Through Axis Bank card",Toast.LENGTH_SHORT).show();
               //Collections.copy(seattemp,SeatNumber);
               /* seattemp = (ArrayList) SeatNumber.clone();
                seattemp.addAll((ArrayList)bookedseat);
               // UserHelperclass helperclass=new UserHelperclass(SeatNumber);
                realtimereference=realtimedatabase.getReference(demothirdActivity.moviename);
                realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).setValue(seattemp);*/

                Dialog dialog =new Dialog(PaymentActivity.this);
                dialog.setContentView(R.layout.creditcard_information_dialog);
                dialog.setCanceledOnTouchOutside(true);
                //set dialog width and height
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                //Set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //set Animation
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                Button proceed = dialog.findViewById(R.id.proceed_payment);
                final EditText creditcardnumber = dialog.findViewById(R.id.creditcardNumber);


                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number = creditcardnumber.getText().toString().trim();
                        CreditCardTesting testing = new CreditCardTesting();

                        boolean   reciver =  testing.validateCreditCardNumber(number);


                        if (reciver == true){
                            seattemp = (ArrayList) SeatNumber.clone();
                            seattemp.addAll((ArrayList)bookedseat);
                            UserBookedSeat.addAll(SeatNumber);
                            // UserHelperclass helperclass=new UserHelperclass(SeatNumber);
                            realtimereference=realtimedatabase.getReference(demothirdActivity.moviename);
                            realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child(Seats).setValue(seattemp);
                            realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child("User").child(mail).setValue(UserBookedSeat);


                            if (seattemp.size()==25){
//                                realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).setValue("done");
                                seattemp.add(26);
                                realtimereference.child(demothirdActivity.databasedate).child(demothirdActivity.databasetime).child(Seats).setValue(seattemp);
                            }

                            Dialog dialog =new Dialog(PaymentActivity.this);
                            dialog.setContentView(R.layout.success_dialog);
                            dialog.setCanceledOnTouchOutside(false);
                            //set dialog width and height
                            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                            //Set transparent background
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            //set Animation
                            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                            dialog.show();

                            savebtn.setVisibility(View.VISIBLE);
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            try{
                                BitMatrix bitMatrix = multiFormatWriter.encode(validation, BarcodeFormat.QR_CODE,400,400);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                 bitmap = barcodeEncoder.createBitmap(bitMatrix);
//                                mimage.setImageBitmap(bitmap);
                            }catch (WriterException e){
                                e.printStackTrace();
                            }


                            if (savebtn.getVisibility() == View.VISIBLE){
                                savebtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                                            // System OS >= Marshmallow(6.0), check If permission is enabled or not
                                            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                                                //permission was not  granted , request it.
                                                String[] permissoins = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                                requestPermissions(permissoins,STORAGE_CODE);
                                            }else {
                                                // permission already granted , call pdf save method
                                                savepdf();
                                                savebtn.setClickable(false);
                                                savebtn.setAlpha(.5f);
                                                savebtn.setText("Saved");
                                            }
                                        }else {
                                            //system OS < Marshmallow  no required to check runtime permission , call savepdf method
                                            savepdf();
                                            savebtn.setClickable(false);
                                            savebtn.setAlpha(.5f);
                                            savebtn.setText("Saved");
                                        }
                                    }
                                });
                            }




                        }else {
                            Toast.makeText(PaymentActivity.this,"Something Went wrong try again",Toast.LENGTH_SHORT).show();
                            Dialog dialog =new Dialog(PaymentActivity.this);
                            dialog.setContentView(R.layout.error_creditcard_dialog);
                            dialog.setCanceledOnTouchOutside(false);
                            //set dialog width and height
                            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                            //Set transparent background
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            //set Animation
                            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                            dialog.show();
                        }
                    }
                });


               dialog.show();

            }
        });

    }

    private void savepdf() {
        Document mDoc = new Document();

        //pdf file name
        String mFileName = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        //pdf file pathg
        String mFilePath = Environment.getExternalStorageDirectory()+ "/" +"Cinemapp"+"/"+ mFileName +".pdf";
        File f = new File(Environment.getExternalStorageDirectory(), "Cinemapp");
        if (!f.exists()) {
            f.mkdirs();
        }


        try{
            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc,new FileOutputStream(mFilePath));



            ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
            ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream3);
            demothirdActivity.icon.compress(Bitmap.CompressFormat.PNG, 100, stream4);
            Image maimg = Image.getInstance(stream3.toByteArray());
            Image movieimag = Image.getInstance(stream4.toByteArray());

//            maimg.setAbsolutePosition(350, 500);
            maimg.scalePercent(40);

            movieimag.scalePercent(10);



//            mDoc.add(maimg);
            //open the document for writing
            mDoc.open();
            mDoc.add(movieimag);
            //get text
            String mText ="\n"+"Registered Email Address : "+demoaAtication.currentuser + "\n\n"+ "Movie : "+demothirdActivity.moviename + "\n\n" + "Date :" + demothirdActivity.databasedate + "\n\n" + "Time :" + demothirdActivity.databasetime + "\n\n" + "Booked Seats : " + SeatNumber;
//                    add paragraph  to the document
//            mDoc.add(new Paragraph(mText));
            mDoc.add(new Paragraph(new Phrase(10f,mText, FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE,16f))));
            mDoc.add(maimg);
            //close the document
            mDoc.close();
            //show message that file is saved succesfully
            Toast.makeText(this,mFileName+".pdf\n is saved to\n"+mFilePath,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            //if anything goes wrong causing exception  , get and show exception message
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted from popup , call savepdf method
                    savepdf();
                }else{
                    //permission was denied from popup , show error message
                    Toast.makeText(this,"Permission denied please except permission to save tickets as pdf in your device",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}