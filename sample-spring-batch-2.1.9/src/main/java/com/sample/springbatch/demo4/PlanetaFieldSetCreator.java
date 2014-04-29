/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.springbatch.demo4;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * Esta clase convierte un planeta a un FieldSet, la interfaz que necesita
 * Spring Batch para representar datos.
 * 
 * Esta clase recibe un planeta y los devuelve como un FieldSet. 
 */
public class PlanetaFieldSetCreator implements FieldSetMapper {

    public Object mapFieldSet(FieldSet data) {
                Planeta planeta = (Planeta) data;

        String[] valores = new String[5];
        valores[0] = String.valueOf(planeta.getCodigo());
        valores[1] = planeta.getNombre();
        valores[2] = String.valueOf(planeta.getDiametro());
        valores[3] = planeta.getTipo();
        valores[4] = planeta.getSignificado();

        return new DefaultFieldSet(valores);
    }
    
}
