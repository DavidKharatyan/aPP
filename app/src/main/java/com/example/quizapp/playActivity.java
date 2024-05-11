package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {"Какая горная вершина является самой высокой на территории Армении?",
            "Какая главная религия преобладает в Армении?"
            ,"Как называется столица Армении?",
            "Какое озеро является крупнейшим в Армении?",
            "Какой год является годом независимости Армении от СССР?"
            ,"Какой вид спорта является национальным в Армении?"
            ,"Какой государственный праздник отмечается в Армении 21 сентября?"
            ,"Какая является национальной валютой Армении?"
            ,"Какой армянский поэт считается национальным героем?",
            "Какой символ обычно ассоциируется с Арменией?"
    };
    String[] choose_list = {"Арарат","Арарат (Восточный)"," Гегам","Арагац",
            "Ислам","Христианство","Буддизм","Иудаизм",
            "Тбилиси","Ереван","Баку","Астрахань",
            "Ваннецкое озеро","Парз","Севанское озеро","Карс",
            "1991","1992","1990","1993",
            "Футбол","Теннис","Шахматы","борьба «Кох»",
            "День Армии","День Конституции","День Независимости","День Независимости Города Ереван",
            "Доллар США","Евро","Рубль","Драм",
            "Ованес Туманян ","Гарегин Нжде","Авдет Исмаил","Мосес Хоренаци",
            "Лев","Солнце","Крест","Огонь",
            "1","2","4","5","6","7","8","9","10"
    };
    String[] correct_list = {"Арагац","Христианство","Ереван","Севанское озеро","1991","борьба «Кох»","День Независимости","Драм","Ованес Туманян ","Крест","10"};


    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next,check1 ;

    private boolean checked = false;
    private boolean questionAnswered = false;
    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);
        check1 = findViewById(R.id.check1);


        findViewById(R.id.image_back).setOnClickListener(
                a -> finish()
        );
        remplirData();
        btn_next.setOnClickListener(view -> {
            new Handler().postDelayed(() -> {
                if (valueChoose.isEmpty()) {
                    Toast.makeText(playActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (currentQuestion != question_list.length - 1) {
                    currentQuestion = currentQuestion + 1;
                    remplirData();
                    valueChoose = "";
                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
                } else {
                    Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                    intent.putExtra("Result", scorePlayer);
                    startActivity(intent);
                    finish();
                }

            }, 200);
        });

        check1.setOnClickListener(
                view -> {
                    if (isclickBtn) {
                        isclickBtn = false;

                        if (!valueChoose.equals(correct_list[currentQuestion])) {

                            Toast.makeText(playActivity.this, "Incorrect. Correct answer is: " + correct_list[currentQuestion], Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);
                        } else {

                            Toast.makeText(playActivity.this, "Correct", Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);
                            scorePlayer++;
                        }


                    } else {
                        Toast.makeText(playActivity.this, "Enter the answer", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }


    void remplirData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }
    void chooseBtn() {
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}