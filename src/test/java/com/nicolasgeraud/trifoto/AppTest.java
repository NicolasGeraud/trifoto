package com.nicolasgeraud.trifoto;


import com.nicolasgeraud.trifoto.configuration.TriPhotoConfiguration;
import com.nicolasgeraud.trifoto.upload.UploadClientFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class AppTest {

	Path configPath = Paths.get("/home/nicolasger/Devel/triphoto/external-resources/configuration.yml");
	
	@Test
	public void testUpload() throws SocketException, IOException {
		UploadClientFactory.getClient().upload();
	}
	
	@Test
	public void testGetConfig() throws FileNotFoundException {
		
		TriPhotoConfiguration config = TriPhotoConfiguration.getConfig(configPath);
		
	}
                
	
	@Test
	public void testImageSorter() throws IOException {
		Path configPath = Paths.get("/home/nicolasger/Devel/triphoto/external-resources/configuration.yml");
		TriPhotoConfiguration config = TriPhotoConfiguration.getConfig(configPath);
		ImageSorter.process(
				Paths.get("/home/nicolasger/Devel/triphoto/src/test/resources/"), 
				Paths.get("/home/nicolasger/tmp"),
				true);
	}
}
