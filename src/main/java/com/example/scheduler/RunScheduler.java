package com.example.scheduler;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunScheduler {

  private JobLauncher jobLauncher;

  
  private Job job;

  public void run() {

    try {

	String dateParam = new Date().toString();
	System.out.println("Inicia la ejecucion : " + dateParam);
	JobParameters param =
	  new JobParametersBuilder().addString("date", dateParam).toJobParameters();

	System.out.println(dateParam);
	
	if(getJob()==null)
		System.out.println("El Job es null");

	JobExecution execution = getJobLauncher().run(getJob(), param);
	System.out.println("Exit Status : " + execution.getStatus());

    } catch (Exception e) {
    	e.printStackTrace();
    }

  }

/**
 * @return the job
 */
public Job getJob() {
	return job;
}

/**
 * @param job the job to set
 */
public void setJob(Job job) {
	this.job = job;
}

/**
 * @return the jobLauncher
 */
public JobLauncher getJobLauncher() {
	return jobLauncher;
}

/**
 * @param jobLauncher the jobLauncher to set
 */
public void setJobLauncher(JobLauncher jobLauncher) {
	this.jobLauncher = jobLauncher;
}
}
