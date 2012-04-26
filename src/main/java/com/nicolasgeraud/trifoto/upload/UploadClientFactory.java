/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto.upload;

/**
 *
 * @author nicolasger
 */
public class UploadClientFactory {
	
	public static UploadClient getClient() {
		return new UploadClientFtp();
	}
}
