package com.vicky.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    TextView t;
    WifiInfo wifiinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        t = (TextView) findViewById(R.id.text);
        getwifiinfo();
    }
    @SuppressLint("SetTextI18n")
    public void getwifiinfo()
    {


        WifiManager wifimanager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        assert wifimanager != null;
        if(wifimanager.isWifiEnabled())
        {
             wifiinfo = wifimanager.getConnectionInfo();
            if(String.valueOf(wifiinfo.getSupplicantState()).equals("COMPLETED"))
            {

//                Toast.makeText(this, wifiinfo.getSSID()+" "+wifiinfo.getRssi(), Toast.LENGTH_SHORT).show();
                t.setText("SSID: "+wifiinfo.getSSID()+"\nSignal strength: "+wifiinfo.getRssi()+"\nBSSID: "+wifiinfo.getBSSID()+"\nFrequency: "+wifiinfo.getFrequency()+"\nLink speed: "+wifiinfo.getLinkSpeed());
            }
            else
            {
                //Toast.makeText(this, "please connect to a wifi network!", Toast.LENGTH_SHORT).show();
                t.setText("please connect to a wifi network!");
            }
        }
        else
        {
            wifimanager.setWifiEnabled(true);
        }
    }
    public void download(View view)
    {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Wifi_Vicky");
        dir.mkdirs();
        try {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy::hh:mm:ss-a");
            String formattedDate = df.format(currentTime);
            File myFile = new File(dir, "Signal_Strength_Log.txt");
            if (myFile.length() < 1024000) {
                FileWriter fw = new FileWriter(myFile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                String printString = wifiinfo.getSSID() + "\t\t" + formattedDate + "\t\t" + wifiinfo.getRssi() + "\n";
                pw.print(printString);
                pw.close();
                Toast.makeText(this, "Written in file", Toast.LENGTH_SHORT).show();
            }else{
                PrintWriter pw = new PrintWriter(myFile);
                pw.print("");
                pw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
