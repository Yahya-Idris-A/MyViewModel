package com.example.myviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private EditText edtLength, edtWidth, edtHeight;
    private Button btn_calculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btn_calculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btn_calculate.setOnClickListener(view -> {
            String length = edtLength.getText().toString();
            String width = edtWidth.getText().toString();
            String height = edtHeight.getText().toString();

            if (width.isEmpty()){
                edtWidth.setError("Please enter width");
            } else if (length.isEmpty()) {
                edtLength.setError("Please enter length");
            } else if (height.isEmpty()) {
                edtHeight.setError("Please enter height");
            }else {
                // Tanpa viemodel tidak dapat mempertahankan nilai apabila
                // layar di rotate
//                calculate(width, height, length);
                viewModel.calculate(width, height, length);
            }
        });
        displayResult();
    }

    private void displayResult() {
        viewModel.result.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvResult.setText(String.valueOf(integer));
            }
        });
    }

    private void calculate(String width, String height, String length){
        String result = String.valueOf(Integer.parseInt(length)
                        * Integer.parseInt(width)
                        * Integer.parseInt(height));
                tvResult.setText(result);
    }
}