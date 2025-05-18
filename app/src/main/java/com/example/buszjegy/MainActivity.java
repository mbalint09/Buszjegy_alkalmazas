package com.example.buszjegy;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG=MainActivity.class.getName();
    private static final String PREF_KEY=MainActivity.class.getPackage().toString();
    private static final int SECRET_KEY=99;
    private static final int RC_NOTIFICATION = 99;

    EditText userNameET;
    EditText passwordET;

    private SharedPreferences preferences;

    //private AlarmManager mAlarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //mAlarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userNameET=findViewById(R.id.editTextUserName);
        passwordET=findViewById(R.id.editTextUserPassword);

        preferences=getSharedPreferences(PREF_KEY, MODE_PRIVATE);

        Log.i(LOG_TAG, "onCreate");
        //setAlarmManager();
        requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, RC_NOTIFICATION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);
        if (requestCode==RC_NOTIFICATION){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Engedélyezett", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Megtagadva", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void login(View view) {
        Intent intentjegyek=new Intent(this, jegyek.class);
        startActivity(intentjegyek);

        String userName=userNameET.getText().toString();
        String password=passwordET.getText().toString();

        Log.i(LOG_TAG, "Bejelentkezett:"+ userName+" jelszó: "+password);


    }

    public void register(View view) {
        Intent intent=new Intent(this, RegisterActivity.class);
        intent.putExtra("SECRET_KEY", 99);

        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("userName", userNameET.getText().toString());
        editor.putString("password", passwordET.getText().toString());
        editor.apply();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    public void loginAsGuest(View view) {
    }

    public void loginWithGoogle(View view) {
    }

    /*public void setAlarmManager(){
        long repeatInterval=60*1000;
        long triggerTime= SystemClock.elapsedRealtime()+repeatInterval;
        Intent intent=new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,repeatInterval,pendingIntent
        );

    }*/
}