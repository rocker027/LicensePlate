package com.coors.licenseplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private HashMap<Integer, LicensePlateVO> lists;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
//        doCreateNew();

    }

    private void doCreateNew() {
        lists = RandomNumberHandler.getInstance(this).getRandomNumbers(200);
        StringBuilder sb = new StringBuilder();
        for (Integer value : lists.keySet()) {
            sb.append("車號 " + RandomNumberHandler.getInstance(this).addZeroNumber(lists.get(value).getLicensePlate()) + " :" + lists.get(value).getLuckyResult() + " , \n");
            textView.setText(sb.toString());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                doCreateNew();
                break;
        }
    }
}
