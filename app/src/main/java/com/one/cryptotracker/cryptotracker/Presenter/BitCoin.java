package com.one.cryptotracker.cryptotracker.Presenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.one.cryptotracker.R;
import com.one.cryptotracker.cryptotracker.Model.BitcoinResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BitCoin extends AppCompatActivity implements MainMVP, MainMVP.MainView {

    EditText etThreshold;
    Button btSetAlertBitCoin;
    USD bitCoinValue;
    float bitCoinValueFloat;
    float bcThreshold;
    TextView tvAlertSetBitCoin;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_coin);
        etThreshold = (EditText) findViewById(R.id.etThreshold);
        tvAlertSetBitCoin = (TextView) findViewById(R.id.tvAlertSetBitCoin);
        btSetAlertBitCoin = (Button) findViewById(R.id.btSetAlertBitCoin);
        mainPresenter=new MainPresenter(BitCoin.this);
        String bitCoinThreshold= etThreshold.getText().toString().trim();
        bcThreshold = ParseFloat(bitCoinThreshold);
        btSetAlertBitCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bcThreshold < 0.0) {
                    etThreshold.setError("Enter a proper value");
                }
                else
                {
                    mainPresenter.alertForBitCoin();
                }
            }
        });
    }

    void getData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coindesk.com/v1/bpi/currentprice.json/").
                addConverterFactory(GsonConverterFactory.create()).build();
        ApiImplimentation apiImplimentation = retrofit.create(ApiImplimentation.class);
        Call<BitcoinResponse> call = apiImplimentation.getBitCoinValue();
        call.enqueue(new Callback<BitcoinResponse>() {
            @Override
            public void onResponse(Call<BitcoinResponse> call, Response<BitcoinResponse> response) {
                //USD bitCoinValue = response.body();
                // bitCoinValueFloat = Float.parseFloat(String.valueOf(bitCoinValue));
            }

            @Override
            public void onFailure(Call<BitcoinResponse> call, Throwable t) {

            }
        });
    }
    float ParseFloat(String bitCoinThreshold) {
        if (bitCoinThreshold != null && bitCoinThreshold.length() > 0) {
            try {
                return Float.parseFloat(bitCoinThreshold);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }

    public void setAlertforBitcoin()
    {
        int _TIME_OUT = 3000;
        boolean handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                if(bitCoinValueFloat < bcThreshold || bitCoinValueFloat < bcThreshold)
                {
                    addNotification();
                }
            }
        }, _TIME_OUT);
    }

    private void addNotification() {

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setContentTitle("Value update")
                        .setContentText("The value of Bitcoin is changed to "+ bitCoinValueFloat +"");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);
        builder.setLights(Color.BLUE, 500, 500);
        long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
        builder.setVibrate(pattern);
        builder.setStyle(new NotificationCompat.InboxStyle());
        builder.setDefaults(Notification.DEFAULT_SOUND);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}