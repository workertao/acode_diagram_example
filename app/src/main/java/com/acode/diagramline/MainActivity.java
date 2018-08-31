package com.acode.diagramline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acode.diagram.DiagramLineView;
import com.acode.diagram.DiagramView;
import com.acode.diagram.bean.DiagramBean;
import com.acode.diagram.listener.OnMoveListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnMoveListener {
    private DiagramView diagramView;
    private DiagramLineView diagramLineView;
    private TextView tv;
    private Button btn;
    private boolean isFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);

        //控制折线图
        diagramView = (DiagramView) findViewById(R.id.diagramView);
        diagramView.setOnMoveListener(this);
        diagramView.setXLength(10);
        diagramView.setYLength(5);
        diagramView.setIsAnim(false);
        diagramView.setStokeColor(R.color.cd54d);
        diagramView.setData(getData());


        //折线图
        diagramLineView = (DiagramLineView) findViewById(R.id.diagramLineView);
        diagramLineView.setXLength(10);
        diagramLineView.setYLength(5);
        diagramLineView.setIsAnim(true);
        diagramLineView.setStokeColor(R.color.cff4400);
        diagramLineView.setFillColor(R.color.cd54d);
        diagramLineView.setData(getData());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlag) {
                    diagramLineView.setData(getData1());
                } else {
                    diagramLineView.setData(getData());
                }
                isFlag = !isFlag;
                diagramLineView.notifyData();
            }
        });
    }

    @Override
    public void onMoved(int index) {
        tv.setText(getData().get(index).getStrdx() + "投递" + getData().get(index).getDy());
    }

    //假数据
    private ArrayList<DiagramBean> getData() {
        ArrayList<DiagramBean> diagramBeans = new ArrayList<>();
        DiagramBean diagramBean = new DiagramBean("8/21", 268);
        DiagramBean diagramBean1 = new DiagramBean("8/22", 25);
        DiagramBean diagramBean2 = new DiagramBean("8/23", 368);
        DiagramBean diagramBean3 = new DiagramBean("8/24", 350);
        DiagramBean diagramBean4 = new DiagramBean("8/25", 125);
        DiagramBean diagramBean5 = new DiagramBean("8/26", 54);
        DiagramBean diagramBean6 = new DiagramBean("8/27", 268);
        DiagramBean diagramBean7 = new DiagramBean("8/26", 333);
        DiagramBean diagramBean8 = new DiagramBean("8/27", 120);
        DiagramBean diagramBean9 = new DiagramBean("8/26", 254);
        diagramBeans.add(diagramBean);
        diagramBeans.add(diagramBean1);
        diagramBeans.add(diagramBean2);
        diagramBeans.add(diagramBean3);
        diagramBeans.add(diagramBean4);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        diagramBeans.add(diagramBean7);
        diagramBeans.add(diagramBean8);
        diagramBeans.add(diagramBean9);
        return diagramBeans;
    }

    //假数据
    private ArrayList<DiagramBean> getData1() {
        ArrayList<DiagramBean> diagramBeans = new ArrayList<>();
        DiagramBean diagramBean = new DiagramBean("8/21", 84);
        DiagramBean diagramBean1 = new DiagramBean("8/22", 65);
        DiagramBean diagramBean2 = new DiagramBean("8/23", 123);
        DiagramBean diagramBean3 = new DiagramBean("8/24", 324);
        DiagramBean diagramBean4 = new DiagramBean("8/25", 425);
        DiagramBean diagramBean5 = new DiagramBean("8/26", 245);
        DiagramBean diagramBean6 = new DiagramBean("8/27", 124);
        DiagramBean diagramBean7 = new DiagramBean("8/26", 321);
        DiagramBean diagramBean8 = new DiagramBean("8/27", 256);
        DiagramBean diagramBean9 = new DiagramBean("8/26", 125);
        diagramBeans.add(diagramBean);
        diagramBeans.add(diagramBean1);
        diagramBeans.add(diagramBean2);
        diagramBeans.add(diagramBean3);
        diagramBeans.add(diagramBean4);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        diagramBeans.add(diagramBean7);
        diagramBeans.add(diagramBean8);
        diagramBeans.add(diagramBean9);
        return diagramBeans;
    }
}
