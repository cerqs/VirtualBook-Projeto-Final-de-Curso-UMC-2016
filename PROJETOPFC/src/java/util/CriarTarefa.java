/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.apache.log4j.BasicConfigurator;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author 11131103404
 */
public class CriarTarefa {
    
    
    
    public void criandoUmaTarefaImportarBoleto() {
        try {
            BasicConfigurator.configure();
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail job = JobBuilder.newJob(TarefaImportacaoBoleto.class)
                .withIdentity("myJob", "group3")
                .build();
            Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group3")
                .withSchedule(CronScheduleBuilder.cronSchedule("1 * * * * ?"))
                .build(); 
             sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            System.out.println("erro");
            e.printStackTrace();
        }
    }
    
    
    
    
     public void criandoUmaTarefaPagamento() {
        try {
            //BasicConfigurator.configure();
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail job = JobBuilder.newJob(TarefaLiberacaoDeConteudo.class)
                .withIdentity("myJob", "group1")
                .build();
            Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("40 * * * * ?"))
                .build();
             sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            System.out.println("erro");
            e.printStackTrace();
        }
    }
     
          public void criandoUmaTarefaCancelarPedido() {
        try {
            BasicConfigurator.configure();
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail job = JobBuilder.newJob(TarefaCancelarPedido.class)
                .withIdentity("myJob", "group2")
                .build();
            Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("45 * * * * ?"))
                .build();            
            //alterar para ("0 59 23 * * ?") para que possa ser executado uma vez no dia a meia noite
             sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            System.out.println("erro");
            e.printStackTrace();
        }
    }
          
          
          
    
}
