/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.*;
import com.nicolasgeraud.trifoto.utils.ImageAnalyzer;

import com.drew.imaging.ImageProcessingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nicolasger
 */
public class ImageSorter {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageSorter.class);

	public static void process(final Path fromPath, final Path toPath) throws IOException {
		
		if(!Files.isDirectory(fromPath, LinkOption.NOFOLLOW_LINKS)) {
			throw new IllegalArgumentException("le chemin source doit être un répertoire existant");
		}
		
		if(!Files.isDirectory(toPath, LinkOption.NOFOLLOW_LINKS)) {
			throw new IllegalArgumentException("le chemin de destination doit être un répertoire existant");
		}

        final Path trash = Paths.get(toPath.toString(), "Trash");
        if(!Files.isDirectory(trash, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(trash);
        }

		Files.walkFileTree(fromPath, new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult visitFile(Path srcfile, BasicFileAttributes attrs) throws IOException {
				try {
					if(Files.probeContentType(srcfile)!=null && Files.probeContentType(srcfile).contains("image")) {
						String newBasename = ImageAnalyzer.getDayTime(srcfile);
						String extension = ImageAnalyzer.getExtension(srcfile);
						String year = ImageAnalyzer.getYear(srcfile);
						String month = ImageAnalyzer.getMonth(srcfile);

						//create destination folder and duplicate folder
						Path newDirectory = Paths.get(toPath.toString(), year, month);
						if(!Files.isDirectory(newDirectory, LinkOption.NOFOLLOW_LINKS)) {
							Files.createDirectories(newDirectory);
						}

						int attempt=0;
                        while(true) {
							String newName = formatName(newBasename, extension, attempt);
							Path newFile = Paths.get(newDirectory.toString(), newName);
                            if(newFile.toFile().exists()) {
                                HashCode srcMD5 = com.google.common.io.Files.hash(srcfile.toFile(), Hashing.md5());
                                HashCode newMD5 = com.google.common.io.Files.hash(newFile.toFile(), Hashing.md5());
                                if(srcMD5.equals(newMD5)) {
                                    //same files
                                    break;
                                } else {
                                    attempt ++;
                                }
                            } else {
                                Files.copy(srcfile, newFile, StandardCopyOption.COPY_ATTRIBUTES);
                                break;
                            }
						}
					} else {
						//not an image, move to Trash
                        Path trashCopy = Paths.get(trash.toString(),System.nanoTime()+"-"+srcfile.getName(srcfile.getNameCount()-1));
                        Files.copy(srcfile,trashCopy,StandardCopyOption.COPY_ATTRIBUTES);
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
		sb.append("-")
                .append(attempt)
		        .append(".")
                .append(extension);
		
		return sb.toString();
	}
}