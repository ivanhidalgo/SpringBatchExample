package com.example.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepListener implements StepExecutionListener {

	
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("StepExecutionListener - beforeStep");
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("StepExecutionListener - afterStep");
		System.out.println("Ivannnn");
		stepExecution.setStatus(BatchStatus.COMPLETED);
		System.out.println("stepExecution.getStatus(): "+stepExecution.getStatus());
		System.out.println("stepExecution.getStepName(): "+stepExecution.getStepName());
		try{
			Thread.sleep(5000);
			System.out.println("Finaliza la ejecucion del paso");
		}
		catch(Exception ex)
		{
			
		}
		
		return ExitStatus.COMPLETED;
	}

}