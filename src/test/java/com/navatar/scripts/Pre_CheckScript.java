package com.navatar.scripts;

import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.click;
import static com.navatar.generic.CommonLib.clickUsingJavaScript;
import static com.navatar.generic.CommonLib.exit;
import static com.navatar.generic.CommonLib.log;
import static com.navatar.generic.CommonLib.switchOnWindow;
import static com.navatar.generic.CommonLib.switchToDefaultContent;
import static com.navatar.generic.CommonLib.switchToFrame;
import static com.navatar.generic.CommonVariables.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EnumConstants.Condition;
import com.navatar.generic.EnumConstants.ObjectFeatureName;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.object;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.FieldAndRelationshipPageBusinessLayer;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.SetupPageBusinessLayer;
import com.relevantcodes.extentreports.LogStatus;

public class Pre_CheckScript extends BaseLib {
	static int interval;
	String markup = "<html><font=inherit color=#000000 size=+0> "+ "Do not touch keyboard or mouse or disconnect from internet or refresh the page" + "</html>";
		String markup2 = "<html><font=inherit color=#000000 size=+1> "+"Script Execution is in progress!! " + "</html>";
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
	
	/// Pre-check ///
	@Test(priority = 1,enabled =true)
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

	@Test(priority =2 ,enabled=true)
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
		String[] industry = {"Advanced Material","Agriculture","Apparel","Banking","Biotechnology","Business Services","Chemicals","Cleantech",
				"Communications","Construction","Consulting","Consumer","Education","Electronics","Energy","Engineering","Entertainment","Environmental",
				"Finance","Financial Services","Food Beverage","Government","Healthcare","Hospitality","Insurance","Leisure","Machinery","Manufacturing",
				"Media","Media & Communications","Niche Industrials","Not For Profit","Other","Recreation","Retail","Shipping","Technology","Telecommunications",
				"Transportation","Utilities","Semi Conductor"};
		
		String[] type = {"Analyst","Competitor","Customer","Integrator","Investor","Partner","Other","Press","Prospect","Reseller"};
		
		String[] AccountSource = {"Advertisement","Employee Referral","External Referral","Public Relations","Seminar - Internal","Seminar - Partner",
				"Trade Show","Word of mouth","Partner","Web","Other"};
		
		
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
										
											CommonLib.ThreadSleep(5000);
											CommonLib.switchToFrame(driver, 20,fr.getaddPicklistIFrame(60));
											CommonLib.ThreadSleep(5000);
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
										
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Added_"+CommonVariables.industryAddedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Added_"+CommonVariables.industryAddedFields.toString()+" :varibale Created", YesNo.No);

										}
									}else if(i==1) {
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Activated_"+CommonVariables.typeAactivatedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Activated_"+CommonVariables.typeAactivatedFields.toString()+" :varibale Created", YesNo.No);

										}
										
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Added_"+CommonVariables.typeAddedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Added_"+CommonVariables.typeAddedFields.toString()+" :varibale Created", YesNo.No);

										}
									}else {
										if(fr.activateOrAddPicklistValueOfField("", fieldName, fieldName+"Activated_"+CommonVariables.accountSourceAactivatedFields.toString(), Condition.activate)) {
											log(LogStatus.PASS, fieldName+"Activated_"+CommonVariables.accountSourceAactivatedFields.toString()+" :varibale Created", YesNo.No);

										}
										
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
	
	
	@Test(priority =2 ,enabled=false)
	public void createMetadataOfAddAndActivatePicklistValueBeforeDeploymentforObjects() {
		String projectName = "";
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		FieldAndRelationshipPageBusinessLayer fr = new FieldAndRelationshipPageBusinessLayer(driver);
		
		String parentWindow = null;
		CommonLib.refresh(driver);
		CommonLib.ThreadSleep(3000);
		
		String fieldName="";
		String list ="";
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
			
			if(i==0) {
				
				fieldName ="IndustryPicklist";
				list =CommonVariables.industryAddedFields.toString()+"<break>"+CommonVariables.industryAactivatedFields.toString(); 
				
			}else if(i==1) {
				fieldName ="TypePicklist";
				list =CommonVariables.typeAddedFields.toString()+"<break>"+CommonVariables.typeAactivatedFields.toString(); 

			}else {
				fieldName ="AccountSourcePicklist";
				list =CommonVariables.accountSourceAddedFields.toString()+"<break>"+CommonVariables.accountSourceAactivatedFields.toString(); 

			}
			if(setup.CreateNewCustomMetaData(fieldName, list, 30)) {
				log(LogStatus.PASS,
						fieldName+" :Custom metadata added",
						YesNo.No);
				
			}else {
				
				log(LogStatus.FAIL,
						fieldName+" :Custom metadata not added",
						YesNo.Yes);
				sa.assertTrue(false,
						fieldName+" :Custom metadata not added");
				
			}
			
			
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				
				driver.switchTo().window(parentWindow);
				parentWindow = null;
			}
			continue;
			
		}
		
		}
		

		if (parentWindow != null) {

			driver.close();
			
			driver.switchTo().window(parentWindow);
			parentWindow = null;
		}
		sa.assertAll();

	}
	
	
}



	
