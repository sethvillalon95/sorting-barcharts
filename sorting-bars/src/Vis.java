import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;


public class Vis extends JPanel {

    private String textToDisplay;
//    private Map<String, Double> data;
//    private Map<String, Double> relativeData;
    private ArrayList<Double> data;
    private ArrayList<Double> relativeData;
    private ArrayList<Bar> Bars;
    
    double max_num;
    boolean isBar = true;
	private boolean debugger = false;
	int time = 100;


    public Vis() {
        super();
        textToDisplay = "There's nothing to see here.";
        relativeData = new ArrayList<>();
        data = new ArrayList<>();
        Bars = new ArrayList<>();
        
    }

    public void setText(String t) {
        textToDisplay = t;
        repaint();
    }

	public void clearMap() {
//		System.out.println("clearMap ran");
//		System.out.println("data   is  "+ relativeData.isEmpty());
		if(!relativeData.isEmpty()) {
			relativeData.clear();
		}
		
		if(!data.isEmpty()) {
			data.clear();
		}
		


	}
	
	public void setData(ArrayList<Double> arr) {
		// this is where you set all the data and instantiate the bar objects
		data = arr;
		double max_num = 0;
		// this is to look for the max num
		for(var v : data) {
			if(v>max_num) {
				max_num=v;
			}
		}
		// this is to get the relative data
		for(var v: arr) {
			double temp = (v/max_num);
			relativeData.add(temp);
			Bars.add(new Bar(v));
			
//			Main.say(v/max_num);
		}
		
		repaint();
		
		
	}
    @Override
    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        int h= getHeight();
        int w = getWidth();
        int x=0, y =0;
        int howManyBars;
    	double barWidth;
    	double ratio;
    	double barHeight;
        int yLabel;
        int xLine;
        int yLine=0;
        int largestHeight =0;
        

        //draw blank background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        //render visualization
        g.setColor(Color.BLACK);
        // how many bars/data we need to draw
        howManyBars = relativeData.size();
//        System.out.println("The number of bars is "+ howManyBars);
        
        // calculates the spacing
        x = (int)(w*.09);

        
        int i = 0;
//        Main.say(Bars.isEmpty());
        for(var bar :Bars) {
        	barWidth= (w/howManyBars)/2;
        	ratio = relativeData.get(i);
        	barHeight = (int)(h*ratio*.90);
        	String barLabel = bar.getLabel();
            g.setColor(Color.black);
            yLabel = (int)(h*.98);
            g.drawString(barLabel, x+5, yLabel);
            xLine =(int)(w*.05);
            yLine =(int)(h*.96);
            
            // vertical line;
            g.drawLine(xLine, 0, xLine, yLine);
            //horizontal line; 
            g.drawLine(xLine,yLine,w,yLine);
            
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< need to change color later >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>            
            g.setColor(Color.BLUE);
            y =(int) ((h*.95)-barHeight);
            if(ratio ==1) {
            	largestHeight = y;
            }
            
            if(debugger ) {
            	debug1();
            	debugger = false;
            }
            bar.draw(g,x, y, barHeight,barWidth);
//            Main.say("drawing "+i);
            x+=barWidth+5;
//            x+=barWidthhowManyBars;
        	i++;
        }

    }
    
    public void bubbleSort() {
    	int arrayLength = Bars.size();
    	for(int i = 0; i<= arrayLength-2; i++){
            for(int j = arrayLength-1; j>=i+1; j--){
                int a = Bars.get(j).getValue();
                int b = Bars.get(j-1).getValue();
                if(a<b){
                	Bar tempBar = Bars.get(j);
                	// delay using thread
                	try {
	                	double relRatio = relativeData.get(j);
	                	relativeData.set(j, relativeData.get(j-1));
	                	relativeData.set(j-1, relRatio);
	                	Bars.get(j-1).highlight();
	                	Bars.get(j).highlight();
	                	Bars.set(j, Bars.get(j-1));
	                	Bars.set(j-1, tempBar);
//	                    Collections.swap(Bars, j-1, j);
	                    // THREAD SLEEP
	                	debugger = true;
		                repaint();
		                update(this.getComponentGraphics(getGraphics()));
	                	Bars.get(j-1).unhighlight();
	                	Bars.get(j).unhighlight();
	                    Main.say("switches after repaint");
	                    debug(); 
	                    
						Thread.sleep(time);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


                    // delay here
                }
            }
        }
//    	Main.say("This is after bubble sort");
//    	for(var bar: Bars) {
//    		Main.say(bar.getValue());
//    	}
    }
    
    public void insertionSort() {
    	int arrayLength = Bars.size();
    	Main.say("The size of the Bars is "+ arrayLength);
    	for(int j = 1; j<arrayLength; j++) {
//        	Main.say("The size of the Bars is "+ arrayLength+" at j "+j+ " the data at index 11 is "+ relativeData.get(11)+"before while loop");

    		Bar tempBar = Bars.get(j);
    		double tempVal = tempBar.getValue();
    		double tempRelData = relativeData.get(j);
    		
    		int index = j-1;

    		while(index>=0 &&((Bars.get(index).getValue()) > tempVal)&&((relativeData.get(index)) > tempRelData)) {
				try {

					relativeData.set(index+1,relativeData.get(index));
					Bars.set(index+1, Bars.get(index));
//					Bars.get(index).highlight();
//					Bars.get(index+1).highlight();
					index--;
					repaint();
//					update(this.getComponentGraphics(getGraphics()));
					Bars.get(index).unhighlight();
					Bars.get(index+1).unhighlight();
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}
//        	Main.say("The size of the Bars is "+ arrayLength+" at j "+j+ " the data at index 11 is "+ relativeData.get(11));

    		Bars.set(index+1, tempBar);
    		relativeData.set(index+1, tempRelData);
    		
    	}
    }
    
    
        
    private void debug() {
    	for(var v:Bars) {
    		Main.say("Printing the value"+v.getValue());
    	}
    	Main.say("");
    }
    
    private void debug1() {
    	Main.say(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


}
