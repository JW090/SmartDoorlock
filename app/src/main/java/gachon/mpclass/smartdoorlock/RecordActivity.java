package gachon.mpclass.smartdoorlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {


    ListView list_visitor;

    public ArrayList<String> visitor_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.recordtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("방문자 기록"); // 툴바 제목 설정

        //방문자 리스트뷰
       list_visitor = findViewById(R.id.visitor_list);

        ArrayList<StorageReference> visitor_list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1,visitor_list);
        list_visitor.setAdapter(adapter);

        //리스트불러오기
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference listRef = storage.getReference().child("visitor");

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {



                        for (StorageReference item : listResult.getItems()) {
                            visitor_list.addAll(listResult.getItems());
                            adapter.notifyDataSetChanged();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                    }
                });

        //리스트 클릭 이벤트
        list_visitor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int check_position = list_visitor.getCheckedItemPosition();
                String data = (String)adapterView.getAdapter().getItem(i).toString();



                Intent intent = new Intent(getApplicationContext(),VisitorList.class);
                intent.putExtra("Visitor",data);
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
