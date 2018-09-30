# acode_diagram_example
自定义折线图
## 效果图 ##
![效果图](http://oif1jvh5f.bkt.clouddn.com/acode_linechare.gif)

## 使用方法 ##
        
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