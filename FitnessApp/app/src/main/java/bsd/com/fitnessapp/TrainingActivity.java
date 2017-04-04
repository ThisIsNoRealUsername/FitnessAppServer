package bsd.com.fitnessapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private String selectedItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // used to align the text of the action bar to center
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_training_activity);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTraining);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.training_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWorkout);

        if (selectedItem.equals("Power")) {
            intent = new Intent(this, PowerActivity.class);
            intent.putExtra("workout", spinner.getSelectedItem().toString());
            startActivity(intent);
        } else if (selectedItem.equals("Cardio")) {
            intent = new Intent(this, CardioActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerWorkout);

        if (selected.equals("Power")) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.power_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            selectedItem = "Power";
        } else if (selected.equals("Cardio")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.cardio_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            selectedItem = "Cardio";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
