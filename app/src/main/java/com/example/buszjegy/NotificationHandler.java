package com.example.buszjegy;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {
    private static final String CHANNEL_ID="shop_notification_channel";
    private final int NOTIFICATION_ID=0;
    private NotificationManager mManager;
    private Context mContext;

    public NotificationHandler(Context context){
        this.mContext=context;
        this.mManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();
    }

    public NotificationHandler(View.OnClickListener onClickListener) {
    }

    public NotificationHandler(JegyAdapterClass jegyAdapterClass) {
    }

    private void createChannel(){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            return;
        }
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID, "Ticket notification", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(Color.YELLOW);
        channel.setDescription("Ertesitesek a jegy alkalmazashoz");
        this.mManager.createNotificationChannel(channel);
    }
    @SuppressLint("NotificationPermission")
    public void send(String message){
        /*Intent intent=new Intent(mContext, ViewJegyActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(mContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);*/
        NotificationCompat.Builder builder=new NotificationCompat.Builder(mContext, CHANNEL_ID).setContentTitle("Buszjegy értesítés").setContentText(message).setSmallIcon(R.drawable.round_blue_autobus_autocar_bus_station_icon_701751694972515da9abrwr17);
        this.mManager.notify(NOTIFICATION_ID, builder.build());
    }
}
