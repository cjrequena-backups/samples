/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.springbatch.test.demo7;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.batch.core.JobParameters;

/**
 * Este test muestra la ejecución del paquete "demo7". El
 * ejemplo trata sobre la lectura de un archivo y la impresión 
 * de su contenido en pantalla usando hilos concurrentes.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/jobs/demo7/spring-batch-demo.xml"
})
public class EjecucionMultithreadTest {

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
        builder.addString("jobName", "Imprimir planetas con hilos concurrentes");
        JobParameters parameters = builder.toJobParameters();
        
        launcher.run(job, parameters);
    }
}
