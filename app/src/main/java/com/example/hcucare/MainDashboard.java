package com.example.hcucare;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {
    CardView cardView_messages,cardViewnotification,cardViewnews;
    Button bt_yes,bt_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_hcucare);
        cardView_messages=findViewById(R.id.id_dashboard_messages);
        cardViewnotification=findViewById(R.id.dashboard_notification);
        cardViewnews=findViewById(R.id.dashboard_news);
        cardView_messages.setOnClickListener(this);
        cardViewnotification.setOnClickListener(this);
        cardViewnews.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_dashboard_messages:
//                startActivity(new Intent(MainDashboard.this,Dashboard.class));
                break;
            case R.id.dashboard_notification:
//                startActivity(new Intent(MainDashboard.this,NotificationActivity.class));
                break;
            case R.id.dashboard_news:
//                startActivity(new Intent(MainDashboard.this,NewsActivity.class));
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
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
