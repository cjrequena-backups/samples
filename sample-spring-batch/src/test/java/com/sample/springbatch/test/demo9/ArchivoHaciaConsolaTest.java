package com.sample.springbatch.test.demo9;

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
 * Este test se encarga de mostrar la ejecución del paquete "demo9".
 * Esta demo realiza las siguientes actividades: 
 *   1) abre el archivo "planetas.csv".
 *   2) Lee la cabecera del archivo. 
 *      En ella vendrá la cantidad de registros y la descripción de los mismos.
 *      Con los datos de la cabecera se crea un objeto de la clase CabeceraArchivo que
 *      se guarda en el contexto del Step, para que pueda ser obtenido por el writer.
 *   3) Lee cada línea del archivo:
 *       3.1) transformar la línea a un objeto Planeta
 *   4) Escribe por consola el detalle de cada registro:
 *      4.1) Obtiene los datos de la cabecera del contexto del Step.
 *      4.2) Escribe por consola los datos de la cabcera junto con la descripcion del planeta.

 * 
 * Este test necesita para funcionar que se encuentre configurada una base
 * de datos con las tablas de Spring Batch.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/jobs/demo9/spring-batch-demo.xml"
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
        JobParameters parameters = builder.toJobParameters();
        
        launcher.run(job, parameters);
    }
}
