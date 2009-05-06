package Grafica;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class JFrame_Main extends JFrame implements WindowListener{

	public JFrame_Main(){
		super("Repository - Ingegneria Informatica");
		
		/*Barra dei menu*/
		JMenuBar BarraDeiMenu = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		
        JMenuItem itemEsci = new JMenuItem("Esci");	
        menuFile.add(itemEsci);
        
        BarraDeiMenu.add(menuFile);
		this.setJMenuBar(BarraDeiMenu);
		/*Fine Barra dei menu*/
		
		
		
		
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
