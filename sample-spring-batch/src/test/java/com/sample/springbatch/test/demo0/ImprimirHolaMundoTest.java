package com.sample.springbatch.test.demo0;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Este test se encarga de mostrar el funcionamiento básico de Spring Batch
 * utilizando un tasklet para imprimir por pantalla un mensaje. 
 * 
 * El mensaje se imprime en 3 pasos (steps). 
 * 
 * A diferencia del resto de las demos, este test no necesita ni utiliza una
 * base de datos.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath*:/jobs/demo0/spring-batch-demo.xml"
})
public class ImprimirHolaMundoTest {

    /** Este objeto es el encargado de lanzar una tarea */
    @Autowired
    private SimpleJobLauncher launcher;
    
    /** La tarea a ejecutar. */
    @Autowired
    private Job job;

    @Test
    public void iniciarJob() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("Ejecucion", new Date());
        builder.addString("jobName", "Imprimir hola mundo por consola");
        JobParameters parameters = builder.toJobParameters();
        
        launcher.run(job, parameters);
    }
}
