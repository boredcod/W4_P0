package com.example.w4_p0;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ToggleButton simpleToggleButton;
    boolean isOn = false;
    Camera camera;
    private GestureDetector detector;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleToggleButton = (ToggleButton) findViewById(R.id.simpleToggleButton);
        camera = Camera.open();
        detector = new GestureDetector(this,this);
        ed = findViewById(R.id.edittext);

        simpleToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("flash on");
                Camera.Parameters param = camera.getParameters();
                if (b){
                    System.out.println("flash on");
                    param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(param);
                    camera.startPreview();
               } else {
                    System.out.println("flash off");
                    param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                   camera.setParameters(param);
                   camera.stopPreview();
               }
            }
        });
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().toLowerCase().equals("on")){
                    simpleToggleButton.setChecked(true);
                }
                else if (charSequence.toString().toLowerCase().equals("off")){
                    simpleToggleButton.setChecked(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        this.detector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        float v = Math.abs(velocityX)+Math.abs(velocityY);
        System.out.println(v);
        if (v > 5000){
            if (e2.getY() < e1.getY()) {
                System.out.println("up");
                simpleToggleButton.setChecked(true);
            }
            else{
                System.out.println("down");
                simpleToggleButton.setChecked(false);
            }
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.release();
    }
}