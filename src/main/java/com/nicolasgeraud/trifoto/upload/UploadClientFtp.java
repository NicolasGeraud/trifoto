/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto.upload;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nicolasger
 */
public class UploadClientFtp implements UploadClient {
	
	private final static Logger logger = LoggerFactory.getLogger(UploadClientFtp.class);

	@Override
	public void upload() throws SocketException, IOException {

		boolean error = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect("192.168.0.5");
			ftp.login("user", "user");
			logger.debug(ftp.getReplyString());

			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				logger.error("FTP server refused connection.");
				System.exit(1);
			}
			FTPFile[] listDirectories = ftp.listDirectories();
			for(FTPFile dir : listDirectories) {
				logger.debug(dir.getName());
			}
			
			
			ftp.changeWorkingDirectory("photo");
			listDirectories = ftp.listDirectories();
			for(FTPFile dir : listDirectories) {
				logger.debug(dir.getName());
			}
			
			ftp.makeDirectory("toto");
			ftp.changeWorkingDirectory("Photo");
			listDirectories = ftp.listDirectories();
			for(FTPFile dir : listDirectories) {
				logger.debug(dir.getName());
			}
			
			
			
			
			
			
			ftp.logout();
		} catch (IOException e) {
			error = true;
			logger.error(e.getMessage(), e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
		}
	}
}
