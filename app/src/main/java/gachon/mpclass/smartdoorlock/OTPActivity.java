package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class OTPActivity extends AppCompatActivity {

    Button gen_otp;
    TextView new_otp;

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


            }
        });


    }

    //툴바액션
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }

}
