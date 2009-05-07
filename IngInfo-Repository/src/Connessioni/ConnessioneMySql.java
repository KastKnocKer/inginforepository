package Connessioni;

import java.util.Vector;

public class ConnessioneMySql {
	
	private static Database db = null;
	
	public ConnessioneMySql(){
		db = new Database("db_newprojectsd","newproject","asdasdasd");
		db.setPublicHost("db4free.net:3306");
	}
	
	public static void connetti(){
		if ( !db.connetti() ) {
			   System.out.println("Errore durante la connessione.");
			   System.out.println( db.getErrore() );
			   System.exit(0);
			}
			
		if( db.isConnesso() )System.out.println("Connesso!!");
			
	}
	
	public static void Disconnetti(){
		db.disconnetti();
	}
	
	public static Vector eseguiQuery(String query){
		return db.eseguiQuery(query);
	}
	
	public static boolean eseguiAggiornamento(String query){
		return db.eseguiAggiornamento(query);
	}

}
