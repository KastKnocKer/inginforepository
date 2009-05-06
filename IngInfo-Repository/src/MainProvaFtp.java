import Connessioni.ConnessioneFTP;


public class MainProvaFtp {


	public static void main(String[] args) {

		ConnessioneFTP ftp = new ConnessioneFTP();
		ConnessioneFTP.Connect();
		
		ConnessioneFTP.getDir();
		//new FinestraCercaFile();
		System.out.println("Connesso: "+ConnessioneFTP.isConnected());
		ConnessioneFTP.Disconnect();
		
		
	}

}
