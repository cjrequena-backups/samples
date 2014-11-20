/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.controller;


public interface IControllerTx<T> {

	public String onCreate() throws Exception;

	public String onEdit() throws Exception;

	public String persist() throws Exception;

	public String delete() throws Exception;

	public String validate() throws Exception;

//	public HtmlPanelGrid getActionsButtonsComponent(String controllerQryName, String controllerTxName) throws Exception;

//	public String runFromActionsButtons(String value, String action) throws Exception;

}
