package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FaceRegister extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_register);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.facetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("얼굴 등록 관리"); // 툴바 제목 설정

        FirebaseStorage Storage = FirebaseStorage.getInstance();
        StorageReference StorageRef = Storage.getReference();



    }

    //툴바액션
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동

                Intent i = new Intent(getApplicationContext(), Registeration.class);
                startActivity(i);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }
}
