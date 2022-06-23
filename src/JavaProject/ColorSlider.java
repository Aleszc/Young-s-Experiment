package JavaProject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ColorSlider extends JSlider implements ChangeListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private final double Gamma = 0.80;
	static private final double IntensityMax = 255;

    ColorSlider()
    {
    	this.setForeground(Color.white);
    	this.setMinimum(380);
        this.setMaximum(750);
        this.setMajorTickSpacing(80);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        Color kolorek = new Color(waveLengthToRGB(380)[0],waveLengthToRGB(380)[1],waveLengthToRGB(380)[2]);
        this.setBackground(kolorek);
        this.setFont(new Font("Verdana", Font.BOLD, 10));
        this.addChangeListener(this);
        this.setBorder(null);
}
	@Override
	public void stateChanged(ChangeEvent e) {
		// double factor;
		    //double Red, Green, Blue;

		    	Color kolor = new Color(waveLengthToRGB(this.getValue())[0],waveLengthToRGB(this.getValue())[1],waveLengthToRGB(this.getValue())[2]);
				this.setBackground(kolor);
				this.setForeground(Color.white);
	}
	public static int[] waveLengthToRGB(double Wavelength) {
	    double factor;
	    double Red, Green, Blue;

	    if((Wavelength >= 380) && (Wavelength < 440)) {
	        Red = -(Wavelength - 440) / (440 - 380);
	        Green = 0.0;
	        Blue = 1.0;
	    } else if((Wavelength >= 440) && (Wavelength < 490)) {
	        Red = 0.0;
	        Green = (Wavelength - 440) / (490 - 440);
	        Blue = 1.0;
	    } else if((Wavelength >= 490) && (Wavelength < 510)) {
	        Red = 0.0;
	        Green = 1.0;
	        Blue = -(Wavelength - 510) / (510 - 490);
	    } else if((Wavelength >= 510) && (Wavelength < 580)) {
	        Red = (Wavelength - 510) / (580 - 510);
	        Green = 1.0;
	        Blue = 0.0;
	    } else if((Wavelength >= 580) && (Wavelength < 645)) {
	        Red = 1.0;
	        Green = -(Wavelength - 645) / (645 - 580);
	        Blue = 0.0;
	    } else if((Wavelength >= 645) && (Wavelength < 751)) {
	        Red = 1.0;
	        Green = 0.0;
	        Blue = 0.0;
	    } else {
	        Red = 0.0;
	        Green = 0.0;
	        Blue = 0.0;
	    }
	    if((Wavelength >= 380) && (Wavelength < 420)) {
	        factor = 0.3 + 0.7 * (Wavelength - 380) / (420 - 380);
	    } else if((Wavelength >= 420) && (Wavelength < 701)) {
	        factor = 1.0;
	    } else if((Wavelength >= 701) && (Wavelength < 781)) {
	        factor = 0.3 + 0.7 * (780 - Wavelength) / (780 - 700);
	    } else {
	        factor = 0.0;
	    }
	    int[] rgb = new int[3];
	    rgb[0] = Red == 0.0 ? 0 : (int)Math.round(IntensityMax * Math.pow(Red * factor, Gamma));
	    rgb[1] = Green == 0.0 ? 0 : (int)Math.round(IntensityMax * Math.pow(Green * factor, Gamma));
	    rgb[2] = Blue == 0.0 ? 0 : (int)Math.round(IntensityMax * Math.pow(Blue * factor, Gamma));

	    return rgb;
	}
	
}
