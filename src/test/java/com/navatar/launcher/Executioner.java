package com.navatar.launcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.navatar.generic.AppListeners;
import com.navatar.generic.CommonLib;
import static com.navatar.generic.CommonLib.*;
import com.navatar.generic.ExcelUtils;
import com.navatar.scripts.AcuityTabAddition;
import com.navatar.generic.EnumConstants.excelLabel;

public class Executioner {

	public Executioner() {
		// TODO Auto-generated constructor stub
		main(null);
		
	}
public static void testNgXmlSuite( String browser, String platform, String mode) {
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
		List<String> listenerClasses = new ArrayList<String>();
		Map<String, String> parameters = new LinkedHashMap<String, String>();
		XmlClass cl = new XmlClass("com.navatar.scripts.AcuityTabAddition");
		XmlSuite suite = new XmlSuite();
		XmlTest test = new XmlTest(suite);
		parameters.put("browser", browser);
		parameters.put("environment", platform);
		parameters.put("mode", mode);
		listenerClasses.add("com.navatar.generic.AppListeners");
		suite.setName("NavatarSuite");
		suite.setListeners(listenerClasses);
		test.setName("Test");
		test.setParameters(parameters);
		classes.add(cl);
		test.setXmlClasses(classes);
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		//tng.setTestClasses(new Class[] {AcuityTabAddition.class});
		System.err.println(CommonLib.getSystemDate("hh:mm:ss"));
		tng.run();
 
		
	}

	public static void main(String[] args) {
		//CommonLib.execution();
//		String browser = ExcelUtils.readDataFromPropertyFile("Browser");
//		String platform = ExcelUtils.readDataFromPropertyFile("Environment");
//		String mode = ExcelUtils.readDataFromPropertyFile("Mode");
		String browser ="chrome";
		String platform = "Sandbox";
		String mode ="Lightning";
		testNgXmlSuite( browser, platform, mode);

	}

}
