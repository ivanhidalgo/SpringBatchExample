/**
 * FileHelper.java
 *
 * @description: Helper to help with the file's operations.
 * @author Ivan Hidalgo.
 * @version 1.0
 * @date 10-03-2017.
 *
 **/

package com.example.helpers;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.util.Assert;

import com.example.Tasklet.FileBackupTasklet;

/**
 * Helper helps with the file's operations.
 * @author ihidalgo
 *
 */
public class FileHelper {
	
	private Logger logger = LoggerFactory.getLogger(FileBackupTasklet.class);
	private String fileNamePattern;
	
	/**
	 * Gets files list from a directory. 
	 * @param directory
	 * @return file list.
	 */
	public synchronized File[] getFilesFromDirectory(File directory)throws IOException {
		
		if(!directory.isDirectory()){
			
			logger.error("The directory "+ directory.getAbsolutePath() +" not found!!");
			throw new java.io.IOException("The directory "+ directory.getAbsolutePath() +" not found!!");
		}
		return directory.listFiles();
	}
	
	/**
	 * Gets files list from a directory with a filter. 
	 * @param directory
	 * @return
	 * @throws IOException
	 */
	public synchronized File[] getFilteredFilesFromDirectory(File directory)throws IOException {
		
		Assert.notNull(fileNamePattern);
		
		if(!directory.isDirectory()){
			
			logger.error("The directory "+ directory.getAbsolutePath() +" not found!!");
			throw new java.io.IOException("The directory "+ directory.getAbsolutePath() +" not found!!");
		}
		
		File[] files = directory.listFiles(new FilenameFilter() {
		    public boolean accept(File directory, String name) {
		        return name.toLowerCase().endsWith(fileNamePattern);
		    }
		});
		logger.debug("files Number to copy: "+ files.length);
		
		return files;
	}
	
	/**
	 * Copies a file from source path to destination path.
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public synchronized void copyFile(File source, File dest) throws IOException {
		  
		  FileUtils.copyFile(source, dest);
	}
	
	/**
	 * Deletes any file.
	 * @param source
	 * @throws IOException
	 */
	public synchronized void deleteFile(File source) throws IOException {
		  
		boolean deleted = FileUtils.deleteQuietly(source);
		if (!deleted) {
			throw new UnexpectedJobExecutionException(
	                   "Could not delete file " + source.getPath());
	    } else {
	        System.out.println(source.getPath() + " was deleted!");
	    }
	}

	/**
	 * @return the fileNamePattern
	 */
	public String getFileNamePattern() {
		return fileNamePattern;
	}

	/**
	 * @param fileNamePattern the fileNamePattern to set
	 */
	public void setFileNamePattern(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}
}
