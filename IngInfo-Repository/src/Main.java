import java.awt.Toolkit;
import javax.swing.UIManager;

import Connessioni.*;
import Componenti.*;
import Grafica.JFrame_Main;


public class Main {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		
		/*Impostazione Look and Feel*/
		Toolkit.getDefaultToolkit().beep();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
			System.err.println("Impossibile impostare L&F di sistema");
			}
			
		/*Inizializzo la connessione a MySql*/	
		new ConnessioneMySql();
		ConnessioneMySql.connetti();
		new ListaFile();
		new ListaDir();
		ConnessioneMySql.Disconnetti();
		/*                   */
		
		/*Inizializzo la connessione all'FTP*/
		ConnessioneFTP ftp = new ConnessioneFTP();
		/*                              */
		
		//Funzioni_Database.AggiornaDBdaFTP();
		
		/*Avvio interfaccia grafica*/
		JFrame_Main MF = new JFrame_Main();
		
		
	}

}
