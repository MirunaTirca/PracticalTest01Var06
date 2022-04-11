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
    final public static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    int scoreTotal = 0;

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
                    int nbChecked = 0;

                    if (!firstCheckBox.isChecked()) {
                        firstEditText.setText(randomArray[rnd1]);
                    } else {
                        nbChecked++;
                    }
                    if (!secondCheckBox.isChecked()) {
                        secondEditText.setText(randomArray[rnd2]);
                    } else{
                        nbChecked++;
                    }
                    if (!thirdCheckbox.isChecked()) {
                        thirdEditText.setText(randomArray[rnd3]);
                    } else {
                        nbChecked++;
                    }
                    Log.d("b1", "randoms: " + randomArray[rnd1] + " -  " + randomArray[rnd2] +
                            " - " + randomArray[rnd3]);

                    // snd activity
                    Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    if (randomArray[rnd1] == "*")
                        randomArray[rnd1] = "0";
                    if (randomArray[rnd2] == "*")
                        randomArray[rnd2] = "0";
                    if (randomArray[rnd3] == "*")
                        randomArray[rnd3] = "0";

                    intent.putExtra("FIRST", Integer.valueOf(randomArray[rnd1]));
                    intent.putExtra("SECOND", Integer.valueOf(randomArray[rnd2]));
                    intent.putExtra("THIRD", Integer.valueOf(randomArray[rnd3]));
                    intent.putExtra("TOTAL", nbChecked);

                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);

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

//        Intent intent1 = getIntent();
//        if(intent1 != null && intent1.getExtras().containsKey("SCORE")) {
//            Toast.makeText(this, "THE ACTIVITY RETURNED WITH SCORE " + intent1.getIntExtra("SCORE", -1), Toast.LENGTH_LONG).show();
//        }
    }

    // C2 - intentii
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        scoreTotal += resultCode;
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "THE ACTIVITY RETURNED WITH CODE " + scoreTotal, Toast.LENGTH_LONG).show();

        }
    }
}