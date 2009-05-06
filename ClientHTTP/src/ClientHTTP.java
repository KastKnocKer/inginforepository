/*
 * Materiale didattico per corso sulle Applicazioni Internet 
 * 
 * Copyright (c) 2005-2008 Link.it srl (http://link.it). All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see .
 *
 */

/**
 * @author Lorenzo Nardi (nardi80@gmail.com)
 */


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClientHTTP {

    public final static void main(String[] args) throws Exception {
    	
	String value = URLEncoder.encode("Bed & Breakfast","UTF-8");
	String queryString = "http://www.google.it/search?q=" + value;
	
	HttpClient httpclient = new DefaultHttpClient();
    	 
	// Prepariamo la richiesta
	HttpGet httpget = new HttpGet(queryString); 
	System.out.println(httpget.getRequestLine());
	System.out.println("----------------------------------------------");
	 
	// Eseguiamo la richiesta e prendiamo la risposta
	HttpResponse response = httpclient.execute(httpget);
	 System.out.println("Love");
	// Stampiamo Status Line e Headers
	System.out.println(response.getStatusLine());
	Header[] headers = response.getAllHeaders();
	for(int i=0; i<headers.length; i++){
		System.out.println(headers[i].getName() + ": " + headers[i].getValue());
	}
	
	// Stampiamo il contenuto della risposta
	HttpEntity entity = response.getEntity();
	if (entity != null) {
		InputStream instream = entity.getContent();
		try {
			String s = null;
	        BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
	        while((s = reader.readLine()) != null)
    		System.out.println(s);
	     	} catch (Exception e) {
	         	httpget.abort();
	         	throw e;
	     	} finally {
	         	instream.close();
	     	}
	 	}
	}
}