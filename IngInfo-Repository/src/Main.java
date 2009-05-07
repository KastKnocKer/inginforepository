import java.awt.Toolkit;
import javax.swing.UIManager;

import Connessioni.ConnessioneFTP;
import Connessioni.ConnessioneMySql;
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
		/*                   */
		
		/*Inizializzo la connessione all'FTP*/
		ConnessioneFTP ftp = new ConnessioneFTP();
		/*                              */
		
		/*Avvio interfaccia grafica*/
		JFrame_Main MF = new JFrame_Main();
		
		
	}

}
