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

    private static String equation = "";


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
        if (clearButton.getId() == R.id.clearButton && equation.length() != 0) {
            Log.i("Mercado", "Deleted character in the equation");
            equation = equation.substring(0, equation.length() - 1);
            TextView equationTV = findViewById(R.id.equationView);
            equationTV.setText(equation);
        }
    }

    public void showResult (View v) {
        String finalResult = "";
        int length = equation.length();
        TextView resultTV = findViewById(R.id.answerView);
        if (v.getId() == R.id.equalsButton) {
            if ((equation.length() == 0) || (equation.indexOf("**") != -1) || (equation.indexOf("//") != -1)
                    || (equation.indexOf("++") != -1) || (equation.indexOf("**") != -1) || (equation.substring(0, 1).equals("*")) || (equation.indexOf("..") != -1) ||
                    (equation.substring(0, 1).equals("/")) || (equation.substring(0, 1).equals("+")) ||
                    (equation.substring(length - 1, length).equals("*")) || (equation.substring(length - 1, length).equals("/")) ||
                    (equation.substring(length - 1, length).equals("+")) || (equation.substring(length - 1, length).equals("-"))) {
                finalResult = "There's a typo in your equation. Please fix it.";
                Log.i("Mercado", "typo made");
            } else {
                String equationCopy = equation;
                boolean keepGoing = true;
                while (keepGoing & equationCopy.indexOf("+") != -1 || equationCopy.indexOf("-") != -1 || equationCopy.indexOf("*") != -1 || equationCopy.indexOf("/") != -1) {

                    if (equationCopy.indexOf("*") != -1) {
                        equationCopy = performOperation(equationCopy, (equationCopy.indexOf("*")));
                    } else if (equationCopy.indexOf("/") != -1) {
                        equationCopy = performOperation(equationCopy, (equationCopy.indexOf("/")));
                    } else if (equationCopy.indexOf("+") != -1) {
                        equationCopy = performOperation(equationCopy, (equationCopy.indexOf("+")));
                    } else if (equationCopy.indexOf("-") != -1) {
                        equationCopy = performOperation(equationCopy, (equationCopy.indexOf("-")));
                    }
                    if (isInteger(equationCopy)) {
                        Log.i("Mercado", "result is a negative int");
                        keepGoing = false;
                    }

                }

                finalResult = equationCopy;
            }

            resultTV.setText(finalResult);
        }
    }

    public String performOperation(String equationCopy, int indexOfOperator) {
        int newNum = 0;
        String operator = equationCopy.substring(indexOfOperator, indexOfOperator + 1);
        int leftNum = getLeftNumber(equationCopy, indexOfOperator);
        int rightNum = getRightNumber(equationCopy, indexOfOperator);

        if (operator.equals("*")) {
            newNum = leftNum*rightNum;
        }
        else if (operator.equals("/")){
            if (rightNum != 0) {
                newNum = leftNum / rightNum;
            } else {
                return "Error";
            }
        }
        else if (operator.equals("+")) {
            newNum = leftNum+rightNum;
        }

        else if (operator.equals("-")) {
            newNum = leftNum - rightNum;
        }

            String newEquation = equationCopy.substring(0, indexOfOperator - findNumberOfDigits(leftNum)) + newNum +
                    equationCopy.substring(indexOfOperator + findNumberOfDigits(rightNum) + 1);

            Log.i("Mercado", newEquation);

            if (String.valueOf(newNum).equals(newEquation)) {

            }
            return newEquation;


    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            // It's not a valid integer
            return false;
        }
    }



    public int findNumberOfDigits (int num) {
        int numDigits = 0;
        int integerNum = (int) (num);
        while (integerNum != 0) {
            numDigits++;
            integerNum /= 10;
        }
        return numDigits;


    }

    public int getLeftNumber (String equationCopy, int indexOfOperator) {
        String leftNumStr = "";
        int index = indexOfOperator - 1;
        while(index >= 0 && !equationCopy.substring(index, index + 1).equals("+") && !equationCopy.substring(index, index + 1).equals("-") &&
                !equationCopy.substring(index, index + 1).equals("*") && !equationCopy.substring(index, index + 1).equals("/")) {
            leftNumStr += equationCopy.substring(index, index + 1);
            index--;
        }
        int leftNum = Integer.valueOf(leftNumStr);
        return leftNum;
    }

    public int getRightNumber (String equationCopy, int indexOfOperator) {
        String rightNumStr = "";
        int index = indexOfOperator + 1;
        while(index < equationCopy.length() && !equationCopy.substring(index, index + 1).equals("+") && !equationCopy.substring(index, index + 1).equals("-") &&
                !equationCopy.substring(index, index + 1).equals("*") && !equationCopy.substring(index, index + 1).equals("/")) {
            rightNumStr += equationCopy.substring(index, index + 1);
            index++;
        }
        int leftNum = Integer.valueOf(rightNumStr);
        return leftNum;
    }

}





