package com.one.cryptotracker;

import com.one.cryptotracker.Presenter.MainMVP;

/**
 * Created by vibhu on 12/25/2017.
 */

public class MainPresenter implements MainMVP.Presenter {
    private final MainMVP.MainView view1;

    public MainPresenter(MainMVP.MainView view) {
        view1 = view;
    }


    @Override
    public void alertForBitCoin() {
        view1.setAlertforBitcoin();
    }
}