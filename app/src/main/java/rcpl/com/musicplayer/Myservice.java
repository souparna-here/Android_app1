package rcpl.com.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import static android.R.attr.action;
import static android.media.session.PlaybackState.ACTION_PAUSE;
import static android.media.session.PlaybackState.ACTION_STOP;
import static rcpl.com.musicplayer.MainActivity.dlag;
import static rcpl.com.musicplayer.MainActivity.duration;
import static rcpl.com.musicplayer.MainActivity.min;
import static rcpl.com.musicplayer.MainActivity.mp;
import static rcpl.com.musicplayer.MainActivity.position;
import static rcpl.com.musicplayer.MainActivity.s;
import static rcpl.com.musicplayer.MainActivity.sb;
import static rcpl.com.musicplayer.MainActivity.sec;
import static rcpl.com.musicplayer.MainActivity.tv3;

/**
 * Created by Hp on 6/11/2017.
 */

public class Myservice extends Service {
    @Override
    public void onStart(Intent intent, int startId) {
        String a = intent.getAction();


        if(a.equals("previous")) {
            switch (position){
                case 3 :
                    if ( mp.isPlaying() ){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.skillet_monster);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.skillet_monster);
                        mp.start();}
                    position=2;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;

                case 0:
                    if ( mp.isPlaying()){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.ed_sheeran_shape_of_you);
                        mp.start();}
                    position=3;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;

                case 1:
                    if ( mp.isPlaying()){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.ed_sheeran_shape_of_you);
                        mp.start();}
                    position=3;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;

                case 2:
                    if ( mp.isPlaying() ){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sia_cheap_thrills);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.sia_cheap_thrills);
                        mp.start();}
                    position=1;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;
            }

            duration=mp.getDuration();
            sb.setMax(duration);
            sec = (duration/1000)%60;
            min= (duration/1000)/60;

            tv3.setText( min +":" + sec);
        }
        else if(a.equals("pause")){
            if (dlag == 0) {
                dlag = 1;
                Toast.makeText(getApplicationContext(), "pause", Toast.LENGTH_LONG).show();
                if (mp.isPlaying() || mp != null) {
                    mp.pause();
                } else {
                    Toast.makeText(getApplicationContext(), "Play First", Toast.LENGTH_LONG).show();

                }
            }
            else if(dlag==1){
                dlag=0;
                Toast.makeText(getApplicationContext(), "play", Toast.LENGTH_LONG).show();
                    mp.start();

            }
        }
        else if(a.equals("next")) {
            switch (position){
                case 1 :
                    if ( mp.isPlaying() ){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.skillet_monster);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.skillet_monster);
                        mp.start();}
                    position=2;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;

                case 2:
                    if ( mp.isPlaying()){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ed_sheeran_shape_of_you);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.ed_sheeran_shape_of_you);
                        mp.start();}
                    position=3;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;

                case 0:
                    if ( mp.isPlaying()){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sia_cheap_thrills);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.sia_cheap_thrills);
                        mp.start();}
                    position=1;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;

                case 3:
                    if ( mp.isPlaying() ){
                        mp.stop();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sia_cheap_thrills);
                        mp.start();
                    } else { mp = MediaPlayer.create(getApplicationContext(), R.raw.sia_cheap_thrills);
                        mp.start();}
                    position=1;
                    Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_LONG).show();
                    break;
            }


            duration=mp.getDuration();
            sb.setMax(duration);
            sec = (duration/1000)%60;
            min= (duration/1000)/60;

            tv3.setText( min +":" + sec);


        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {



        return null;
    }


}


