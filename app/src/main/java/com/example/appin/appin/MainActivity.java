package com.example.appin.appin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText email,password;
    private Button sign_in_register;
    private RequestQueue requestQueue;
    private static final String URL = "http://itinfocube.info/dev1/urgoe/login";
    private JsonObjectRequest request;
    String respons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sign_in_register = (Button) findViewById(R.id.login);
        requestQueue = Volley.newRequestQueue(this);
        sign_in_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("username",email.getText().toString());
                hashMap.put("password",password.getText().toString());
                final JSONObject param = new JSONObject(hashMap);
                Log.d("Nivi",param.toString());
                        request = new JsonObjectRequest(URL, param,new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response){
                                respons = response.toString();
                                try {
                                    parsestring();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        requestQueue.add(request);
                    }
                });
            }

    public void parsestring() throws JSONException
    {
        Log.d("Nivi","Called");

        JSONObject jsonObj = new JSONObject(respons);
        JSONArray ob = jsonObj.getJSONArray("data");
        JSONObject data = ob.getJSONObject(0);
        String fname = data.getString("firstname");
        String lname = data.getString("lastname");
        String email = data.getString("email");

        Toast.makeText(getApplicationContext(),respons,Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(),Welcome.class);
        i.putExtra("fname",fname);
        i.putExtra("lname",lname);
        i.putExtra("email",email);

        startActivity(i);


        Log.d("Nivi",fname);
        Log.d("Nivi",lname);
        Log.d("Nivi",email);
    }
}

