package JavaProject;


import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Glowne frame1;
			try {
				frame1 = new Glowne();
				frame1.setVisible(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		});
	}
}
