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

public class UserList extends AppCompatActivity {

    ImageView load_user;
    Button b_delete_u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_img);

        //Storage에서 이미지 불러오기
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("face_registered");

        ImageView load_user = findViewById(R.id.user_image);
        Button b_delete_u = findViewById(R.id.delete_user);

        String u_data = getIntent().getStringExtra("User");
        String u_img = u_data.substring(37);


        if (pathReference == null) {
            Toast.makeText(this, "저장소에 사진이 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            StorageReference visitorImg = storageReference.child(u_img);
            visitorImg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getApplicationContext()).load(uri).into(load_user);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });

        }

        b_delete_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storageReference.child(u_img).delete();
                Intent i = new Intent(getApplicationContext(), FaceRegister.class);
                startActivity(i);
            }
        });

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.usertoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle(u_img); // 툴바 제목 설정

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동

                Intent i = new Intent(getApplicationContext(), FaceRegister.class);
                startActivity(i);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }
}
