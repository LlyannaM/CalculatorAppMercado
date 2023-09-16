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

    public void createEquation(View numButton) {
        if (numButton.getId() == R.id.multiplyButton) {
            Log.i("Mercado", "Selected multiplication symbol");
            equation += "*";
        } else if (numButton.getId() == R.id.sevenButton) {
            Log.i("Mercado", "Selected 7");
            equation += "7";
        } else if (numButton.getId() == R.id.eightButton) {
            Log.i("Mercado", "Selected 8");
            equation += "8";
        } else if (numButton.getId() == R.id.nineButton) {
            Log.i("Mercado", "Selected 9");
            equation += "9";
        } else if (numButton.getId() == R.id.fourButton) {
            Log.i("Mercado", "Selected 4");
            equation += "4";
        } else if (numButton.getId() == R.id.fiveButton) {
            Log.i("Mercado", "Selected 5");
            equation += "5";
        } else if (numButton.getId() == R.id.sixButton) {
            Log.i("Mercado", "Selected 6");
            equation += "6";
        } else if (numButton.getId() == R.id.minusButton) {
            Log.i("Mercado", "Selected -");
            equation += "-";
        } else if (numButton.getId() == R.id.divideButton) {
            Log.i("Mercado", "Selected divide symbol");
            equation += "/";
        } else if (numButton.getId() == R.id.oneButton) {
            Log.i("Mercado", "Selected 1");
            equation += "1";
        } else if (numButton.getId() == R.id.twoButton) {
            Log.i("Mercado", "Selected 2");
            equation += "2";
        } else if (numButton.getId() == R.id.threeButton) {
            Log.i("Mercado", "Selected 3");
            equation += "3";
        } else if (numButton.getId() == R.id.plusButton) {
            Log.i("Mercado", "Selected +");
            equation += "+";
        } else if (numButton.getId() == R.id.zeroButton) {
            Log.i("Mercado", "Selected 0");
            equation += "0";
        }

        TextView equationTV = findViewById(R.id.equationView);
        equationTV.setText(equation);

    }


    public void clearEquation(View clearButton) {
        Log.i("Mercado", "Deleted character in the equation");
        if (equation.length() != 0) {
            equation = equation.substring(0, equation.length() - 1);
            TextView equationTV = findViewById(R.id.equationView);
            equationTV.setText(equation);
        }
    }


    public void findNumList () {
        numList.clear();
        Log.i("Mercado", "findNumListcalled");
        int startIndex = 0;
        int endIndex = 0;

        while (endIndex <= equation.length()) {
            if (endIndex == equation.length() || !Character.isDigit(equation.charAt(endIndex))) {
                if (endIndex > startIndex) {
                    String numberStr = equation.substring(startIndex, endIndex);
                    double number = 0;
                    number = Double.parseDouble(numberStr);
                    numList.add(number);
                    Log.i("Mercado", "findNumList currently adding " + numberStr);
                }
                startIndex = endIndex + 1;
            }
            endIndex++;
        }
        if (equation.charAt(0) == '-')
            numList.set(0, -numList.get(0));

        Log.i("Mercado", "findNumList CREATED" + numList.toString());
            }

    public void setOperationList () {
        operationList.clear();
        Log.i("Mercado", "operation list making" + equation);
        equation = equation.replace("+-", "-");
        equation = equation.replace("--", "+");
        Log.i("Mercado", "operation list edited" + equation);

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '+' || (equation.charAt(i) == '-' && i != 0)
                    || equation.charAt(i) == '/' || equation.charAt(i) == '*') {
                operationList.add(String.valueOf(equation.charAt(i)));
            }
        }
        Log.i("Mercado", "operation list created" + operationList.toString());
        // if num elements in operationList is greater than numList, set error

    }

    public void showResult(View v) {
        Log.i("Mercado", "calculating result");
        TextView resultTV = findViewById(R.id.answerView);
        double result = 0;
        if(equation.length() != 0) {
            findNumList();
            setOperationList();
            if (operationList.size() >= numList.size()) {
                resultTV.setText("Error");
            } else {


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
    }



    public void performOperation(int indexOfOperation, String operation) {
        Log.i("Mercado", "performOperationCalled" + operation + indexOfOperation);
        Log.i("Mercado", numList.toString());
        Log.i("Mercado", operationList.toString());
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
        Log.i("Mercado", numList.toString());
        Log.i("Mercado", operationList.toString());

    }
}