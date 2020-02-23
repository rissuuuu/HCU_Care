package com.example.hcucare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AppointmentActivity extends AppCompatActivity {
    Appointmentsend data;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dfsendmessage;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
     SimpleDateFormat simpleDateFormat;
     Calendar calendar;
     Activity activity;
     TextView text_date;
    Spinner spinner;
    Button requestappointment;
    String symptom="";
    String appointmentdata="";
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        spinner=findViewById(R.id.spinner_diseases);
        activity=this;
        simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.getDefault());
        text_date=findViewById(R.id.id_datetime);
        requestappointment=findViewById(R.id.id_requestappointment);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        dfsendmessage=FirebaseDatabase.getInstance().getReference().child("Appointment");
        back=findViewById(R.id.id_backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button_select = findViewById(R.id.id_selectdatatime);
        button_select.setOnClickListener(dateListener);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Symptoms");
        categories.add("Chest Pain");
        categories.add("Stomach Pain");
        categories.add("Headache");
        categories.add("Eyes pain");
        categories.add("Joints pain");
        categories.add("Back pain");
        categories.add("Others");
        data =new Appointmentsend();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                symptom= (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        requestappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (symptom.equals("Select Symptoms")||text_date.getText().equals("Date and time")) {
                    Toast.makeText(getApplicationContext(), "Please select symptoms/Date from list", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    assert user != null;
                    String email = user.getEmail();
                    Random random=new Random();
                    int rand=random.nextInt(1000000000);
                        data.setId(email);
                        data.setStatus("Waiting");
                        data.setSymptom(symptom);
                        data.setTime(appointmentdata);
                        data.setToken_no(Integer.toString(rand));

                        dfsendmessage.push().setValue(data);
                        text_date.setText("");
                        startActivity(new Intent(AppointmentActivity.this,Appointmentstatus.class));
                    }

            }
        });
    }

    public View.OnClickListener dateListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calendar=Calendar.getInstance();
            new DatePickerDialog(activity,mdatedataset,calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };
    public DatePickerDialog.OnDateSetListener mdatedataset=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            new TimePickerDialog(activity,timedataset,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
        }
    };
    public TimePickerDialog.OnTimeSetListener timedataset=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE,minute);
            appointmentdata=simpleDateFormat.format(calendar.getTime());
            text_date.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };

}
