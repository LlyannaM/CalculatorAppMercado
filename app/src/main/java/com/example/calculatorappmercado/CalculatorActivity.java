package com.example.calculatorappmercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CalculatorActivity extends AppCompatActivity {

    private String equation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //String equation = "";


        //TextView equationTV = findViewById(R.id.equationView);
        //TextView answerTV = findViewById(R.id.answerView);

    }


    public void createEquation (View numButton) {
        if (numButton.getId() == R.id.leftParenthesesButton) {
            Log.i("Mercado", "Selected left parentheses");
            equation +="(";
        }
        else if (numButton.getId() == R.id.RightParenthesesButton) {
            Log.i("Mercado", "Selected right parentheses");
            equation +=")";
        }
        else if (numButton.getId() == R.id.multiplyButton) {
            Log.i("Mercado", "Selected multiplication symbol");
            equation +="*";
        }
        else if (numButton.getId() == R.id.sevenButton) {
            Log.i("Mercado", "Selected 7");
            equation +="7";
        }
        else if (numButton.getId() == R.id.eightButton) {
            Log.i("Mercado", "Selected 8");
            equation +="8";
        }
        else if (numButton.getId() == R.id.nineButton) {
            Log.i("Mercado", "Selected 9");
            equation +="9";
        }
        else if (numButton.getId() == R.id.fourButton) {
            Log.i("Mercado", "Selected 4");
            equation +="4";
        }
        else if (numButton.getId() == R.id.fiveButton) {
            Log.i("Mercado", "Selected 5");
            equation +="5";
        }
        else if (numButton.getId() == R.id.sixButton) {
            Log.i("Mercado", "Selected 6");
            equation +="6";
        }
        else if (numButton.getId() == R.id.minusButton) {
            Log.i("Mercado", "Selected -");
            equation +="-";
        }
        else if (numButton.getId() == R.id.divideButton) {
            Log.i("Mercado", "Selected divide symbol");
            equation +="/";
        }
        else if (numButton.getId() == R.id.oneButton) {
            Log.i("Mercado", "Selected 1");
            equation +="1";
        }
        else if (numButton.getId() == R.id.twoButton) {
            Log.i("Mercado", "Selected 2");
            equation +="2";
        }
        else if (numButton.getId() == R.id.threeButton) {
            Log.i("Mercado", "Selected 3");
            equation +="3";
        }
        else if (numButton.getId() == R.id.plusButton) {
            Log.i("Mercado", "Selected +");
            equation +="+";
        }
        else if (numButton.getId() == R.id.zeroButton) {
            Log.i("Mercado", "Selected 0");
            equation +="0";
        }
        else if (numButton.getId() == R.id.decimalButton) {
            Log.i("Mercado", "Selected .");
            equation +=".";
        }

       /*

        for (int i = 0; i < equation.length(); i++ ) {
            if (equation.substring(i, i+1).equals("+") || equation.substring(i, i+1).equals("-") ||
                    equation.substring(i, i+1).equals("*") || equation.substring(i, i+1).equals("/")) {
                answer += 0;
            }
            else {
                answer += Double.valueOf(equation.substring(i+i+1));
            }


        }

        */



    }

    public void showResult(View v) {
        double answer = 0;
        if (v.getId() == R.id.equalsButton) {
            Log.i("Mercado", "Selected = button");

            for (int i = 0; i < equation.length(); i++ ) {
                double nextNum = findNextNumber(i);





            }



            }

            TextView equationTV = findViewById(R.id.equationView);
            equationTV.setText(equation);
            TextView answerTV = findViewById(R.id.answerView);
            answerTV.setText("=" + answer);

    }


        public double findNextNumber (int i) {
            double nextNum = 0;
            boolean keepGoing = true;
            while (keepGoing) {
            if (equation.substring(i, i+1).equals("+") || equation.substring(i, i+1).equals("-") ||
                    equation.substring(i, i+1).equals("*") || equation.substring(i, i+1).equals("/")) {
                keepGoing = false;
            }
            else {
                nextNum += Double.valueOf(equation.substring(i+i+1));
            }
            i++;
        }
            return nextNum;

}
}