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
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.HTMLReporter;
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
	
	@Test(priority =1 ,enabled=true)
	public void reportConfig() {
		
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		String parentWindow = null;
		String id = "";
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
			if (setup.searchStandardOrCustomObject("", mode, object.Company_Information)) {
				log(LogStatus.PASS, object.Company_Information.toString() + " object has been opened in setup page", YesNo.Yes);
				CommonLib.ThreadSleep(5000);
			
				switchToFrame(driver,50, bp.getenterpriseeditionFrame(50));
				CommonLib.ThreadSleep(5000);
				String xpath = "//td[@class='labelCol' and contains(text(),'Organization ID')]/following-sibling::td";
				WebElement ele = FindElement(driver, xpath, "Orgnization id", action.SCROLLANDBOOLEAN, 10);
				id = ele.getText();
				CommonVariables.orgID=id.trim();
				
				log(LogStatus.PASS, "Successfully get the organization ID:"+CommonVariables.orgID, YesNo.Yes);

			}else {
				log(LogStatus.FAIL,object.Company_Information.toString() + " object has been opened in setup page", YesNo.Yes);
				sa.assertTrue(false,object.Company_Information.toString() + " object has been opened in setup page");
			
			}
		
			} catch (Exception e) {
				if (parentWindow != null) {

					driver.close();
					driver.switchTo().window(parentWindow);
					parentWindow = null;
				}
				sa.assertAll();
			}

			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
				parentWindow = null;
			}
			DateFormat dateFormat = new SimpleDateFormat("MM_dd_YYYY");
			DateFormat dateFormat1 = new SimpleDateFormat("HH_MM");
			Date date = new Date();
			String dateTime =dateFormat.format(date).toUpperCase()+"_TIME_"+dateFormat1.format(date).toUpperCase();
			extentReport = new ExtentReports(
					System.getProperty("user.dir") + "/Reports/"+orgID+"_"+orgName+"_"+dateTime+".html",
					true);
			
	}
	
	@AfterTest
	public void after() {
		f.setVisible(false);
		f.dispose();
		
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
		String[] industry = {"Agriculture","Apparel","Banking","Biotechnology","Chemicals","Communications","Construction","Consulting",
				"Education","Electronics","Energy","Engineering","Entertainment","Environmental","Finance","Food & Beverage","Government","Healthcare",
				"Hospitality","Insurance","Machinery","Manufacturing","Media","Not For Profit","Other","Recreation","Retail","Shipping","Technology"
				,"Telecommunications","Transportation","Utilities","Business Services","Aerospace & Defense","Advertising"};
		
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
		
			String api =object.Account.toString();
			
				log(LogStatus.PASS, "Going to check and Add tab for " + api.toString() + " object", YesNo.No);
						if(setup.searchStandardOrCustomObjectApi(api,20)) {
							log(LogStatus.PASS, api + " object has been opened in setup page", YesNo.Yes);
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
							
							if (setup.clickOnObjectFeatureUsingAPIName(environment, mode, api,
									ObjectFeatureName.FieldAndRelationShip)) {
								log(LogStatus.PASS, "clicked on page layout of object feature of "
										+ api + " object", YesNo.Yes);
								
							if (CommonLib.sendKeysAndPressEnter(driver,fr.getQucikSearchInFieldAndRelationshipPage(50), fieldName, "Field",action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO, "Field value has been passed in " + "Industry", YesNo.No);
								CommonLib.ThreadSleep(6000);
								xpath = "//span[text()='" + fieldName + "']";
								ele = FindElement(driver, xpath, fieldName + "xpath", action.SCROLLANDBOOLEAN, 30);
								if (CommonLib.click(driver, ele, fieldName + " field", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "clicked  on  Field" + fieldName, YesNo.No);
									
									for(String value:fields) {
										
											CommonLib.ThreadSleep(3000);
											CommonLib.switchToFrame(driver, 30,fr.getaddPicklistIFrame(60));
											CommonLib.ThreadSleep(3000);
										if(fr.activateOrAddPicklistValueOfField("", fieldName, value, Condition.activate,true)) {
											log(LogStatus.PASS, value+" :value activated or created sucessfully ", YesNo.No);

										}else {
											log(LogStatus.FAIL,
													value+" :value Not activated and Not created sucessfully ",
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
									sa.assertTrue(false, "Not able to click on Record type of object feature of "+ api + " object");
								}
							} else {
								log(LogStatus.ERROR, "Could not pass the Field value " + fieldName, YesNo.Yes);
								sa.assertTrue(false,"Not able to click on Record type of object feature of " + api + " object");
							}
							
						} else {
							log(LogStatus.FAIL,"Not able to click on Record type of object feature of " + api + " object",YesNo.Yes);
							sa.assertTrue(false,"Not able to click on Record type of object feature of " + api + " object");
						}
						
						
						
					} else {
						log(LogStatus.FAIL, "Not able to open " + api + " object", YesNo.Yes);
						sa.assertTrue(false, "Not able to open " + api + " object");
			
			
					}
		} catch (Exception e) {
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
				parentWindow=null;
				
			}
			
		}
		
		}
		

		if (parentWindow != null) {

			driver.close();
			driver.switchTo().window(parentWindow);
			parentWindow=null;
		}
		sa.assertAll();

	}
	
	@Test(priority =3 ,enabled=true)
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
			CommonLib.ThreadSleep(3000);
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
			if (parentWindow != null) {

				driver.close();
				
				driver.switchTo().window(parentWindow);
				parentWindow = null;
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
		

	
		sa.assertAll();

	}
	
	
	
	
	// out of scope
	
	
	/// Pre-check ///
		@Test(priority = 3,enabled =false)
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
					CommonLib.ThreadSleep(3000);
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
					parentWindow=null;
				}
				sa.assertAll();
			}
			if (parentWindow != null) {

				driver.close();
				driver.switchTo().window(parentWindow);
				parentWindow=null;
			}
			sa.assertAll();
		}

}



	
