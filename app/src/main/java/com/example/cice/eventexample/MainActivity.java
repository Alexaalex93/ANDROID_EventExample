package com.example.cice.eventexample;

import android.database.sqlite.SQLiteBlobTooBigException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myBtn = (Button) findViewById(R.id.mybutton);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView myTextView = (TextView) findViewById(R.id.myTextView);
                myTextView.setText("OnClick realizado");
            }
        });

        myBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView myTextView = (TextView) findViewById(R.id.myTextView);
                myTextView.setText("OnLongClick realizado");
                return true; //Si lo pongo en false vuelve al estado inicial
            }
        });

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                handleTouch(motionEvent);
                return true;
            }
        });
    }

    void handleTouch(MotionEvent m){
        TextView textView = (TextView) findViewById(R.id.myTextView);
        TextView textView2 = (TextView) findViewById(R.id.myTextView2);

        int pointerCount = m.getPointerCount();
        for(int i = 0; i < pointerCount; i++){
            int x = (int) m.getX();
            int y = (int) m.getY();
            int id = m.getPointerId(i);
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();

            String actionString;
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    actionString = "Down";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "Up";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "Pointer Down";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "Pointer Up";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "Moving";
                    break;
                default:
                    actionString = "";
            }
            String touchStatus = "Action: " + actionString + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
            if(id == 0)
                textView.setText(touchStatus);
            else
                textView2.setText(touchStatus);
        }
    }
}
