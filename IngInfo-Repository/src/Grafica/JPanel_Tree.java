package Grafica;

import javax.swing.*;

import Componenti.JTree_Repository;

public class JPanel_Tree extends JPanel{
	
	public JPanel_Tree(){
		super();
		this.add( new JScrollPane( JTree_Repository.getTree() ) );
		
	}

}
