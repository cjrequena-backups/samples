package com.sample.springbatch.test.demo1;

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
 * Este test se encarga de mostrar la ejecución del paquete "demo1".
 * Esta demo realiza las siguientes actividades: 
 *   1) abre el archivo "planetas.csv" (separado por comas).
 *   2) por cada línea del archivo:
 *       2.1) transformar la línea a un objeto Planeta
 *       2.2) imprimir el objeto planeta por consola
 * 
 * Este test necesita para funcionar que se encuentre configurada una base
 * de datos con las tablas de Spring Batch.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/jobs/demo1/spring-batch-demo.xml"
})
public class ArchivoHaciaConsolaTest {

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
        builder.addString("jobName", "Imprimir planetas por consola");
        //builder.addString("input.file.name", "file:///home/carlos/Temporal/planetas.csv");

        JobParameters parameters = builder.toJobParameters();
        
        launcher.run(job, parameters);
    }
}
