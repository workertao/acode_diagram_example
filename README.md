# acode_diagram_example
自定义折线图(动画+滑动监听)
## 效果图 ##
![效果图](http://ohdryj9ow.bkt.clouddn.com/diagramline.gif)

## 使用方法 ##
### xml ###

	 <com.acode.diagram.DiagramLineView
	        android:background="#ffffff"
	        android:id="@+id/diagramLineView"
	        android:layout_width="match_parent"
	        android:layout_height="211dp" />

### activity/fragment ###

 		diagramLineView.setXLength(10);
        diagramLineView.setYLength(5);
        diagramLineView.setIsAnim(true);
        diagramLineView.setStokeColor(R.color.cff4400);
        diagramLineView.setFillColor(R.color.cd54d);
        diagramLineView.setData(getData());
 		diagramLineView.notifyData();
	 	diagramView.setOnMoveListener(new OnMoveListener(){
	            @Override
	            public void onMoved(int index) {

	            }
	        });
