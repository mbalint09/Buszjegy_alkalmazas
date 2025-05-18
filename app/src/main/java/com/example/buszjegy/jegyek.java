package com.example.buszjegy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class jegyek extends AppCompatActivity {
    private NotificationHandler mNotificationHandler;

    EditText editText_viszonylat, editText_ar;
    Button button_add,button_view;
    private static final String LOG_TAG=jegyek.class.getName();
    private FirebaseUser user;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mNotificationHandler=new NotificationHandler(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jegyek);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText_viszonylat = findViewById(R.id.edittext_viszonylat);
        editText_ar = findViewById(R.id.edittext_ar);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringViszonylat = editText_viszonylat.getText().toString();
                String stringAr = editText_ar.getText().toString();

                if (stringViszonylat.length() <=0 || stringAr.length() <=0){
                    Toast.makeText(jegyek.this, "Írj be minden adatot", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(jegyek.this);
                    JegyModelClass jegyModelClass = new JegyModelClass(stringViszonylat,stringAr);
                    databaseHelperClass.addJegy(jegyModelClass);
                    Toast.makeText(jegyek.this, "Jegy hozzáadása sikeres", Toast.LENGTH_SHORT).show();
                    mNotificationHandler.send("Jegy hozzáadása sikeres");
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jegyek.this,ViewJegyActivity.class);
                startActivity(intent);
            }
        });

        /*user= FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            Log.d(LOG_TAG,"Hitelesített felhasználó");
        }else{
            Log.d(LOG_TAG,"NEM hitelesített felhasználó");
            finish();
        }*/


    }


}