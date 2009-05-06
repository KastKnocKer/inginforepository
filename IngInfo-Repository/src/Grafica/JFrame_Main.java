package Grafica;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class JFrame_Main extends JFrame implements WindowListener{

	public JFrame_Main(){
		super("Repository - Ingegneria Informatica");
		
		
		
		
		
		
		this.setSize(1000, 800);
		setVisible(true);
	}
	
	public void windowClosed(WindowEvent e){System.exit(0);}
	public void windowClosing(WindowEvent e){System.exit(0);}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}
