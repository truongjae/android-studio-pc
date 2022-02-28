package com.example.giaiphuongtrinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView numberA, numberB, numberC;
    private Button btn;
    private TextView txtResult;
    private RadioGroup radioGroup;
    private RadioButton choice1, choice2;
    private int choice;


    public static String giaiPTBac1(int a,int b){
        if(a==0) return b==0 ? "Phương trình vô số nghiệm" : "Phương trình vô nghiệm";
        else return String.valueOf((float)-b/a);
    }
    public static String giaiPTBac2(int a,int b,int c){
        if(a == 0){
            if(b==0) return c==0? "Phương trình vô số nghiệm" : "Phương trình vô nghiệm";
            else return String.valueOf((float) -c/b);
        }
        else {
            double delta = b*b - 4*a*c;
            if (delta < 0) return "Phương trình vô nghiệm";
            else if(delta == 0) return "Phương trình có nghiệm kép: "+ String.valueOf((float) -b/(2*a));
            else return "X1 = "+((-b- Math.sqrt(delta))/(2*a))+ "\nX2 = "+((-b+ Math.sqrt(delta))/(2*a));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.numberA = findViewById(R.id.numberA);
        this.numberB = findViewById(R.id.numberB);
        this.numberC = findViewById(R.id.numberC);
        this.btn = findViewById(R.id.button);
        this.txtResult = findViewById(R.id.txtResult);

        this.radioGroup = findViewById(R.id.radioGroup);
        this.choice1 = findViewById(R.id.choice1);
        this.choice2 = findViewById(R.id.choice2);

        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                choice(radioGroup,i);
            }
        });
        choice1.setChecked(true);

        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = numberA.getText().toString().isEmpty() ? 0 : Integer.parseInt(numberA.getText().toString());
                int b = numberB.getText().toString().isEmpty() ? 0 :Integer.parseInt(numberB.getText().toString());
                int c = numberC.getText().toString().isEmpty() ? 0 : Integer.parseInt(numberC.getText().toString());
//                txtResult.setText ("Kết quả:\n"+((choice == 1) ? giaiPTBac1(a,b) : giaiPTBac2(a,b,c)));
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("result", "Kết quả:\n"+((choice == 1) ? giaiPTBac1(a,b) : giaiPTBac2(a,b,c)));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void choice(RadioGroup group, int checkedId){
            int checkedRadioId = group.getCheckedRadioButtonId();
            if(checkedRadioId == R.id.choice1){
                this.numberC.setVisibility(View.GONE);
                this.choice = 1;
            }
            if(checkedRadioId == R.id.choice2){
                this.numberC.setVisibility(View.VISIBLE);
                this.choice = 2;
            }
    }
}