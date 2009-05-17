package Connessioni;

import it.sauronsoftware.ftp4j.FTPFile;

import java.util.Vector;

public class Funzioni_Database {
	
	/*******************************************************/
	public static void AggiornaDBdaFTP(){
		ConnessioneFTP.Connect();
		ConnessioneMySql.connetti();
		aggiornaDBdaFTP();
		ConnessioneFTP.Disconnect();
		ConnessioneMySql.Disconnetti();
	}
	
	private static void aggiornaDBdaFTP(){
		String query="DELETE FROM rep_directory;";
		ConnessioneMySql.eseguiAggiornamento(query);
		query="DELETE FROM rep_file;";
		ConnessioneMySql.eseguiAggiornamento(query);
		aggiornaDBdaFTP("/Repository");
	}
	
	private static void aggiornaDBdaFTP(String dirHome){
		ConnessioneFTP.changeDirectory(dirHome); //Vado nella directory
		FTPFile[] lista = ConnessioneFTP.getListFTPFile(); //Mi faccio restituire l'array del filesystem della cartella
		String query = null;
		Vector<String> vettoreDir = new Vector<String>();
		
		try{
			for(int i=0;;i++){
				
				if(lista[i].getType() == FTPFile.TYPE_DIRECTORY){
					System.out.println("Dir: "+lista[i].getName());
					vettoreDir.add( lista[i].getName() );
					query="INSERT INTO rep_directory (Nome, Path) VALUES ('"+lista[i].getName()+"', '"+dirHome+"');";
				}else{
					System.out.println("File: "+lista[i].getName());
					String url = "http://util.altervista.org"+dirHome+lista[i].getName();
					query="INSERT INTO rep_file (Nome, Path, Url) VALUES ('"+lista[i].getName()+"', '"+dirHome+"', '"+url+"');";
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
