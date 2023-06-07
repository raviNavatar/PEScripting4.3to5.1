package com.navatar.generic;

import java.awt.Color;
import java.awt.Font;
import java.beans.ExceptionListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.*;

import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.pageObjects.BasePageBusinessLayer;
//import com.navatar.generic.CommonLib.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

/**
 * 
 * @author Ankur Rana
 * @description Declared listeners and there behaviour according to the execution
 */

public class AppListeners extends By implements ITestListener, IInvokedMethodListener, WebDriverEventListener, ExceptionListener,
		ITestNGListener, IExecutionListener,SearchContext,FindsByXPath {

	public static int iPassCount;
	public static int iFailCount;
	public static int iskipCount;
	public static Logger appLog;
	public static Map<String, String> status = new LinkedHashMap<String, String>();
	public static int exe;
	public static ExtentReports extentReport;
	public static ExtentTest extentLog;
	public static String executingMethod;
	public static Test test;
	public Field f;
	public static String currentlyExecutingTC;
	public static String startTime;
	public static String EndTime;
	public static int numberOfExecution=0;
	public static List<String> failedTestcase = new ArrayList<>();
	
	public AppListeners() {
		DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
		Date date = new Date();
		System.setProperty("LongTimeDate", dateFormat.format(date));
		System.setProperty("ProjectLocation", System.getProperty("user.dir"));
		System.setProperty("ProjectName", "PE_DocumentTaggingLog");
		appLog = Logger.getLogger(this.getClass());
//		extentReport = new ExtentReports(
//				System.getProperty("user.dir") + "/Reports/ExtentReports/ExtentLog" + dateFormat.format(date) + ".html",
//				true);
//		try {
//			extentReport.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
//			extentReport.addSystemInfo("User Name", System.getProperty("user.name"));
//			extentReport.addSystemInfo("Environment", ExcelUtils.readDataFromPropertyFile("Environment"));
//			extentReport.addSystemInfo("Mode", ExcelUtils.readDataFromPropertyFile("Mode"));
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\ConfigFiles\\extent-config.xml"));
	}

	public String getTestName(Method m) {
		return m.getName();
	}

	@Override
	public void onTestStart(ITestResult result) {
		currentlyExecutingTC=result.getMethod().getMethodName();
//		appLog.info/*CommonLib.log(LogStatus.INFO, "*************************Starting: "+result.getMethod().getMethodName()+"**********************", YesNo.No);*/("\n\n*************************Starting: "+result.getMethod().getMethodName()+"**********************\n\n");
		extentLog = extentReport.startTest(result.getMethod().getMethodName());
		CommonLib.log(LogStatus.INFO, "\n\n*************************Starting: "+result.getMethod().getMethodName()+"**********************\n\n", YesNo.No);
		executingMethod = result.getMethod().getMethodName();
		startTime = new Timestamp(new Date().getTime()).toLocaleString();
		appLog.info("Test Case: " + result.getMethod().getMethodName() + " Started At: " + new Timestamp(new Date().getTime()));
		System.out.println("Test Case: " + result.getMethod().getMethodName() + " Started At: " + new Timestamp(new Date().getTime()));

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		

		String screenshot=CommonLib.screenshot(result.getName());
		extentLog.log(LogStatus.PASS, result.getMethod().getMethodName()+" is passed.","");
		iPassCount++;
		status.put(result.getMethod().getMethodName(), "Pass");
		appLog.info("Pass: " + result.getMethod().getMethodName());
		System.out.println("Pass: " + result.getMethod().getMethodName());

		extentReport.endTest(extentLog);
		appLog.info("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"  At: " + new Timestamp(new Date().getTime())+"****************\n");
		System.out.println("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"  At: " + new Timestamp(new Date().getTime())+"****************\n");
         
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		try{
			String screenshot=CommonLib.screenshot(result.getMethod().getMethodName());
			CommonLib.log(LogStatus.FAIL, result.getMethod().getMethodName()+" is failed.", YesNo.No);
		} catch (Exception e){
			CommonLib.log(LogStatus.INFO, "Screenshot cannot be taken because window is closed.", YesNo.No);
		}
		// extentLog.log(LogStatus.FAIL, result.getName()+" is
		// failed.",extentLog.addScreenCapture(screenshot));
//		CommonLib.exit("Priority is high");
		iFailCount++;
		String msg=result.getThrowable().getMessage();
		if(msg==null){
			status.put(result.getMethod().getMethodName(), "Fail: " + CommonLib.errorMessage);
			appLog.fatal("Failed Test Case: " + result.getMethod().getMethodName() + " \nReason: " + CommonLib.errorMessage);
			System.out.println("Failed Test Case: " + result.getMethod().getMethodName() + " \nReason: " + CommonLib.errorMessage);

		} else {
			status.put(result.getMethod().getMethodName(), "Fail: " + result.getThrowable().getMessage());
			appLog.fatal("Failed Test Case: " + result.getMethod().getMethodName() + " \nReason: " + result.getThrowable().getMessage());
			System.out.println("Failed Test Case: " + result.getMethod().getMethodName() + " \nReason: " + result.getThrowable().getMessage());

		}
		extentReport.endTest(extentLog);
		failedTestcase.add(" : " + result.getMethod().getMethodName());
		appLog.info("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"****************\n");
		System.out.println("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"****************\n");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 String screenshot=CommonLib.screenshot(result.getMethod().getMethodName());
		 extentLog.log(LogStatus.SKIP, result.getMethod().getMethodName()+" is skipped.",extentLog.addScreenCapture(screenshot));
		iskipCount++;
		status.put(result.getMethod().getMethodName(), "Skip: " + result.getThrowable().getMessage());
//		appLog.fatal("Skipped Test Case: " + result.getMethod().getMethodName() + " Skip Reason: " + result.getThrowable().getMessage()
//				+ "\n Stack Trace");
		CommonLib.log(LogStatus.SKIP, "Skipped Test Case: " + result.getMethod().getMethodName() + " Skip Reason: " + result.getThrowable().getMessage()
				+ "\n Stack Trace", YesNo.Yes);
		extentReport.endTest(extentLog);
		appLog.info("\n************************Finidshed TestCase: "+result.getMethod().getMethodName()+"****************\n");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// System.out.println(result.);
	}

	@Override
	public void onStart(ITestContext context) {
		
	}


	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		CommonLib.waitForPageLoad(driver);

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
	//	CommonLib.waitForPageLoad(driver);
	//	CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
		
		CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadprogress']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='processingImg']/div", 120);
		
		
		
		
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
			CommonLib.waitForPageLoad(driver);
	//		CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
	//		CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='loadprogress']/div", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']/div", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='processingImg']/div", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@class='slds-spinner_container']/parent::div[contains(@style,'block')]", 120);
			CommonLib.checkForLoaderImage(driver, "//section//lightning-spinner[@class='slds-spinner_container']", 120);
			
			
			
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
			CommonLib.waitForPageLoad(driver);
	//		CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
	//		CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
			CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='loadprogress']/div", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']/div", 120);
			CommonLib.checkForLoaderImage(driver, "//div[@id='processingImg']/div", 120);
	} 

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
			CommonLib.waitForPageLoad(driver);
	//		CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
	//		CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
	} 

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		CommonLib.waitForPageLoad(driver);
	//	CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
	//	CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
		CommonLib.checkForLoaderImage(driver, "//img[@src='/resource/1499340792000/DR_CRMFinal/DR_CRMFinal/images/processing-image.gif']", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadprogress']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='processingImg']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@class='slds-spinner_container']/parent::div[contains(@style,'block')]", 120);
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		CommonLib.waitForPageLoad(driver);
//		CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
//		CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadprogress']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='processingImg']/div", 120);
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		CommonLib.waitForPageLoad(driver);
//		CommonLib.checkForLoaderImage(driver, "//div[@role='status']//span[text()='Loading...']", 120);
//		CommonLib.checkForLoaderImage(driver, "//div[@role='alert']//span[text()='Loading...']", 120);
		CommonLib.checkForLoaderImage(driver, "//img[contains(@src,'processing-image.gif')]", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='loadprogress']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='blurred']/div", 120);
		CommonLib.checkForLoaderImage(driver, "//div[@id='processingImg']/div", 120);
		
		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
//		extentReport.endTest(extentLog);
//		System.err.println("OnException: ");
//		CommonLib.failedMethod(throwable);
	}

	@Override
	public void exceptionThrown(Exception e) {
		// TODO Auto-generated method stub
		System.err.println("Excepetion Thrown: ");
//		CommonLib.failedMethod(e);

	}

	@Override
	public void onExecutionStart() {
		
		JFrame frame = new JFrame();
		try {
			frame.setResizable(true);
		frame.setSize(1000, 700);
        frame.setAlwaysOnTop(true);
        UIManager.put("OptionPane.messageForeground", Color.black);
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
		          "Arial", Font.BOLD, 13)));

        Object[] options = {"Production", "Sandbox" };
        //...and passing `frame` instead of `null` as first parameter
        Object selectionObject = JOptionPane.showInputDialog(frame, " All applications must be closed to start the process. "+ "\n"+ "\n"+" Please Select Login Environment : ", "Please respond!", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String selectionString = selectionObject.toString(); 
         
		if(selectionString.equalsIgnoreCase("Sandbox")) {
			CommonVariables.environment="Sandbox";
			CommonVariables.URL = "test.salesforce.com";
			CommonVariables.orgName="_SANDBOX_";
		}else if(selectionString.equalsIgnoreCase("Production")){
			CommonVariables.environment="Testing";
			CommonVariables.URL = "login.salesforce.com";
			CommonVariables.orgName="_PROD_";
		}
		}catch (NullPointerException e) {
			 String markup = "<html><font=Arial color=#FF0000 size=+0> " + "You have NOT initiated the process." + "<br> </br><br> <center>Please execute the script again<center></br></html>";
	         JLabel l = new JLabel(markup);
	         l.setHorizontalAlignment(SwingConstants.CENTER);
	         JPanel p = new JPanel(new java.awt.GridLayout(0, 1));
	         p.add(l);
	         JFrame f = new JFrame("Error");
	         f.setContentPane(p);
	         f.setSize(300,150);
	         f.setLocationRelativeTo(null);
	         f.setVisible(true);
//	         long startTime = System.currentTimeMillis();
//	         while (System.currentTimeMillis() < (startTime + 5000)) {
//	             f.setVisible(true);
//	         }
//	         f.setVisible(false);
//	         f.dispose();
	         //f = null;
	         BaseLib.driver.quit();

		}
	}


	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		
		if(failedTestcase.isEmpty()) {
			new BasePageBusinessLayer(BaseLib.driver).popupPassed();

		}else {
			new BasePageBusinessLayer(BaseLib.driver).popupFailed();;

		}

//		Process process;
//		try {
//			
//			//process = Runtime.getRuntime().exec(System.getProperty("user.dir")+"/killbrowser.bat");
////			process = Runtime.getRuntime().exec(System.getProperty("user.dir")+"/killJRE1.bat");
////			process = Runtime.getRuntime().exec(System.getProperty("user.dir")+"/killJRE2.bat");
////
////			Runtime.getRuntime().exit(0);
////			process.waitFor();
//
//		} catch (IOException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return by.findElement(this);
	}

	@Override
	public List<WebElement> findElements(SearchContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElementByXPath(String using) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElementsByXPath(String using) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
}
