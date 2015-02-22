package com.example.pruebasenal;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class VentanaPrincipal extends Activity {

    private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_ventana_principal);
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                BTAdapter.startDiscovery();
            }
        });
    }



    private final BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                TextView rssi_msg = (TextView) findViewById(R.id.textView);
                rssi_msg.setText(rssi_msg.getText() + name + " => " + rssi + "dBm\n");
            }
        }
    };



}
