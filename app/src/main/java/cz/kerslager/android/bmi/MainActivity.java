package cz.kerslager.android.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editTextWeight, editTextHeight;
    TextView printBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = (EditText) findViewById(R.id.editTextVaha);
        editTextHeight = (EditText) findViewById(R.id.editTextVyska);
        printBMI = (TextView) findViewById(R.id.textViewBMI);
    }

    public void onButtonVypocti(View view) {
        Double vaha = 0.0, vyska = 0.0;
        try {
            vaha = Double.parseDouble(editTextWeight.getText().toString());
            vyska = Double.parseDouble(editTextHeight.getText().toString());
        } catch (NumberFormatException e) {
            showError(getString(R.string.input_error));
        }
        if (vaha != 0 && vyska != 0) {
            printBMI.setText(String.format(Locale.getDefault(),
                    "%.2f", vaha / Math.pow(vyska / 100, 2)));
        }
    }

    private void showError(String zprava) {
        Toast myToast = Toast.makeText(
                getApplication(), zprava,
                Toast.LENGTH_LONG
        );
        myToast.show();
    }
}
