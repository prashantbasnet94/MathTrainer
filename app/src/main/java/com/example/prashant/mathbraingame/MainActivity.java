package com.example.prashant.mathbraingame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton, first, second, third, fourth, playAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int answerLocation, numberOfQuestion=0, score;
    TextView answerText, solved, timer, sumTextView;
    ConstraintLayout constraintLayout;



    public void start(View view) {

        goButton.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        playAgain(null);
    }

    public void chooseAnswer(View view) {
        numberOfQuestion++;

        int viewClick = Integer.parseInt(view.getTag().toString());

        if (Integer.toString(answerLocation).equals(view.getTag().toString())) {
            answerText.setText("Correct Answer");
            score++;
            Log.i("pressed tag", view.getTag().toString());
            solved.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

            newQuestion();

        } else {


            answerText.setText("Incorrect Answer");
            solved.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

            newQuestion();
        }
    }

    public void newQuestion() {

        Random rand = new Random();
        int a = (int) (Math.random() * 20 + 0);
        int b = rand.nextInt(20);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        answerLocation = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (answerLocation == i) {
                answers.add(a + b);

                Log.i("tag", Integer.toString(answerLocation));
            } else {
                int wrongAnswer = rand.nextInt(41);
                while ((a + b) == wrongAnswer) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);

            }


        }
        first.setText(Integer.toString(answers.get(0)));
        second.setText(Integer.toString(answers.get(1)));
        third.setText(Integer.toString(answers.get(2)));
        fourth.setText(Integer.toString(answers.get(3)));

        answerText = findViewById(R.id.answerText);


    }


    public void playAgain(final View view) {
        playAgain.setVisibility(View.INVISIBLE);

        solved.setText(score + "/" + numberOfQuestion);


        new CountDownTimer(30000, 1000) {


            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000));
            }


            @Override
            public void onFinish() {
                answerText.setText("Done!");
                Log.i("number of question", Integer.toString(numberOfQuestion));
                Toast.makeText(MainActivity.this, "Your score is  " + score + "/" + numberOfQuestion, Toast.LENGTH_SHORT).show();
                playAgain.setVisibility(View.VISIBLE);
                first.setEnabled(false);
                second.setEnabled(false);
                third.setEnabled(false);
                fourth.setEnabled(false);
                score = 0;
                numberOfQuestion = 0;
                playAgain.setVisibility(View.VISIBLE);


            }
        }.start();


        first.setEnabled(true);
        second.setEnabled(true);
        third.setEnabled(true);
        fourth.setEnabled(true);
        newQuestion();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.questionTextView);
        goButton = (Button) findViewById(R.id.go);


        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        solved = findViewById(R.id.solved);
        timer = findViewById(R.id.time);
        playAgain = findViewById(R.id.playAgain);
        constraintLayout=findViewById(R.id.contains);
        constraintLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);

    }


}
