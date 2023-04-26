package com.navatar.scripts;

import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.FindElements;
import static com.navatar.generic.CommonLib.ThreadSleep;
import static com.navatar.generic.CommonLib.click;
import static com.navatar.generic.CommonLib.clickUsingJavaScript;
import static com.navatar.generic.CommonLib.exit;
import static com.navatar.generic.CommonLib.log;
import static com.navatar.generic.CommonLib.sendKeys;
import static com.navatar.generic.CommonLib.switchOnWindow;
import static com.navatar.generic.CommonLib.switchToDefaultContent;
import static com.navatar.generic.CommonLib.switchToFrame;
import static com.navatar.generic.CommonVariables.*;
import static com.navatar.generic.CommonVariables.*;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Image;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EnumConstants.Condition;
import com.navatar.generic.EnumConstants.GlobalActionItem;
import com.navatar.generic.EnumConstants.ObjectFeatureName;
import com.navatar.generic.EnumConstants.RecordType;
import com.navatar.generic.EnumConstants.TabName;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.excelLabel;
import com.navatar.generic.EnumConstants.object;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.EditPageBusinessLayer;
import com.navatar.pageObjects.FieldAndRelationshipPageBusinessLayer;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.SetupPageBusinessLayer;
import com.relevantcodes.extentreports.LogStatus;

