package gachon.mpclass.smartdoorlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b_otp;
    Button b_record;
    Button b_door;
    Button b_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_otp = findViewById(R.id.b_otp);
        b_record = findViewById(R.id.b_record);
        b_register = findViewById(R.id.b_register);
        b_door = findViewById(R.id.b_door);


        //OTP버튼이벤트
        b_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                startActivity(intent);
            }
        });

        //방문자기록버튼이벤트
        b_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(i);
            }
        });

        //등록관리버튼이벤트
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Registeration.class);
                startActivity(intent);
            }
        });

        //문열림
        b_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }





}