import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
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
    
    double max_num;
    boolean isBar = true;


    public Vis() {
        super();
        textToDisplay = "There's nothing to see here.";
        relativeData = new ArrayList<>();
        data = new ArrayList<>();
        
    }

    public void setText(String t) {
        textToDisplay = t;
        repaint();
    }

//	public void setData(Map<String, Double> inData) {
//		// TODO Auto-generated method stub
//        data = inData;
//        var allValues = data.values();
//        double max=0;
//        max_num=0;
//        for (var currObj : allValues) {
//            if (currObj > max) {
//                max = currObj;
//                max_num=currObj;
//            }
//        }
//        for (var key : data.keySet()) {
//            relativeData.put(key, data.get(key) / max);
//        }
//        repaint();	
//	}
	
	public void clearMap() {
//		System.out.println("clearMap ran");
//		System.out.println("data.clear() is  "+ relativeData.isEmpty());
		if(!relativeData.isEmpty() && !data.isEmpty()) {
			relativeData.clear();
		}
		


	}
	
	public void setData(ArrayList<Double> arr) {
		// this is where you set all the data and instantiate the bar objects
		
		
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
//        g.drawString(textToDisplay, 10, 20);
        
//        BarChart bc = new BarChart();
//        bc.draw(g1, w, h);
        

        
        // how many bars/data we need to draw
        howManyBars = relativeData.keySet().size();
        int poly_X[] = new int[howManyBars];
        int poly_Y[] = new int[howManyBars];

        
//        System.out.println("The number of bars is "+ howManyBars);
        
        // calculates the spacing
        x = (int)(w*.1);

        
        int i = 0;
        // renders the bar 
        for (var jerico : relativeData.keySet()) {
//           double barHeight = getWidth() * relativeData.get(jerico);
       	
        	barWidth= (w/howManyBars)/2;
        	ratio = relativeData.get(jerico);
        	barHeight = (int)(h*ratio*.90);
        	System.out.println("relativeData.get is "+relativeData.get(jerico));
        	System.out.println("The barHeight is "+ barHeight);
            String s = jerico;
            g.setColor(Color.black);
            yLabel = (int)(h*.98);
            g.drawString(s, x+15, yLabel);
            
            // draw the vertical line on the left
            xLine =(int)(w*.05);
            yLine =(int)(h*.96);
            
            // vertical line;
            g.drawLine(xLine, 0, xLine, yLine);
            //horizontal line; 
            g.drawLine(xLine,yLine,w,yLine);

            g.setColor(Color.BLUE);
            
            // Draw the bars
            y =(int) ((h*.95)-barHeight);
            System.out.println("the y pos is "+ y);
            if(ratio ==1) {
            	largestHeight = y;
            }

            // this is for the yAxis
            
            if(isBar) {
            	// this is where you should draw the bars
                g.fillRect(x, y, (int)barWidth,(int)barHeight);

            }else {
            	g.fillOval(x-10, y-10, 20, 20);
            }
            // start collecting the poly_X and poly_Y values;
            poly_X[i] = x;
            poly_Y[i] = y;
           
            
           x+=barWidth+10;
           i++;

        
        
        if(!isBar) {
        	g.drawPolyline(poly_X, poly_Y, howManyBars);
        }
        
        System.out.println("*******************End *******************");

       
   
      }
    }
        


}
