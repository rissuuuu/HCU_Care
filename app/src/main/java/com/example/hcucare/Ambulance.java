package com.example.hcucare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.opencensus.internal.StringUtils;

public class Ambulance extends AppCompatActivity {
    Spinner spinner_condtn,spinner_hostl;
    Button button;
    EditText text_roomno;
    String conditions="";
    String hostel_name="";
    EditText phone_no;
    getAmbulance data;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dfsendmessage;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Button back;
    String roomno;
    String phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        spinner_condtn=findViewById(R.id.spinner_condition);
        spinner_hostl=findViewById(R.id.spinner_Hostel);
        button=findViewById(R.id.id_requestambulance);
        text_roomno=findViewById(R.id.id_roomno);
        phone_no=findViewById(R.id.id_phonenumber);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        dfsendmessage=FirebaseDatabase.getInstance().getReference().child("Ambulance");
        data=new getAmbulance();

        back=findViewById(R.id.id_backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> condition = new ArrayList<>();
        condition.add("Select Emergency");
        condition.add("Bleeding");
        condition.add("Breathing difficulties");
        condition.add("Someone Died");
        condition.add("Heart attack");
        condition.add("Severe pain");
        condition.add("A stroke");
        condition.add("Accident");
        condition.add("Fainted");
        condition.add("Other/Urgent");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, condition);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_condtn.setAdapter(dataAdapter);

        List<String> hostels = new ArrayList<>();
        hostels.add("Select the Hostel");
        hostels.add("Ladies Hostels");
        hostels.add("LH-1");
        hostels.add("LH-2");
        hostels.add("LH-3");
        hostels.add("LH-4");
        hostels.add("LH-5");
        hostels.add("LH-6");
        hostels.add("LH-7");
        hostels.add("LH-8");
        hostels.add("LH-9");
        hostels.add("Mens Hostels");
        hostels.add("MH-A");
        hostels.add("MH-B");
        hostels.add("MH-C");
        hostels.add("MH-D");
        hostels.add("MH-E");
        hostels.add("MH-F");
        hostels.add("MH-G");
        hostels.add("MH-H");
        hostels.add("MH-I");
        hostels.add("MH-J");
        hostels.add("MH-K");
        hostels.add("MH-L");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hostels);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hostl.setAdapter(dataAdapter2);


        spinner_condtn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                conditions= (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_hostl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hostel_name= (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roomno=text_roomno.getText().toString();
                phno=phone_no.getText().toString();
                Date currentTime = Calendar.getInstance().getTime();
                String time=currentTime.toString();
                String globaltime=time.substring(0,time.length()-15);

                if (conditions.equals("Select Emergency")||hostel_name.equals("Select the Hostel")||roomno.equals("")||hostel_name.equals("Mens Hostels")||hostel_name.equals("Ladies Hostels")||phone_no.equals("")){
                    Toast.makeText(getApplicationContext(), "Please select Condition/Hostel/Room No", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    assert user != null;
                    String email = user.getEmail();
                    Random random=new Random();
                    int rand=random.nextInt(1000000000);
                    data.setToken(Integer.toString(rand));
                    data.setUserid(email);
                    data.setCondition(conditions);
                    data.setHostelname(hostel_name);
                    data.setRoomno(roomno);
                    data.setTime(globaltime);
                    data.setPhoneno(phno);
                    data.setStatus("Requested");
                    dfsendmessage.push().setValue(data);
                    startActivity(new Intent(Ambulance.this,Ambulancestatus.class));
                }

            }
        });
    }

    }
