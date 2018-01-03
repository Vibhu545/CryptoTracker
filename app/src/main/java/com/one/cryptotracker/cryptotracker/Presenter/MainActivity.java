package com.one.cryptotracker.cryptotracker.Presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.one.cryptotracker.R;
import com.one.cryptotracker.cryptotracker.Model.BitcoinResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btBitCoin, btEthereum, btLitecoin, btDigitalCash, btMonero, btDogecoin;
    TextView tvResult;
    ApiImplimentation apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btBitCoin = (Button) findViewById(R.id.btBitCoin);
        btEthereum = (Button) findViewById(R.id.btEthereum);
        btDigitalCash = (Button) findViewById(R.id.btDigitalCash);
        btDogecoin = (Button) findViewById(R.id.btDogecoin);
        btLitecoin = (Button) findViewById(R.id.btLitecoin);
        btMonero = (Button) findViewById(R.id.btMonero);
        tvResult = (TextView) findViewById(R.id.tvResult);

        apiInterface = APIClient.getClient().create(ApiImplimentation.class);

        btBitCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent = new Intent(MainActivity.this, BitCoin.class);
                startActivity(intent);
                Call<BitcoinResponse> call1 = apiInterface.getBitCoinValue();
                call1.enqueue(new Callback<BitcoinResponse>() {
                    @Override
                    public void onResponse(Call<BitcoinResponse> call, Response<BitcoinResponse> response) {
                        int status = response.code();
                        Log.i("status...",status+"");
                        // Log.i("user obj...",user1+"");
                        // Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<BitcoinResponse> call, Throwable t) {
                        call.cancel();
                    }
                });

            }
        });
    }
}

