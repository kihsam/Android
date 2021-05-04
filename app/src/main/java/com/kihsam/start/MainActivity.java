package com.kihsam.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.kihsam.start.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private char CURRENT_ACTION;
    private double result = Double.NaN;
    private double value;
    private DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        decimalFormat = new DecimalFormat("#.##########");

        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().toString().indexOf(".") == -1) {
                    binding.editText.setText(binding.editText.getText() + ".");
                } else {
                    binding.editText.setText(binding.editText.getText() + "");
                }
            }
        });

        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation();
                CURRENT_ACTION = PLUS;
                binding.infoTextView.setText(decimalFormat.format(result) + "+");
                binding.editText.setText(null);
            }
        });

        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation();
                CURRENT_ACTION = MINUS;
                binding.infoTextView.setText(decimalFormat.format(result) + "-");
                binding.editText.setText(null);
            }
        });

        binding.buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.infoTextView.setText(decimalFormat.format(result) + "*");
                binding.editText.setText(null);
            }
        });

        binding.buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation();
                CURRENT_ACTION = DIVISION;
                binding.infoTextView.setText(decimalFormat.format(result) + "/");
                binding.editText.setText(null);
            }
        });

        binding.buttonEqually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation();
                binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                        decimalFormat.format(value) + " = " + decimalFormat.format(result));
                result = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length()-1));
                }
                else {
                    result = Double.NaN;
                    value = Double.NaN;
                    binding.editText.setText("");
                    binding.infoTextView.setText("");
                }
            }
        });

    }


    // сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putChar("CURRENT_ACTION", CURRENT_ACTION);
        if(result!=Double.NaN) {
            outState.putDouble("RESULT", result);
            outState.putString("RES", binding.infoTextView.getText().toString());
        }
        if(value!=Double.NaN)
            outState.putDouble("VALUE", value);
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CURRENT_ACTION = savedInstanceState.getChar("CURRENT_ACTION");
        result = savedInstanceState.getDouble("RESULT");
        value = savedInstanceState.getDouble("VALUE");
        binding.infoTextView.setText(savedInstanceState.getString("RES"));

    }

    private void calculation() {
        if (!Double.isNaN(result)) {
            value = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if (CURRENT_ACTION == PLUS)
                result = this.result + value;
            else if (CURRENT_ACTION == MINUS)
                result = this.result - value;
            else if (CURRENT_ACTION == MULTIPLICATION)
                result = this.result * value;
            else if (CURRENT_ACTION == DIVISION)
                result = this.result / value;
        } else {
            try {
                result = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {
            }
        }
    }

}


