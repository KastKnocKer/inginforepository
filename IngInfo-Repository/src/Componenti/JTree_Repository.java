package Componenti;
import it.sauronsoftware.ftp4j.FTPFile;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import Connessioni.ConnessioneFTP;
import Connessioni.ConnessioneMySql;
import Grafica.JPanel_Visualizzazione;

public class JTree_Repository implements TreeSelectionListener{
	
	private DefaultTreeModel modelloAlbero = null;
    private static DefaultMutableTreeNode nodoRadiceAlbero = null;
    private String percorsoSelezionato = null;
	private static JTree albero = null;
	private static Vector VettoreDir = null;
	private static DefaultMutableTreeNode nodoSelezionato;
    
    public JTree_Repository(){
    	
    	this.nodoRadiceAlbero = new DefaultMutableTreeNode(""); 
        this.albero = new JTree(this.nodoRadiceAlbero);
        this.modelloAlbero = (DefaultTreeModel)this.albero.getModel();
        
     // impostazioni varie
        this.albero.addTreeSelectionListener(this);
        this.albero.setEditable(false);
        this.albero.setShowsRootHandles(true);
        this.albero.setRootVisible(false);
        this.albero.setVisibleRowCount(25);
        this.albero.setPreferredSize(new Dimension(300,0));

        
        
        /*Carico da database i nodi del jtree*/
        ConnessioneMySql.connetti();
        caricaAlberoDaDBFast();
        ConnessioneMySql.Disconnetti();
        
        modelloAlbero.reload();
    }
    
    private void caricaAlberoDaDBFast() {
		String query = "SELECT * FROM rep_directory;";
		VettoreDir = ConnessioneMySql.eseguiQuery(query);
		String NomeDir,Path;
		for(int i=0; i<VettoreDir.size(); i++){
			String[] record = (String[]) VettoreDir.elementAt(i);
			if( !record[1].equals("/Repository") ) continue;
			NomeDir=record[0];
			Path=record[1];
			System.out.println(NomeDir +" "+ Path);
			DefaultMutableTreeNode nuovoNodo = new DefaultMutableTreeNode( NomeDir );
			modelloAlbero.insertNodeInto(nuovoNodo, this.nodoRadiceAlbero, this.nodoRadiceAlbero.getChildCount());
			caricaAlberoDaDBFast(Path+"/"+NomeDir, nuovoNodo);
			
			}
		
	}
    
    private void caricaAlberoDaDBFast(String path, DefaultMutableTreeNode nodo){
    	String NomeDir,Path;
		for(int i=0; i<VettoreDir.size(); i++){
			String[] record = (String[]) VettoreDir.elementAt(i);
			if( !record[1].equals(path) ) continue;
			NomeDir=record[0];
			Path=record[1];
			System.out.println(NomeDir +" "+ Path);
			DefaultMutableTreeNode nuovoNodo = new DefaultMutableTreeNode( NomeDir );
			modelloAlbero.insertNodeInto(nuovoNodo, nodo, nodo.getChildCount());
			caricaAlberoDaDBFast(Path+"/"+NomeDir, nuovoNodo);
			
			}
	}
    
/*
 * 	private void caricaAlberoDaDB() {
		String query = "SELECT * FROM rep_directory WHERE path='/Repository';";
		Vector v = ConnessioneMySql.eseguiQuery(query);
		String NomeDir,Path;
		for(int i=0; i<v.size(); i++){
			   String[] record = (String[]) v.elementAt(i);
			   NomeDir=record[0];
			   Path=record[1];
			   System.out.println(NomeDir +" "+ Path);
			DefaultMutableTreeNode nuovoNodo = new DefaultMutableTreeNode( NomeDir );
			modelloAlbero.insertNodeInto(nuovoNodo, this.nodoRadiceAlbero, this.nodoRadiceAlbero.getChildCount());
			caricaAlberoDaDB(Path+"/"+NomeDir, nuovoNodo);
			
			}
		
	}
	
	private void caricaAlberoDaDB(String path, DefaultMutableTreeNode nodo){
		String query = "SELECT * FROM rep_directory WHERE path='"+path+"';";
		Vector v = ConnessioneMySql.eseguiQuery(query);
		String NomeDir,Path;
		for(int i=0; i<v.size(); i++){
			   String[] record = (String[]) v.elementAt(i);
			   NomeDir=record[0];
			   Path=record[1];
			   System.out.println(NomeDir +" "+ Path);
			DefaultMutableTreeNode nuovoNodo = new DefaultMutableTreeNode( NomeDir );
			modelloAlbero.insertNodeInto(nuovoNodo, nodo, nodo.getChildCount());
			caricaAlberoDaDB(Path+"/"+NomeDir, nuovoNodo);
			
		}
	}
 */

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
	
	


	public void valueChanged(TreeSelectionEvent e) {
		
	nodoSelezionato = (DefaultMutableTreeNode) albero.getSelectionPath().getLastPathComponent();
	
	if(nodoSelezionato.getLevel()==2) {
		
		String[] tmp = null;
		String path = null; /*PATH ASSOLUTO*/
		for(int i=0; i<VettoreDir.size();i++){
			tmp = (String[]) VettoreDir.get(i);
			if(tmp[0].equals( nodoSelezionato.toString()) ){
				path = tmp[1]+"/"+tmp[0];
				break;
			}
		}
	System.out.println("Selezionato "+nodoSelezionato.toString());
	System.out.println("PATH: "+path);
	JPanel_Visualizzazione.setDirVisualizzata(path);
	}
	
	
	}
		
		
		

	
    
}
