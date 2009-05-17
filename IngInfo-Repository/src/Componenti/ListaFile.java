package Componenti;

import java.util.Vector;

import Connessioni.ConnessioneMySql;


public class ListaFile extends Vector{
	
public static ListaFile LinkListaFile = null;
	
	public ListaFile(){
		super();
		LinkListaFile = this;
		this.caricaFileDaDB();
	}
	
	public void caricaFileDaDB(){
		String query="SELECT * FROM rep_file;";
		Vector V = ConnessioneMySql.eseguiQuery(query);
		Obj_File file;
		String[] record;
		for(int i=0; i<V.size(); i++){
			file = new Obj_File();
			record = (String[]) V.get(i);
			file.setNome(record[0]);
			file.setPath(record[1]);
			file.setUrl(record[2]);
			this.add(file);
		}
		
	}

}
