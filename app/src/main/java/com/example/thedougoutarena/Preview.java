package com.example.thedougoutarena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Preview extends AppCompatActivity {
    String name,date,time,phone,ground;
    TextView n,p,d,t,g;
    Button book;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Intent i = getIntent();
        n=(TextView)findViewById(R.id.pname);
        g=(TextView)findViewById(R.id.pground);
        t=(TextView)findViewById(R.id.ptime);
        d=(TextView)findViewById(R.id.pdate);
        p=(TextView)findViewById(R.id.pphone);
        book=(Button)findViewById(R.id.book);
        DB=new DBHelper(this);

        name=i.getStringExtra("name");
        phone=i.getStringExtra("phone");
        date=i.getStringExtra("date");
        time=i.getStringExtra("time");
        ground=i.getStringExtra("ground");
        Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
        n.setText(new StringBuilder().append("Name : ").append(name));
        p.setText(new StringBuilder().append("Phone No. : ").append(phone));
        d.setText(new StringBuilder().append("Date : ").append(date));
        t.setText(new StringBuilder().append("Time : ").append(time));
        g.setText(new StringBuilder().append("Ground : ").append(ground));

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean success= DB.bookGround(name,phone,date,time,"1");
                if(success=true){
                    Toast.makeText(getApplicationContext(), "Booked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}