package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Registeration extends AppCompatActivity {

    Button b_pw;
    Button b_face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        b_pw = findViewById(R.id.set_pw);
        b_face = findViewById(R.id.set_face);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.regitoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("등록 관리"); // 툴바 제목 설정


        //비밀번호등록
        b_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PwRegister.class);
                startActivity(intent);
            }
        });

        //얼굴인식관리
        b_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FaceRegister.class);
                startActivity(intent);
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
