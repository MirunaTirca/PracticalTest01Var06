package ro.pub.cs.systems.eim.practicaltest01var06.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ro.pub.cs.systems.eim.practicaltest01var06.R;

public class ThirdActivity extends AppCompatActivity {
    final public static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    int score = 0;

    private TextView gainedTextView;
    private Button okButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.okButton:
                    setResult(score, null);
                    break;
            }

            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // C1 - trb .xml, manifest + event listener pe botoanelel ok si cancel
        gainedTextView = (TextView) findViewById(R.id.gained);
        Intent intent = getIntent();

        if (intent != null && intent.getExtras().containsKey("FIRST")
                && intent.getExtras().containsKey("SECOND") && intent.getExtras().containsKey("THIRD")) {
//            gainedTextView.setText(intent.getIntExtra("FIRST", -1));
            int nr1 = Integer.valueOf(intent.getIntExtra("FIRST", -1));
            int nr2 = Integer.valueOf(intent.getIntExtra("SECOND", -1));
            int nr3 = Integer.valueOf(intent.getIntExtra("THIRD", -1));

            int nbchecked = Integer.valueOf(intent.getIntExtra("TOTAL", -1));
            int OK = 0;




            if (( nr1 == nr2 && nr2 == nr3) || (nr1 == 0 && nr2==nr3 ) || (nr2==0 && nr1==nr3) ||
                    (nr3==0 && nr1==nr2) || (nr1== 0 && nr2==0) || (nr1==0 && nr3==0) || (nr2==0 && nr3==0)) {
                OK = 1;
                gainedTextView.setText("YOU WON" + String.valueOf(score));

            } else {
                OK = 0; //lost
                gainedTextView.setText("LOST..");

            }


            if (OK == 1) {
                if (nbchecked == 0) {
                    score = 100;
                } else if (nbchecked == 1) {
                    score = 50;
                } else {
                    score = 10;
                }
            } else {
                score = 0;
            }

            if (savedInstanceState != null) {
                if (savedInstanceState.containsKey("SCORESAVE")) {
                    gainedTextView.setText(savedInstanceState.getString("SCORESAVE"));
                }
            } else {
                gainedTextView.setText("You lost..");
            }

        }

        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(buttonClickListener);

    }


    // salvarea starii
    // B2 b) - salvarea starii
    // obs: pt a nu se salva - in .xml -> saveEnabled=false
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("SCORESAVE", String.valueOf(gainedTextView.getText()));
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState.containsKey("SCORESAVE") && score == Integer.valueOf(savedInstanceState.getString("SCORESAVE"))) {
            gainedTextView.setText(savedInstanceState.getString("SCORESAVE"));
        } else {
            gainedTextView.setText("YOU LOST...");
        }

    }
}