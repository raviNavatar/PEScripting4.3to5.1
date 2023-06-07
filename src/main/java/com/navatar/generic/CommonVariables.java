
package com.navatar.generic;

import static com.navatar.generic.BaseLib.phase1DataSheetFilePath;
import static com.navatar.generic.BaseLib.ResearchDataSheetFilePath;
import static com.navatar.generic.BaseLib.AcuityDataSheetFilePath;
import static com.navatar.generic.CommonLib.getDateAccToTimeZone;
import static com.navatar.generic.CommonLib.previousOrForwardDate;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.navatar.generic.EnumConstants.ActivityRelatedLabel;
import com.navatar.generic.EnumConstants.excelLabel;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.BasePageErrorMessage;




/**
 * @author Ankur Rana
 * 	
 */
public class CommonVariables {
	//	public static String abc;

	public static double dealReceivedScore=1.0,NDASignedScore=1.0,managementMeetingScore=3.0,ioiScore=3.0,loiScore=5.0;
	public static double dueDiligenceScore=5.0,closedScore=5.0,declinedDeadScore=5.0,parkedScore=5.0;
	public static List<String> industryAddedFields =new ArrayList<>();
	public static String orgName,orgID;
	public static List<String> industryAactivatedFields =new ArrayList<>();

	public static List<String> typeAddedFields =new ArrayList<>();
	public static List<String> typeAactivatedFields =new ArrayList<>();

	public static List<String> accountSourceAddedFields =new ArrayList<>();
	public static List<String> accountSourceAactivatedFields =new ArrayList<>();


	public static String todaysDateSingleMonth,todaysDateSingleDate,todaysDateSingleDateSingleMonth;
	public static String URL,todaysDate,todaysDate1,tomorrowsDate,dayBeforeYesterdaysDate,dayAfterTomorrowsDate,todaysDateEurope,todaysDateddmm,todaysDateSingleDigit,todaysDateNewZealand,yesterdaysDate;
	public static String browserToLaunch,superAdminUserName,superAdminRegistered,tabCustomObj,tabCustomObjAPIName,appVersion,mode;
	public static String tabObj1,tabObj2,tabObj3,tabObj4,tabObj5,tabObj6,tabObj7,tabObj8Coverage,tabObj9,tabCustomObjField;
	public static String environment,AppDescription,AppDeveloperName,rgOutLookUser1Password,rgOutLookUser1Email,outLookAddress,SDG,adminPassword;
	
	public CommonVariables(Object obj) {
		//TODO Auto-generated constructor stub
		AppListeners.appLog.info("Kindly hold on starting variable intialization........");
		long StartTime = System.currentTimeMillis();
		todaysDate=getDateAccToTimeZone(BasePageErrorMessage.AmericaLosAngelesTimeZone, "MM/dd/YYYY");
		todaysDateSingleDate=getDateAccToTimeZone(BasePageErrorMessage.AmericaLosAngelesTimeZone, "MM/d/YYYY");
		todaysDateSingleDateSingleMonth=getDateAccToTimeZone(BasePageErrorMessage.AmericaLosAngelesTimeZone, "M/d/YYYY");
		todaysDateSingleMonth=getDateAccToTimeZone(BasePageErrorMessage.AmericaLosAngelesTimeZone, "M/dd/YYYY");
		yesterdaysDate=previousOrForwardDate(-1, "M/d/YYYY");
		dayBeforeYesterdaysDate=previousOrForwardDate(-2, "M/d/YYYY");
		tomorrowsDate=previousOrForwardDate(1, "M/d/YYYY");
		dayAfterTomorrowsDate=previousOrForwardDate(2, "M/d/YYYY");

		//****************************************************************	SuperAdmin And CRM User **********************************************************//
		
		mode="Lightning";
		browserToLaunch = "safari";
		
		//****************************************************************	Toggle Variable **********************************************************//


		
		System.err.println("");
		AppListeners.appLog.info("Done with intialization. Enjoy the show.\nTotal Time Taken: "+((System.currentTimeMillis()-StartTime)/1000)+" seconds.");


	}
	
	
}