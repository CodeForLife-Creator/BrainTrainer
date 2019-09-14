package com.glowedupgiftinc.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnGo, button0, button1, button2, button3, btnPlayAgain;
    ConstraintLayout layout2;
    TextView tvScore, tvProblem, tvTime, tvResult;
    int a;
    int b;
    int numOfQ = 0;
    int score = 0;
    Random r = new Random();
    int locationOfCorrect = r.nextInt(4);
    ArrayList<Integer> anwsers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.btnGo);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        layout2 = findViewById(R.id.layout2);
        tvProblem = findViewById(R.id.tvProblem);
        tvResult = findViewById(R.id.tvResult);
        tvScore = findViewById(R.id.tvScore);
        tvTime = findViewById(R.id.tvTime);

        btnGo.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.INVISIBLE);
    }

    public  void startGame(View view)
    {
        btnGo.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.VISIBLE);
        playAgain(tvTime);
    }
    public void chooseAnswer(View view)
    {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrect))){
         tvResult.setText("Correct!");
         score++;
        }
        else {
            tvResult.setText("Wrong :(");
        }
        numOfQ++;
        tvScore.setText(Integer.toString(score) + "/" + Integer.toString(numOfQ));
        newQuestion();
    }

    public void newQuestion(){
        a = r.nextInt(21);
        b = r.nextInt(21);
        tvProblem.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrect = r.nextInt(4);

        anwsers.clear();
        for (int i =0; i < 4; i++)
        {
            if (i == locationOfCorrect)
            {
                anwsers.add(a + b);
            } else
            {
                int wrongAns = r.nextInt(41);
                while (wrongAns == a+b)
                {
                    wrongAns = r.nextInt(41);
                }
                anwsers.add(wrongAns);
            }
        }

        button0.setText(Integer.toString(anwsers.get(0)));
        button1.setText(Integer.toString(anwsers.get(1)));
        button2.setText(Integer.toString(anwsers.get(2)));
        button3.setText(Integer.toString(anwsers.get(3)));

    }
    public  void playAgain(View view){
        btnPlayAgain.setVisibility(View.INVISIBLE);
        score = 0;
        numOfQ = 0;
        tvScore.setText(Integer.toString(score) + "/" + Integer.toString(numOfQ));
        tvResult.setText("");
        tvTime.setText("30s");
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        newQuestion();
        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                tvTime.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                tvResult.setText("Done!");
                btnPlayAgain.setVisibility(View.VISIBLE);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
    }



}
