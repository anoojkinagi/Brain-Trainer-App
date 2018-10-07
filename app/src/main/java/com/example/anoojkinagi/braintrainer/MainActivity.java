package com.example.anoojkinagi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int noOfQuestions = 0;
    TextView sumTextView;
    public void generateQuestion()
    {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(a+" + "+b);
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(a+b);

            }
            else
            {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b)
                {
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
         sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
         button0 = (Button) findViewById(R.id.button0);
         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);




    }

    public void start(View view) {

       // startButton.setVisibility(View.INVISIBLE);
        startButton.animate().translationYBy(-1500).setDuration(500);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view) {


       if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
       {
           score++;
           resultTextView.setText("Correct!");
       }
       else {
           resultTextView.setText("Wrong!");
       }

        noOfQuestions++;
       pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
       generateQuestion();
    }

    public void playAgain(View view) {

        score = 0;
        noOfQuestions = 0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        pointsTextView.setText("0/0");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {

                resultTextView.setText("You Scored "+ Integer.toString(score) +"/" + Integer.toString(noOfQuestions));
                timerTextView.setText("0s");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);


            }
        }.start();
    }
}
