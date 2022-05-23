package gachon.mpclass.smartdoorlock;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PwRegister extends AppCompatActivity {

    public String password;

    Button set_newpw;
    Button reset_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw_register);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.pwtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("비밀번호 등록"); // 툴바 제목 설정

        password = null;

        set_newpw = findViewById(R.id.new_pw);
        reset_pw = findViewById(R.id.reset_pw);

        set_newpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password == null){
                    enterpassword();
                }
            }
        });



        }


        //새비밀번호 입력 팝업
        void enterpassword(){

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("새 비밀번호를 입력해주세요");

            final EditText pw = new EditText(this);
            pw.setInputType(TYPE_CLASS_NUMBER|TYPE_NUMBER_VARIATION_PASSWORD);
            alert.setView(pw);

            alert.setPositiveButton("저장",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    password = pw.getText().toString();
                }
            });


            alert.setNegativeButton("최소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.show();

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
