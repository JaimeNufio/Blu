package com.jaimenufio.blu;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private final String DEVICE_ADDRESS = "00:14:03:06:6F:63"; //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    Button forward_btn, forward_left_btn, forward_right_btn, reverse_btn, bluetooth_connect_btn, alpha_btn, beta_btn;
    ToggleButton on_switch;
    SeekBar scroll;
    TextView tBox,sBox, scrollText;

    byte command; //string variable that will store value to be transmitted to the bluetooth module
    ArrayList<Byte> down = new ArrayList<Byte>();

    protected void updateList(Byte command) {
        String t = "";
        down.add(command);
        for (Byte s : down) {
            t += "" + s;
        }
        tBox.setText(t);
    }

    protected void updateList() {
        String t = "";
        for (Byte s : down) {
            t += "" + s;
        }
        tBox.setText(t);
    }

    protected void sendLinear(int num) {

        try{
            byte x = (byte)(Math.floor(num/2)+50);
            if (on_switch.isChecked()) {
                outputStream.write((byte) x);
            }
            sBox.setText(""+x);
        }catch(IOException e){

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration of button variables
        forward_btn = (Button) findViewById(R.id.forward_btn);
        forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
        reverse_btn = (Button) findViewById(R.id.reverse_btn);
        bluetooth_connect_btn = (Button) findViewById(R.id.bluetooth_connect_btn);
        on_switch = (ToggleButton) findViewById(R.id.on_switch);
        tBox = (TextView) findViewById(R.id.tBox);
        alpha_btn = (Button) findViewById(R.id.alpha);
        beta_btn = (Button) findViewById(R.id.beta);
        scroll = (SeekBar) findViewById(R.id.scroll);
        scrollText = (TextView) findViewById(R.id.scrollText);
        sBox = (TextView) findViewById(R.id.tBox1);


        scroll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int t;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t=progress;
                command=(byte)t;
                scrollText.setText(""+t);
                sendLinear(t);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sBox.setText("");
            }
        });

        forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN is when you hold a button down
                {
                    command = 1;
                    updateList(command);

                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) { //MotionEvent.ACTION_UP is when you release a button
                    Byte t = 1;
                    down.remove(t);
                    command = 126;
                    updateList();
                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return false;

            }});

        //OnTouchListener code for the reverse button (button long press)
        reverse_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = 2;
                    updateList(command);

                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    Byte t = 2;
                    down.remove(t);
                    command = 125;
                    updateList();
                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return false;
            }
        });

        //OnTouchListener code for the forward left button (button long press)
        forward_left_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = 3;
                    updateList(command);

                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    Byte t = 3;
                    down.remove(t);
                    command = 124;
                    updateList();
                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return false;
            }
        });

        //OnTouchListener code for the forward right button (button long press)
        forward_right_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN )
                {
                    command = 4;
                    updateList(command);

                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    Byte t = 4;
                    down.remove(t);
                    command = 123;
                    updateList();
                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return false;
            }
        });

        alpha_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN )
                {
                    command = 5;
                    updateList(command);

                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    Byte t = 5;
                    down.remove(t);
                    command = 122;
                    updateList();
                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return false;
            }
        });

        beta_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = 6;
                    updateList(command);

                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    Byte t = 6;
                    down.remove(t);
                    command = 121;
                    updateList();
                    if (on_switch.isChecked()) {
                        try {
                            outputStream.write(command); //transmits the value of command to the bluetooth module
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return false;
            }
        });



        //Button that connects the device to the bluetooth module when pressed
        bluetooth_connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(BTinit())
                {
                    BTconnect();
                }

            }
        });

    }

    //Initializes bluetooth module
    public boolean BTinit()
    {
        boolean found = false;

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null) //Checks if the device supports bluetooth
        {
            Toast.makeText(getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }

        if(!bluetoothAdapter.isEnabled()) //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty()) //Checks for paired bluetooth devices
        {
            Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public boolean BTconnect()
    {
        boolean connected = true;

        try
        {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            socket.connect();

            Toast.makeText(getApplicationContext(),
                    "Connection to bluetooth device successful", Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            connected = false;
        }

        if(connected)
        {
            try
            {
                outputStream = socket.getOutputStream(); //gets the output stream of the socket
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        return connected;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

}