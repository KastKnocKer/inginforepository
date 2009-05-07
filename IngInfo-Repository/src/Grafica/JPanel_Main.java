package Grafica;

import javax.swing.JPanel;

public class JPanel_Main extends JPanel{

	public JPanel_Main(){
		super();
		this.setLayout(new java.awt.BorderLayout());
		this.add(new JPanel_Tree(), java.awt.BorderLayout.WEST);
		this.add(new JPanel_Visualizzazione(), java.awt.BorderLayout.CENTER);
		 
	}
}
