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


        //String equation = "";


        //TextView equationTV = findViewById(R.id.equationView);
        //TextView answerTV = findViewById(R.id.answerView);

    }
 // include previous answer method

    public void createEquation (View numButton) {
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
        } else if (numButton.getId() == R.id.decimalButton) {
            Log.i("Mercado", "Selected .");
            equation += ".";
        }

        TextView equationTV = findViewById(R.id.equationView);
        equationTV.setText(equation);

    }

    public void clearEquation (View clearButton) {
        if (clearButton.getId() == R.id.clearButton && equation.length() != 0) {
            Log.i("Mercado", "Deleted character in the equation");
            equation = equation.substring(0, equation.length() - 1);
            TextView equationTV = findViewById(R.id.equationView);
            equationTV.setText(equation);
        }
    }

    public void showResult (View v) {
        String finalResult = "";
        int numericResult = 0;
        int length = equation.length();
        TextView resultTV = findViewById(R.id.answerView);
        if (v.getId() == R.id.equalsButton) {
            if ((equation.length() == 0 )|| (equation.indexOf("**") != -1) || (equation.indexOf("//") != -1)
            || (equation.indexOf("++") != -1) || (equation.indexOf("**") != -1) || (equation.substring(0, 1).equals("*")) ||
                    (equation.substring(0, 1).equals("/")) || (equation.substring(0, 1).equals("+")) || (equation.substring(0, 1).equals("-")) ||
                    (equation.substring(length - 1, length).equals("*")) || (equation.substring(length -1, length).equals("/")) ||
                    (equation.substring(length - 1, length).equals("+")) || (equation.substring(length - 1, length).equals("-"))){
                finalResult = "There's a typo in your equation. Please fix it.";
            }
            // here's the calculation method - maybe put it in its own method lol!
            else {
                String equationCopy = equation;
                int currentNum = findNextNum(equationCopy, 0); // gets the first number
                boolean keepGoing = true;
                while (keepGoing) {
                    int indexOfNextOperator = findIndexOfNextOperation(equationCopy, 0);
                    int index = indexOfNextOperator + 1;
                    if (indexOfNextOperator == -1)  {
                        keepGoing = false; }
                    else {
                        if (equationCopy.substring(indexOfNextOperator, indexOfNextOperator + 1).equals("+")) {
                            currentNum += findNextNum(equationCopy, index);
                        }
                        else if (equationCopy.substring(indexOfNextOperator, indexOfNextOperator + 1).equals("-")) {
                            currentNum -= findNextNum(equationCopy, index);
                        }
                        else if (equationCopy.substring(indexOfNextOperator, indexOfNextOperator + 1).equals("*")) {
                            currentNum *= findNextNum(equationCopy, index);
                        }
                        else {
                            currentNum /= findNextNum(equationCopy, index);
                        }
                        equationCopy = equationCopy.substring(indexOfNextOperator + 1); //set equation copy to 0
                    }
                }
                numericResult = currentNum;
                finalResult = String.valueOf(numericResult);
                    }
                }

            resultTV.setText(finalResult);
            }

        public int findNextNum (String equationCopy, int startIndex) {
        String currentNum = "";
        boolean keepGoing = true;
        int index = startIndex;
        while(keepGoing) {
            String currentChar = equationCopy.substring(index, index + 1);
            if (currentChar.equals("+") || currentChar.equals("-") || currentChar.equals("*") || currentChar.equals("/") || index == equation.length()) {
                keepGoing = false;
            }
            else {
                currentNum += currentChar;
            }
            index++;
        }
        int nextNum = Integer.parseInt(currentNum);
        return nextNum;
        }

        public int findIndexOfNextOperation (String equationCopy, int startIndex) {
        int indexOfNextOperator = equationCopy.indexOf("+");

            if (equationCopy.indexOf("-") < indexOfNextOperator) {
                indexOfNextOperator = equationCopy.indexOf("-");
            }

            if (equationCopy.indexOf("*") < indexOfNextOperator) {
                indexOfNextOperator = equationCopy.indexOf("*");
            }

            if (equationCopy.indexOf("/") < indexOfNextOperator) {
                indexOfNextOperator = equationCopy.indexOf("/");
            }

            return indexOfNextOperator;
        }


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




/*
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

 */

/*
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

 */
