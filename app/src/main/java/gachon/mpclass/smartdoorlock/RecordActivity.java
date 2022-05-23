package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.recordtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("방문자 기록"); // 툴바 제목 설정

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
