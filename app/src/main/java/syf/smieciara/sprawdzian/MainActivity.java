package syf.smieciara.sprawdzian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.IllegalFormatConversionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.obliczButton);
        EditText aText = findViewById(R.id.aNumber);
        EditText bText = findViewById(R.id.bNumber);
        EditText cText = findViewById(R.id.cNumber);
        EditText wynikEdit = findViewById(R.id.wynikEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wynikEdit.setText("");
                try{
                    double a = Double.parseDouble(String.valueOf(aText.getText().toString()));
                    double b = Double.parseDouble(String.valueOf(bText.getText().toString()));
                    double c = Double.parseDouble(String.valueOf(cText.getText().toString()));
                    double delta = Math.pow(b, 2)*(-4)*a*c;
                    if (delta < 0) {
                        Toast.makeText(getBaseContext(), "Brak rozwiązań", Toast.LENGTH_SHORT).show();
                    } else {
                        if (delta == 0){
                            double x = -b*2*a;
                            wynikEdit.setText(String.format("x=%d", x));
                        } else if (delta > 0){
                            double x1 = -b + Math.sqrt(delta) / 2 * a;
                            double x2 = -b - Math.sqrt(delta) / 2 * a;
                            wynikEdit.setText(String.format("x1=%d, x2=%d", x1, x2));
                        } else {
                            wynikEdit.setText("sdf");
                        }
                    }
                } catch (NumberFormatException e){
                    Toast.makeText(getBaseContext(), "Uzupełnij pola", Toast.LENGTH_SHORT).show();
                } catch (IllegalFormatConversionException e){
                    Toast.makeText(getBaseContext(), "Nie może być zera!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}