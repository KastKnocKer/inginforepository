import java.awt.Toolkit;
import javax.swing.UIManager;

import Connessioni.ConnessioneFTP;
import Grafica.JFrame_Main;


public class Main {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		
		Toolkit.getDefaultToolkit().beep();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
			System.err.println("Impossibile impostare L&F di sistema");
			}
			
		/*Inizializzo e connetto all'FTP*/
		ConnessioneFTP ftp = new ConnessioneFTP();
		ConnessioneFTP.Connect();
		if(!ConnessioneFTP.isConnected())System.exit(-1);
		/*                              */
		
		JFrame_Main MF = new JFrame_Main();
		
		System.out.println("Fabio era gay e ora sta con lui");
		System.out.println("W le twins");
	}

}
