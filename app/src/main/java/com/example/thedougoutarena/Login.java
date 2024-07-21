package com.example.thedougoutarena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {
    EditText username,password;
    MaterialButton loginbtn;
    TextView signuplink;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.username1);
        password=(EditText) findViewById(R.id.password1);
        loginbtn=(MaterialButton) findViewById((R.id.loginbtn));
        signuplink=(TextView) findViewById(R.id.signuplink);
        DB=new DBHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass = password.getText().toString();
                
                if(user.equals("")|pass.equals("")){
                    Toast.makeText(Login.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=DB.checkPassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Select.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
    }
}