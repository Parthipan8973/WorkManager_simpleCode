package com.example.workmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.workmanager.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    WorkManager workManager;


    ActivityMainBinding activityMainBinding; // DataBinding Declare above on create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       activityMainBinding = DataBindingUtil. setContentView(this,R.layout.activity_main); //Initization method databinding

        workManager = WorkManager.getInstance();


        activityMainBinding.btnOneTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(false)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder
                        (NotiWorker.class)
                        .setConstraints(constraints)
                        .build();
                workManager.enqueue(oneTimeWorkRequest);


            }
        });


        activityMainBinding.btnPeriodicTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(false)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                        NotiWorker.class, 10, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();
                workManager.enqueue(periodicWorkRequest);

            }
        });



    }
}