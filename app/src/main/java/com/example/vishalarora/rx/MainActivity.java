package com.example.vishalarora.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> items = new ArrayList();
        items.add("a");
        items.add("b");
        items.add("c");
        items.add("d");
        items.add("e");
        items.add("f");


        Observable.fromIterable(items)
                .subscribeOn(Schedulers.io())
                .concatMap(integer -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(integer + "x").delay(delay, TimeUnit.SECONDS);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("hello", s);
                });


        Observable.range(1,10)
                .scan((integer, integer2) -> integer + integer2)
                .subscribeOn(Schedulers.io())
                .concatMap(integer -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(integer + "x").delay(delay, TimeUnit.SECONDS);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("hello", " scan " +  s);
                });


        Observable.range(1,10)
                .reduce((integer, integer2) -> integer + integer2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("hello", " reduce " +  s);
                });

    }
}
