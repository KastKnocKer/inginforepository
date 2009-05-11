package Grafica;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import it.sauronsoftware.ftp4j.FTPFile;

import javax.swing.*;

import Connessioni.ConnessioneFTP;

public class JPanel_Visualizzazione  extends JPanel{
	
	private static String DirVisualizzata = null;
	public static JPanel_Visualizzazione JPV = null;
	private static Vector<String> listaFile = null;
	private static GridBagConstraints c = null;
	
	public JPanel_Visualizzazione(){
		super();
		JPV=this;
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

	}
	
	public static void caricaFileDirVisualizzata(){
		JPV.removeAll();
		ConnessioneFTP.Connect();
		ConnessioneFTP.changeDirectory(DirVisualizzata);
		Vector<String> listaFile = ConnessioneFTP.getVectorFile();
		
	
		for(int i=0; i<listaFile.size(); i++){
			c.gridy = i;
			String tmp = listaFile.get(i);
			JButton bottone = new JButton(tmp);
			
			JPanel_Visualizzazione.JPV.add( bottone,c);
		}
		
		JPV.updateUI();
		ConnessioneFTP.Disconnect();
	}

	public static void setDirVisualizzata(String dirVisualizzata) {
		DirVisualizzata = dirVisualizzata;
		caricaFileDirVisualizzata();
	}

	public static String getDirVisualizzata() {
		return DirVisualizzata;
	}
	

}
