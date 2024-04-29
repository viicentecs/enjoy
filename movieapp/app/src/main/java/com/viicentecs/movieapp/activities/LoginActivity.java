package com.viicentecs.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.viicentecs.movieapp.R;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnLogin;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    private void initUI(){
        btnLogin = findViewById(R.id.btn_login_login);
        edtEmail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin.setOnClickListener(v -> validateFieldAndOpenMain());
    }

    private void validateFieldAndOpenMain(){

        if(edtEmail.getText().toString().isEmpty() ||
            edtPassword.getText().toString().isEmpty()){
            showToastMessage(getString(R.string.error_empty_fields));
            return;
        }

        if(!edtEmail.getText().toString().equals("viicente@gmail.com") ||
                !edtPassword.getText().toString().equals("vicente")){
            showToastMessage(getString(R.string.error_fields));
            return;
        }

        openMain();
    }

    private void openMain(){
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
        this.finish();
    }

    private void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}