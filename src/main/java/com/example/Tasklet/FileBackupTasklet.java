/**
 * FileBackupTasklet.java
 *
 * @description: Task to save the processed files.
 * @author Ivan Hidalgo.
 * @version 1.0
 * @date 10-03-2017.
 *
 **/

package com.example.Tasklet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.example.helpers.FileHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Task to save the processed files.
 * @author ihidalgo 
 * 
 */
public class FileBackupTasklet implements Tasklet, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(FileBackupTasklet.class);
	private Resource sourceDirectory;
	private String destDirectory;
	private String dateFormatPattern;
	private DateTimeFormatter dateTimeFormatter;
	private String fileNamePattern;
	private FileHelper fileHelper;
	private static final String BACKUP_EXT = ".bck";
	
	public void afterPropertiesSet() throws Exception {
		
		Assert.notNull(sourceDirectory, "sourceDirectory attribute cannot be null");
		Assert.notNull(destDirectory, "destDirectory attribute cannot be null");
		Assert.notNull(dateFormatPattern, "dateFormatPattern attribute cannot be null");
		Assert.notNull(fileNamePattern, "fileNamePattern attribute cannot be null");
		
	}

	/**
	 * Executes the File Backup Step. 
	 */
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		logger.info("FileBackupTaskled started!!");
		
		LocalDateTime dateNow = LocalDateTime.now();
		dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatPattern);
		
		try
		{
			File dir = sourceDirectory.getFile();
			File[] files = fileHelper.getFilteredFilesFromDirectory(dir);
			
			for (int i = 0; i < files.length; i++) {
				
				logger.debug("Old File: "+ files[i].getPath());
				File newfile = new File( BuildFileName(dateNow, files[i].getName()));
				logger.debug("New File: "+ newfile.getPath());
				copyFile(files[i], newfile);
				deleteFile(files[i]);
			}
		}
		catch(IOException ex){
			throw new UnexpectedJobExecutionException("FileBackupTask Error: " + ex);
		}
		
		logger.info("FileBackupTaskled finished!!");
		
		return RepeatStatus.FINISHED;
	}

	/**
	 * Calls to delete operation from fileHelper class. 
	 * @param file
	 * @throws IOException
	 */
	private void deleteFile(File file) throws IOException {
		fileHelper.deleteFile(file);
	}

	/**
	 * Builds new file name.
	 * @param dateNow
	 * @param fileName
	 * @return new file name.
	 */
	private String BuildFileName(LocalDateTime dateNow, String fileName) {
		return destDirectory + fileName + "." + dateTimeFormatter.format(dateNow) + BACKUP_EXT;
	}
	
	/**
	 * Gets the source directorys files. 
	 * @return directory files. 
	 */
	public Resource getSourceDirectory() {
		return sourceDirectory;
	}
	
	/**
	 * Sets the source directory. 
	 * @param sourceDirectory
	 */
	public void setSourceDirectory(Resource sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}
	 
	/**
	 * Calls to save operation from fileHelper class.  
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private void copyFile(File source, File dest) throws IOException {
		  
		fileHelper.copyFile(source, dest);
	}

	/**
	 * @return the destination directory.
	 */
	public String getDestDirectory() {
		return destDirectory;
	}

	/**
	 * @param destDirectory the destination directory to set.
	 */
	public void setDestDirectory(String destDirectory) {
		this.destDirectory = destDirectory;
	}

	/**
	 * @return the dateFormatPattern.
	 */
	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	/**
	 * @param dateFormatPattern the dateFormatPattern to set.
	 */
	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	/**
	 * @return the fileNamePattern.
	 */
	public String getFileNamePattern() {
		return fileNamePattern;
	}

	/**
	 * @param fileNamePattern the fileNamePattern to set.
	 */
	public void setFileNamePattern(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}

	/**
	 * @return the fileHelper.
	 */
	public FileHelper getFileHelper() {
		return fileHelper;
	}

	/**
	 * @param fileHelper the fileHelper to set.
	 */
	public void setFileHelper(FileHelper fileHelper) {
		this.fileHelper = fileHelper;
	}


}
