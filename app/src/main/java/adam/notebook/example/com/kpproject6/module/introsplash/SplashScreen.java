package adam.notebook.example.com.kpproject6.module.introsplash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceKey;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.TablayoutActivity;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    protected boolean active = true;
    protected int splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThread = new Thread() {
            public void run() {
                try {
                    int waited = 300;

                    while (active && (waited < splashTime)) {
                        sleep(300);
                        if (active) {
                            waited += 300;
                        }
                    }
                } catch (Exception e) {
                    e.toString();
                } finally {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        };

        splashThread.start();
    }
}