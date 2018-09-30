# acode_diagram_example
自定义折线图(动画+滑动监听)
## 效果图 ##
![效果图](http://oif1jvh5f.bkt.clouddn.com/acode_linechare.gif)

## 使用方法 ##
### xml ###

	 	<com.acode.diagram.DiagramLineView
	        android:background="#ffffff"
	        android:id="@+id/diagramLineView"
	        android:layout_width="match_parent"
	        android:layout_height="211dp" />

### activity/fragment ###
	
	 		//设置X轴数量
			diagramLineView.setXLength(10);
			//设置Y轴数量
	        diagramLineView.setYLength(5);
			//设置是否动画
	        diagramLineView.setIsAnim(true);
			//设置点的边框色
	        diagramLineView.setStokeColor(R.color.cff4400);
			//设置点的填充色
	        diagramLineView.setFillColor(R.color.cd54d);
			//设置数据
	        diagramLineView.setData(getData());
	 		//更新数据
			diagramLineView.notifyData();
			
### 需要监听滑动可以使用以下view ###

		<com.acode.diagram.DiagramView
            android:id="@+id/diagramView"
            android:layout_width="match_parent"
            android:layout_height="211dp"
            android:layout_centerInParent="true"
            android:layout_marginLeft="14dp"
            android:layout_marginRight="14dp"
            android:background="#ffffff" />

			//使用方法同上，只是多了一个滑动监听
			//滑动监听
		 	diagramView.setOnMoveListener(new OnMoveListener(){
		            @Override
		            public void onMoved(int index) {
	
		            }
		        });