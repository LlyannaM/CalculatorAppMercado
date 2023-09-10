package com.example.calculatorappmercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button button = (R.id.)
        createEquation()
        double answer;

        TextView equationTV = findViewById(R.id.equationView);
        TextView answerTV = findViewById(R.id.answerView);
        
    }

    public double createEquation (View view, Button calcButton) {
        String equation = "";
        if (calcButton == findViewById(R.id.sevenButton)) {
            equation += 7;
        }
        else if (calcButton == findViewById(R.id.eightButton)) {
            equation += 8;
        }

        double answer = 0;
       return answer;



    }









}