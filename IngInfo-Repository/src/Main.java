import java.awt.Toolkit;

import javax.swing.UIManager;


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
		
		System.out.println("Fabio era gay e ora sta con lui");
		System.out.println("W le twins");
	}

}
