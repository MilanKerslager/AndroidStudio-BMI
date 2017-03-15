package cz.kerslager.android.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextVaha, editTextVyska;
    TextView tiskBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextVaha = (EditText) findViewById(R.id.editTextVaha);
        editTextVyska = (EditText) findViewById(R.id.editTextVyska);
        tiskBMI = (TextView) findViewById(R.id.textViewBMI);
    }

    public void onButtonKlik(View view) {
        Double vaha = 0.0, vyska = 0.0;
        try {
            vaha = Double.parseDouble(editTextVaha.getText().toString());
            vyska = Double.parseDouble(editTextVyska.getText().toString());
        } catch (NumberFormatException e) {
            showError("Chybný vstup!");
        }
        if (vaha != 0 && vyska != 0) {
            tiskBMI.setText(String.format("%.2f",
                    vaha / Math.pow(vyska / 100, 2)));
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
