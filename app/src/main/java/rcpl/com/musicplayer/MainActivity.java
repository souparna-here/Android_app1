package rcpl.com.musicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.ListIterator;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    static TextView tv1, tv2, tv3;
    RelativeLayout rl;
    Spinner sp;
    PendingIntent p1, p2, p3;
    static SeekBar sb;
    Button start, stop, pause, next, previous;
    static MediaPlayer mp;
    static int position = 0;
    static int duration, cp, sec, min;
    static final String s[] = {" Click For Song List", "Cheap Thrills - Sia", "Monster - skillet", "Shape Of You - Ed Sheeran"};
    SensorManager sm;
    NotificationManager nm;
    Sensor sensor1, sensor2;
static  int dlag;
    TextView tv5, tv6;
    int flag = 0;
    String set;
    int max, curr, val, direction = 0;
    static double div, result, r;

    int count = 0, d = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView);
        rl = (RelativeLayout) findViewById(R.id.id);
        rl.setBackgroundResource(R.drawable.download);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv6 = (TextView) findViewById(R.id.textView6);
        sp = (Spinner) findViewById(R.id.spinner3);
        sb = (SeekBar) findViewById(R.id.seekBar4);
        pause = (Button) findViewById(R.id.button);
        start = (Button) findViewById(R.id.button2);
        stop = (Button) findViewById(R.id.button3);
        previous = (Button) findViewById(R.id.button4);
        next = (Button) findViewById(R.id.button5);
        tv5 = (TextView) findViewById(R.id.textView5);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor1 = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor2 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        Intent k = getIntent();
        if (mp == null) {
            mp = new MediaPlayer();
        }

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    if (mp != null) {
                        mp.seekTo(progress);
                    }

                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (mp != null) {
                        cp = mp.getCurrentPosition();
                        sec = (cp / 1000) % 60;
                        min = cp / 60000;
                        sb.setProgress(cp);
                    } else {
                        sb.setProgress(0);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mp != null) {
                                tv2.setText(min + ":" + sec);

                            }
                        }
                    });


                }
            }
        };
        th.start();


        ArrayAdapter addapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, s);
        sp.setAdapter(addapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                Toast.makeText(MainActivity.this, s[i], Toast.LENGTH_LONG).show();
                position = i;


                if (i == 1) {
                    if (mp != null) {
                        mp.start();
                    }
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                        mp.start();
                    }
                }
                if (i == 2) {
                    if (mp != null) {
                        mp.start();
                    }

                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                        mp.start();
                    }
                }
                if (i == 3) {
                    if (mp != null) {
                        mp.start();
                    }

                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    }
                }

                duration = mp.getDuration();
                sb.setMax(duration);
                sec = (duration / 1000) % 60;
                min = (duration / 1000) / 60;

                tv3.setText(min + ":" + sec);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "play in Play'em", Toast.LENGTH_LONG).show();


            }
        });


    }

    ;


    public void play(View v) {

        mp.start();
        if (mp.isPlaying()) {
            Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();

        } else {
            switch (position) {
                case 2:
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                        mp.start();
                    }
                    position = 2;
                    Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                    break;

                case 3:
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    }
                    position = 3;
                    Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                    break;

                case 1:
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                        mp.start();
                    }
                    position = 1;
                    Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                    break;

                case 0:
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                        mp.start();
                    }
                    position = 1;
                    Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                    break;
            }


        }


        duration = mp.getDuration();
        sb.setMax(duration);
        sec = (duration / 1000) % 60;
        min = (duration / 1000) / 60;

        tv3.setText(min + ":" + sec);


    }


    public void pause(View v) {
        if (mp.isPlaying() || mp != null) {
            mp.pause();

        } else {
            Toast.makeText(MainActivity.this, "Play First", Toast.LENGTH_LONG).show();

        }
    }

    public void stop(View v) {
        if (mp != null || mp.isPlaying()) {
            mp.stop();


        } else {
            Toast.makeText(MainActivity.this, "Play First", Toast.LENGTH_LONG).show();

        }
    }

    public void next(View v) {

        switch (position) {
            case 1:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                    mp.start();
                }
                position = 2;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;

            case 2:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                    mp.start();
                }
                position = 3;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;

            case 0:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                    mp.start();
                }
                position = 1;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;

            case 3:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                    mp.start();
                }
                position = 1;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;
        }


        duration = mp.getDuration();
        sb.setMax(duration);
        sec = (duration / 1000) % 60;
        min = (duration / 1000) / 60;

        tv3.setText(min + ":" + sec);


    }

    public void previous(View v) {
        switch (position) {
            case 3:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                    mp.start();
                }
                position = 2;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;

            case 0:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                    mp.start();
                }
                position = 3;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;

            case 1:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                    mp.start();
                }
                position = 3;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;

            case 2:
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                    mp.start();
                }
                position = 1;
                Toast.makeText(MainActivity.this, s[position], Toast.LENGTH_LONG).show();
                break;
        }


        duration = mp.getDuration();
        sb.setMax(duration);
        sec = (duration / 1000) % 60;
        min = (duration / 1000) / 60;

        tv3.setText(min + ":" + sec);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sn = event.sensor;
        if (sn.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            int x = (int) values[0];
            int y = (int) values[1];
            int z = (int) values[2];
            tv5.setText("X=" + x + "\nY=" + y + "\nZ=" + z);
            if (flag == 0) {
                if (x <= -5) {
                    flag = 1;

                    switch (position) {
                        case 1:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                                mp.start();
                            }
                            position = 2;

                            break;

                        case 2:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                                mp.start();
                            }
                            position = 3;

                            break;

                        case 0:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                                mp.start();
                            }
                            position = 1;

                            break;

                        case 3:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                                mp.start();
                            }
                            position = 1;

                            break;
                    }


                    duration = mp.getDuration();
                    sb.setMax(duration);
                    sec = (duration / 1000) % 60;
                    min = (duration / 1000) / 60;

                    tv3.setText(min + ":" + sec);


                }


                if (x >= 5) {
                    flag = 1;
                    switch (position) {
                        case 3:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.skillet_monster);
                                mp.start();
                            }
                            position = 2;

                            break;

                        case 0:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                                mp.start();
                            }
                            position = 3;

                            break;

                        case 1:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.ed_sheeran_shape_of_you);
                                mp.start();
                            }
                            position = 3;

                            break;

                        case 2:
                            if (mp.isPlaying()) {
                                mp.stop();
                                mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                                mp.start();
                            } else {
                                mp = MediaPlayer.create(MainActivity.this, R.raw.sia_cheap_thrills);
                                mp.start();
                            }
                            position = 1;

                            break;
                    }


                    duration = mp.getDuration();
                    sb.setMax(duration);
                    sec = (duration / 1000) % 60;
                    min = (duration / 1000) / 60;

                    tv3.setText(min + ":" + sec);

                }
            }
            if (flag == 1) {
                if (x >= -1 && x <= 1) {
                    flag = 0;

                    Toast.makeText(this, s[position], Toast.LENGTH_SHORT).show();
                }


            }
        }
        if (sn.getType() == Sensor.TYPE_PROXIMITY) {
            float[] values = event.values;
            tv6.setText(String.valueOf(values[0]));
            if (String.valueOf(values[0]).equals("0.0")) {
                if (mp.isPlaying() || mp != null) {
                    mp.pause();
                }
            } else {
                mp.start();


            }
        }

    }


    @Override
    protected void onPause() {

         nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder nb = new Notification.Builder(this);
        nb.setSmallIcon(R.drawable.icon);

        nb.setContentTitle("Play'em");
        //nb.setAutoCancel(true);
        Intent i1 = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), i1, 0);
        nb.setContentIntent(pi);


        Intent i2 = new Intent(this, Myservice.class);
        i2.setAction("previous");
        i2.putExtra("k", position);
        i2.putExtra("k1", s);
        PendingIntent pi2 = PendingIntent.getService(this, (int) System.currentTimeMillis(), i2, 0);


        nb.addAction(android.R.drawable.ic_media_previous, " ", pi2);

        Intent i3 = new Intent(this, Myservice.class);
        i3.setAction("pause");
        i3.putExtra("k", position);
        i3.putExtra("k1", s);
        PendingIntent pi3 = PendingIntent.getService(this, (int) System.currentTimeMillis(), i3, 0);


        nb.addAction(android.R.drawable.ic_media_pause, " ", pi3);

        Intent i4 = new Intent(this, Myservice.class);
        i4.setAction("next");
        i4.putExtra("k", position);
        i4.putExtra("k1", s);
        PendingIntent pi4 = PendingIntent.getService(this, (int) System.currentTimeMillis(), i4, 0);


        nb.addAction(android.R.drawable.ic_media_next, "", pi4);


        Notification n = nb.build();
        nm.notify(1, n);
        nb.setAutoCancel(true);

        sm.unregisterListener(this);
        super.onPause();



    }



    @Override
    protected void onRestart() {
        super.onRestart();
        duration = mp.getDuration();
        sb.setMax(duration);
        sec = (duration / 1000) % 60;
        min = (duration / 1000) / 60;

        tv3.setText(min + ":" + sec);

        sb.setProgress(mp.getCurrentPosition());

    }

    @Override
    protected void onResume() {
        super.onResume();
        duration = mp.getDuration();
        sb.setMax(duration);
        sec = (duration / 1000) % 60;
        min = (duration / 1000) / 60;

        tv3.setText(min + ":" + sec);

        sb.setProgress(mp.getCurrentPosition());
        sm.registerListener(this, sensor1, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM);
        sm.registerListener(this, sensor2, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();


    }



}





