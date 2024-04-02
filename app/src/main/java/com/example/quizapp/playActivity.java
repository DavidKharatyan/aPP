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
    String[] question_list = {"Когда произошло рождение христианства в Армении?",
            "Кто был основателем древнейшей армянской династии?"
            ,"Как называется столица Армении?",
           "Какая горная система охватывает большую часть территории Армении?",
            "Какое событие привело к великой катастрофе армянского народа в начале XX века, известное как \"Армянский геноцид\"?",
            "Кто был основателем армянского алфавита в V веке н.э.?",
            "В каком году было провозглашено независимое государство Армения после развала Российской империи?",
            "Какое событие привело к началу Нагорно-Карабахского конфликта в конце XX века?",
            "Какое событие привело к завоеванию Завоевания Селевкидов армянским вождем Тиграном II в I веке до н.э.?",
            "Когда была принята христианская религия как государственная религия в Армении?"
    };
    String[] choose_list = {"IV век н.э."," V век до н.э.","II век н.э."," VI век до н.э.",
            " Тигран II Великий","Ардавазд I","Гаятон I","Харадзман I",
            "Тбилиси","Ереван","Баку","Астрахань",
            " Анкар"," Вишап","Эчмиадзин"," Хачкар",
            "Вторжение Сассанидов","Война за независимость","Массовые аресты армянских лидеров"," Политика истребления Османской империи",
            "Гайус Юлий Ахиллес","Григорий Просвещенный","Месроп Маштоц","Константин Философ",
            "1915"," 1918","1920","1923",
           "Распад Советского Союза","Признание независимости Азербайджана","Армянское национальное восстание","Конфликт между армянами и азербайджанцами на границе",
            "Вторжение римлян","Восстание рабов","Война с Парфянами","Падение македонской династии",
            "301 год","451 год","632 год","825 год",
            "1","2","4","5","6","7","8","9","10"
    };
    String[] correct_list = {"V век н.э.","Гаятон I","Ереван","Хачкар","Политика истребления Османской империи"," Месроп Маштоц"," 1918","Распад Советского Союза","Война с Парфянами","301 год","10"};


    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;


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

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()
        );
        remplirData();
        btn_next.setOnClickListener(
                view -> {
                        if (isclickBtn){
                            isclickBtn = false;

                            if(!valueChoose.equals(correct_list[currentQuestion])){
                                Toast.makeText(playActivity.this , "неправильно",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                            }else {
                                Toast.makeText(playActivity.this , "правильно",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                                scorePlayer++;
                            }
                            new Handler().postDelayed(() -> {
                                if(currentQuestion!=question_list.length-1){
                                    currentQuestion = currentQuestion + 1;
                                    remplirData();
                                    valueChoose = "";
                                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                                }else {
                                    Intent intent  = new Intent(playActivity.this , ResulteActivity.class);
                                    intent.putExtra("Result" , scorePlayer);
                                    startActivity(intent);
                                    finish();
                                }

                            },2000);

                        }else {
                            Toast.makeText(playActivity.this ,  "выберите ответ",Toast.LENGTH_LONG).show();
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
    void chooseBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}