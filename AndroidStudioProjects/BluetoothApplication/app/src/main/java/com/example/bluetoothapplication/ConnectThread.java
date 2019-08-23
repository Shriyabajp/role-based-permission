package com.example.bluetoothapplication;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;


public class ConnectThread extends Thread {
    private final BluetoothSocket socket;
    private final BluetoothDevice device;

    public ConnectThread(BluetoothDevice device) {
        this.device = device;
        BluetoothSocket tmp = null;
        try {
            tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket = tmp;
    }

    public void run() {
        setName("ConnectThread");

        // Always cancel discovery because it will slow down a connection
        bluetoothAdapter.cancelDiscovery();

        // Make a connection to the BluetoothSocket
        try {
            socket.connect();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e2) {
            }
            connectionFailed();
            return;
        }

        // Reset the ConnectThread because we're done
        synchronized (ChatController.this) {
            connectThread = null;
        }

        // Start the connected thread
        connected(socket, device);
    }

    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}
