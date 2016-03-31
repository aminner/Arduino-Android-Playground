package com.arduino.myfunstuff.org.arduinofun;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String status;
    private ListView blutoothDisplay;
    private Set<BluetoothDevice> pairedDevices;
    private boolean mKeepRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (bluetooth.isEnabled()) {
            String mydeviceaddress = bluetooth.getAddress();
            String mydevicename = bluetooth.getName();
            status = mydevicename + " : "  + mydeviceaddress;
            blutoothDisplay = (ListView) findViewById(R.id.blutoothStuff);
            blutoothDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    connectDevice(position);
                }
            });
            pairedDevices = bluetooth.getBondedDevices();
            // If there are paired devices
            ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {
                    // Add the name and address to an array adapter to show in a ListView
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
            blutoothDisplay.setAdapter(mArrayAdapter);
            ITwitterApi api = TwitterApi.createTwitterApi();
            api.UserFeed().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TwitterResponse>(){

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(TwitterResponse twitterResponse) {

                        }
                    });


        }
        else {
            status = "Bluetooth is not Enabled.";
        }

        Toast.makeText(this, status, Toast.LENGTH_LONG).show();
    }

    char current = '1';
    BluetoothSocket tmp;
    private void connectDevice(int position) {
        BluetoothDevice device = (BluetoothDevice) pairedDevices.toArray()[position];
        try {
            if(tmp == null)
            {
                tmp = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
                tmp.connect();
            }
            if(tmp.isConnected()) {
                if(current == '2')
                    current = '1';
                else
                    current = '2';
                tmp.getOutputStream().write(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

