package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VisitorList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visitor_img);

        //Storage에서 이미지 불러오기
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("visitor");

        ImageView load_visitor = findViewById(R.id.visitor_image);
        Button b_delete_v = findViewById(R.id.delete_visitor);

        String v_data = getIntent().getStringExtra("Visitor");
        String v_img = v_data.substring(37);


        if (pathReference == null) {
            Toast.makeText(this, "저장소에 사진이 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            StorageReference visitorImg = storageReference.child(v_img);
            visitorImg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getApplicationContext()).load(uri).into(load_visitor);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });

        }

        //삭제버튼
        b_delete_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storageReference.child(v_img).delete();
                Intent i = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(i);

            }
        });

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.visitortoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle(v_img.substring(8)); // 툴바 제목 설정



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동

                Intent i = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(i);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }
}
