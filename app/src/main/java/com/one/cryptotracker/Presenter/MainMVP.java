package com.one.cryptotracker.Presenter;

/**
 * Created by vibhu on 12/25/2017.
 */

public interface MainMVP {

    interface MainView {
        void setAlertforBitcoin();


    }
    interface Presenter{
        void alertForBitCoin();
    }
}
