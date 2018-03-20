package rcpl.com.musicplayer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Splash_screen extends AppCompatActivity {
    CountDownTimer ct ;
    RelativeLayout rl;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        rl= (RelativeLayout) findViewById(R.id.layout );
        tv = (TextView) findViewById(R.id.textView4);
        rl.setBackgroundResource(R.drawable.download);
      ct =new CountDownTimer(4000,1000) {
          @Override
          public void onTick(long l) {
              tv.setText(String.valueOf(l/1000));



          }

          @Override
          public void onFinish() {
              Intent intent = new Intent(Splash_screen.this,MainActivity.class);
              startActivity(intent);
 }
      };ct.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();

    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}

