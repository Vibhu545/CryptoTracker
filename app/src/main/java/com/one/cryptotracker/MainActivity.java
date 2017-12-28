package com.one.cryptotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btBitCoin, btEthereum, btLitecoin, btDigitalCash, btMonero, btDogecoin;
    TextView tvResult;
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

        btBitCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, BitCoin.class);
                startActivity(intent);
            }
        });
    }
}

