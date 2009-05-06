package Componenti;
import java.awt.Component;

import javax.swing.*;
import javax.swing.tree.*;

public class JTree_Repository{
	
	private DefaultTreeModel modelloAlbero = null;
    private DefaultMutableTreeNode nodoRadiceAlbero = null;
    private String percorsoSelezionato = null;
	private static JTree albero = null;
    
    public JTree_Repository(){
    	
    	this.nodoRadiceAlbero = new DefaultMutableTreeNode(""); 
        this.albero = new JTree(this.nodoRadiceAlbero);
        this.modelloAlbero = (DefaultTreeModel)this.albero.getModel();
        
     // impostazioni varie
        this.albero.setEditable(false);
        this.albero.setShowsRootHandles(true);
        this.albero.setRootVisible(false);
        this.albero.setVisibleRowCount(25);
        
    }

	public static Component getTree() {
		return albero;
	}
    
}
