package cundi.edu.co.demo.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cundi.edu.co.demo.service.IAutorService;

@Component
public class AutorSchedule {

	@Autowired
	private IAutorService service;

	@Scheduled(fixedRate = 500000)
	public void scheduleTaskWithFixedRate() {
	    System.out.println("Tarea programada scheduleTaskWithFixedRate");
	}	

}