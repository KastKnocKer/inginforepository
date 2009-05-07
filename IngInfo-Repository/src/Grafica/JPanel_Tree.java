package Grafica;

import javax.swing.*;

import Componenti.JTree_Repository;

public class JPanel_Tree extends JPanel{
	
	public JPanel_Tree(){
		super();
		this.setLayout(new java.awt.BorderLayout());
		JTree_Repository JTR = new JTree_Repository();
		JScrollPane JSP = new JScrollPane( JTree_Repository.getTree());
		this.add(JSP , java.awt.BorderLayout.CENTER );
	}

}
