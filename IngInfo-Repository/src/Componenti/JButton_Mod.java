package Componenti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
		System.out.println(this.getName());
		
	}

}
