package com.vicky.wifi;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         *Each activity must have a corresponding layout XML file
         */
        setContentView(R.layout.activity_main);// activity_main is corresponding XML file

        /*
         * Initializing button XML button id. findViewById is a method which
         * helps to initialize with particular id. btn_go_to_another_activity is
         * a button name which I have given in XML file
         */
        button1 = (Button) findViewById(R.id.navbtn);


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */
                Intent intent = new Intent(MainActivity.this,
                        Main2Activity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

}
