package com.example.calculatorappmercado;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class CalculatorActivity extends AppCompatActivity {

    private static String equation = "";
    private static ArrayList<Double> numList = new ArrayList<>();
    private static ArrayList<String> operationList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

    }


    public void createEquation(View v) {
        if (v.getId() == R.id.multiplyButton) {
            Log.i("Mercado", "Selected multiplication symbol");
            equation += "*";
        } else if (v.getId() == R.id.sevenButton) {
            Log.i("Mercado", "Selected 7");
            equation += "7";
        } else if (v.getId() == R.id.eightButton) {
            Log.i("Mercado", "Selected 8");
            equation += "8";
        } else if (v.getId() == R.id.nineButton) {
            Log.i("Mercado", "Selected 9");
            equation += "9";
        } else if (v.getId() == R.id.fourButton) {
            Log.i("Mercado", "Selected 4");
            equation += "4";
        } else if (v.getId() == R.id.fiveButton) {
            Log.i("Mercado", "Selected 5");
            equation += "5";
        } else if (v.getId() == R.id.sixButton) {
            Log.i("Mercado", "Selected 6");
            equation += "6";
        } else if (v.getId() == R.id.minusButton) {
            Log.i("Mercado", "Selected -");
            equation += "-";
        } else if (v.getId() == R.id.divideButton) {
            Log.i("Mercado", "Selected divide symbol");
            equation += "/";
        } else if (v.getId() == R.id.oneButton) {
            Log.i("Mercado", "Selected 1");
            equation += "1";
        } else if (v.getId() == R.id.twoButton) {
            Log.i("Mercado", "Selected 2");
            equation += "2";
        } else if (v.getId() == R.id.threeButton) {
            Log.i("Mercado", "Selected 3");
            equation += "3";
        } else if (v.getId() == R.id.plusButton) {
            Log.i("Mercado", "Selected +");
            equation += "+";
        } else if (v.getId() == R.id.zeroButton) {
            Log.i("Mercado", "Selected 0");
            equation += "0";
        }

        TextView equationTV = findViewById(R.id.equationView);
        equationTV.setText(equation);
    }

    public void clearEquation(View v) {
        TextView equationTV = findViewById(R.id.equationView);
        if(v.getId() == R.id.clearButton) {
            if (equation.length() != 0) {
                equation = equation.substring(0, equation.length() - 1);
                Log.i("Mercado", "Deleted a character in the equation");
                equationTV.setText(equation);
            }
        }
        else {
            equation = "";
            equationTV.setText("");
            Log.i("Mercado", "Cleared equation");
        }
    }


    public void findNumList () {
        numList.clear();
        int startIndex = 0;
        int endIndex = 0;
        while (endIndex <= equation.length()) {
            // I used the isDigit() function to check if a character in a certain index of the equation
            // was a number. I learned how to do this here: https://www.scaler.com/topics/isdigit-java/.
            if (endIndex == equation.length() || !Character.isDigit(equation.charAt(endIndex))) {
                if (endIndex > startIndex) {
                    String numberStr = equation.substring(startIndex, endIndex);
                    // I used the .parseDouble() function to convert a number in a String to a double
                    // in order to perform calculations. I learned about this function here: https://www.digitalocean.com/community/tutorials/java-convert-string-to-double.
                    double number = Double.parseDouble(numberStr);
                    numList.add(number);
                    Log.i("Mercado", "currently adding " + numberStr + " to numList");
                }
                startIndex = endIndex + 1;
            }
            endIndex++;
        }
        if (equation.charAt(0) == '-')
            numList.set(0, -numList.get(0));
        Log.i("Mercado", "NumList created: " + numList.toString());
            }

    public void setOperationList () {
        operationList.clear();

        equation = equation.replace("+-", "-");
        equation = equation.replace("-+", "-");
        equation = equation.replace("--", "+");

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '+' || (equation.charAt(i) == '-' && i != 0)
                    || equation.charAt(i) == '/' || equation.charAt(i) == '*') {
                operationList.add(String.valueOf(equation.charAt(i)));
            }
        }
        Log.i("Mercado", "operation list created: " + operationList.toString());

    }

    public void showResult(View v) {
        Log.i("Mercado", "Calculating result");
        TextView resultTV = findViewById(R.id.answerView);
        double result = 0;
        if(equation.length() != 0) {
            setOperationList();
            findNumList();
            if (operationList.size() >= numList.size()) {
                resultTV.setText("Syntax Error");
            }
            else {
                while (numList.size() > 1) {
                    if (operationList.indexOf("*") != -1 || operationList.indexOf("/") != -1) {
                        int multiplicationIndex = operationList.indexOf("*");
                        int divisionIndex = operationList.indexOf("/");

                        if (divisionIndex == -1) {
                            performOperation(operationList.indexOf("*"), "*");
                        } else if (multiplicationIndex == -1) {
                            performOperation(operationList.indexOf("/"), "/");
                        } else if (multiplicationIndex < divisionIndex) {
                            performOperation(operationList.indexOf("*"), "*");
                        } else {
                            performOperation(operationList.indexOf("/"), "/");
                        }

                    } else if (operationList.indexOf("+") != -1 || operationList.indexOf("-") != -1) {
                        int plusIndex = operationList.indexOf("+");
                        int minusIndex = operationList.indexOf("-");

                        if (minusIndex == -1) {
                            performOperation(operationList.indexOf("+"), "+");
                        } else if (plusIndex == -1) {
                            performOperation(operationList.indexOf("-"), "-");
                        } else if (plusIndex < minusIndex) {
                            performOperation(operationList.indexOf("+"), "+");
                        } else {
                            performOperation(operationList.indexOf("-"), "-");
                        }
                    }
                }
                result = numList.get(0);
                resultTV.setText("= " + result);
            }
        }
        else{
            resultTV.setText("= ");
        }
    }


    public void performOperation(int indexOfOperation, String operation) {

        double result = 0;

        Log.i("Mercado", String.valueOf(indexOfOperation));

        double firstNum = numList.get(indexOfOperation);
        double secondNum = numList.get(indexOfOperation+1);


        if (operation.equals("+"))
            result = firstNum + secondNum;
        else if (operation.equals("-"))
            result = firstNum - secondNum;
        else if (operation.equals("*"))
            result = firstNum * secondNum;
        else if (operation.equals("/"))
            result = firstNum / secondNum;

        numList.set(indexOfOperation, result);
        numList.remove(indexOfOperation+1);
        operationList.remove(indexOfOperation);

        Log.i("Mercado", "Operation performed: " + operation);
        Log.i("Mercado", numList.toString());
        Log.i("Mercado", operationList.toString());

    }
}