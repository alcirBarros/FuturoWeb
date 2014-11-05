package br.com.selfSystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Iniciando agendador de terefas.");
            this.tarefas();
        } catch (ObjectAlreadyExistsException ex) {
            Logger.getLogger(QuartzListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Tarefa.sched.shutdown(true);
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tarefas() throws ObjectAlreadyExistsException {
        try {
             /* Fábrica para criar instância de Scheduler */
            StdSchedulerFactory schedFactory = new StdSchedulerFactory();
            Tarefa.sched = schedFactory.getScheduler();
            
            
            JobDetail job = JobBuilder.newJob(Tarefa.class).withIdentity("tarefaAloMundo", "group1").build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("gatilhoAloMundo", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0-59 0-59 0-23 ? 1-12 1-7 1970-2099"))
                    .build();

            // Agenda e voa lá!
            //Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            Tarefa.sched.start();
            Tarefa.sched.scheduleJob(job, trigger);
            
            
            
            
            
            
            

//
//            getLog().info("---------------- Criar Agendador ----------------");
//            Agenda.sched = schedFactory.getScheduler();
//
//
//            /* ************************Inicio Job Hora *****************************/
//            getLog().info("---------------- Criando Tarefa: JobHora para ser executada a cada 60 minutos ----------------");
//            JobDetail jobHora = new JobDetail("JobIntegraBysoft", Scheduler.DEFAULT_GROUP, JobHora.class);
//
//            getLog().info("---------------- Criar Gatilho Job Hora  ----------------");
//            SimpleTrigger sptJobHora = new SimpleTrigger(
//                    "JobHora", sched.DEFAULT_GROUP, new Date(), null,
//                    SimpleTrigger.REPEAT_INDEFINITELY, 3600L * 1000L);
//
//            getLog().info("---------------- Faz o Agendamento de JobHora ----------------");
//            java.util.Date ft = sched.scheduleJob(jobHora, sptJobHora);
//
//            /* Imprime informações do Job */
//            getLog().info(jobHora.getFullName() + " Foi programado para funcionar em: "
//                    + ft + " e repete baseado na expressão: ");
//            /* ************************ Fim Job Hora *******************************/
//
//
//            /* ************************ Inicio Job Dia *****************************/
//            getLog().info("---------------- Criando tarefa: JobDia para ser executado todos os dias as 22 horas ----------------");
//            JobDetail JobDia = new JobDetail("JobDia", Scheduler.DEFAULT_GROUP,
//                    JobDia.class);
//
//            getLog().info("---------------- Criar Gatilho Para JobDia  ----------------");
//            CronTrigger ctgJobDia = new CronTrigger("JobDia", sched.DEFAULT_GROUP);
//            /* setCronExpression(* Segundos,* Minutos,* Horas,* Dias do mês,* Mês,* Dias da semana) */
//            ctgJobDia.setCronExpression("0 00 22 * * ?");
//
//            getLog().info("---------------- Faz Agendamento de JobDia ----------------");
//            java.util.Date dataAniversariosDoDia = sched.scheduleJob(JobDia, ctgJobDia);
//
//            /* Imprime informações do Job */
//            getLog().info(JobDia.getFullName() + " Foi programado para funcionar em: "
//                    + dataAniversariosDoDia + " e repete baseado na expressão: ");
//            /* ************************ Fim Job Dia **************************** */
//
//
//            /* Iniciar execução do Scheduler */
//            Agenda.sched.start();
        } catch (Exception e) {
            System.out.println("Erro ao tentar iniciar Scheduler.");
            e.printStackTrace();
        }
    }

}


class Tarefa implements Job {
    
    public static Scheduler sched;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataHora = format.format(new Date());
        System.out.println(dataHora);
    }

}
