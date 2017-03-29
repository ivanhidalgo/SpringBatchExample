package com.example.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  public static void main(String[] args) {

	String springConfig = "spring/batch/config/job-report.xml";

	ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

  }
}