package com.example.appin.appin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView fname = (TextView) findViewById(R.id.fname);
        TextView lname = (TextView) findViewById(R.id.lname);
        TextView email = (TextView) findViewById(R.id.email);

        Intent i = getIntent();
        String fnameS = i.getStringExtra("fname");
        String lnameS = i.getStringExtra("lname");
        String Semail = i.getStringExtra("email");

       /* Log.d("Nivi",fnameS);
        Log.d("Nivi",lnameS);
        Log.d("Nivi",Semail);
*/
        fname.setText(fnameS);
        lname.setText(lnameS);
        email.setText(Semail);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
