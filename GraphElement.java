import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JPanel;

// General element of a graph (nodes and edges)

abstract public class GraphElement 
{
	 private double xPos;
	 private double yPos;
	 private double xPos2;
	 private double yPos2;
	 protected String label;
	 protected Color color;
	 boolean selected;
	    
	 public GraphElement()
	 {
	    xPos = 0;
	    yPos = 0;
	    label = "";
	    color = Color.BLACK;
	 }
	   
	 public GraphElement(double x, double y)
	 {
	    xPos = x;
	    yPos = y;
	    label = "";
	    color = Color.BLACK;
	 }
	   
	 public final double getXPos()
	 {
	    return xPos;
	 }
	   
	 public final double getYPos()
	 {
	    return yPos;
	 }
	 public final double getXPos2()
	 {
	    return xPos2;
	 }
	   
	 public final double getYPos2()
	 {
	    return yPos2;
	 }
	 public void setXPos(double x){
		 xPos = x;
	 }
	 public void setYPos(double y){
		 yPos = y;
	 }
	 public void setXPos2(double x){
		 xPos2 = x;
	 }
	 public void setYPos2(double y){
		 yPos2 = y;
	 }
	 
	 
	 public void setColor(Color color)
	 {
		 this.color = color;
	 }

	 public void moveTo (double xLoc, double yLoc)
	 {
	    xPos = xLoc;
	    yPos = yLoc;
	 }
	   
	 public String toString()
	 {
	    String str = "(X,Y) Position: (" + xPos + "," + yPos + ")\n";
	    return str;
	 }
	   
	 abstract void    draw(Graphics2D g2);	
     abstract boolean isClicked(double x, double y);
     abstract boolean applyLabel();
  
     public String getLabel()
     {
       return label;
     }
		  
     public void setLabel(String label)
     {
       this.label = label;
     }
}
