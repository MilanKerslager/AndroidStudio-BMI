package cz.kerslager.android.bmi;

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
    TextView printBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // when activity starts, find pointers to GUI widgets to use them later
        editTextWeight = (EditText) findViewById(R.id.editTextVaha);
        editTextHeight = (EditText) findViewById(R.id.editTextVyska);
        printBMI = (TextView) findViewById(R.id.textViewBMI);
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
        if (weight != 0 && height != 0) {
            printBMI.setText(String.format(Locale.getDefault(),
                    "%.2f", weight / Math.pow(height / 100, 2)));
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
