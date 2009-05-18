package Grafica;

import java.awt.*;
import java.util.*;
import it.sauronsoftware.ftp4j.FTPFile;
import javax.swing.*;
import Componenti.*;
import Connessioni.*;

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
	
		Vector<Obj_File> V = new Vector();
		for(int i=0; i<ListaFile.LinkListaFile.size(); i++){
			if(((Obj_File) ListaFile.LinkListaFile.get(i)).getPath().equals(DirVisualizzata)){
				V.add( (Obj_File) ListaFile.LinkListaFile.get(i) );
			}
		}
		
		for(int i=0; i<V.size(); i++){
			c.gridy = i;
			String tmp = V.get(i).getNome();
			String urlFile = V.get(i).getUrl();
			JButton_Mod bottone = new JButton_Mod(tmp);
			bottone.setUrlFile(urlFile);
			System.out.println("FILE VISUAL: "+tmp);
			JPanel_Visualizzazione.JPV.add( bottone,c);
		}
		
		JPV.updateUI();
	}

	public static void setDirVisualizzata(String dirVisualizzata) {
		DirVisualizzata = dirVisualizzata;
		System.out.println("DIR VISUAL: "+DirVisualizzata);
		caricaFileDirVisualizzata();
	}

	public static String getDirVisualizzata() {
		return DirVisualizzata;
	}
	

}
