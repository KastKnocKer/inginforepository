package Grafica;

import javax.swing.*;

import Componenti.JTree_Repository;

public class JPanel_Tree extends JPanel{
	
	public JPanel_Tree(){
		super();
		JTree_Repository JTR = new JTree_Repository();
		this.add( new JScrollPane( JTree_Repository.getTree() ) );
		
	}

}
