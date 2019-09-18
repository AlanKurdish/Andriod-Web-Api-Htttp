package com.example.networkapijson.data;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.networkapijson.MainActivity;
import com.example.networkapijson.controller.AppController;
import com.example.networkapijson.model.Questions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.networkapijson.controller.AppController.TAG;

public class QuestionBind {

    private String url="https://opentdb.com/api.php?amount=50&difficulty=easy&type=boolean";

    ArrayList<Questions> questions=new ArrayList<>();

    public List<Questions> getQuestions(final AnswerListAsyncResponse callback)
    {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("results");
//                    Log.d("Json", "onResponse: "+jsonArray);

                   for (int i=0;i<jsonArray.length();i++)
                   {
                        Questions qst=new Questions();
                        qst.setAnswer( jsonArray.getJSONObject(i).getString("question"));
                        qst.setIstrue(jsonArray.getJSONObject(i).getBoolean("correct_answer"));
                        questions.add(qst);
                     //  Log.d("Json", "Question : "+ jsonArray.getJSONObject(i).getString("question")+" Asnwer : "+ jsonArray.getJSONObject(i).getBoolean("correct_answer"));
                   }
                    if (callback != null) callback.proccessFinished(questions);
//                    Log.d("Json", "Question : "+questions.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        return questions;
    }

}
