package Componenti;
import java.awt.Component;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.*;

import Connessioni.ConnessioneFTP;

public class JTree_Repository{
	
	private DefaultTreeModel modelloAlbero = null;
    private static DefaultMutableTreeNode nodoRadiceAlbero = null;
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
        
        caricaAlbero();
        modelloAlbero.reload();
    }
    
	public static Component getTree() {
		return albero;
	}
	
	private void caricaAlbero(){
		Vector<String> dirHome = new Vector<String>();
		dirHome.add("/Repository");
		caricaAlbero(dirHome,(DefaultMutableTreeNode)nodoRadiceAlbero);
	}
	
	
	private void caricaAlbero(Vector<String> VettoreDir, DefaultMutableTreeNode nodoGenitore){
		
		for(int i=0; i<VettoreDir.size() ; i++){
			
			/*Vado nella Directory*/
			ConnessioneFTP.changeDirectory( VettoreDir.get(i) );
			String tmp = VettoreDir.get(i);
			int index = tmp.lastIndexOf('/')+1;
			tmp=tmp.substring(index);
			DefaultMutableTreeNode nuovoNodo = new DefaultMutableTreeNode( tmp );
			modelloAlbero.insertNodeInto(nuovoNodo, nodoGenitore, nodoGenitore.getChildCount());
					
			caricaAlbero(ConnessioneFTP.getVectorDir(), nuovoNodo);
			
		}
		
		
		
		
    }
	
    
}
