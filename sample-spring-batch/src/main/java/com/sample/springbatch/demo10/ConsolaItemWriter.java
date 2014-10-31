/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.springbatch.demo10;

import java.util.Collection;
import java.util.List;
import org.springframework.batch.item.ItemWriter;

/**
 * 
 * @author ldeseta
 */
public class ConsolaItemWriter implements ItemWriter<Planeta> {

	@SuppressWarnings("unchecked")
	public void write(List<? extends Planeta> item) throws Exception {
		Collection<Planeta> col = (Collection<Planeta>) item;
		for (final Planeta planeta : col) {
			System.out.println("Planeta leido: " + planeta.getNombre());
		}
	}

}
