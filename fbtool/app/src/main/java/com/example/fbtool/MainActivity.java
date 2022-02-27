package com.example.fbtool;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText txtToken;
    private EditText txtContent;
    private EditText txtQuantity;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContent = (EditText) findViewById(R.id.txtContent);
        txtToken = (EditText) findViewById(R.id.txtToken);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtContent.getText().equals("")){

                }
                else{

                }
            }
        });
    }
}
