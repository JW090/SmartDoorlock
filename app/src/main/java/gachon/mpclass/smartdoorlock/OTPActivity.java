package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class OTPActivity extends AppCompatActivity {

    Button gen_otp;
    TextView new_otp;

    ProgressBar timer;
    CountDownTimer cdt;

    DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
    DatabaseReference otp_save = mRoot.child("OTP");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.otptoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("임시 비밀번호 발급"); // 툴바 제목 설정

        //비밀번호발급버튼
        gen_otp = findViewById(R.id.gen_otp);
        new_otp = findViewById(R.id.temp_otp);

        timer = findViewById(R.id.otp_timer);

        //타이머
        CountDownTimer cdt = new CountDownTimer(50*1000,1000) {
            @Override
            public void onTick(long l) {

                int num = (int)(l/1000);
                timer.setProgress(num*2);

            }

            @Override
            public void onFinish() {
                this.start();
                int arr[] = new int[4];

                //OTP생성
                Random random = new Random();
                for(int i=0; i<4; i++){

                    arr[i] = random.nextInt(10);
                }

                String otp = Arrays.toString(arr).replaceAll("[^0-9]","");
                new_otp.setText(otp);

                //비밀번호 암호화
                String key = "0123456789abcdef0123456789abcdef";
                try {
                    String encText = AES.encByKey(key, otp);
                    otp_save.setValue(encText);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };

        gen_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int arr[] = new int[4];

                //OTP생성
                Random random = new Random();
                for(int i=0; i<4; i++){

                    arr[i] = random.nextInt(10);
                }

                String otp = Arrays.toString(arr).replaceAll("[^0-9]","");
                new_otp.setText(otp);

                //비밀번호 암호화
                String key = "0123456789abcdef0123456789abcdef";
                try {
                    String encText = AES.encByKey(key, otp);
                    otp_save.setValue(encText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cdt.start();

            }
        });




    }

    //툴바액션
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동

                otp_save.setValue(null);
                cdt.cancel();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }

}
