package Grafica;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Componenti.JTree_Repository;

public class JFrame_Main extends JFrame implements WindowListener, ActionListener{

	public JFrame_Main(){
		super("Repository - Ingegneria Informatica");
		
		/*Barra dei menu*/
		JMenuBar BarraDeiMenu = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		
        JMenuItem itemEsci = new JMenuItem("Esci");	
        itemEsci.addActionListener(this);
        
        menuFile.add(itemEsci);
        
        BarraDeiMenu.add(menuFile);
		this.setJMenuBar(BarraDeiMenu);
		/*Fine Barra dei menu*/
		
		JPanel_Main MP = new JPanel_Main();
		this.add( MP );
		
		
		
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

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Esci")) System.exit(0);
		
	}
	
}
