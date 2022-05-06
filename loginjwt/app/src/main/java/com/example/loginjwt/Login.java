package com.example.loginjwt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginjwt.api.ApiClient;
import com.example.loginjwt.model.JwtResponse;
import com.example.loginjwt.model.UserRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtJwtToken;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        edtJwtToken = findViewById(R.id.edtJwtToken);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRequest userRequest = new UserRequest(edtUsername.getText().toString(),edtPassword.getText().toString());
                login(userRequest);
            }
        });
    }
    public void login(UserRequest userRequest){
        Call<JwtResponse> jwtResponseCall = ApiClient.getUserService().login(userRequest);
        jwtResponseCall.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(response.isSuccessful()){
                    edtJwtToken.setVisibility(View.VISIBLE);
                    edtJwtToken.setText(response.body().getJwtToken());
                }
                else {
                    edtJwtToken.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không chính xác!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
            Toast.makeText(Login.this, "Thất bại, Không có internet!",Toast.LENGTH_LONG).show();
            }
        });
    }
}