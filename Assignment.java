import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Assignment extends JFrame implements ActionListener, MouseListener,Shape{

	private ArrayList <GraphElement> arrList = new ArrayList<GraphElement>();
	private String tempShape;
	private int x;
	private int y;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Assignment frame = new Assignment();
		frame.pack();
        frame.setVisible(true);
        frame.setSize(700,700);
        frame.setTitle("Graph Draw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	  public Assignment()
	  {
      
	      //make sure the program exits when the frame closes

	    
	      //This will center the JFrame in the middle of the screen
	      this.setLocationRelativeTo(null);
 
	      
      //The first JPanel contains Buttons to create shapes
	      JPanel panel1 = new JPanel();
	      JButton rectButton = new JButton( "Rectangle");
	      JButton circButton = new JButton( "Ellipse");
	      JButton edgeButton = new JButton( "Edge");

	      rectButton.addActionListener(this);
	      rectButton.setActionCommand("Rectangle");
	      circButton.addActionListener(this);
	      circButton.setActionCommand("Ellipse");
	      edgeButton.addActionListener(this);
	      edgeButton.setActionCommand("Edge");
	      
	      panel1.add(rectButton);
	      panel1.add(circButton);
	      panel1.add(edgeButton);

      //The second JPanel contains a "label" button and a textfield
	      JPanel panel2 = new JPanel();
	      JButton labelButton = new JButton("Label");
	      JTextField labeltext = new JTextField(10);
	      
	      labelButton.addActionListener(this);
	      labelButton.setActionCommand("Label");
	      
	      panel2.add(labelButton);
	      panel2.add(labeltext);
	      
      
	      //drawing.setBackground(Color.white);
      //The Last JPanel adds all panel to organize
	      JPanel panel4 = new JPanel(new GridLayout(2,0));
	      panel4.add(panel1);
	      panel4.add(panel2);
	     	      JPanel drawing = new JPanel();
	      drawing.setLayout(new FlowLayout()) ;
	      drawing.add(panel4);


	    //mouselistener
	     drawing.addMouseListener(this);	      drawing.setLayout(new FlowLayout());
	      this.add(drawing);
	      
	      
	  }
	  public void actionPerformed(ActionEvent e) {
         tempShape = e.getActionCommand();

       
	  }
	  

	public void paint(Graphics g) {
		paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for(GraphElement a : arrList){
			int w = 60;
			int h = 40;
			
						
			if(a.getLabel().equals("R")){
				x = (int) a.getXPos()-20;
				y = (int) a.getYPos()+15;
				g2.drawRect(x, y, w, h);
			}
			else if(a.getLabel().equals("El")){
				x = (int) a.getXPos()-20;
				y = (int) a.getYPos()+15;
				g2.draw(new Ellipse2D.Double(x,y,w,h));
			}
			else if(a.getLabel().equals("Ed")){
				x = (int) a.getXPos()+8;
				y = (int) a.getYPos()+35;
				int x2 = (int) a.getXPos2()+8;
				int y2 = (int) a.getYPos2()+35;
				
				g2.drawLine(x,y,x2,y2);
				
			}
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		 x = arg0.getX();
		 y = arg0.getY();
		 for(GraphElement a : arrList){
			if(x > (int) a.getXPos()-50 && x < (int) a.getXPos()+10 
					&& y >(int) a.getYPos()-5 && y < (int) a.getYPos()+35 )
				a.setColor(Color.white);
			this.repaint();
		 }
			
		//drawing area
		if (y >=115){
			
			if (SwingUtilities.isLeftMouseButton(arg0)) {
				
				if (tempShape.equals("Rectangle")){
					arrList.add(new RectangleNode(x,y));
					arrList.get(arrList.size()-1).setLabel("R");
					tempShape = "";
					arrList.get(arrList.size()-1).setXPos((double)x);
					arrList.get(arrList.size()-1).setYPos((double)y);
				
					//JOptionPane.showMessageDialog(null, arrList.get(arrList.size()-1).getXPos());
					
				}
				else if(tempShape.equals("Ellipse")){
					arrList.add(new EllipseNode(x,y));
					arrList.get(arrList.size()-1).setLabel("El");
					arrList.get(arrList.size()-1).setXPos((double)x);
					arrList.get(arrList.size()-1).setYPos((double)y);
					tempShape = "";
				}

				
			}
			
		}
		this.repaint();

		
	
			
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(tempShape.equals("Edge")){
			x = arg0.getX();
			y = arg0.getY();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(tempShape.equals("Edge")){
			int x2 = arg0.getX();
			int y2 = arg0.getY();
			arrList.add(new Edge(x,y));
			arrList.get(arrList.size()-1).setLabel("Ed");
			tempShape = "";
			arrList.get(arrList.size()-1).setXPos((double)x);
			arrList.get(arrList.size()-1).setYPos((double)y);
			arrList.get(arrList.size()-1).setXPos2((double)x2);
			arrList.get(arrList.size()-1).setYPos2((double)y2);
			repaint();
		}
	}

	@Override
	public boolean contains(Point2D arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean intersects(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub
		return false;
	}


}

