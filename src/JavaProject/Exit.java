package JavaProject;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exit extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	//okno wyjscia z programu(exit w menu)
	JButton b2, b3;
	JPanel panel1, panel2;
	JLabel l1;
	Exit()
	{
		this.setTitle("Wyjście");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(330,140);
        this.setLayout(new GridLayout(2,1));
        panel1= new JPanel();
        this.add(panel1);
        panel2= new JPanel();
        this.add(panel2);
        
        b2= new JButton("Tak");
        b2.setFocusPainted(false);
        panel2.add(b2);
        b3= new JButton("Nie");
        panel2.add(b3);
        
        l1= new JLabel("Czy na pewno chcesz wyjść z programu?");
        l1.setFont(new Font("Verdana", Font.BOLD, 12));
        panel1.add(l1);
        
        b2.addActionListener(this);
        b3.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b2) 
		{
			System.exit(0);
		}
		else if(e.getSource()==b3) 
		{
			this.setVisible(false);
		}
		
	}
	public void English2() 
	{
		b2.setText("Yes");
  	  	b3.setText("No");
  	  	l1.setText("Are you sure you want to exit the application?");
  	  	this.setTitle("Exit");
		
	}
}
