package JavaProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


 
public class Glowne extends JFrame implements ActionListener, ChangeListener
{  
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JMenu menu, submenu;  
JMenuItem i1, i2, i3,i6,i7,i8,i9,i10; 
JRadioButtonMenuItem i4, i5;
JMenuBar mb;
Exit exit;
int x= 0;
PanelGlowny panel;
Point p1,p2;
float hue,saturation,brightness;
PanelSlider panelslider;
XYSeries series,series2;
StringBuffer sb,sb2;

	Glowne(){  
		  p2= new Point();
          mb=new JMenuBar();
          mb.setPreferredSize(new Dimension(800,20));
          menu=new JMenu("Menu");  
          menu.setFont(new Font("Verdana", Font.BOLD, 12));
          submenu=new JMenu("Język"); 
          submenu.setFont(new Font("Verdana", Font.BOLD, 12));
          i1=new JMenuItem("Wykres"); 
          i1.setFont(new Font("Verdana", Font.BOLD, 12));
          i2=new JMenuItem("Zapisz"); 
          i2.setFont(new Font("Verdana", Font.BOLD, 12));
          i3=new JMenuItem("Wyjscie"); 
          i3.setFont(new Font("Verdana", Font.BOLD, 12));
          
          ButtonGroup grupa = new ButtonGroup();
          i4=new JRadioButtonMenuItem("polski");
          i4.setSelected(true);
          i4.setFont(new Font("Verdana", Font.BOLD, 12));
          i5=new JRadioButtonMenuItem("english");
          i5.setFont(new Font("Verdana", Font.BOLD, 12));
          grupa.add(i4);
          grupa.add(i5);
          menu.add(i1); menu.add(i2); menu.add(i3);  
          submenu.add(i4); submenu.add(i5);  
          menu.add(submenu);  
          mb.add(menu); 
            
          this.setTitle("Doświadczenie Younga");
          this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		  this.setSize(800,540);
		  this.setLocationRelativeTo(null);
		  i5.addActionListener(this);
		  i4.addActionListener(this);
		  i3.addActionListener(this);	
		  i2.addActionListener(this);	
		  i1.addActionListener(this);	
		  this.setLayout(new BorderLayout());
			
		  //glowny panel
		  panel= new PanelGlowny();
		  this.add(panel,BorderLayout.CENTER);
			
		  //panel slider
		  panelslider = new PanelSlider();
		  this.add(panelslider, BorderLayout.EAST);
		  panelslider.a.addChangeListener(this);
		  panelslider.d.addChangeListener(this);
		  panelslider.lambda.addChangeListener(this);
		  panelslider.b1.addActionListener(this);
		  panelslider.b2.addActionListener(this);
			 
          this.setJMenuBar(mb);  
          this.setVisible(true);
         
     }
public void  English() {
        i1.setText("Plot");
        i2.setText("Save");
        i3.setText("Exit");
        submenu.setText("Language");
        this.setTitle("Young's experiment");
        	 
   }
public void Polski() {
        i1.setText("Wykres");
        i2.setText("Zapisz");
        i3.setText("Wyjscie");
        submenu.setText("Jezyk");
        this.setTitle("Doświdaczenie Younga");
   }
          
@Override
public void actionPerformed(ActionEvent e) 
{
  		//wyjscie
  		if(e.getSource() == i3 ) 
  		{
  			if(i4.isSelected()) 
  			{
  				 exit = new Exit();
  			}
  			else if(i5.isSelected()) 
  			{
  				exit= new Exit();
  				exit.English2();
  			}
  			
  		}//zamian na ang
  		else if(e.getSource() == i5) 
  		{
  			this.English();
  			panelslider.English3();
  		}
  		//zmiana na polski
  		else if(e.getSource() == i4) 
  		{
  			this.Polski();
  			panelslider.Polski3();
  		}
  		else if(e.getSource() == i2  && panelslider.b1.isSelected()==true) //laser dane
  		{
  			sb=new StringBuffer(); 
			sb.append(0);  
			sb.append("                                                ");
			sb.append(1);  
			sb.append("\n");
  			for(int i =1+(int)(0.5*panel.X); i<(int)(1*panel.X);i++) 
			{
				double theta= Math.atan(Math.abs(0.5*panel.X-i)/(0.67*panel.Y));
			 	double alpha = 0.1*((Math.PI * panel.A*1000)/(panel.lambda))*(Math.sin(theta));
			 	double bheta = 0.1*((Math.PI *panel.D*1000)/(panel.lambda))*(Math.sin(theta));
			 	double intensywnosc = (100*(Math.cos(bheta))*(Math.cos(bheta))*(Math.sin(alpha)/alpha)*(Math.sin(alpha)/alpha));
			 	sb.append(theta*180/Math.PI);  
				sb.append("                                ");
				sb.append(intensywnosc/100);  
				sb.append("\n");
			}
  			
        try {
       		 JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
       		 FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TEXT FILES");
       		 chooser.setFileFilter(filter);
             //int returnVal = chooser.showOpenDialog(null);
             chooser.showSaveDialog(null);
                    
             File inputFile = new File(chooser.getSelectedFile().getAbsolutePath());//creates a new file instance  
             String str;
             str = sb.toString();
             String x = "Degree [°]            Light Intensity [I/I0]";
             PrintWriter out = new PrintWriter(inputFile);
			 out.write(x);
			 out.write("\n");
			 out.write(str);
			 out.close();
						    	    
			} 
        catch (Exception e1) 
        	{
				// TODO Auto-generated catch block
			}   
  		}
  		else if(e.getSource() == i2  && panelslider.b2.isSelected()==true) //zarowka dane
  		{
			sb2=new StringBuffer(); 
			sb2.append(0);  
			sb2.append("                                ");
			sb2.append(1);  
			sb2.append("\n");
			for(int i = 1+(int)(0.5*panel.X); i<(int)(1*panel.X);i++)
    		{
			 	double theta1= Math.atan(Math.abs(0.5*panel.X-i)/(0.67*panel.Y));
			 	double bheta1 = 0.1*((Math.PI * panel.D*1000)/(panel.lambda))*(Math.sin(theta1));
			 	double height1 = (100*((Math.sin(bheta1))/(bheta1))*((Math.sin(bheta1))/(bheta1)));
			 	sb2.append(theta1*180/Math.PI);  
				sb2.append("                ");
				sb2.append(height1/100);  
				sb2.append("\n");

    		}
  			
        try {
       		 JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
       		 FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TEXT FILES");
       		 chooser.setFileFilter(filter);
             chooser.showSaveDialog(null);
                    
             File inputFile = new File(chooser.getSelectedFile().getAbsolutePath());//creates a new file instance  
             String str;  
             str = sb2.toString();
             String x = "Degree [°]            Light Intensity [I/I0]";
             PrintWriter out = new PrintWriter(inputFile);
			 out.write(x);
			 out.write("\n");
			 out.write(str);
			 out.close();
						    	    
			} 
        catch (Exception e1) 
        	{
				// TODO Auto-generated catch block
			}   
  		}
  		else if(e.getSource() == i1 && panelslider.b1.isSelected()==true ) //wykres
  		{
			series = new XYSeries("Nazwa serii 1");
  			for(int i = 0; i<(int)(1*panel.X);i++) 
			{
				double theta= Math.atan(Math.abs(0.5*panel.X-i)/(0.67*panel.Y));
				double alpha = 0.1*((Math.PI * panel.A*1000)/(panel.lambda))*(Math.sin(theta));
				double bheta = 0.1*((Math.PI *panel.D*1000)/(panel.lambda))*(Math.sin(theta));
				double intensywnosc = (100*(Math.cos(bheta))*(Math.cos(bheta))*(Math.sin(alpha)/alpha)*(Math.sin(alpha)/alpha));
				series.add(-theta*180/Math.PI, intensywnosc/100);
				series.add(theta*180/Math.PI, intensywnosc/100);

			}
  			 XYSeriesCollection dataset = new XYSeriesCollection();
  			 try{
  				dataset.addSeries(series);
  			 }
  			 catch (Exception ex)
  			 { 
  				 System.out.println("No Data/Brak Danych");
  			 }
  			  JFreeChart chart = ChartFactory.createXYAreaChart(
  			  "Intensity in the function of angle",//Tytul
  			  "θ°", // opisy osi
  			  "I/I0", 
  			  dataset, // Dane 
  			  PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
  			  false, // legenda
  			  true, // tooltips
  			  false);
  			  XYPlot plot = chart.getXYPlot();
  			  plot.setDataset(0, dataset);
  			  XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(); 
  			  plot.setRenderer(0, renderer0); 
  			  plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, panelslider.lambda.getBackground()); 
  			  ChartFrame frame1=new ChartFrame( "Intensity chart",chart);
  			  frame1.setVisible(true);
  			  frame1.setLocationRelativeTo(this);
  			  frame1.setSize(500,400);
  		}
  		else if(e.getSource() == i1 && panelslider.b2.isSelected()==true ) //wykres
  		{
			series2 = new XYSeries("Nazwa serii 2");
  			for(int i=0;i<(int)(1*panel.X);i++)
    		{
			 	double theta1= Math.atan(Math.abs(0.5*panel.X-i)/(0.67*panel.Y));
			 	double bheta1 = 0.1*((Math.PI * panel.D*1000)/(panel.lambda))*(Math.sin(theta1));
			 	double height1 = (100*((Math.sin(bheta1))/(bheta1))*((Math.sin(bheta1))/(bheta1)));
			 	series2.add(-theta1*180/Math.PI, height1/100);
				series2.add(theta1*180/Math.PI, height1/100);
			 	
    		}
  			 XYSeriesCollection dataset = new XYSeriesCollection();
  			 try{
  				dataset.addSeries(series2);
  			 }
  			 catch (Exception ex)
  			 { 
  				 System.out.println("No Data/Brak Danych");
  			 }
  			  JFreeChart chart = ChartFactory.createXYAreaChart(
  			  "Intensity in the function of angle",//Tytul
  			  "θ°", // opisy osi
  			  "I/I0", 
  			  dataset, // Dane 
  			  PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
  			  false, // legenda
  			  true, // tooltips
  			  false);
  			  XYPlot plot = chart.getXYPlot();
  			  plot.setDataset(0, dataset);
  			  XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(); 
  			  plot.setRenderer(0, renderer0); 
  			  plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, panelslider.lambda.getBackground()); 
  			  ChartFrame frame1=new ChartFrame( "Intensity chart",chart);
  			  frame1.setVisible(true);
  			  frame1.setLocationRelativeTo(this);
  			  frame1.setSize(500,400);
  		}
  		else if(panelslider.b1.isSelected()==true) //laser on
  		{
  			  //panel.lista.clear();
  			  panel.laser=true;
  			  panel.zarowka=false;
  			  ImageIcon im = new ImageIcon("obrazy/laser3.png");
  			  panel.image1.setImage(im.getImage());
  			  panelslider.a.setEnabled(true);
  			  i1.setEnabled(true);
  			  i2.setEnabled(true);
  			  panelslider.lambda.setEnabled(true);
  			  panel.repaint();
  		}
  		else if(panelslider.b2.isSelected()==true)// zarowka on
  		{
  			  //panel.lista.clear();
  			  panel.laser=false;
  			  panel.zarowka=true;
  			  ImageIcon im = new ImageIcon("obrazy/zarowka.png");
			  panel.image1.setImage(im.getImage());
			  panelslider.a.setEnabled(false);
			  panel.repaint();
			  
  		}
}	    
		@Override
		public void stateChanged(ChangeEvent e) {//zmienianie linii w panelu glownym
			// TODO Auto-generated method stub
			if(e.getSource()==panelslider.a) {
				panel.setA(panelslider.a.getValue());
				panel.repaint();
			}
			else if(e.getSource()==panelslider.d) 
			{
				panel.setD(panelslider.d.getValue());
				panel.repaint();
			}
			else if(e.getSource()==panelslider.lambda) //zmiana koloru lasera
			{
				panel.setlambda(panelslider.lambda.getValue());
				panel.setColor(panelslider.lambda.getBackground());
				panel.repaint();
			}
			
			//wizualizacja
			ColorSlider.waveLengthToRGB(panelslider.lambda.getValue());
			float[] hsbValues = new float[3];
		    int red = ColorSlider.waveLengthToRGB(panel.lambda)[0];
		    int green = ColorSlider.waveLengthToRGB(panel.lambda)[1];
		    int blue = ColorSlider.waveLengthToRGB(panel.lambda)[2];
		    
		    hsbValues = Color.RGBtoHSB(red,green,blue,hsbValues);
		    //Dane do wykresu 2D
		    if(panel.laser) {
				for(int i = 0; i<(int)(1*panel.X);i++) 
				{
					double theta= Math.atan(Math.abs(0.5*panel.X-i)/(0.67*panel.Y));
					double alpha = 0.1*((Math.PI * panel.A*1000)/(panel.lambda))*(Math.sin(theta));
					double bheta = 0.1*((Math.PI *panel.D*1000)/(panel.lambda))*(Math.sin(theta));
					double intensywnosc = (100*(Math.cos(bheta))*(Math.cos(bheta))*(Math.sin(alpha)/alpha)*(Math.sin(alpha)/alpha));
					panel.setNatezenie(intensywnosc);
					Point p1 = new Point();
					p1.setx(i);
					panel.setx(i);
					hue = hsbValues[0];
					saturation = hsbValues[1];
					brightness = hsbValues[2];
					p1.setkolor(hue, (float)(saturation),(float)((intensywnosc*0.01)*brightness));
				    panel.lista.add(p1);
					if(i==(int)(0.5*panel.X)) 
					{
						p1.setkolor(hue,saturation,brightness);
					    panel.lista.add(p1);
						
					}
				}
				
			}
		    panel.repaint();
		}
 }