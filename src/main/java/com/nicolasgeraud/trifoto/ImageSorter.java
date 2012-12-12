/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto;

import com.nicolasgeraud.trifoto.utils.ImageAnalyzer;
import com.drew.imaging.ImageProcessingException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nicolasger
 */
public class ImageSorter {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageSorter.class);
	
	public static void process(final Path fromPath, final Path toPath, final boolean ignoreDuplicate) throws IOException {
		
		if(!Files.isDirectory(fromPath, LinkOption.NOFOLLOW_LINKS)) {
			throw new IllegalArgumentException("le chemin source doit être un répertoire existant");
		}
		
		if(!Files.isDirectory(toPath, LinkOption.NOFOLLOW_LINKS)) {
			throw new IllegalArgumentException("le chemin de destination doit être un répertoire existant");
		}
		
		
		Files.walkFileTree(fromPath, new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				try {
					if(Files.probeContentType(file).contains("image")) {
						String newBasename = ImageAnalyzer.getDayTime(file);
						String extension = ImageAnalyzer.getExtension(file);
						String year = ImageAnalyzer.getYear(file);
						String month = ImageAnalyzer.getMonth(file);

						//create destination folder and duplicate folder
						Path newDirectory = Paths.get(toPath.toString(), year, month);
						if(!Files.isDirectory(newDirectory, LinkOption.NOFOLLOW_LINKS)) {
							Files.createDirectory(newDirectory);
						}

						int attempt=0;
						while(true) {
							if(attempt>0 && ignoreDuplicate) {
								logger.info("Ignore duplicate files");
								break;
							}
							String newName = formatName(newBasename, extension, attempt);
							Path newFile = Paths.get(newDirectory.toString(), newName);
							try {
								Files.copy(file, newFile, StandardCopyOption.COPY_ATTRIBUTES);
								break;
							} catch(FileAlreadyExistsException ex) {
								logger.info("File {} already exist.", newName);
								attempt ++;
							}
						}
					} else {
						logger.warn("\n Ignoring file {} \n cause : not an image.", file.toFile().getAbsolutePath());
					}
					
					return FileVisitResult.CONTINUE;
				} catch (ImageProcessingException ex) {
					logger.error(ex.getMessage());
					throw new IOException(ex);
				}		
			}
		});
	}
	
	private static String formatName(String baseName, String extension, int attempt) {
		StringBuilder sb = new StringBuilder(baseName);
		if(attempt>0) sb.append(" (").append(attempt).append(")");
		sb.append(".").append(extension);
		
		return sb.toString();
	}
}