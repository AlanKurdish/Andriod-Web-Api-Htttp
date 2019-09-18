package com.example.networkapijson;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.networkapijson.data.AnswerListAsyncResponse;
import com.example.networkapijson.data.QuestionBind;
import com.example.networkapijson.model.Questions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btntrue;
    private Button btnfalse;
    private ImageButton next;
    private ImageButton prev;
    private TextView txtcount;
    private TextView txtquestion;
    private int Current_Question_Index=0;
    private List<Questions> questions;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnfalse=findViewById(R.id.btnfalse);
        btntrue=findViewById(R.id.btntrue);
        next=findViewById(R.id.btnnext);
        prev=findViewById(R.id.btnprev);
        txtcount=findViewById(R.id.countertxt);
        txtquestion=findViewById(R.id.Qurtiontext);

        btntrue.setOnClickListener(this);
        btnfalse.setOnClickListener(this);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);

        questions = new QuestionBind().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void proccessFinished(ArrayList<Questions> QuestionArrayList) {
                txtquestion.setText(Html.fromHtml(QuestionArrayList.get(Current_Question_Index).getAnswer()));
                txtcount.setText(Current_Question_Index+" / "+questions.size());
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnnext :
                Current_Question_Index=(Current_Question_Index+1) % questions.size();
                txtquestion.setText(Html.fromHtml(questions.get(Current_Question_Index).getAnswer()));
                txtcount.setText(Current_Question_Index+" / "+questions.size());
                break;
            case R.id.btnprev:
                Current_Question_Index= Current_Question_Index > 0 ? ((Current_Question_Index-1)%questions.size()) : 0;
                txtcount.setText(Current_Question_Index+" / "+questions.size());
                txtquestion.setText(Html.fromHtml(questions.get(Current_Question_Index).getAnswer()));
                break;
            case R.id.btnfalse:
                checkanswer(true);
                break;
            case R.id.btntrue:
                checkanswer(false);
                break;
        }
    }

    private void checkanswer(boolean userchose) {

        boolean answer=questions.get(Current_Question_Index).isIstrue();
        shakeanmie();
        if (userchose==answer)
        {
            Toast.makeText(MainActivity.this,"Answer is Correct",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(MainActivity.this,"Answer is Wrong",Toast.LENGTH_SHORT).show();
        }
        Current_Question_Index=(Current_Question_Index+1) % questions.size();
        txtquestion.setText(Html.fromHtml(questions.get(Current_Question_Index).getAnswer()));
        txtcount.setText(Current_Question_Index+" / "+questions.size());
    }


    private void shakeanmie()
    {
        Animation shake= AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        CardView card=findViewById(R.id.cardView);
        card.setAnimation(shake);
    }
}
