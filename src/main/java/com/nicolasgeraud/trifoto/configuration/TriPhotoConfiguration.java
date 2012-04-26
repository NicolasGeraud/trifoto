/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto.configuration;

import java.io.*;
import java.nio.file.Path;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author nicolasger
 */
public class TriPhotoConfiguration {

	private String name;
	private Boolean useTmpDir;
	private String tmpDir;
	private Boolean putDuplicateToOneSide;
	private String duplicateDirName;
	private UploadClientConfig uploadClientConfig;

	public static TriPhotoConfiguration getConfig(Path configPath) throws FileNotFoundException {
		Yaml yaml = new Yaml(new Constructor(TriPhotoConfiguration.class));
		return (TriPhotoConfiguration)yaml.load(new FileInputStream(configPath.toFile()));
	}

	public String getDuplicateDirName() {
		return duplicateDirName;
	}

	public void setDuplicateDirName(String duplicateDirName) {
		this.duplicateDirName = duplicateDirName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPutDuplicateToOneSide() {
		return putDuplicateToOneSide;
	}

	public void setPutDuplicateToOneSide(Boolean putDuplicateToOneSide) {
		this.putDuplicateToOneSide = putDuplicateToOneSide;
	}

	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
	}

	public Boolean getUseTmpDir() {
		return useTmpDir;
	}

	public void setUseTmpDir(Boolean useTmpDir) {
		this.useTmpDir = useTmpDir;
	}

	public UploadClientConfig getUploadClientConfig() {
		return uploadClientConfig;
	}

	public void setUploadClientConfig(UploadClientConfig uploadClientConfig) {
		this.uploadClientConfig = uploadClientConfig;
	}
	
}
