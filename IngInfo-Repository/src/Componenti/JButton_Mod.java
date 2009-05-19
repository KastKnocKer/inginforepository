package Componenti;

import it.sauronsoftware.ftp4j.FTPClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;

import org.omg.CORBA_2_3.portable.InputStream;

import sun.net.www.URLConnection;



public class JButton_Mod extends JButton implements ActionListener{
	
	private String UrlFile = null;
	
	public JButton_Mod(String nomebottone){
		super(nomebottone);
		this.addActionListener(this);
	}

	public void setUrlFile(String urlFile) {
		UrlFile = urlFile;
	}

	public String getUrlFile() {
		return UrlFile;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(this.getUrlFile());

	
	}

}