public class AcuityTabAddition extends BaseLib {
	static int interval;
	static Timer timer;
	String markup = "<html><font=inherit color=#000000 size=+0> "+ "Do not touch keyboard or mouse or disconnect from internet or refresh the page" + "</html>";
		String markup2 = "<html><font=inherit color=#008000 size=+1> "+"Script Execution is in progress!! " + "</html>";
		JLabel l = new JLabel(markup);
		JLabel hello = new JLabel(markup2,JLabel.CENTER);
		JPanel p = new JPanel(new java.awt.GridLayout(2, 2));
		JFrame f = new JFrame("WARNING!!");
		
	
	@Test(priority =0 ,enabled=true)
	public void before() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);

		if (lp.CRMLogin("", "")) {
			log(LogStatus.PASS, "User is sucessfully able to login", YesNo.Yes);

		} else {
			sa.assertTrue(false, "User is not able to login");
			log(LogStatus.FAIL, "User is not able to login", YesNo.Yes);

		}
		hello.setFont(hello.getFont().deriveFont(18f));
	    hello.setForeground(Color.black);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setFont(l.getFont().deriveFont(15f));
		p.add(hello);
		p.add(l);
		f.setContentPane(p);
		f.setAlwaysOnTop(true);
		f.setSize(650, 150);
		f.setLocationRelativeTo(null);
		f.setVisible(true);		
	}
	
	@AfterTest
	public void after() {
		f.setVisible(false);
		f.dispose();
		
	}
	
	@Test(priority = 10,enabled =false)
	public void VerifyAcuityNavatarSetting() {

		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.openAppFromAppLauchner("Navatar Setting", 20)) {

				if(click(driver, bp.getNavatarSettingNotificationButton(15),"Navatar Setting Notification button",action.BOOLEAN)) {
					log(LogStatus.PASS, "able to add tab", YesNo.No);
					
				boolean actionflag = CommonLib.isSelected(driver, bp.getActionNotificationCheckbox(10), "Action Notification");
				boolean infoflag = CommonLib.isSelected(driver, bp.getInformationalNotificationCheckbox(10), "Info Notification");
	
				if(actionflag) {
					
					log(LogStatus.PASS, "Action Notification Setting already Enable/Checked", YesNo.No);

				}else {
					log(LogStatus.INFO, "Action Notification Setting Is disable, Now going to Enable setting", YesNo.No);

					if(click(driver, bp.getActionNotificationCheckbox(10),"Navatar Setting Notification button",action.BOOLEAN)) {
						log(LogStatus.INFO, " able to click on action notification checkbox in navatar setting tab", YesNo.No);

						actionflag = CommonLib.isSelected(driver, bp.getActionNotificationCheckbox(10), "Action Notification");
						if(actionflag) {
							log(LogStatus.PASS, "Action Notification Setting is now Enable/Checked", YesNo.No);

						}else {
							log(LogStatus.FAIL, "Not able to Enable/Checked Action Notification Setting ", YesNo.Yes);
							sa.assertTrue(false, "Not able to Enable/Checked Action Notification Setting ");
						}
					}else {
						log(LogStatus.FAIL, "Not able to click on action notification checkbox in navatar setting tab", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on action notification checkbox in navatar setting tab");
					}
					
				}
				
				if(infoflag) {
					
					log(LogStatus.PASS, "Info Notification Setting already Enable/Checked", YesNo.No);

				}else {
					
					if(click(driver, bp.getInformationalNotificationCheckbox(10),"Informational Notification Checkbox",action.BOOLEAN)) {
						log(LogStatus.INFO, " able to click on information notification checkbox in navatar setting tab", YesNo.No);

						infoflag = CommonLib.isSelected(driver, bp.getInformationalNotificationCheckbox(10), "Information Notification");
						if(infoflag) {
							log(LogStatus.PASS, "Information Notification Setting is now Enable/Checked", YesNo.No);

						}else {
							log(LogStatus.FAIL, "Not able to Enable/Checked Information Notification Setting ", YesNo.Yes);
							sa.assertTrue(false, "Not able to Enable/Checked Information Notification Setting ");
						}
					}else {
						log(LogStatus.FAIL, "Not able to click on Information notification checkbox in navatar setting tab", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on Information notification checkbox in navatar setting tab");
					}
					
				}
					

				}else {
					
					log(LogStatus.FAIL, "Not able to click on notification link in navatar setting tab", YesNo.Yes);
					sa.assertTrue(false, "Not able to click on notification link in navatar setting tab");
				}
				
			}else {
				log(LogStatus.FAIL, "Not able to open Tab Navatar Setting form app laucnher  ", YesNo.Yes);
				sa.assertTrue(false, "Not able to open Tab Navatar Setting form app laucnher  ");
			}
		} catch (Exception e) {
		
				switchToDefaultContent(driver);

			sa.assertAll();
		}
		sa.assertAll();
	}
	
	@Test(priority = 2,enabled =false)
	public void VerifyRenamingTabAndLableInActivity() {
		
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		String parentWindow = null;
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
			
				String[] labelsWithValues1 = {"Assigned To<break>User"};			
				
					if (setup.searchStandardOrCustomObject(projectName, mode,  object.Rename_Tabs_And_Labels)) {
						log(LogStatus.PASS,  object.Rename_Tabs_And_Labels + " object has been opened in setup page", YesNo.Yes);
						CommonLib.ThreadSleep(3000);
						if (setup.renameLabelsOfFields(driver, "Activities", labelsWithValues1, 10)) {
							//flag1 = true;
							log(LogStatus.PASS, "Assigned To is updated as User" , YesNo.Yes);
						}else {
							log(LogStatus.FAIL, "Not able update Assigned To is updated as User", YesNo.Yes);
							sa.assertTrue(false, "Not able update Assigned To is updated as User");
						}
				
					} else {
						log(LogStatus.FAIL, "Not able to open " +  object.Rename_Tabs_And_Labels + " object", YesNo.Yes);
						sa.assertTrue(false, "Not able to open " +  object.Rename_Tabs_And_Labels + " object");
					}
				

		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();
		
	}
	
	@Test(priority = 4,enabled=true)
	public void verifyAddQuickActiononPageLayoutsofObjects () {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		String parentWindow = null;
		
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
			
			List<String> layoutName = new ArrayList<String>();
			ArrayList<String> sourceANDDestination = new ArrayList<String>();
			
			object[] objects = { /* object.Contact, object.Fund, object.Fundraising, object.Deal, */ object.Firm };
			for (object obj : objects) {
			log(LogStatus.PASS, "Going to check and Add tab for " + obj.toString() + " object", YesNo.Yes);
			if (setup.searchStandardOrCustomObject(environment, mode, obj)) {
				log(LogStatus.PASS, obj + " object has been opened in setup page", YesNo.Yes);
				CommonLib.ThreadSleep(3000);
				if (setup.clickOnObjectFeature(environment, mode, obj,
						ObjectFeatureName.pageLayouts)) {
					log(LogStatus.PASS, "clicked on page layout of object feature of "
							+ obj.toString() + " object", YesNo.Yes);
					List<WebElement> allElements = setup.getAllPageLayoutList();
					int no = allElements.size();
					 for(int i=0;i<no;i++) {
					String name = null;
						allElements = setup.getAllPageLayoutList();
						WebElement labelElement = allElements.get(i);
						name = labelElement.getText();
						 if((name.equals("Institution"))|| (name.equals("Private Equity"))|| (name.equals("Portfolio Company")) ||  (name.equals("Intermediary"))|| (name.equals("Lender"))|| (name.equals("Limited Partner"))|| (name.equals("Advisor")) || (name.equals("Company")) 
						|| (name.equals("Individual Investor")) || (name.equals("Affiliation Layout")) || (name.equals("Contact Layout")) || (name.equals("Financing Layout")) || (name.equals("Fundraising Layout")) || (name.equals("Pipeline Layout"))) {
//								 for(int j=0;j<allElements.size();j++) {
//							String value = allElements.get(j).getText().trim();
//							if(value.equalsIgnoreCase("Fund Manager") ||value.equalsIgnoreCase("Fund Manager’s Fund")) {
//								allElements.remove(j);
//							}
//						}
//						 labelElement = allElements.get(i);
						
							
									
									if(name.equals("Advisor")) {

//										if (click(driver, labelElement, "lightning record  page label :" + labelElement,
//												action.SCROLLANDBOOLEAN)) {
//											log(LogStatus.INFO, "clicked on the lightning record  page label:" + name,
//													YesNo.No);
//													CommonLib.ThreadSleep(3000);
//											switchToFrame(driver, 10, setup.getEditPageLayoutFrame_Lighting(20));
										
									    sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());
										
									} else if(name.equals("Company")) {
										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());

									} else if((name.equals("Individual Investor"))|| (name.equals("Institution"))) {

										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());
									} else if(name.equals("Intermediary")){
										
//										 sourceANDDestination = new ArrayList<String>();
//										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
//										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
//										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
//										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
//										sourceANDDestination.add(GlobalActionItem.Email.toString());
									
									} else if(name.equals("Lender")){

//										 sourceANDDestination = new ArrayList<String>();
//										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
//										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
//										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
//										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
//										sourceANDDestination.add(GlobalActionItem.Email.toString());

									} else if(name.equals("Limited Partner")){

//										 sourceANDDestination = new ArrayList<String>();
//										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
//										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
//										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
//										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
//										sourceANDDestination.add(GlobalActionItem.Email.toString());
									
									} else if(name.equals("Portfolio Company")){

//										 sourceANDDestination = new ArrayList<String>();
//										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
//										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
//										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
//										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
//										sourceANDDestination.add(GlobalActionItem.Email.toString());
										
									} else if(name.equals("Private Equity")){

										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());
										
									} else if(name.equals("Contact Layout")){
										
										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());
									
									} else if(name.equals("Fund Layout")){
										
										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());
											
									} else if(name.equals("Fundraising Layout")){
										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());
											
									} else if(name.equals("Pipeline Layout")){
										 sourceANDDestination = new ArrayList<String>();
										sourceANDDestination.add(GlobalActionItem.New_Event.toString());
										sourceANDDestination.add(GlobalActionItem.Mobile_Smart_Actions.toString());
										sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
										sourceANDDestination.add(GlobalActionItem.New_Task.toString());
										sourceANDDestination.add(GlobalActionItem.Email.toString());

									} else {

									log(LogStatus.FAIL, "No Requested Layout",YesNo.No);

									}
										if (click(driver, labelElement, "lightning record  page label :" + labelElement,
												action.SCROLLANDBOOLEAN)) {
											log(LogStatus.INFO, "clicked on the lightning record  page label:" + name,
													YesNo.No);
													CommonLib.ThreadSleep(3000);
											switchToFrame(driver, 10, setup.getEditPageLayoutFrame_Lighting(20));
											
									List<String> abc = setup.removeDragNDropFromPagelayoutContact("", mode, obj, ObjectFeatureName.pageLayouts, layoutName, sourceANDDestination);
									ThreadSleep(10000);
									if (!abc.isEmpty()) {
										log(LogStatus.PASS, "field  removed Successfully", YesNo.No);
									}else{
										log(LogStatus.FAIL, "field not be ABLE To removed from quick action layout", YesNo.Yes);
										sa.assertTrue(false,
												"field not be ABLE To removed from quick action layout");
									}

								} else {
									log(LogStatus.ERROR,
											"Not able to clicked on the page layout of  page label:" + name,
											YesNo.Yes);
									sa.assertTrue(false,
											"Not able to clicked on the page layout of  page label:" + name);

								}
						 }
						 }
					 
					} else {
						log(LogStatus.ERROR,
								"clicked on page layout of object feature of "
										+ obj.toString() + " object", YesNo.Yes);
						sa.assertTrue(false,
								"clicked on page layout of object feature of "
										+ obj.toString() + " object");
						}
				} else {
					log(LogStatus.ERROR,
							obj + " object has been opened in setup page", YesNo.Yes);
					sa.assertTrue(false,
							obj + " object has been opened in setup page");
					}
			}
		
			
		
			
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
				CommonLib.ThreadSleep(3000);
				if (home.clickOnSetUpLink()) {

					parentWindow = switchOnWindow(driver);
					if (parentWindow == null) {
						sa.assertTrue(false,
								"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
						log(LogStatus.FAIL,
								"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
								YesNo.Yes);
						exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					}
				}
				object[] objects = { /* object.Contact, object.Fund, object.Fundraising, object.Deal , */object.Firm };
									for (object obj : objects) {
									log(LogStatus.PASS, "Going to check and Add tab for " + obj.toString() + " object", YesNo.Yes);			
									
				if (setup.searchStandardOrCustomObject(environment, mode, obj)) {
					log(LogStatus.PASS, obj + " object has been opened in setup page", YesNo.Yes);
					CommonLib.ThreadSleep(3000);
					if (setup.clickOnObjectFeature(environment, mode, obj,
							ObjectFeatureName.pageLayouts)) {
						log(LogStatus.PASS, "clicked on page layout of object feature of "
								+ obj.toString() + " object", YesNo.Yes);
						List<WebElement> allElements = setup.getAllPageLayoutList();
						int no = allElements.size();
						 for(int i=0;i<no;i++) {
						String name = null;
							allElements = setup.getAllPageLayoutList();
							WebElement labelElement = allElements.get(i);
							name = labelElement.getText();
							 if((name.equals("Institution"))|| (name.equals("Private Equity"))|| (name.equals("Portfolio Company")) ||  (name.equals("Intermediary"))|| (name.equals("Lender"))|| (name.equals("Limited Partner"))|| (name.equals("Advisor")) || (name.equals("Company")) 
										|| (name.equals("Individual Investor")) || (name.equals("Affiliation Layout")) || (name.equals("Contact Layout")) || (name.equals("Financing Layout")) || (name.equals("Fundraising Layout")) || (name.equals("Pipeline Layout"))) {
//							for(int j=0;j<allElements.size();j++) {
//								String value = allElements.get(j).getText().trim();
//								if(value.equalsIgnoreCase("Fund Manager") ||value.equalsIgnoreCase("Fund Manager’s Fund")) {
//									allElements.remove(j);
//								}
//							}
						 	
							
								
					
									List<String> layoutName1 = new ArrayList<String>();
									ArrayList<String> sourceANDDestination1 = new ArrayList<String>();
									if(name.equals("Advisor")) {

										layoutName1 = new ArrayList<String>();
										layoutName1.add("Advisor");
										sourceANDDestination1 = new ArrayList<String>();
										sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
										sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Client.toString());
										
									} else if(name.equals("Company")) {
										layoutName1 = new ArrayList<String>();
										layoutName1.add("");
										sourceANDDestination1 = new ArrayList<String>();
										sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Deal.toString());
										sourceANDDestination1.add(GlobalActionItem.Export.toString());
										sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Investor.toString());

									} else if((name.equals("Individual Investor"))|| (name.equals("Institution"))) {

										layoutName1 = new ArrayList<String>();
										layoutName1.add("Advisor");
										 sourceANDDestination1 = new ArrayList<String>();
										sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
										sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Advisor.toString());

									} else if(name.equals("Intermediary")){
										
									layoutName1 = new ArrayList<String>();
									layoutName1.add("Advisor");
									 sourceANDDestination1 = new ArrayList<String>();
									sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
									sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
									sourceANDDestination1.add(GlobalActionItem.Edit.toString());
									sourceANDDestination1.add(GlobalActionItem.Export.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Sourced_Deal.toString());
									
									} else if(name.equals("Lender")){

										layoutName1 = new ArrayList<String>();
										layoutName1.add("Advisor");
										sourceANDDestination1 = new ArrayList<String>();
										sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
										sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Financing.toString());

									} else if(name.equals("Limited Partner")){

									layoutName1 = new ArrayList<String>();
									layoutName1.add("Advisor");
									sourceANDDestination1 = new ArrayList<String>();
									sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
									sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Commitment.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Fundraising.toString());
									
									} else if(name.equals("Portfolio Company")){

										layoutName1 = new ArrayList<String>();
										layoutName1.add("Advisor");
										sourceANDDestination1 = new ArrayList<String>();
										sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
										sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Deal.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Investor.toString());
										
									} else if(name.equals("Private Equity")){

										layoutName1 = new ArrayList<String>();
										layoutName1.add("Advisor");
										sourceANDDestination1 = new ArrayList<String>();
										sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Contact.toString());
										sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Deal.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
										sourceANDDestination1.add(GlobalActionItem.New_Sourced_Deal.toString());
										
									} else if(name.equals("Contact Layout")){
								   layoutName1 = new ArrayList<String>();
									layoutName1.add("Advisor");
									 sourceANDDestination1 = new ArrayList<String>();
									sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Deal_Contact.toString());
									sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Affiliation.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Sourced_Deal.toString());
									sourceANDDestination1.add(GlobalActionItem.Export.toString());
									sourceANDDestination1.add(GlobalActionItem.New_Referral.toString());
									
									} else if(name.equals("Fund Layout")){
										   layoutName1 = new ArrayList<String>();
											layoutName1.add("Advisor");
											 sourceANDDestination1 = new ArrayList<String>();
											sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
											sourceANDDestination1.add(GlobalActionItem.Bulk_Fundraising.toString());
											sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
											sourceANDDestination1.add(GlobalActionItem.New_Partnership.toString());
											
									} else if(name.equals("Fundraising Layout")){
										   layoutName1 = new ArrayList<String>();
											layoutName1.add("Advisor");
											 sourceANDDestination1 = new ArrayList<String>();
											sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
											sourceANDDestination1.add(GlobalActionItem.New_Fundraising_Contact.toString());
											sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
											sourceANDDestination1.add(GlobalActionItem.Create_Commitments.toString());
											
									} else if(name.equals("Pipeline Layout")){
										   layoutName1 = new ArrayList<String>();
											layoutName1.add("Advisor");
											 sourceANDDestination1 = new ArrayList<String>();
											sourceANDDestination1.add(GlobalActionItem.Create_Task.toString());
											sourceANDDestination1.add(GlobalActionItem.New_Team_Member.toString());
											sourceANDDestination1.add(GlobalActionItem.Add_To_Theme.toString());
											sourceANDDestination1.add(GlobalActionItem.New_Deal_Contact.toString());
											sourceANDDestination1.add(GlobalActionItem.New_Review.toString());
											sourceANDDestination1.add(GlobalActionItem.Convert_to_Portfolio.toString());
											sourceANDDestination1.add(GlobalActionItem.New_Lender.toString());

									} else {

									log(LogStatus.FAIL, "No Requested Layout",YesNo.No);

									}
									if (click(driver, labelElement, "lightning record  page label :" + name,
											action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, "clicked on the lightning record  page label:" + name,
												YesNo.No);
												CommonLib.ThreadSleep(3000);
										switchToFrame(driver, 10, setup.getEditPageLayoutFrame_Lighting(20));
									
									List<String> abc1 = setup.AddDragNDropFromPagelayoutContact("", mode, obj, ObjectFeatureName.pageLayouts, layoutName1, sourceANDDestination1);
									ThreadSleep(10000);
									if (!abc1.isEmpty()) {
										log(LogStatus.PASS, "field  removed Successfully", YesNo.No);
									}else{
										log(LogStatus.FAIL, "field not be ABLE To removed from quick action layout", YesNo.Yes);
										sa.assertTrue(false,
												"field not be ABLE To removed from quick action layout");
									}

								} else {
									log(LogStatus.ERROR,
											"Not able to clicked on the page layout of  page label:" + name,
											YesNo.Yes);
									sa.assertTrue(false,
											"Not able to clicked on the page layout of  page label:" + name);

								}
						 }
						 }
					} else {
						log(LogStatus.ERROR,
								"clicked on page layout of object feature of "
										+ obj.toString() + " object", YesNo.Yes);
						sa.assertTrue(false,
								"clicked on page layout of object feature of "
										+ obj.toString() + " object");
						}
				} else {
					log(LogStatus.ERROR,
							obj + " object has been opened in setup page", YesNo.Yes);
					sa.assertTrue(false,
							obj + " object has been opened in setup page");
					}
			}
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();

	}
	@Test(priority = 3,enabled =false)
	public void VerifyRemovingGlobalAction() {
		
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);

		String parentWindow = null;
		
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
							
						log(LogStatus.PASS,  object.Global_Actions + " object has been opened in setup page", YesNo.Yes);
						CommonLib.ThreadSleep(3000);
						List<String> layoutName = new ArrayList<String>();
						layoutName.add("Global Layout");
						ArrayList<String> sourceANDDestination = new ArrayList<String>();
						sourceANDDestination.add(GlobalActionItem.New_Event.toString());
						sourceANDDestination.add(GlobalActionItem.Log_a_Call.toString());
						sourceANDDestination.add(GlobalActionItem.New_Task.toString());

						List<String> abc = setup.removeDragNDropFromPagelayout("", mode, object.PublisherLayout, ObjectFeatureName.pageLayouts, layoutName, sourceANDDestination);
						ThreadSleep(10000);
						if (!abc.isEmpty()) {
							log(LogStatus.PASS, "field  removed Successfully", YesNo.No);
						}else{
							log(LogStatus.FAIL, "field not be ABLE To removed from quick action layout", YesNo.Yes);
							sa.assertTrue(false,
									"field not be ABLE To removed from quick action layout");
						}
					
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		
		sa.assertAll();
		
	}
	
	@Test(priority = 4,enabled=false)
	public void verifyRemovingRelatedListFromObjects() {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);

		String parentWindow = null;
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}

			object[] objects = { object.Institution,object.Contact, object.Fund, object.Fundraising, object.Pipeline };
			for (object obj : objects) {
				log(LogStatus.PASS, "Going to check and Add tab for " + obj.toString() + " object", YesNo.Yes);
				try {
					if (setup.searchStandardOrCustomObject(projectName, mode, obj)) {
						log(LogStatus.PASS, obj + " object has been opened in setup page", YesNo.Yes);
						CommonLib.ThreadSleep(3000);
						if (setup.clickOnObjectFeature(projectName, mode, obj,
								ObjectFeatureName.pageLayouts)) {
							log(LogStatus.PASS, "clicked on page layout of object feature of "
									+ obj.toString() + " object", YesNo.Yes);
							List<WebElement> allElements = setup.getAllPageLayoutList();
							int no = allElements.size();
							 for(int i=0;i<no;i++) {
							String name = null;
							try {
								allElements = setup.getAllPageLayoutList();
								WebElement labelElement = allElements.get(i);
								name = labelElement.getText();
								if (click(driver, labelElement, "lightning record  page label :" + name,
										action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "clicked on the lightning record  page label:" + name,
											YesNo.No);
									CommonLib.ThreadSleep(3000);
									switchToFrame(driver, 20, setup.getSetUpPageIframe(20));
									CommonLib.ThreadSleep(5000);

									if (setup.removeRelatedList(obj)) {
										log(LogStatus.PASS, "able to remove open activities and activity history related list from object:"+obj,
												YesNo.No);

									} else {
										log(LogStatus.ERROR, "Not able to remove open activities and activity history related list from object:"+obj, YesNo.Yes);
										sa.assertTrue(false, "Not able to remove open activities and activity history related list from object:"+obj);

									}

								} else {
									log(LogStatus.ERROR,
											"Not able to clicked on the page layout of  page label:" + name,
											YesNo.Yes);
									sa.assertTrue(false,
											"Not able to clicked on the page layout of  page label:" + name);

								}
							} catch (Exception e) {
								driver.navigate().back();
								
							}
							 }
						} else {
							log(LogStatus.FAIL,
									"Not able to click on Record type of object feature of " + obj + " object",
									YesNo.Yes);
							sa.assertTrue(false,
									"Not able to click on Record type of object feature of " + obj + " object");
						}
					} else {
						log(LogStatus.FAIL, "Not able to open " + obj + " object", YesNo.Yes);
						sa.assertTrue(false, "Not able to open " + obj + " object");
					}
				} catch (Exception e) {
					log(LogStatus.FAIL, "Not able to add Acuity Tab for the " + obj + " object", YesNo.Yes);
					sa.assertTrue(false, "Not able to add Acuity Tab for the " + obj + " object");
					continue;
				}
			}

		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();

	}
	
	@Test(priority = 4,enabled=false)
	public void verifyOverridingtheTaskEventstandardbuttons() {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);

		String parentWindow = null;
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
				if (setup.searchStandardOrCustomObject(projectName, mode, object.Task)) {
					log(LogStatus.PASS, object.Task + " object has been opened in setup page", YesNo.Yes);
					CommonLib.ThreadSleep(3000);
					if (setup.clickOnObjectFeature(projectName, mode, object.Task,
							ObjectFeatureName.ButtonLinksAndActions)) {
						log(LogStatus.PASS, "clicked on page layout of object feature of "
								+ object.Task.toString() + "Task", YesNo.Yes);
						if (sendKeys(driver, setup.getsearchTextboxFieldsAndRelationships(10),
								excelLabel.New_Task.toString() + Keys.ENTER, "status", action.BOOLEAN)) {
							log(LogStatus.INFO, " able to search Text box Fields And Relationships", YesNo.No);
							ThreadSleep(3000);
							if(click(driver, setup.getNewTaskdropdown(10),"New Task drop down",action.BOOLEAN)) {
								log(LogStatus.INFO, " able to click on New Task drop down", YesNo.No);
								
								if(click(driver, setup.getNewTaskEditbutton(10),"New Task Edit button",action.BOOLEAN)) {
									log(LogStatus.INFO, " able to click on New Task Edit button", YesNo.No);
									ThreadSleep(3000);
									switchToFrame(driver,30, bp.getenterpriseeditionFrame(30));
									if (setup.CreateOverridingtheTaskEventstandardbuttons(projectName, mode,10)) {
										//flag1 = true;
										log(LogStatus.PASS, "able to Create Overriding the Task Event standard buttons" , YesNo.Yes);
									}else {
										log(LogStatus.FAIL, "Not able to Create Overriding the Task Event standard buttons", YesNo.Yes);
										sa.assertTrue(false, "Not able to Create Overriding the Task Event standard buttons");
									}
								}else {
									log(LogStatus.FAIL, "Not able to click on New Task Edit button", YesNo.Yes);
									sa.assertTrue(false, "Not able to click on New Task Edit button");
								}
							}else {
								log(LogStatus.FAIL, "Not able to click on New Task drop down", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on New Task drop down");
							}
						}else {
							log(LogStatus.FAIL, "Not able to search Text box Fields And Relationships", YesNo.Yes);
							sa.assertTrue(false, "Not able to search Text box Fields And Relationships");
						}
					}else {
						log(LogStatus.FAIL, "Not able to clicked on page layout of object feature of "
								+object.Task.toString() + "Task", YesNo.Yes);
						sa.assertTrue(false, "Not able to clicked on page layout of object feature of "+object.Task.toString() + "Task");
					}
				}else {
					log(LogStatus.FAIL, object.Task + " object has been opened in setup page", YesNo.Yes);
					sa.assertTrue(false, object.Task + " object has been opened in setup page");
				}
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
				if (setup.searchStandardOrCustomObject(projectName, mode, object.Task)) {
					log(LogStatus.PASS, object.Task + " object has been opened in setup page", YesNo.Yes);
					CommonLib.ThreadSleep(3000);
					if (setup.clickOnObjectFeature(projectName, mode, object.Task,
							ObjectFeatureName.ButtonLinksAndActions)) {
						log(LogStatus.PASS, "clicked on page layout of object feature of "
								+ object.Task.toString() + "Task", YesNo.Yes);
						if (sendKeys(driver, setup.getsearchTextboxFieldsAndRelationships(10),
								excelLabel.View.toString() + Keys.ENTER, "status", action.BOOLEAN)) {
							log(LogStatus.INFO, " able to search Text box Fields And Relationships", YesNo.No);
							ThreadSleep(3000);
							if(click(driver, setup.getViewdropdown(10),"View drop down",action.BOOLEAN)) {
								log(LogStatus.INFO, " able to click on View drop down", YesNo.No);
								
								if(click(driver, setup.getNewTaskEditbutton(10),"View Edit button",action.BOOLEAN)) {
									log(LogStatus.INFO, " able to click on View Edit button", YesNo.No);
									ThreadSleep(3000);
									switchToFrame(driver,30, bp.getenterpriseeditionFrame(30));
									if (setup.CreateOverridingtheTaskEventstandardbuttons(projectName, mode,10)) {
										//flag1 = true;
										log(LogStatus.PASS, "able to Create Overriding the Task Event standard buttons" , YesNo.Yes);
									}else {
										log(LogStatus.FAIL, "Not able to Create Overriding the Task Event standard buttons", YesNo.Yes);
										sa.assertTrue(false, "Not able to Create Overriding the Task Event standard buttons");
									}
								}else {
									log(LogStatus.FAIL, "Not able to click on New Task Edit button", YesNo.Yes);
									sa.assertTrue(false, "Not able to click on New Task Edit button");
								}
							}else {
								log(LogStatus.FAIL, "Not able to click on New Task drop down", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on New Task drop down");
							}
						}else {
							log(LogStatus.FAIL, "Not able to search Text box Fields And Relationships", YesNo.Yes);
							sa.assertTrue(false, "Not able to search Text box Fields And Relationships");
						}
					}else {
						log(LogStatus.FAIL, "Not able to clicked on page layout of object feature of "
								+object.Task.toString() + "Task", YesNo.Yes);
						sa.assertTrue(false, "Not able to clicked on page layout of object feature of "+object.Task.toString() + "Task");
					}
				}else {
					log(LogStatus.FAIL, object.Task + " object has been opened in setup page", YesNo.Yes);
					sa.assertTrue(false, object.Task + " object has been opened in setup page");
				}
					} catch (Exception e) {
						if (parentWindow != null) {

							driver.close();
							driver.switchTo().window(parentWindow);
						}
					}

					if (parentWindow != null) {

						driver.close();
						driver.switchTo().window(parentWindow);
					}
					
					CommonLib.refresh(driver);
					CommonLib.ThreadSleep(3000);
					try {
						CommonLib.ThreadSleep(3000);
						if (home.clickOnSetUpLink()) {

							parentWindow = switchOnWindow(driver);
							if (parentWindow == null) {
								sa.assertTrue(false,
										"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
								log(LogStatus.FAIL,
										"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
										YesNo.Yes);
								exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
							}
						}
							if (setup.searchStandardOrCustomObject(projectName, mode, object.Event)) {
								log(LogStatus.PASS, object.Event + " object has been opened in setup page", YesNo.Yes);
								CommonLib.ThreadSleep(3000);
								if (setup.clickOnObjectFeature(projectName, mode, object.Event,
										ObjectFeatureName.ButtonLinksAndActions)) {
									log(LogStatus.PASS, "clicked on page layout of object feature of "
											+ object.Event.toString() + "Event", YesNo.Yes);
									if (sendKeys(driver, setup.getsearchTextboxFieldsAndRelationships(10),
											excelLabel.New_Event.toString() + Keys.ENTER, "status", action.BOOLEAN)) {
										log(LogStatus.INFO, " able to search Text box Fields And Relationships", YesNo.No);
										ThreadSleep(3000);
										if(click(driver, setup.getNewEventdropdown(10),"New Event drop down",action.BOOLEAN)) {
											log(LogStatus.INFO, " able to click on New Task drop down", YesNo.No);
											
											if(click(driver, setup.getNewTaskEditbutton(10),"New Event Edit button",action.BOOLEAN)) {
												log(LogStatus.INFO, " able to click on New Event Edit button", YesNo.No);
												ThreadSleep(3000);
												switchToFrame(driver,30, bp.getenterpriseeditionFrame(30));
												if (setup.CreateOverridingtheTaskEventstandardbuttons(projectName, mode,10)) {
													//flag1 = true;
													log(LogStatus.PASS, "able to Create Overriding the Event Event standard buttons" , YesNo.Yes);
												}else {
													log(LogStatus.FAIL, "Not able to Create Overriding the Event Event standard buttons", YesNo.Yes);
													sa.assertTrue(false, "Not able to Create Overriding the Event Event standard buttons");
												}
											}else {
												log(LogStatus.FAIL, "Not able to click on New Event Edit button", YesNo.Yes);
												sa.assertTrue(false, "Not able to click on New Event Edit button");
											}
										}else {
											log(LogStatus.FAIL, "Not able to click on New Event drop down", YesNo.Yes);
											sa.assertTrue(false, "Not able to click on New Event drop down");
										}
									}else {
										log(LogStatus.FAIL, "Not able to search Text box Fields And Relationships", YesNo.Yes);
										sa.assertTrue(false, "Not able to search Text box Fields And Relationships");
									}
								}else {
									log(LogStatus.FAIL, "Not able to clicked on page layout of object feature of "
											+object.Task.toString() + "Task", YesNo.Yes);
									sa.assertTrue(false, "Not able to clicked on page layout of object feature of "+object.Task.toString() + "Task");
								}
							}else {
								log(LogStatus.FAIL, object.Task + " object has been opened in setup page", YesNo.Yes);
								sa.assertTrue(false, object.Task + " object has been opened in setup page");
							}
					} catch (Exception e) {
						if (parentWindow != null) {

							driver.close();
							driver.switchTo().window(parentWindow);
						}
						sa.assertAll();
					}

					if (parentWindow != null) {

						driver.close();
						driver.switchTo().window(parentWindow);
					}
					sa.assertAll();
				}

					
					
	@Test(priority = 3,enabled =false)
	public void VerifyHelpmenutodisplaycustomdetails() {
		
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String parentWindow = null;
		String domainurl = "";
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
			if (setup.searchStandardOrCustomObject("", mode, object.My_Domain)) {
				log(LogStatus.PASS, object.My_Domain.toString() + " object has been opened in setup page", YesNo.Yes);
				CommonLib.ThreadSleep(3000);
			
				switchToFrame(driver,30, bp.getenterpriseeditionFrame(30));
				CommonLib.ThreadSleep(5000);
				String xpath = "//label[contains(text(),'My Domain URL')]//ancestor::tr/td/span/span";
				WebElement ele = FindElement(driver, xpath, "My Domian Url", action.SCROLLANDBOOLEAN, 10);
				 domainurl = ele.getText();
				domainurl="https://"+domainurl+"/c/NavatarHelpandSupport.app";
			}else {
				log(LogStatus.FAIL,object.My_Domain.toString() + " object has been opened in setup page", YesNo.Yes);
				sa.assertTrue(false,object.My_Domain.toString() + " object has been opened in setup page");
			
			}
			
			} catch (Exception e) {
				if (parentWindow != null) {

					driver.close();
					driver.switchTo().window(parentWindow);
				}
				sa.assertAll();
			}

			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			CommonLib.refresh(driver);
			CommonLib.ThreadSleep(3000);
			try {
					CommonLib.ThreadSleep(3000);
					if (home.clickOnSetUpLink()) {

						parentWindow = switchOnWindow(driver);
						if (parentWindow == null) {
							sa.assertTrue(false,
									"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
							log(LogStatus.FAIL,
									"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
									YesNo.Yes);
							exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
						}
					}
				if (setup.searchStandardOrCustomObject(projectName, mode,  object.Help_Menu)) {
					log(LogStatus.PASS,  object.Help_Menu + " object has been opened in setup page", YesNo.Yes);
					CommonLib.ThreadSleep(3000);
					
				if (setup.CreateHelpMenu(projectName, mode,"Navatar Help","View Our User Guide",domainurl, 10)) {
					//flag1 = true;
					log(LogStatus.PASS, "able to setup ulr in help menu" , YesNo.Yes);
				}else {
					log(LogStatus.FAIL, "Not able to setup ulr in help menu", YesNo.Yes);
					sa.assertTrue(false, "Not able to setup ulr in help menu");
				}

			} else {
				log(LogStatus.FAIL, "Not able to open " + object.Help_Menu + " object", YesNo.Yes);
				sa.assertTrue(false, "Not able to open " + object.Help_Menu + " object");
			}
		}

		catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}

		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();

}
	

	@Test(priority =5 ,enabled=false
			)
	public void verifyAcuityTabAddedInObjects() {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		EditPageBusinessLayer edit = new EditPageBusinessLayer(driver);

		String parentWindow = null;
		String xPath;

		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}

			object[] objects = { object.Institution,object.Contact, object.Fund, object.Fundraising, object.Pipeline };
			for (object obj : objects) {
				log(LogStatus.PASS, "Going to check and Add tab for " + obj.toString() + " object", YesNo.Yes);
				try {
					if (setup.searchStandardOrCustomObject(projectName, mode, obj)) {
						log(LogStatus.PASS, obj + " object has been opened in setup page", YesNo.Yes);
						CommonLib.ThreadSleep(3000);
						if (setup.clickOnObjectFeature(projectName, mode, obj,
								ObjectFeatureName.lightningRecordPages)) {
							log(LogStatus.PASS, "clicked on lightning Record Pages of object feature of "
									+ obj.toString() + " object", YesNo.Yes);
							List<WebElement> allElements = setup.getOtherAssignmentColumnLabelValue();
							int no = allElements.size();
							 for(int i=0;i<no;i++) {
							String name = null;
							try {
								allElements = setup.getOtherAssignmentColumnLabelValue();
								WebElement labelElement = allElements.get(i);
								name = labelElement.getText();
								if (click(driver, labelElement, "lightning record  page label :" + name,
										action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "clicked on the lightning record  page label:" + name,
											YesNo.No);
									CommonLib.ThreadSleep(3000);
									switchToFrame(driver, 30, setup.getSetUpPageIframe(60));
									CommonLib.ThreadSleep(5000);

									if (click(driver, setup.editButton(10), "edit button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, "clicked on the edit button", YesNo.No);
										CommonLib.ThreadSleep(10000);
										xPath = "//div[@role='dialog']//button[contains(@title,'Close')]";
										List<WebElement> closeButtons = FindElements(driver, xPath);

										for (WebElement elem : closeButtons) {

											click(driver, elem, "close popup button", action.SCROLLANDBOOLEAN);
											CommonLib.ThreadSleep(1000);

										}
										CommonLib.ThreadSleep(5000);
										switchToFrame(driver, 20, edit.getEditPageFrame("", 20));
										CommonLib.ThreadSleep(3000);

										if (edit.verifyAndAddAcuityTabInPages("Navatar Acuity", "Acuity", "Acuity",
												new String[] { "Z (Do not use) Navatar Clip Edit Utility",
														"Z (Do not use) Navatar Add Subscribe" },true)) {
											log(LogStatus.INFO, "able to add tab", YesNo.No);
											CommonLib.ThreadSleep(2000);
											
											
											
										} else {
											log(LogStatus.ERROR, "Not able to able to add tab", YesNo.Yes);
											sa.assertTrue(false, "Not able to able to add tab");

										}
											if (setup.clickOnObjectFeature(projectName, mode, obj,
													ObjectFeatureName.lightningRecordPages)) {
												log(LogStatus.PASS,
														"clicked on lightning Record Pages of object feature of "
																+ obj + " object",
														YesNo.Yes);
											} else {
												log(LogStatus.FAIL,
														"Not able to click on Record type of object feature of contact object so cannot going to check anohter iteration",
														YesNo.Yes);
												
											}

										
										

									} else {
										log(LogStatus.ERROR, "Not able to click on edit button of ", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on edit button of ");

									}

								} else {
									log(LogStatus.ERROR,
											"Not able to clicked on the lightning record  page label:" + name,
											YesNo.Yes);
									sa.assertTrue(false,
											"Not able to clicked on the lightning record  page label:" + name);

								}
							} catch (Exception e) {
								driver.navigate().back();
								CommonLib.ThreadSleep(2000);
								if (setup.clickOnObjectFeature(projectName, mode, object.Contact,
										ObjectFeatureName.lightningRecordPages)) {
									log(LogStatus.PASS, "clicked on lightning Record Pages of object feature of "
											+ object.Contact.toString() + " object", YesNo.Yes);
								} else {
									log(LogStatus.FAIL,
											"Not able to click on Record type of object feature of contact object so cannot going to check anohter iteration",
											YesNo.Yes);
								}
								log(LogStatus.FAIL,
										"Not able to add/check Acuity Tab for the page:" + name + " " + obj + " object",
										YesNo.Yes);
								sa.assertTrue(false, "Not able to add/check Acuity Tab for the " + name + " object");
								continue;
							}
							 }
						} else {
							log(LogStatus.FAIL,
									"Not able to click on Record type of object feature of " + obj + " object",
									YesNo.Yes);
							sa.assertTrue(false,
									"Not able to click on Record type of object feature of " + obj + " object");
						}
					} else {
						log(LogStatus.FAIL, "Not able to open " + obj + " object", YesNo.Yes);
						sa.assertTrue(false, "Not able to open " + obj + " object");
					}
				} catch (Exception e) {
					log(LogStatus.FAIL, "Not able to add Acuity Tab for the " + obj + " object", YesNo.Yes);
					sa.assertTrue(false, "Not able to add Acuity Tab for the " + obj + " object");
					continue;
				}
			}

		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();

	}


	@Test(priority =6 ,enabled=false)
	public void verifyAddNotificationOnHomePageForPEFOFApp() {
		String projectName = "";
		String[] appName = {"PE", "FOF"}; 
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		EditPageBusinessLayer edit = new EditPageBusinessLayer(driver);
		
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		for(int i = 0; i < appName.length; i++ ) {
		if (lp.clickOnTab(projectName, TabName.HomeTab)) {
			log(LogStatus.PASS, "Click on Tab : " + TabName.HomeTab, YesNo.No);
		
			if (lp.openAppFromAppLauncher(60, appName[i])) {
				log(LogStatus.PASS, "Click on App From App Launcher : " + appName[i], YesNo.No);
				ThreadSleep(2000);
				if (edit.addNotificationComponent(projectName, "Navatar Notification", "Notifications", "Z (Do not use) Navatar Notification Popup")) {
					log(LogStatus.PASS, "Component Added to Home Page: Navatar Notification", YesNo.No);
				}
				else {
					log(LogStatus.FAIL, "Component Not Able to Add to Home Page: Navatar Notification", YesNo.Yes);
					sa.assertTrue(false, "Component Not Able to Add to Home Page: Navatar Notification");
					}
				}
				else {
					log(LogStatus.FAIL, "Not able to click on App From App Launcher : " + appName[i], YesNo.Yes);
					sa.assertTrue(false, "Not able to click on App From App Launcher : " + appName[i]);
				}
			} else {
					sa.assertTrue(false, "Not Able to Click on Tab : " + TabName.HomeTab);
					log(LogStatus.FAIL, "Not Able to Click on Tab : " + TabName.HomeTab, YesNo.Yes);
			}
		}
		
		sa.assertAll();
	}
	

	/// Pre-check ///
	@Test(priority = 1,enabled =false)
	public void verifyAllowUsersRelateMultipleContactsTasksEvents() {
		
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);

		String parentWindow = null;
		
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
			
			if (setup.searchStandardOrCustomObject("", mode, object.Activity_Setting)) {
				log(LogStatus.PASS, object.Activity_Setting.toString() + " object has been opened in setup page", YesNo.Yes);
				CommonLib.ThreadSleep(3000);
				
				switchToFrame(driver,30, bp.getActivitySettingFrame(30));
				CommonLib.ThreadSleep(3000);
				if(bp.getAllowUserToRelateMulipleTaskCheckbox(10)==null) {
					driver.navigate().refresh();
					CommonLib.ThreadSleep(2000);
					switchToFrame(driver,30, bp.getActivitySettingFrame(30));
					CommonLib.ThreadSleep(3000);
				}
				
				boolean permission =CommonLib.isSelected(driver, bp.getAllowUserToRelateMulipleTaskCheckbox(10), "Allow user to multiple task checkbox ");;
				
				if(permission) {
					
					log(LogStatus.PASS, "Allow user to multiple task Setting already Enable/Checked", YesNo.No);

				}else {
					log(LogStatus.INFO, "Allow user to multiple task Setting Is disable, Now going to Enable setting", YesNo.No);

					if(click(driver, bp.getAllowUserToRelateMulipleTaskCheckbox(10),"Allow user to multiple task checkbox",action.BOOLEAN)) {
						log(LogStatus.INFO, " able to click on Allow user to multiple task in activity setting", YesNo.No);
						
						if(click(driver, bp.getActivitySettingSubmitButton(10),"getActivitySettingSubmitButton",action.BOOLEAN)) {
							log(LogStatus.INFO, " able to click on submit button in activity setting", YesNo.No);
							CommonLib.ThreadSleep(2000);
							switchToDefaultContent(driver);
							
							clickUsingJavaScript(driver,
									FindElement(driver, "(//mark[text()='Activity Setting'])[1]/parent::a",
											"Activity Setting", action.BOOLEAN, 10),
									"Activity Setting", action.BOOLEAN);	
							CommonLib.ThreadSleep(3000);
							switchToFrame(driver,30, bp.getActivitySettingFrame(30));
							CommonLib.ThreadSleep(2000);
							permission = CommonLib.isSelected(driver, bp.getAllowUserToRelateMulipleTaskCheckbox(10), "Allow user to multiple task checkbox ");
							if(permission) {
								log(LogStatus.PASS, "Allow user to multiple task Setting is now Enable/Checked", YesNo.No);

								switchToDefaultContent(driver);
							}else {
								log(LogStatus.FAIL, "Not able to Enable/Checked Allow user to multiple task Setting ", YesNo.Yes);
								sa.assertTrue(false, "Not able to Enable/Checked Allow user to multiple task Setting ");
							}

							
						}else {
							log(LogStatus.FAIL, "Not able to click on sumbit button in activity setting", YesNo.Yes);
							sa.assertTrue(false, "Not able to click on sumbit button in activity setting");
						}
						
						
					}else {
						log(LogStatus.FAIL, "Not able to click on Allow user to multiple task checkbox in navatar setting tab", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on Allow user to multiple task checkbox in navatar setting tab");
					}
					
				}
				
			} else {

			}
		} catch (Exception e) {
			if (parentWindow != null) {
				switchToDefaultContent(driver);

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			sa.assertAll();
		}
		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();
	}

	@Test(priority =2 ,enabled=false)
	public void verifyAddAndActivatePicklistValueBeforeDeploymentforObjects() {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		FieldAndRelationshipPageBusinessLayer fr = new FieldAndRelationshipPageBusinessLayer(driver);
		
		String parentWindow = null;
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		String xpath =null;
		WebElement ele = null;
		String[] industry = {"INTest1","INTest2","INTest3"};
//		String[] industry = {"Advanced Material","Agriculture","Apparel","Banking","Biotechnology","Business Services","Chemicals","Cleantech","",
//				"Communications","Construction","Consulting","Consumer","Education","Electronics","Energy","Engineering","Entertainment","Environmental",
//				"Finance","Financial Services","Food Beverage","Government","Healthcare","Hospitality","Insurance","Leisure","Machinery","Manufacturing",
//				"Media","Media & Communications","Niche Industrials","Not For Profit","Other","Recreation","Retail","Shipping","Technology","Telecommunications",
//				"Transportation","Utilities","Semi Conductor"};
//		
//		String[] type = {"Analyst","Competitor","Customer","Integrator","Investor","Partner","Other","Press","Prospect","Reseller"};
//		
//		String[] AccountSource = {"Advertisement","Employee Referral","External Referral","Public Relations","Seminar - Internal","Seminar - Partner",
//				"Trade Show","Word of mouth","Partner","Web","Other"};
		
		String[] type = {"TTest1","TTest2","TTest3"};
		String[] AccountSource = {"ASTest1","ASTest2","ASTest3"};

		
		for(int i=0;i<3;i++) {
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
			String[] fields =null;
			String fieldName =null;
			object obj =object.Institution;
			
				log(LogStatus.PASS, "Going to check and Add tab for " + obj.toString() + " object", YesNo.No);
				
					if (setup.searchStandardOrCustomObject(projectName, mode, obj)) {
						log(LogStatus.PASS, obj + " object has been opened in setup page", YesNo.No);
						CommonLib.ThreadSleep(3000);
						
			
							if(i==0) {
								fields =industry;
								fieldName ="Industry";
							}else if(i==1) {
								fields =type;
								fieldName ="Type";
							}else {
								fields=AccountSource;
								fieldName ="Account Source";
							}
						// industry
						if (setup.clickOnObjectFeature(projectName, mode, obj,ObjectFeatureName.FieldAndRelationShip)) {
							log(LogStatus.PASS, "clicked on FieldAndRelationShip of object feature of "+ obj.toString() + " object", YesNo.No);
							
							if (CommonLib.sendKeysAndPressEnter(driver,fr.getQucikSearchInFieldAndRelationshipPage(50), fieldName, "Field",action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO, "Field value has been passed in " + "Industry", YesNo.No);
								CommonLib.ThreadSleep(6000);
								xpath = "//span[text()='" + fieldName + "']";
								ele = FindElement(driver, xpath, fieldName + "xpath", action.SCROLLANDBOOLEAN, 30);
								if (CommonLib.click(driver, ele, fieldName + " field", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "clicked  on  Field" + fieldName, YesNo.No);
									
									for(String value:fields) {

										if(fr.activateOrAddPicklistValueOfField("", fieldName, value, Condition.activate)) {
											log(LogStatus.PASS, value+" :value activated or created sucessfully ", YesNo.No);

										}else {
											log(LogStatus.FAIL,
													value+" :value Not activated and Not created sucessfully ",
													YesNo.Yes);
											sa.assertTrue(false,
													value+" :value Not activated and Not created sucessfully ");
											
										}
										
									}
									if(i==0) {
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Activated_"+CommonVariables.industryAactivatedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Activated_"+CommonVariables.industryAactivatedFields.toString()+" :varibale Created", YesNo.No);

										}
										CommonLib.ThreadSleep(2000);
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Added_"+CommonVariables.industryAddedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Added_"+CommonVariables.industryAddedFields.toString()+" :varibale Created", YesNo.No);

										}
									}else if(i==1) {
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Activated_"+CommonVariables.typeAactivatedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Activated_"+CommonVariables.typeAactivatedFields.toString()+" :varibale Created", YesNo.No);

										}
										CommonLib.ThreadSleep(2000);
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Added_"+CommonVariables.typeAddedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Added_"+CommonVariables.typeAddedFields.toString()+" :varibale Created", YesNo.No);

										}
									}else {
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Activated_"+CommonVariables.accountSourceAactivatedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Activated_"+CommonVariables.accountSourceAactivatedFields.toString()+" :varibale Created", YesNo.No);

										}
										CommonLib.ThreadSleep(2000);
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Added_"+CommonVariables.accountSourceAddedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Added_"+CommonVariables.accountSourceAddedFields.toString()+" :varibale Created", YesNo.No);

										}
									}
									
									if (parentWindow != null) {

										driver.close();
										driver.switchTo().window(parentWindow);
										parentWindow=null;
									}

								}else {
									log(LogStatus.ERROR, "Could not click on the " + fieldName, YesNo.Yes);
									sa.assertTrue(false, "Not able to click on Record type of object feature of "+ obj + " object");
								}
							} else {
								log(LogStatus.ERROR, "Could not pass the Field value " + fieldName, YesNo.Yes);
								sa.assertTrue(false,"Not able to click on Record type of object feature of " + obj + " object");
							}
							
						} else {
							log(LogStatus.FAIL,"Not able to click on Record type of object feature of " + obj + " object",YesNo.Yes);
							sa.assertTrue(false,"Not able to click on Record type of object feature of " + obj + " object");
						}
						
						
						
					} else {
						log(LogStatus.FAIL, "Not able to open " + obj + " object", YesNo.Yes);
						sa.assertTrue(false, "Not able to open " + obj + " object");
			
			
					}
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			
		}
		
		}
		

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();

	}
	
	///// Pre-check ///
	
	@Test(priority =2 ,enabled=false)
	public void verifydeleteAndDectivatePicklistValueAfterDeploymentforObjects() {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		FieldAndRelationshipPageBusinessLayer fr = new FieldAndRelationshipPageBusinessLayer(driver);
		
		String parentWindow = null;
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		String xpath =null;
		WebElement ele = null;
		
		for(int i=0;i<3;i++) {
		try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
			String fieldName =null;
			

			object obj =object.Institution;
			
				log(LogStatus.PASS, "Going to check and Add tab for " + obj.toString() + " object", YesNo.No);
				
					if (setup.searchStandardOrCustomObject(projectName, mode, obj)) {
						log(LogStatus.PASS, obj + " object has been opened in setup page", YesNo.No);
						CommonLib.ThreadSleep(3000);
						
			
							if(i==0) {
								fieldName ="Industry";
								
							}else if(i==1) {
								fieldName ="Type";
							}else {
								fieldName ="Account Source";
							}
						// industry
						if (setup.clickOnObjectFeature(projectName, mode, obj,ObjectFeatureName.FieldAndRelationShip)) {
							log(LogStatus.PASS, "clicked on FieldAndRelationShip of object feature of "+ obj.toString() + " object", YesNo.No);
							
							if (CommonLib.sendKeysAndPressEnter(driver,fr.getQucikSearchInFieldAndRelationshipPage(50), fieldName, "Field",action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO, "Field value has been passed in " + "Industry", YesNo.No);
								CommonLib.ThreadSleep(6000);
								xpath = "//span[text()='" + fieldName + "']";
								ele = FindElement(driver, xpath, fieldName + "xpath", action.SCROLLANDBOOLEAN, 30);
								if (CommonLib.click(driver, ele, fieldName + " field", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "clicked  on  Field" + fieldName, YesNo.No);
									CommonLib.ThreadSleep(3000);
								String text	 = FindElement(driver, "//th[@scope='row' and contains(text(),'"+fieldName+"Activated_"+"')]", fieldName + "xpath", action.SCROLLANDBOOLEAN, 30).getText();
								String text2	 = FindElement(driver, "//th[@scope='row' and contains(text(),'"+fieldName+"Added_"+"')]", fieldName + "xpath", action.SCROLLANDBOOLEAN, 30).getText();

								String[] deactivated =text.split("_")[1].replaceAll("[", "").replaceAll("]", "").split(",");	
								String[] deleted =text2.split("_")[1].replaceAll("[", "").replaceAll("]", "").split(",");	
								
									for(Object valueObj:deactivated) {
										String value =valueObj.toString();
										if(fr.deactivatePicklistValueOfField("", fieldName, value, Condition.deactivate)) {
											log(LogStatus.PASS, value+" :value deactivated and revert action sucessfully ", YesNo.No);
											
										}else {
											log(LogStatus.FAIL,
													value+" :value Not deactivated and revert action not done ",
													YesNo.Yes);
											sa.assertTrue(false,
													value+" :value Not deactivated and revert action not done ");
											
										}
										
									}
									
									for(Object delete:deleted) {
										
										String value = delete.toString();
										if(fr.deletePicklistOptionAndReplaceValue(projectName, fieldName, value, xpath, null)) {
											log(LogStatus.PASS, value+" :value deleted and revert action successfully ", YesNo.No);
											
											
										}else {
											log(LogStatus.FAIL,
													value+" :value Not deleted as for revert action ",
													YesNo.Yes);
											sa.assertTrue(false,
													value+" :value Not activated and Not created sucessfully ");
											
										}
									}

									if (parentWindow != null) {

										driver.close();
										driver.switchTo().window(parentWindow);
										parentWindow=null;
									}

								}else {
									log(LogStatus.ERROR, "Could not click on the " + fieldName, YesNo.Yes);
									sa.assertTrue(false, "Not able to click on Record type of object feature of "+ obj + " object");
								}
							} else {
								log(LogStatus.ERROR, "Could not pass the Field value " + fieldName, YesNo.Yes);
								sa.assertTrue(false,"Not able to click on Record type of object feature of " + obj + " object");
							}
							
						} else {
							log(LogStatus.FAIL,"Not able to click on Record type of object feature of " + obj + " object",YesNo.Yes);
							sa.assertTrue(false,"Not able to click on Record type of object feature of " + obj + " object");
						}
						
						
						
					} else {
						log(LogStatus.FAIL, "Not able to open " + obj + " object", YesNo.Yes);
						sa.assertTrue(false, "Not able to open " + obj + " object");
			
			
					}
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
			
		}
		
		}
		

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
		}
		sa.assertAll();

	}
	
 @Test(priority = 3,enabled =false)
 public void VerifyScheduleUsageMetrics () {

 String projectName = "";
 HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
 SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
 BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
 String parentWindow = null;
 String domainurl = "";	
 CommonLib.refresh(driver);
	CommonLib.ThreadSleep(3000);
	try {
			CommonLib.ThreadSleep(3000);
			if (home.clickOnSetUpLink()) {

				parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2");
					log(LogStatus.FAIL,
							"No new window is open after click on setup link in lighting mode so cannot create CRM User2",
							YesNo.Yes);
					exit("No new window is open after click on setup link in lighting mode so cannot create CRM User2");
				}
			}
		if (setup.searchStandardOrCustomObject(projectName, mode,  object.Scheduled_Jobs)) {
			log(LogStatus.PASS,  object.Scheduled_Jobs + " object has been opened in setup page", YesNo.Yes);
			CommonLib.ThreadSleep(3000);
			
		if (setup.CreateHelpMenu(projectName, mode,"Navatar Help","View Our User Guide",domainurl, 10)) {
			//flag1 = true;
			log(LogStatus.PASS, "able to setup ulr in help menu" , YesNo.Yes);
		}else {
			log(LogStatus.FAIL, "Not able to setup ulr in help menu", YesNo.Yes);
			sa.assertTrue(false, "Not able to setup ulr in help menu");
		}

	} else {
		log(LogStatus.FAIL, "Not able to open " + object.Help_Menu + " object", YesNo.Yes);
		sa.assertTrue(false, "Not able to open " + object.Help_Menu + " object");
	}
}

catch (Exception e) {
	if (parentWindow != null) {

		driver.close();
		driver.switchTo().window(parentWindow);
	}

}

if (parentWindow != null) {

	driver.close();
	driver.switchTo().window(parentWindow);
}
sa.assertAll();

}
}



	
