/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto.upload;

import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author nicolasger
 */
public interface UploadClient {

	void upload() throws SocketException, IOException;
	
}
