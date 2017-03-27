package cz.kerslager.android.bmi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // global variables for accessing GUI widgets
    EditText editTextWeight, editTextHeight;
    TextView textViewBMI, textViewBMIverbose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // when activity starts, find pointers to GUI widgets to use them later
        editTextWeight = (EditText) findViewById(R.id.editTextVaha);
        editTextHeight = (EditText) findViewById(R.id.editTextVyska);
        textViewBMI = (TextView) findViewById(R.id.textViewBMI);
        textViewBMIverbose = (TextView) findViewById(R.id.textViewBMIverbose);
    }

    public void onButtonVypocti(View view) {
        Double weight = 0.0, height = 0.0;
        try {
            // try to parse input form the user
            weight = Double.parseDouble(editTextWeight.getText().toString());
            height = Double.parseDouble(editTextHeight.getText().toString());
        } catch (NumberFormatException e) {
            // when unable to parse, show error message by Toast
            showError(getResources().getString(R.string.input_error));
        }
        // if we have a data, show the result to the user (only two decimal places)
        Double bmi = weight / Math.pow(height / 100, 2);
        if (weight != 0 && height != 0) {
            textViewBMI.setText(String.format(Locale.getDefault(), "%.2f", bmi));
        }
        // print verbose meaning of the calculated bmi index
        if (bmi < 15) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_15));
            textViewBMIverbose.setTextColor(Color.RED);
        } else if (bmi < 16) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_16));
            textViewBMIverbose.setTextColor(Color.RED);
        } else if (bmi < 18.5) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_185));
            textViewBMIverbose.setTextColor(Color.rgb(255, 165, 0)); // orange
        } else if (bmi < 25) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_25));
            textViewBMIverbose.setTextColor(Color.GREEN);
        } else if (bmi < 30) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_30));
            textViewBMIverbose.setTextColor(Color.rgb(255, 165, 0)); // orange
        } else if (bmi < 35) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_35));
            textViewBMIverbose.setTextColor(Color.YELLOW);
        } else if (bmi < 40) {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_40));
            textViewBMIverbose.setTextColor(Color.RED);
        } else {
            textViewBMIverbose.setText(getResources().getString(R.string.bmi_400));
            textViewBMIverbose.setTextColor(Color.RED);
        }
    }

    // inform user by Toast message
    private void showError(String message) {
        Toast myToast = Toast.makeText(
                getApplication(), message,
                Toast.LENGTH_LONG
        );
        myToast.show();
    }
}
