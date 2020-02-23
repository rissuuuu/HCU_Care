package com.example.hcucare;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {
    CardView card_doctors,card_appointment,card_treatments,card_medicines,card_ambulance,card_emergency;
    Button bt_yes,bt_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_hcucare);
        card_doctors=findViewById(R.id.id_doctors);
        card_appointment=findViewById(R.id.id_appointments);
        card_treatments=findViewById(R.id.id_treatments);
        card_medicines=findViewById(R.id.id_searchmedicines);
        card_ambulance=findViewById(R.id.id_ambulance);
        card_emergency=findViewById(R.id.id_emergency);
        card_doctors.setOnClickListener(this);
        card_appointment.setOnClickListener(this);
        card_treatments.setOnClickListener(this);
        card_medicines.setOnClickListener(this);
        card_ambulance.setOnClickListener(this);
        card_emergency.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_doctors:
                startActivity(new Intent(MainDashboard.this,DoctorsActivity.class));
                break;
            case R.id.id_appointments:
                startActivity(new Intent(MainDashboard.this,AppointmentActivity.class));
                break;
            case R.id.id_treatments:
                Toast.makeText(getApplicationContext(),"Treatments",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_searchmedicines:
                Toast.makeText(getApplicationContext(),"Search Medicines",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_ambulance:
                startActivity(new Intent(MainDashboard.this,Ambulance.class));
                break;
            case R.id.id_emergency:
                Toast.makeText(getApplicationContext(),"Emergency",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void ShowPopup(View view) {
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.inflate(R.menu.options);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_signout:
                        final Dialog dialog = new Dialog(MainDashboard.this);
                        dialog.setContentView(R.layout.logout);
                        bt_yes = dialog.findViewById(R.id.yes);
                        bt_no = dialog.findViewById(R.id.no);
                        dialog.show();
                        bt_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainDashboard.this, Login.class));
                            }
                        });
                        bt_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        return true;

                    case R.id.id_changepw:
                        startActivity(new Intent(MainDashboard.this, ChangePassword.class));
                        return true;
                    case R.id.id_viewstatus:
                        startActivity(new Intent(MainDashboard.this, Appointmentstatus.class));
                        return true;
                    case R.id.id_ambulancestatus:
                        startActivity(new Intent(MainDashboard.this, Ambulancestatus.class));
                        return true;
                }
                return true;
            }
        });
    }

        @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(MainDashboard.this);
        dialog.setContentView(R.layout.exit);
        bt_yes = dialog.findViewById(R.id.yes);
        bt_no = dialog.findViewById(R.id.no);
        dialog.show();
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainDashboard.this.finishAffinity();
            }
        });
        bt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
