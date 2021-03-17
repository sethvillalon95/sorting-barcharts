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
//		System.out.println("data.clear() is  "+ relativeData.isEmpty());
		if(!relativeData.isEmpty() && !data.isEmpty()) {
			relativeData.clear();
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
            bar.draw(g,x, y, barHeight,barWidth);
            Main.say("drawing "+i);
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
                	double relRatio = relativeData.get(j);
                	relativeData.set(j, relativeData.get(j-1));
                	relativeData.set(j-1, relRatio);

                	Bars.set(j, Bars.get(j-1));
                	Bars.set(j-1, tempBar);
//                    Collections.swap(Bars, j-1, j);
                    
                    Main.say("switches");
                    repaint();
                }
            }
        }
    	Main.say("This is after bubble sort");
    	for(var bar: Bars) {
    		Main.say(bar.getValue());
    	}
    }
        


}
