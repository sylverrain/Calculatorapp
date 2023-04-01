package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNum1, etNum2, etOperation;
    private TextView tvResultText;
    private  Button btncalculateresult;
    private Toast toasterror;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum1 = findViewById(R.id.num1_edit_text);
        etNum2 = findViewById(R.id.num2_edit_text);
        etOperation = findViewById(R.id.operation_edit_text);
        tvResultText = findViewById(R.id.result_text_view);
        btncalculateresult=findViewById(R.id.calculate_result_button);
        btncalculateresult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1, num2, result=0;
        boolean correctoperation=true;
        String operation="";
        try {
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
            operation = etOperation.getText().toString();
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2==0)  throw new ArithmeticException();
                    result = num1 / num2;
                    break;
                default:
                    correctoperation=false;
                    break;
            }
        } catch(ArithmeticException e) {
            int duration=Toast.LENGTH_SHORT;
            if(toasterror!=null){
                toasterror.cancel();
            }
            toasterror=Toast.makeText(this,R.string.divide_zero,duration);
            toasterror.show();
            return;
        } catch (NullPointerException e){
            int duration=Toast.LENGTH_SHORT;
            if(toasterror!=null){
                toasterror.cancel();
            }
            toasterror=Toast.makeText(this,R.string.null_data,duration);
            toasterror.show();
            return;
        } catch (NumberFormatException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toasterror != null) {
                toasterror.cancel();
            }
            toasterror = Toast.makeText(this, R.string.wrong_format, duration);
            toasterror.show();
            return;
        }

        if(correctoperation) {

            tvResultText.setText(num1 + " " + operation + " " + num2 + "=" + result);
        } else{
            int duration = Toast.LENGTH_SHORT;
            if (toasterror != null) {
                toasterror.cancel();
            }
            toasterror = Toast.makeText(this, R.string.wrong_operation, duration);
            toasterror.show();
            return;
        }

    }
}