package Connessioni;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

import java.io.File;
import java.io.IOException;

public class ConnessioneFTP {
	
	private static FTPClient ftpClient;
	
	public ConnessioneFTP(){
		FTPClient ftpClient = new FTPClient();
		this.ftpClient=ftpClient;
	}
	
	public static boolean Connect(){
		try {
			ftpClient.connect("ftp.util.altervista.org");
			ftpClient.login("util", "speranza");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isConnected(){
		System.out.println("Connesso :O");
		return ftpClient.isConnected();
	}
	
	public static void Disconnect(){
		try {
			ftpClient.disconnect(true);
		} catch (Exception e) {
		
		}
	}
	
	public static void getDir(){
		try {
			FTPFile[] listina = ftpClient.list();
			int i = 0;
			boolean love = true;
			while(love){
				try{
					System.out.println(ftpClient.serverStatus().toString()+" "+listina[i].getType());
					i++;
				}catch(Exception e){
					love = false;
				}
				
			}
		} catch (Exception e) {
			
		}
	}
	
	/*
	 * FTPClient ftpClient = new FTPClient();
		File file = new File("A.jpg");
		try{
		ftpClient.connect("ftp.util.altervista.org");
		ftpClient.login("util", "speranza");
		if(ftpClient.isConnected())System.out.println("Connesso :O");
		
		ftpClient.upload( file , new MyTransferListener() );
		
		ftpClient.disconnect(true);
		}catch(Exception e){
			
		}
	*/
	
	
}
