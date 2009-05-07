package Componenti;
import it.sauronsoftware.ftp4j.FTPFile;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.*;

import Connessioni.ConnessioneFTP;
import Connessioni.ConnessioneMySql;

public class JTree_Repository{
	
	private DefaultTreeModel modelloAlbero = null;
    private static DefaultMutableTreeNode nodoRadiceAlbero = null;
    private String percorsoSelezionato = null;
	private static JTree albero = null;
	private static Vector VettoreDir = null;
    
    public JTree_Repository(){
    	
    	this.nodoRadiceAlbero = new DefaultMutableTreeNode(""); 
        this.albero = new JTree(this.nodoRadiceAlbero);
        this.modelloAlbero = (DefaultTreeModel)this.albero.getModel();
        
     // impostazioni varie
        this.albero.setEditable(false);
        this.albero.setShowsRootHandles(true);
        this.albero.setRootVisible(false);
        this.albero.setVisibleRowCount(25);
        this.albero.setPreferredSize(new Dimension(300,0));
        //aggiornaDBdaFTP();
        //caricaAlbero();
        //caricaAlberoDaDB();
        caricaAlberoDaDBFast();
       
        modelloAlbero.reload();
    }
    
    private void caricaAlberoDaDBFast() {
		String query = "SELECT * FROM rep_filesystem WHERE tipo='dir';";
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
    
	private void caricaAlberoDaDB() {
		String query = "SELECT * FROM rep_filesystem WHERE tipo='dir' AND path='/Repository';";
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
		String query = "SELECT * FROM rep_filesystem WHERE tipo='dir' AND path='"+path+"';";
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
	
	public void aggiornaDBdaFTP(){
		String query="DELETE FROM rep_filesystem;";
		ConnessioneMySql.eseguiAggiornamento(query);
		aggiornaDBdaFTP("/Repository");
	}
	
	private void aggiornaDBdaFTP(String dirHome){
		ConnessioneFTP.changeDirectory(dirHome); //Vado nella directory
		FTPFile[] lista = ConnessioneFTP.getListFTPFile(); //Mi faccio restituire l'array del filesystem della cartella
		String query = null;
		Vector<String> vettoreDir = new Vector<String>();
		
		try{
			for(int i=0;;i++){
				
				if(lista[i].getType() == FTPFile.TYPE_DIRECTORY){
					System.out.println("Dir: "+lista[i].getName());
					vettoreDir.add( lista[i].getName() );
					query="INSERT INTO rep_filesystem (nome, path, tipo) VALUES ('"+lista[i].getName()+"', '"+dirHome+"', 'dir');";
				}else{
					System.out.println("File: "+lista[i].getName());
					query="INSERT INTO rep_filesystem (nome, path, tipo) VALUES ('"+lista[i].getName()+"', '"+dirHome+"', 'file');";
				}
				ConnessioneMySql.eseguiAggiornamento(query);
			}
			
		}catch(Exception e){
			
		}
		
		for(int i=0; i<vettoreDir.size(); i++){
			aggiornaDBdaFTP( dirHome+"/"+vettoreDir.get(i) );
		}
			
			
	}
		
		
		

	
    
}
