import java.awt.Color;
import java.awt.Graphics2D;

public class Bar {
	private double val; 
	private String label;
	private enum State {
		NORMAL,
		HIGHLIGHTED,
	}
	State state = State.NORMAL;
	public Bar(double v) {
		// since we awe will be using numbers the label is the value
		label =Double.toString(v);
		val = v;
	}
	
	
	public void draw(Graphics2D g, int x, int y, double barHeight, double barWidth) {
		Color c = null;
		if(state==State.HIGHLIGHTED) {
			c = Color.red;
		}else if (state==State.NORMAL) {
			c = Color.blue;
			
		}
		g.setColor(c);
		g.fillRect(x,y, (int)barWidth,(int)barHeight);
		
	}
	
	public String getLabel() {
		return label;
	}
	public int getValue() {
		return (int)val;
	}
}
