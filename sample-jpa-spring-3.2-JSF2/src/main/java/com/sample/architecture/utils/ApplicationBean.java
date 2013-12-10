package com.sample.architecture.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@ManagedBean(name = "ApplicationBean")
@RequestScoped
public class ApplicationBean {

    public String getColumnName(String column) {
        if (column == null || column.length() == 0) {
            return column;
        }
        final Pattern p = Pattern.compile("[A-Z][^A-Z]*");
        final Matcher m = p.matcher(Character.toUpperCase(column.charAt(0)) + column.substring(1));
        final StringBuilder builder = new StringBuilder();
        while (m.find()) {
            builder.append(m.group()).append(" ");
        }
        return builder.toString().trim();
    }

	private MenuModel menuModel;

	@PostConstruct
    public void init() {
  
    }

	public MenuModel getMenuModel() {
        return menuModel;
    }

	public String getAppName() {
        return "**SAMPLE**";
    }
}