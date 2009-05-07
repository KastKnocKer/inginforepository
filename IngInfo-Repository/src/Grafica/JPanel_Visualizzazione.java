package Grafica;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Vector;

import it.sauronsoftware.ftp4j.FTPFile;

import javax.swing.*;

import Connessioni.ConnessioneFTP;

public class JPanel_Visualizzazione  extends JPanel{
	
	private static String DirVisualizzata = null;
	public static JPanel_Visualizzazione JPV = null;
	private static Vector<String> listaFile = null;
	
	public JPanel_Visualizzazione(){
		super();
		JPV=this;
	}
	
	public static void caricaFileDirVisualizzata(){
		//JPV.removeAll();
		JPanel_Visualizzazione.JPV.add(new JButton( "FOTTIAMOCI INSIEMEEE" ));
		ConnessioneFTP.Connect();
		ConnessioneFTP.changeDirectory(DirVisualizzata);
		Vector<String> listaFile = ConnessioneFTP.getVectorDir();
		ConnessioneFTP.Disconnect();
		
		for(int i=0; i<listaFile.size(); i++){
			String tmp = listaFile.get(i);
			JPanel_Visualizzazione.JPV.add(new JButton("SOLI AMAMAI"));
			System.out.println(listaFile.get(i));
			
			
		}
		
		JPV.setBackground(Color.black);
		JPV.updateUI();
	}

	public static void setDirVisualizzata(String dirVisualizzata) {
		DirVisualizzata = dirVisualizzata;
		caricaFileDirVisualizzata();
	}

	public static String getDirVisualizzata() {
		return DirVisualizzata;
	}
	

}
