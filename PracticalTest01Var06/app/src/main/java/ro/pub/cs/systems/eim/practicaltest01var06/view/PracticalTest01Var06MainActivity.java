package ro.pub.cs.systems.eim.practicaltest01var06.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var06.R;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText firstEditText, secondEditText, thirdEditText;
    private CheckBox firstCheckBox, secondCheckBox, thirdCheckbox;
    private Button playButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        public void onClick(View view) {

            String[] randomArray  = {"1", "2", "3", "*"};

            switch (view.getId()) {
                case R.id.play_button:
                    int rnd1 = new Random().nextInt(randomArray.length);
                    int rnd2 = new Random().nextInt(randomArray.length);
                    int rnd3 = new Random().nextInt(randomArray.length);

                    if (!firstCheckBox.isChecked()) {
                        firstEditText.setText(randomArray[rnd1]);
                    }
                    if (!secondCheckBox.isChecked()) {
                        secondEditText.setText(randomArray[rnd2]);
                    }
                    if (!thirdCheckbox.isChecked()) {
                        thirdEditText.setText(randomArray[rnd3]);
                    }
//                    Toast.makeText(this, "we picked numbers: " + randomArray[rnd1],
//                            Toast.LENGTH_LONG).show();
//                    Toast.makeText(this, "first nb " + randomArray[rnd1], Toast.LENGTH_LONG).show();
                    Log.d("b1", "randoms: " + randomArray[rnd1] + " -  " + randomArray[rnd2] +
                            " - " + randomArray[rnd3]);

                    break;
            }



        }
    }

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        if(firstCheckBox.isChecked()){

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        firstEditText = (EditText) findViewById(R.id.first_edit_text);
        secondEditText = (EditText) findViewById(R.id.second_edit_text);
        thirdEditText = (EditText) findViewById(R.id.third_edit_text);

        firstCheckBox = (CheckBox) findViewById(R.id.first_checkbox);
        secondCheckBox = (CheckBox) findViewById(R.id.second_checkbox);
        thirdCheckbox = (CheckBox) findViewById(R.id.third_checkbox);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(buttonClickListener);

    }


}