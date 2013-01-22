package com.nicolasgeraud.trifoto;


import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.nicolasgeraud.trifoto.configuration.TriPhotoConfiguration;
import com.nicolasgeraud.trifoto.upload.UploadClientFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import javax.xml.transform.sax.SAXSource;

public class AppTest {

	//@Test
	public void testUpload() throws SocketException, IOException {
		UploadClientFactory.getClient().upload();
	}
	
	//@Test
	public void testGetConfig() throws FileNotFoundException {
        Path configPath = Paths.get("/home/nicolasger/Devel/triphoto/external-resources/configuration.yml");
		TriPhotoConfiguration config = TriPhotoConfiguration.getConfig(configPath);
	}
                
	
	@Test
	public void testImageSorter() throws IOException {
		ImageSorter.process(
				Paths.get("/home/nicolasger/tmp/trifotoSrc"),
				Paths.get("/home/nicolasger/tmp/trifotoDest"));
	}

}
