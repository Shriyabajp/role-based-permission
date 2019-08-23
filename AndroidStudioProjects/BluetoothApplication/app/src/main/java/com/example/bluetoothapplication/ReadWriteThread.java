package com.example.bluetoothapplication;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReadWriteThread extends  Thread {
    private final BluetoothSocket bluetoothSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public ReadWriteThread(BluetoothSocket socket) {
        this.bluetoothSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
        }

        inputStream = tmpIn;
        outputStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        // Keep listening to the InputStream
        while (true) {
            try {
                // Read from the InputStream
                bytes = inputStream.read(buffer);

                // Send the obtained bytes to the UI Activity
                handler.obtainMessage(MainActivity.MESSAGE_READ, bytes, -1,
                        buffer).sendToTarget();
            } catch (IOException e) {
                connectionLost();
                // Start the service over to restart listening mode
                ChatController.this.start();
                break;
            }
        }
    }

    // write to OutputStream
    public void write(byte[] buffer) {
        try {
            outputStream.write(buffer);
            handler.obtainMessage(MainActivity.MESSAGE_WRITE, -1, -1,
                    buffer).sendToTarget();
        } catch (IOException e) {
        }
    }

    public void cancel() {
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
