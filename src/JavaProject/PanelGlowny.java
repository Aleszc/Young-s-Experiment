package JavaProject;

import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class PanelGlowny extends JPanel implements ComponentListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage image;
	public int X;
	public int Y;
	public int A = 5;
	public int D = 10;
	public int  x,y;
	Graphics2D g2;
	public Color kolor, k;
	public Graphics g;
	int lambda = 380;
	Graphics2D g3;
	public double natezenie=0;
	public Point p;
	public ArrayList<Point> lista;
	public boolean zarowka;
	public boolean laser;
	ImageIcon image1;
	public int speed;
	
	 
	PanelGlowny()
	{
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(580, 50));
		this.setBackground(Color.black);
		this.setSize(new Dimension(580, 540));
		k= new Color(97,0,97);
		image1 = new ImageIcon("obrazy/laser3.png");
		JLabel label = new JLabel("", image1, JLabel.CENTER);
		label.setLocation((int)0.5*X+30, 80);
		label.setPreferredSize(new Dimension(60,65));
		lista = new ArrayList<Point>();
		this.add( label);
		X=getWidth();
		Y=getHeight();
		zarowka=false;
		laser=true;
		speed=5;
		this.addComponentListener(this);
		this.setBorder(null);
		
	}
	public void setA(int a) 
	{
		this.A=a;
	}
	public void setD(int d) 
	{
		this.D=d;
	}
	public void setColor(Color b) 
	{
		this.k=b;
	}
	public void setlambda(int x) 
	{ 
		this.lambda=x;
	}
	public void setNatezenie(double i)
	{
		this.natezenie=i;
	}
	public void setx(int x) 
	{
		
		this.x=x;;
	}
	
	public void paintComponent(Graphics g) {
		
	    super.paintComponent(g);
	    Graphics2D gg = (Graphics2D) g;
		BasicStroke bs1 = new BasicStroke(3);
		gg.setStroke(bs1);
		
	    g.setColor(Color.white);
	    if(laser==true) {
	    	g.drawLine((int)(0.1*X),(int)(0.3*Y),(int)(0.49*X),(int)(0.3*Y));
	    	g.drawLine((int)(0.51*X),(int)(0.3*Y),(int)(0.9*X),(int)(0.3*Y)); 
	    }
	    
	    g.drawLine((int)(0.1*X),(int)(0.5*Y),(int)(0.5*X-(2*A/2)-(5*D/2)),(int)(0.5*Y));
	    g.drawLine((int)(0.5*X+(2*A)-(5*D/2)),(int)(0.5*Y),(int)(0.5*X-(2*A)+(5*D/2)),(int)(0.5*Y));
	    g.drawLine((int)(0.5*X+(2*A/2)+(5*D/2)),(int)(0.5*Y),(int)(0.9*X),(int)(0.5*Y));
	    
	    g.setColor(k);
	    if(laser==true) {
	    	g.fillRect((int)(0.5*X)-8 ,70,15,5);
	    }

        image =  (BufferedImage)this.createImage((int)(X), (int)(0.06*Y));
        g2 = (Graphics2D) image.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(0, 0, (int)(X), (int)(0.06*Y));
        
        if(laser==true) {
           	 for(int i=0;i<lista.size();i++)
                {
                	g2.setColor(lista.get(i).kolor);
                	g2.fillRect(lista.get(i).x, 10 , 15, 30);
                }
        }
        if(laser==true) {//laser
	    	for(int i=  0;i<lista.size();i++)
    		{
			 	double theta= Math.atan(Math.abs(0.5*X-i)/(0.67*Y));
			 	double alpha = 0.1*((Math.PI * A*1000)/(lambda))*(Math.sin(theta));
			 	double bheta = 0.1*((Math.PI * D*1000)/(lambda))*(Math.sin(theta));
			 	int height = (int)(100*(Math.cos(bheta))*(Math.cos(bheta))*(Math.sin(alpha)/alpha)*(Math.sin(alpha)/alpha));
			 	g.fillRect(i , Y-5-height,1, 150 );
			 	g.fillRect((int)(0.5*X) , Y -5- height,1, 150 );
    		}				
        }
        else if(zarowka==true) //zarowka
        {
        	g.setColor(kolor);
        	for(int i=  0;i<X;i++)
    		{
			 	double theta1= Math.atan(Math.abs(0.5*X-i)/(0.67*Y));
			 	double bheta1 = 0.1*((Math.PI * D*1000)/(lambda))*(Math.sin(theta1));
			 	int height1 = (int)(100*((Math.sin(bheta1))/(bheta1))*((Math.sin(bheta1))/(bheta1)));
			 	g.fillRect(i , Y-5-height1,1, 150);
			 	g.fillRect(X/2,Y-5-height1,1,150);
    		}
        	
        }
			     g2 = (Graphics2D)g;
			     g.drawImage(image, 0, (int)(0.67*Y), null);
	}	    
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		X=this.getWidth();
		Y=this.getHeight();
		this.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
