/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.springbatch.test.demo8;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Este test se encarga de mostrar la ejecución del paquete "demo8".
 *
 * Esta demo realiza las siguientes actividades:
 *   1) ejecuta un query en la base de datos contra la tabla PLANETA.
 *   2) por cada fila del query:
 *       2.1) transformar la fila a un objeto Planeta
 *       2.2) modifica los campos "nombre" y "significado" del objeto Planeta
 *            pasandolos a mayusculas (el procesamiento)
 *       2.2) imprimir los campos modificados (nombre y significado).
 *
 * Este test necesita para funcionar que se encuentre configurada una base
 * de datos con las tablas de Spring Batch.
 *
 * Además se necesita tener creada la tabla PLANETA; el script de esta tabla y
 * datos de ejemplo se encuentran en "script.sql" en el paquete demo4. *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/jobs/demo8/spring-batch-demo.xml"
})
public class ProcesamientoDeDatosTest {

    /** Este objeto es el encargado de lanzar una tarea */
    @Autowired
    private SimpleJobLauncher launcher;

    /** La tarea a ejecutar. */
    @Autowired
    private Job job;

    @Test
    public void iniciarJob() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("fecha", new Date());
        JobParameters parameters = builder.toJobParameters();
        
        launcher.run(job, parameters);
    }

}