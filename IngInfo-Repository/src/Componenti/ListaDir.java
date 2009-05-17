package Componenti;

import java.util.Vector;

import Connessioni.ConnessioneMySql;

public class ListaDir extends Vector{
	
	public static ListaDir LinkListaDir = null;
	
	public ListaDir(){
		super();
		LinkListaDir = this;
		this.caricaDirectoryDaDB();
	}
	
	public void caricaDirectoryDaDB(){
		String query="SELECT * FROM rep_directory;";
		Vector V = ConnessioneMySql.eseguiQuery(query);
		Obj_Directory dir;
		String[] record;
		for(int i=0; i<V.size(); i++){
			dir = new Obj_Directory();
			record = (String[]) V.get(i);
			dir.setNome(record[0]);
			dir.setPath(record[1]);
		}
		
	}
	
}
