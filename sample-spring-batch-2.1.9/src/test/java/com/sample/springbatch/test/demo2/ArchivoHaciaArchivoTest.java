package com.sample.springbatch.test.demo2;

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
 * Este test se encarga de mostrar la ejecución del paquete "demo2". 
 * 
 * Esta demo realiza las siguientes actividades: 
 *   1) abre el archivo "planetas.csv" (separado por comas).
 *   2) por cada línea del archivo:
 *       2.1) transformar la línea a un objeto Planeta
 *       2.2) toma algunos datos del objeto Planeta
 *       2.3) guarda esos datos en el archivo planetas-resultado.txt
 * 
 * Este test necesita para funcionar que se encuentre configurada una base
 * de datos con las tablas de Spring Batch.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/jobs/demo2/spring-batch-demo.xml"
})
public class ArchivoHaciaArchivoTest {

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
        builder.addString("jobName", "Exportar planetas a archivo txt");
        JobParameters parameters = builder.toJobParameters();
        
        launcher.run(job, parameters);
    }
}
