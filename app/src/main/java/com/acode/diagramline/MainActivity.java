package com.acode.diagramline;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acode.diagram.DiagramHorizerScrollView;
import com.acode.diagram.DiagramLineView;
import com.acode.diagram.DiagramView;
import com.acode.diagram.bean.DiagramBean;
import com.acode.diagram.listener.OnItemClickListener;
import com.acode.diagram.listener.OnMoveListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DiagramView diagramView;
    private DiagramLineView diagramLineView;
    private DiagramHorizerScrollView diagramHSView;
    private Button btn2;
    private Button btn1;
    private Button btn;
    private boolean isFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        //折线图
        diagramLineView = (DiagramLineView) findViewById(R.id.diagramLineView);
        diagramLineView.setXLength(7);
        diagramLineView.setYLength(5);
        diagramLineView.setYInterval(100);
        diagramLineView.setIsAnim(true);
        diagramLineView.setStokeColor(R.color.cff4400);
        diagramLineView.setFillColor(R.color.cd54d);
        diagramLineView.setData(getData2());

        //滑动折线图
        diagramView = (DiagramView) findViewById(R.id.diagramView);
        diagramView.setXLength(7);
        diagramView.setYLength(5);
        diagramView.setYInterval(100);
        diagramView.setIsAnim(false);
        diagramView.setStokeColor(R.color.cd54d);
        diagramView.setData(getData());
        diagramView.setOnMoveListener(new OnMoveListener() {
            @Override
            public void onMoved(int index) {
                btn1.setText("滑动折线图:" + getData().get(index).getStrdx() + "/" + getData().get(index).getDy());
            }
        });



        //滚动折线图
        diagramHSView = (DiagramHorizerScrollView) findViewById(R.id.diagramHSView);
        diagramHSView.setXLength(30);
        diagramHSView.setYLength(5);
        diagramHSView.setIsAnim(false);
        diagramHSView.setYInterval(100);
        diagramHSView.setStokeColor(R.color.cff4400);
        diagramHSView.setFillColor(R.color.cd54d);
        diagramHSView.setData(getData3());
        diagramHSView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int index) {
                btn2.setText("滚动折线图:" + getData3().get(index).getStrdx() + "/" + getData3().get(index).getDy());
            }
        });

        //折线图
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlag) {
                    diagramLineView.setData(getData2());
                    diagramLineView.notifyData();
                } else {
                    diagramLineView.setData(getData());
                    diagramLineView.notifyData();
                }
                isFlag = !isFlag;
            }
        });
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
        diagramBeans.add(diagramBean);
        diagramBeans.add(diagramBean1);
        diagramBeans.add(diagramBean2);
        diagramBeans.add(diagramBean3);
        diagramBeans.add(diagramBean4);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        return diagramBeans;
    }

    //假数据
    private ArrayList<DiagramBean> getData2() {
        ArrayList<DiagramBean> diagramBeans = new ArrayList<>();
        DiagramBean diagramBean = new DiagramBean("8/21", 84);
        DiagramBean diagramBean1 = new DiagramBean("8/22", 65);
        DiagramBean diagramBean2 = new DiagramBean("8/23", 123);
        DiagramBean diagramBean3 = new DiagramBean("8/24", 324);
        DiagramBean diagramBean4 = new DiagramBean("8/25", 425);
        DiagramBean diagramBean5 = new DiagramBean("8/26", 245);
        DiagramBean diagramBean6 = new DiagramBean("8/27", 124);
        diagramBeans.add(diagramBean);
        diagramBeans.add(diagramBean1);
        diagramBeans.add(diagramBean2);
        diagramBeans.add(diagramBean3);
        diagramBeans.add(diagramBean4);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        return diagramBeans;
    }

    //假数据
    private ArrayList<DiagramBean> getData3() {
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
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        diagramBeans.add(diagramBean7);
        diagramBeans.add(diagramBean8);
        diagramBeans.add(diagramBean9);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        diagramBeans.add(diagramBean7);
        diagramBeans.add(diagramBean8);
        diagramBeans.add(diagramBean9);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        diagramBeans.add(diagramBean7);
        diagramBeans.add(diagramBean8);
        diagramBeans.add(diagramBean9);
        diagramBeans.add(diagramBean5);
        diagramBeans.add(diagramBean6);
        diagramBeans.add(diagramBean7);
        diagramBeans.add(diagramBean8);
        diagramBeans.add(diagramBean9);
        return diagramBeans;
    }
}
