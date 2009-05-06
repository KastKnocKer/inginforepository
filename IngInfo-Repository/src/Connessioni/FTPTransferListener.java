package Connessioni;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class FTPTransferListener implements FTPDataTransferListener {

	public void started() {
		// Trasferimento avviato
		System.out.println("Trasferimento avviato");
	}

	public void transferred(int length) {
		// Altri length byte sono stati trasferiti da quando questo metodo
		// è stato richiamanto l'ultima volta
		//System.out.println("");
	}

	public void completed() {
		// Trasferimento completato
		System.out.println("Trasferimento completato");
	}

	public void aborted() {
		// Trasferimento annullato
		System.out.println("Trasferimento annullato");
	}

	public void failed() {
		// Trasferimento fallito
		System.out.println("Trasferimento fallito");
	}

}