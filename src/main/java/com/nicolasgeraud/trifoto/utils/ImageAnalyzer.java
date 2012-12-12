package com.nicolasgeraud.trifoto.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;

public class ImageAnalyzer {

	private static SimpleDateFormat sdfDayTime = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.FRANCE);
	private static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy", Locale.FRANCE);
	private static SimpleDateFormat sdfMonth = new SimpleDateFormat("MM", Locale.FRANCE);

	public static String getDayTime(Path image) throws ImageProcessingException, IOException {
		Metadata metadata = ImageMetadataReader.readMetadata(image.toFile());
		Directory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
		StringBuilder sb = new StringBuilder()
				.append(sdfDayTime.format(directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)))
				.append("_")
				.append(FilenameUtils.getBaseName(image.getFileName().toString()));
		return sb.toString();
	}
	
	public static String getExtension(Path image) {
		return FilenameUtils.getExtension(image.getFileName().toString());
	}
	
	public static String getYear(Path image) throws ImageProcessingException, IOException {
		Metadata metadata = ImageMetadataReader.readMetadata(image.toFile());
		Directory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
		return sdfYear.format(directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
	}
        public static String getMonth(Path image) throws ImageProcessingException, IOException {
		Metadata metadata = ImageMetadataReader.readMetadata(image.toFile());
		Directory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
		return sdfMonth.format(directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
	}
}