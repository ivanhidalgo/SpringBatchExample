package com.example.Tasklet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.remote.synchronizer.AbstractInboundFileSynchronizer;
import org.springframework.integration.ftp.filters.FtpSimplePatternFileListFilter;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizer;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.util.Assert;

public class FtpGetRemoteFilesTasklet implements Tasklet, InitializingBean
{
    private Logger logger = LoggerFactory.getLogger(FtpGetRemoteFilesTasklet.class);

    private File localDirectory;

    private AbstractInboundFileSynchronizer<?> ftpInboundFileSynchronizer;

    private SessionFactory sessionFactory;

    private boolean autoCreateLocalDirectory = true;

    private boolean deleteLocalFiles = true;
    
    private boolean deleteRemoteFiles = true;

    private String fileNamePattern;

    private String remoteDirectory;

    private int downloadFileAttempts = 12;

    private long retryIntervalMilliseconds = 300000;

    private boolean retryIfNotFound = false;


    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull(sessionFactory, "sessionFactory attribute cannot be null");
        Assert.notNull(localDirectory, "localDirectory attribute cannot be null");
        Assert.notNull(remoteDirectory, "remoteDirectory attribute cannot be null");
        Assert.notNull(fileNamePattern, "fileNamePattern attribute cannot be null");
      
        setupFileSynchronizer();

        if (!this.localDirectory.exists())
        {
            if (this.autoCreateLocalDirectory)
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("The '" + this.localDirectory + "' directory doesn't exist; Will create.");
                }
                this.localDirectory.mkdirs();
            }
            else
            {
                throw new FileNotFoundException(this.localDirectory.getName());
            }
        }
    }

    private void setupFileSynchronizer()
    {
    	if (isSftp())
        {
            ftpInboundFileSynchronizer = new SftpInboundFileSynchronizer(sessionFactory);
            ((SftpInboundFileSynchronizer) ftpInboundFileSynchronizer).setFilter(new SftpSimplePatternFileListFilter(fileNamePattern));
        }
        else
        {
            ftpInboundFileSynchronizer = new FtpInboundFileSynchronizer(sessionFactory);
            ((FtpInboundFileSynchronizer) ftpInboundFileSynchronizer).setFilter(new FtpSimplePatternFileListFilter(fileNamePattern));
        }
        ftpInboundFileSynchronizer.setRemoteDirectory(remoteDirectory);
        ftpInboundFileSynchronizer.setDeleteRemoteFiles(deleteRemoteFiles);
    }
    
    private void deleteLocalFiles()
    {
        if (deleteLocalFiles)
        {
            SimplePatternFileListFilter filter = new SimplePatternFileListFilter(fileNamePattern);
            List<File> matchingFiles = filter.filterFiles(localDirectory.listFiles());
            if (CollectionUtils.isNotEmpty(matchingFiles))
            {
                for (File file : matchingFiles)
                {
                    FileUtils.deleteQuietly(file);
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
     */
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
    	deleteLocalFiles();

        ftpInboundFileSynchronizer.synchronizeToLocalDirectory(localDirectory);

        if (retryIfNotFound)
        {
            SimplePatternFileListFilter filter = new SimplePatternFileListFilter(fileNamePattern);
            int attemptCount = 1;
            while (filter.filterFiles(localDirectory.listFiles()).size() == 0 && attemptCount <= downloadFileAttempts)
            {
                logger.info("File(s) matching " + fileNamePattern + " not found on remote site.  Attempt " + attemptCount + " out of " + downloadFileAttempts);
                Thread.sleep(retryIntervalMilliseconds);
                ftpInboundFileSynchronizer.synchronizeToLocalDirectory(localDirectory);
                attemptCount++;
            }

            if (attemptCount >= downloadFileAttempts && filter.filterFiles(localDirectory.listFiles()).size() == 0)
            {
                System.out.println("Could not find remote file(s) matching " + fileNamePattern + " after " + downloadFileAttempts + " attempts.");
            }
        }

       return RepeatStatus.FINISHED;
    }

    /**
     * @return the downloadFileAttempts
     */
    public int getDownloadFileAttempts()
    {
        return downloadFileAttempts;
    }

    /**
     * @return the fileNamePattern
     */
    public String getFileNamePattern()
    {
        return fileNamePattern;
    }

    /**
     * @return the localDirectory
     */
    public File getLocalDirectory()
    {
        return localDirectory;
    }

    /**
     * @return the remoteDirectory
     */
    public String getRemoteDirectory()
    {
        return remoteDirectory;
    }

    /**
     * @return the retryIntervalMilliseconds
     */
    public long getRetryIntervalMilliseconds()
    {
        return retryIntervalMilliseconds;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * @return the autoCreateLocalDirectory
     */
    public boolean isAutoCreateLocalDirectory()
    {
        return autoCreateLocalDirectory;
    }

    /**
     * @return the deleteLocalFiles
     */
    public boolean isDeleteLocalFiles()
    {
        return deleteLocalFiles;
    }


    /**
     * @return the retryIfNotFound
     */
    public boolean isRetryIfNotFound()
    {
        return retryIfNotFound;
    }

    /**
     * @return the sftp
     */
    public boolean isSftp()
    {
        return sessionFactory instanceof DefaultSftpSessionFactory;
    }

    /**
     * @param autoCreateLocalDirectory
     *            the autoCreateLocalDirectory to set
     */
    public void setAutoCreateLocalDirectory(boolean autoCreateLocalDirectory)
    {
        this.autoCreateLocalDirectory = autoCreateLocalDirectory;
    }

    /**
     * @param deleteLocalFiles
     *            the deleteLocalFiles to set
     */
    public void setDeleteLocalFiles(boolean deleteLocalFiles)
    {
        this.deleteLocalFiles = deleteLocalFiles;
    }

    /**
     * @param downloadFileAttempts
     *            the downloadFileAttempts to set
     */
    public void setDownloadFileAttempts(int downloadFileAttempts)
    {
        this.downloadFileAttempts = downloadFileAttempts;
    }

    /**
     * @param fileNamePattern
     *            the fileNamePattern to set
     */
    public void setFileNamePattern(String fileNamePattern)
    {
        this.fileNamePattern = fileNamePattern;
    }

    /**
     * @param localDirectory
     *            the localDirectory to set
     */
    public void setLocalDirectory(File localDirectory)
    {
        this.localDirectory = localDirectory;
    }

    /**
     * @param remoteDirectory
     *            the remoteDirectory to set
     */
    public void setRemoteDirectory(String remoteDirectory)
    {
        this.remoteDirectory = remoteDirectory;
    }

    /**
     * @param retryIfNotFound
     *            the retryIfNotFound to set
     */
    public void setRetryIfNotFound(boolean retryIfNotFound)
    {
        this.retryIfNotFound = retryIfNotFound;
    }

    /**
     * @param retryIntervalMilliseconds
     *            the retryIntervalMilliseconds to set
     */
    public void setRetryIntervalMilliseconds(long retryIntervalMilliseconds)
    {
        this.retryIntervalMilliseconds = retryIntervalMilliseconds;
    }

    /**
     * @param sessionFactory
     *            the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

	/**
	 * @return the deleteRemoteFiles
	 */
	public boolean getDeleteRemoteFiles() {
		return deleteRemoteFiles;
	}

	/**
	 * @param deleteRemoteFiles the deleteRemoteFiles to set
	 */
	public void setDeleteRemoteFiles(boolean deleteRemoteFiles) {
		this.deleteRemoteFiles = deleteRemoteFiles;
	}
}
