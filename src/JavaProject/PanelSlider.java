package JavaProject;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class PanelSlider extends JPanel implements ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lambdatext,  dtext, atext;
	JRadioButton b1, b2;
	JSlider  a, d;
	ColorSlider lambda;
	Color tlo= new Color(217, 217, 217);
	//Slajdery i text fildy
	PanelSlider(){
		
	this.setBackground(tlo);
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.setPreferredSize(new Dimension(200, 540));
	this.setLayout(new GridLayout(9,1));
	this.setBorder(null);	
	
	lambda= new ColorSlider();
    d= new JSlider(JSlider.HORIZONTAL);
    d.setMinimum(10);
    d.setValue(10);
    d.setBackground(tlo);
    d.setFont(new Font("Verdana", Font.BOLD, 10));
    d.setMaximum(50);
    d.setBorder(null);
    d.setMajorTickSpacing(10);
    d.setPaintTicks(true);
    d.setPaintLabels(true);
    a = new JSlider(JSlider.HORIZONTAL);
    a.setMinimum(2);
    a.setValue(5);
    a.setBackground(tlo);
    a.setFont(new Font("Verdana", Font.BOLD, 10));
    a.setMaximum(10);
    a.setBorder(null);
    a.setMajorTickSpacing(2);
    a.setPaintTicks(true);
    a.setPaintLabels(true);
    lambdatext = new JLabel("λ: 380[nm]");
    lambdatext.setHorizontalAlignment(JLabel.CENTER);
    lambdatext.setBackground(tlo);
    lambdatext.setBorder(null);
    lambdatext.setPreferredSize(new Dimension(5, 10));
    lambdatext.setFont(new Font("Verdana", Font.BOLD, 12));
    atext = new JLabel("a: 5[μm]");
    atext.setHorizontalAlignment(JLabel.CENTER);
    atext.setBackground(tlo);
    atext.setBorder(null);
    atext.setPreferredSize(new Dimension(5, 10));
    atext.setFont(new Font("Verdana", Font.BOLD, 12));
    dtext = new JLabel("d: 10[μm]");
    dtext.setBackground(tlo);
    dtext.setHorizontalAlignment(JLabel.CENTER);
    dtext.setBorder(null);
    dtext.setPreferredSize(new Dimension(5, 10));
    dtext.setFont(new Font("Verdana", Font.BOLD, 12));
	this.add(lambda);
	this.add(lambdatext);
	this.add(d);
	this.add(dtext);
	this.add(a);
	this.add(atext);
	
		
	//Listenery dla slajderow
	lambda.addChangeListener(this);
	d.addChangeListener(this);
	a.addChangeListener(this);
		
	ButtonGroup group1 = new ButtonGroup();
	b1= new JRadioButton("Laser");
	b1.setFont(new Font("Verdana", Font.BOLD, 10));
	b1.setBackground(tlo);
	b1.setSelected(true);
	b2= new JRadioButton("Dwa punktowe źrodla światła");
	b2.setFont(new Font("Verdana", Font.BOLD, 10));
	b2.setBackground(tlo);
	group1.add(b1);
	group1.add(b2);
	this.add(b1);
	this.add(b2);
	
		 	
}
 		
@Override
public void stateChanged(ChangeEvent e) {
 	if(e.getSource() == lambda) {
 			String value = String.format("%d", lambda.getValue());
 			String x = "λ: ";
 			String y = "[nm]";
 			lambdatext.setText(x + value + y);
 	}
 	else if (e.getSource() == d) {
 			String value = String.format("%d", d.getValue());
 	 		String x = "d: ";
 	 		String y = "[μm]";
 	 		dtext.setText(x + value + y);
 	}
 	else if (e.getSource() == a) {
 			String value = String.format("%d", a.getValue());
 	 		String x = "a: ";
 	 		String y = "[μm]";
 	 		atext.setText(x + value + y);
 	}		
 	
}
	public void English3() 
	{
		b2.setText("Two point sources of light");

	}
	public void Polski3() 
	{

		b2.setText("Dwa punktowe źrodla światła");
	}

}
