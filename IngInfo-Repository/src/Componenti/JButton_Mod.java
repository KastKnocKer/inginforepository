package Componenti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLConnection;

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
		System.out.println(this.getUrlFile());
		/******************************************************************/
		
		URL u;
	      InputStream is = null;
	      DataInputStream dis;
	      String s;

	      try {

	         //------------------------------------------------------------//
	         // Step 2:  Create the URL.                                   //
	         //------------------------------------------------------------//
	         // Note: Put your real URL here, or better yet, read it as a  //
	         // command-line arg, or read it from a file.                  //
	         //------------------------------------------------------------//

	         u = new URL("http://200.210.220.1:8080/index.html");

	         //----------------------------------------------//
	         // Step 3:  Open an input stream from the url.  //
	         //----------------------------------------------//

	         is = u.openStream();         // throws an IOException

	         //-------------------------------------------------------------//
	         // Step 4:                                                     //
	         //-------------------------------------------------------------//
	         // Convert the InputStream to a buffered DataInputStream.      //
	         // Buffering the stream makes the reading faster; the          //
	         // readLine() method of the DataInputStream makes the reading  //
	         // easier.                                                     //
	         //-------------------------------------------------------------//

	         dis = new DataInputStream(new BufferedInputStream(is));

	         //------------------------------------------------------------//
	         // Step 5:                                                    //
	         //------------------------------------------------------------//
	         // Now just read each record of the input stream, and print   //
	         // it out.  Note that it's assumed that this problem is run   //
	         // from a command-line, not from an application or applet.    //
	         //------------------------------------------------------------//

	         while ((s = dis.readLine()) != null) {
	            System.out.println(s);
	         }

	      } catch (MalformedURLException mue) {

	         System.out.println("Ouch - a MalformedURLException happened.");
	         mue.printStackTrace();
	         System.exit(1);

	      } catch (IOException ioe) {

	         System.out.println("Oops- an IOException happened.");
	         ioe.printStackTrace();
	         System.exit(1);

	      } finally {

	         //---------------------------------//
	         // Step 6:  Close the InputStream  //
	         //---------------------------------//

	         try {
	            is.close();
	         } catch (IOException ioe) {
	            // just going to ignore this one
	         }

	      } // end of 'finally' clause

	   
		
		/******************************************************************/
		
		
	}

}
