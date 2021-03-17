import java.awt.Graphics2D;

public class Bar {
	private int val; 
	private String label;
	
	public Bar(String l) {
		// since we awe will be using numbers the label is the value
		label = l;
		val = Integer.parseInt(l);
	}
	
	
	public void draw(Graphics2D g, double barHeight, double barWidth) {
		
	}
}
