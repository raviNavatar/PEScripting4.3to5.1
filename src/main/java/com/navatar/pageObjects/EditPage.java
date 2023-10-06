package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.PageName;
import com.navatar.generic.EnumConstants.RelatedTab;
import com.navatar.generic.EnumConstants.SDGCreationLabel;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.excelLabel;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;

public class EditPage extends BasePageBusinessLayer {

	public EditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//iframe[@class=' surfaceFrame' or @name='surfaceFrame' or contains(@title,'Surface')]")
	private WebElement editPageFrame;

	/**
	 * @return the editPageFrame
	 */
	public WebElement getEditPageFrame(String projectName, int timeOut) {
		return isDisplayed(driver, editPageFrame, "Visibility", timeOut, "Edit Page Frame");
	}

	@FindBy(xpath = "//*[text()='SDG Config Data Provider']/following-sibling::div/input")
	private WebElement sdgConfigDataProviderTextBox;

	/**
	 * @return the sdgConfigDataProviderTextBox
	 */
	public WebElement getsdgConfigDataProviderTextBox(String projectName, int timeOut) {
		return isDisplayed(driver, sdgConfigDataProviderTextBox, "Visibility", timeOut,
				"sdg Config Data Provider TextBox");
	}

	@FindBy(xpath = "//button[text()='Add Tab']")
	private WebElement addTab;

	public WebElement getAddTab(int timeOut) {
		return isDisplayed(driver, addTab, "Visibility", timeOut, "Add Tab");
	}

	/**
	 * @return the editPageFrame
	 */
	public WebElement getPageTabEle(String tabName, int timeOut) {
		String xpath = "(//*[@class='uiOutputText']/../..//*[@title='" + tabName + "'])[2]";
		WebElement ele = FindElement(driver, xpath, tabName, action.BOOLEAN, timeOut);
		if (ele != null) {
			return isDisplayed(driver, ele, "Visibility", timeOut, tabName);
		} else {
			xpath = "(//*[@class='uiOutputText']/../..//*[@title='tabName'])[1]";
			ele = FindElement(driver, xpath, tabName, action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, tabName);
		}

	}

	@FindBy(xpath = "//*[text()='Tab Label']/../following-sibling::*//select[@class='slds-select']")
	private WebElement subTabDropdownList;

	/**
	 * @return the userProfileDropDownList
	 */
	public WebElement getSubTabDropdownListt(int timeOut) {
		return isDisplayed(driver, subTabDropdownList, "Visibility", timeOut, "Sub Tab Drop Down List");
	}

	@FindBy(xpath = "//button[text()='Done']")
	private WebElement doneButton;

	public WebElement getdoneButton(int timeOut) {
		return isDisplayed(driver, doneButton, "Visibility", timeOut, "Done Button");
	}

	@FindBy(xpath = "//*[text()='Default SDG Toggle']/following-sibling::div/input")
	private WebElement defaultSDGToggleTextBox;

	/**
	 * @return the defaultSDGToggleTextBox
	 */
	public WebElement getDefaultSDGToggleTextBox(String projectName, int timeOut) {
		return isDisplayed(driver, defaultSDGToggleTextBox, "Visibility", timeOut, "Default SDG Toggle TextBox");
	}

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement editPageSaveButton;

	/**
	 * @return the editPageSaveButton
	 */
	public WebElement getEditPageSaveButton(String projectName, int timeOut) {
		return isDisplayed(driver, editPageSaveButton, "Visibility", timeOut, "Edit Page Save Button");
	}

	@FindBy(xpath = "//a[contains(@class,'backButton')]")
	private WebElement editPageBackButton;

	/**
	 * @return the editPageBackButton
	 */
	public WebElement getEditPageBackButton(String projectName, int timeOut) {
		return isDisplayed(driver, editPageBackButton, "Visibility", timeOut, "Edit Page Back Button");
	}

	@FindBy(xpath = "//*[text()='Search...']/..//*[@placeholder='Search...']")
	private WebElement editPageSeachTextBox;

	/**
	 * @return the editPageSeachTextBox
	 */
	public WebElement getEditPageSeachTextBox(String projectName, int timeOut) {
		return isDisplayed(driver, editPageSeachTextBox, "Visibility", timeOut, "Edit Page Search TextBox");
	}


	@FindBy(xpath = "//div[@class='actualNode']//div[@data-label='Navatar Acuity']")
	private WebElement addButtonOfComponent;

	/**
	 * @return the editPageSeachTextBox
	 */
	public WebElement getSectionOfComponent( int timeOut) {
		return isDisplayed(driver, addButtonOfComponent, "Visibility", timeOut, "addButtonOfComponent");
	}

	
	/**
	 * @return the editPageSeachTextBox
	 */
	public WebElement getAddButtonAfterSection( int timeOut) {
		return FindElement(driver, "(//div[@class='actualNode']//div[@data-label='Navatar Acuity']//a[contains(@title,'Insert')])[2]",  "Edit Page Search TextBox",action.BOOLEAN,timeOut);
	}

	
	@FindBy(xpath = "//*[text()='Data Provider']/following-sibling::div//input")
	private WebElement elgDataProviderTextBox;

	/**
	 * @return the elgDataProviderTextBox
	 */
	public WebElement getElgDataProviderTextBox(String projectName, int timeOut) {
		return isDisplayed(driver, elgDataProviderTextBox, "Visibility", timeOut, "ELG Data Provider TextBox");
	}

	@FindBy(xpath = "//*[text()='Title']/..//following-sibling::div//input[@type='text']")
	private WebElement elgTitleTextBox;

	/**
	 * @return the elgTitleTextBox
	 */
	public WebElement getElgTitleTextBox(String projectName, int timeOut) {
		return isDisplayed(driver, elgTitleTextBox, "Visibility", timeOut, "ELG Title TextBox");
	}

	@FindBy(xpath = "//*[text()='Enable Toggle']/..//preceding-sibling::input")
	private WebElement enableToggleCheckBox;

	/**
	 * @return the enableToggleCheckBox
	 */
	public WebElement getEnableToggleCheckBox(String projectName, int timeOut) {
		return isDisplayed(driver, enableToggleCheckBox, "Visibility", timeOut, "Enable Toggle CheckBox");
	}

	@FindBy(xpath = "//*[text()='Field Set Name']/following-sibling::div/input")
	private WebElement fieldSetNameTextBox;

	public WebElement getFieldSetNameTextBox(int timeOut) {
		return isDisplayed(driver, fieldSetNameTextBox, "Visibility", timeOut, "field set name text box");
	}

	@FindBy(xpath = "//a[@aria-describedby='backButton']")
	private WebElement backButton;

	public WebElement getBackButton(int timeOut) {
		return isDisplayed(driver, backButton, "Visibility", timeOut, "back button");
	}

	@FindBy(xpath = "//a[text()='Add Component(s) Here']")
	private WebElement addComponent;

	/**
	 * @return the addComponent
	 */
	public WebElement getAddComponent(String projectName, int timeOut) {
		return isDisplayed(driver, addComponent, "Visibility", timeOut, "Add Component(s) Here");
	}

	@FindBy(xpath = "//*[text()='Data Provider']/following-sibling::div//*[@data-key='search']/../..")
	private WebElement elgDataProviderTextBoxSearchIcon;

	/**
	 * @return the elgDataProviderTextBoxSearchIcon
	 */
	public WebElement getElgDataProviderTextBoxSearchIcon(String projectName, int timeOut) {
		return isDisplayed(driver, elgDataProviderTextBoxSearchIcon, "Visibility", timeOut,
				"ELG Data Provider TextBox Search Box Icon");
	}

	@FindBy(xpath = "//div[@data-label='Navatar Fieldset']/div[@class='toolbox']")
	private WebElement fieldSetCompoentXpath;

	public WebElement getFieldSetCompoentXpath(int timeOut) {
		scrollDownThroughWebelement(driver, fieldSetCompoentXpath, "");
		return fieldSetCompoentXpath;
	}

	@FindBy(xpath = "//*[contains(text(),'Image Field  Name')]/following-sibling::div/input")
	private WebElement imageFieldNameTextBox;

	public WebElement getImageFieldNameTextBox(int timeOut) {
		return isDisplayed(driver, imageFieldNameTextBox, "Visibility", timeOut, "image field name text box");
	}

	@FindBy(xpath = "//button[text()='Activate']")
	private WebElement activateButton;

	/**
	 * @return the activateButton
	 */
	public WebElement getActivateButton(String projectName, int timeOut) {
		return isDisplayed(driver, activateButton, "Visibility", timeOut, "Activate Button");
	}

	@FindBy(xpath = "(//button[text()='Save'])[2]")
	private WebElement editPageSaveButton2;

	/**
	 * @return the editPageSaveButton
	 */
	public WebElement getEditPageSaveButton2(String projectName, int timeOut) {
		return isDisplayed(driver, editPageSaveButton2, "Visibility", timeOut, "Edit Page Save Button2");
	}

	@FindBy(xpath = "//button[text()='Finish']")
	private WebElement finishButton2;

	/**
	 * @return the finishButton2
	 */
	public WebElement getFinishButton2(String projectName, int timeOut) {
		return isDisplayed(driver, finishButton2, "Visibility", timeOut, "Finish Button2");
	}

	public WebElement getFieldTextbox(String projectName, String pageLabel, int timeOut) {
		String label = pageLabel.replace("_", " ");
		String xpath = "//*[text()='" + label + "']/following-sibling::div/input";
		WebElement ele = FindElement(driver, xpath, pageLabel.toString(), action.SCROLLANDBOOLEAN, timeOut);
		return isDisplayed(driver, ele, "Visibility", timeOut, "Finish Button2");
	}

	@FindBy(xpath = "//*[text()='Number of Records to Display']/following-sibling::div[2]")
	private WebElement noOfRecordsErrorMessage;

	/**
	 * @return the finishButton2
	 */
	public WebElement getnoOfRecordsErrorMessage(String projectName, int timeOut) {
		return isDisplayed(driver, noOfRecordsErrorMessage, "Visibility", timeOut, "noOfRecordsErrorMessage");
	}

	@FindBy(xpath = "//span[text()='Expanded']/../..//input[@type='checkbox']")
	private WebElement expandedCheckbox;

	/**
	 * @return the finishButton2
	 */
	public WebElement getexpandedCheckbox(String projectName, int timeOut) {
		return isDisplayed(driver, expandedCheckbox, "Visibility", timeOut, "expandedCheckbox");
	}

	@FindBy(xpath = "//*[@data-id='Field Set Name']/div/*/div[@role='alert']")
	private WebElement noOfRecordsErrorPopup;

	/**
	 * @return the finishButton2
	 */
	public WebElement getnoOfRecordsErrorPopup(String projectName, int timeOut) {
		return isDisplayed(driver, noOfRecordsErrorPopup, "Visibility", timeOut, "noOfRecordsErrorPopup");
	}

	@FindBy(xpath = "//div[@class='error']")
	private WebElement noOfRecordsErrorPopupForAccordion;

	/**
	 * @return the finishButton2
	 */
	public WebElement getnoOfRecordsErrorPopupForAccordion(String projectName, int timeOut) {
		return isDisplayed(driver, noOfRecordsErrorPopupForAccordion, "Visibility", timeOut, "noOfRecordsErrorPopup");
	}

	@FindBy(xpath = "//h2[text()='Error']/../following-sibling::div[contains(@class,'footer')]//button[text()='OK']")
	private WebElement noOfRecordsErrorPopupOK;

	/**
	 * @return the finishButton2
	 */
	public WebElement getnoOfRecordsErrorPopupOK(String projectName, int timeOut) {
		return isDisplayed(driver, noOfRecordsErrorPopupOK, "Visibility", timeOut, "noOfRecordsErrorPopupOK");
	}

	@FindBy(xpath = "//div[contains(@class,'elementProxyContainer')]//a")
	private WebElement calendarOnEditPage;

	/**
	 * @return the finishButton2
	 */
	public WebElement getcalendarOnEditPage(String projectName, int timeOut) {
		return isDisplayed(driver, calendarOnEditPage, "Visibility", timeOut, "calendarOnEditPage");
	}

	@FindBy(xpath = "//button[contains(@class,'addTab')]")
	private WebElement addTabLink;

	/**
	 * @return the addTabLink
	 */
	public WebElement getAddTabEditPageLink(int timeOut) {
		return isDisplayed(driver, addTabLink, "Visibility", timeOut, "Add a Tab Link");
	}

	/**
	 * @return the addTabLink
	 */
	public List<WebElement> getAlreadyAddedTabListEditPage(int timeOut) {
		String xpath = "//div[@class='visualEditorSortableList']//li";
		List<WebElement> list = new ArrayList<WebElement>();

		list = FindElements(driver, xpath, "already added tab list on edit page");
		return list;
	}

	@FindBy(xpath = "//section[@class='visualEditorComponentPropertiesEditorSection']//select")
	private WebElement tablabelDropdownEditPage;

	/**
	 * @return the addTabLink
	 */
	public WebElement getTabLabelDropdownOnEditPage(int timeOut) {
		return isDisplayed(driver, tablabelDropdownEditPage, "Visibility", timeOut, "Add a Tab Link");
	}

	@FindBy(xpath = "//section[@class='visualEditorComponentPropertiesEditorSection']//input")
	private WebElement tablabelInputEditPage;

	/**
	 * @return the addTabLink
	 */
	public WebElement getTabLabelInputOnEditPage(int timeOut) {
		return isDisplayed(driver, tablabelInputEditPage, "Visibility", timeOut, "Add a Tab Link");
	}

	@FindBy(xpath = "//button[contains(@class,'ItemAttributesEditorDone')]")
	private WebElement tablabelDoneButtonOnEditPage;

	/**
	 * @return the donebutton
	 */
	public WebElement getTabLabelDoneButtonOnEditPage(int timeOut) {
		return isDisplayed(driver, tablabelDoneButtonOnEditPage, "Visibility", timeOut, "Add a Tab Link");
	}

	@FindBy(xpath = "//button[contains(@class,'save')]")
	private WebElement saveButtonEditpage;

	/**
	 * @return the donebutton
	 */
	public WebElement getSaveButtonOnEditPage(int timeOut) {
		return isDisplayed(driver, saveButtonEditpage, "Visibility", timeOut, "Add a Tab Link");
	}

	@FindBy(xpath = "//input[@placeholder='Search...']")
	private WebElement ComponentSearchBox;

	public WebElement getComponentSearchBox(int timeOut) {
		return isDisplayed(driver, ComponentSearchBox, "Visibility", timeOut, "Component Search Box");
	}

	@FindBy(xpath = "//iframe[@title='Surface']")
	private WebElement AppBuilderIframe;

	public WebElement getAppBuilderIframe(int timeOut) {
		return AppBuilderIframe;
	}

	@FindBy(xpath = "//span[text()='Navatar SDG']")
	private WebElement NavatarSDGBtn;

	public WebElement getNavatarSDGBtn(int timeOut) {
		return isDisplayed(driver, NavatarSDGBtn, "Visibility", timeOut, "Navatar SDG Component Link");

	}
	@FindBy(xpath = "//span[text()='Navatar Acuity']")
	private WebElement NavatarAcuityBtn;

	public WebElement getNavatarAcuityBtn(int timeOut) {
		return isDisplayed(driver, NavatarAcuityBtn, "Visibility", timeOut, "Navatar Acuity Component Link");

	}

	public WebElement getComponentLink(String component,int timeOut) {
 		return FindElement(driver, "//span[text()='"+component+"']",  "Component Link",action.BOOLEAN,timeOut);


	}
	
	@FindBy(xpath = "//input[@name='Title']")

	private WebElement Title;

	public WebElement getTitle(int timeOut) {
		return isDisplayed(driver, Title, "Visibility", timeOut, "Get Title");

	}

	@FindBy(xpath = "//label[text()='Data Provider']/parent::lightning-grouped-combobox//input[@role='combobox']")
	private WebElement DataProvider;

	public WebElement getDataProvider(int timeOut) {
		return isDisplayed(driver, DataProvider, "Visibility", timeOut, "object manage");

	}

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement SaveButton;

	public WebElement getSaveButton(int timeOut) {
		return isDisplayed(driver, SaveButton, "Visibility", timeOut, "Save Button");

	}

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//button[text()='Activate']")
	private WebElement ActivateButton;

	public WebElement getActivateButton(int timeOut) {
		return isDisplayed(driver, ActivateButton, "Visibility", timeOut, "Activate Button");

	}
	
	@FindBy(xpath = "//div[contains(@class,'modal-container')]//button[text()='Assign as Org Default']")
	private WebElement AssignAsDefaultButton;

	public WebElement getAssignAsDefaultButton(int timeOut) {
		return isDisplayed(driver, AssignAsDefaultButton, "Visibility", timeOut, "Assign As Default Button");

	}
	
	@FindBy(xpath = "//div[contains(@class,'modal-container')]//button[text()='Save']")
	private WebElement SaveButtonOnPopup;

	public WebElement getSaveButtonOnPopup(int timeOut) {
		return isDisplayed(driver, SaveButtonOnPopup, "Visibility", timeOut, "Save Button On Popup");

	}
	
	@FindBy(xpath = "//label[text()='Data Provider']/parent::lightning-grouped-combobox//lightning-base-combobox-formatted-text")
	private List<WebElement> DataProviderDropDownList;

	public List<WebElement> getDataProviderDropDownList(int timeOut) {
		return DataProviderDropDownList;
	}

	@FindBy(xpath = "//input[@placeholder='Search...']")
	private WebElement SearchonAppBuilder;

	public WebElement getSearchonAppBuilder(int timeOut) {
		return isDisplayed(driver, SearchonAppBuilder, "Visibility", timeOut, "object manage");
	}

	@FindBy(xpath = "//button[text()='Activate']")
	private WebElement AvtivateButton;

	public WebElement getAvtivateButton(int timeOut) {
		return isDisplayed(driver, AvtivateButton, "Visibility", timeOut, "Activate Button");

	}

	@FindBy(xpath = "//button[text()='Save' and contains(@class,'activateButton')]")
	private WebElement AvtivatesaveButton;

	public WebElement getAvtivatesaveButton(int timeOut) {
		return isDisplayed(driver, AvtivatesaveButton, "Visibility", timeOut, "Activate Save Button");

	}

	@FindBy(xpath = "//button[text()='Finish']")
	private WebElement AvtivateFinishButton;

	public WebElement getAvtivateFinishButton(int timeOut) {
		return isDisplayed(driver, AvtivateFinishButton, "Visibility", timeOut, "Activate Finish Button");

	}

	@FindBy(xpath = "//button[text()='Activation...']")
	private WebElement AvtivationButton;

	public WebElement getAvtivationButton(int timeOut) {
		return isDisplayed(driver, AvtivationButton, "Visibility", timeOut, "Activation Button");

	}

	// a[@title='Insert a component before this one.']

	@FindBy(xpath = "(//div[@id='brandBand_1']//div[@data-label='Navatar SDG'])[1]")
	private WebElement dropComponent;

	public WebElement dropComponent(int timeOut) {
		return isDisplayed(driver, dropComponent, "Visibility", timeOut, "Drop Component");

	}

	@FindBy(xpath = "//span[text()='Navatar SDG']/parent::a")
	private WebElement dragComponent;

	public WebElement dragComponent(int timeOut) {
		return isDisplayed(driver, dragComponent, "Visibility", timeOut, "Drag Component");

	}

	@FindBy(xpath = "(//a[@title='Insert a component before this one.'])[1]")
	private WebElement addComponentLink;

	public WebElement addComponentLink(int timeOut) {
		return isDisplayed(driver, addComponentLink, "Visibility", timeOut, "Add Component Link");

	}

	@FindBy(xpath = "(//a[@title='Insert a component before this one.'])[1]/parent::div")
	private WebElement addComponentDiv;

	public WebElement addComponentDiv(int timeOut) {
		return isDisplayed(driver, addComponentDiv, "Visibility", timeOut, "Add Component Div");

	}

	@FindBy(xpath = "//a[text()='Deals']")
	private WebElement dealHeader;

	public WebElement dealHeader(int timeOut) {
		return isDisplayed(driver, dealHeader, "Visibility", timeOut, "Deal Header");

	}

	@FindBy(xpath = "//h2[contains(text(),'retrieved: 0')]")
	private WebElement afterAddComponentMsg;

	public WebElement afterAddComponentMsg(int timeOut) {
		return isDisplayed(driver, afterAddComponentMsg, "Visibility", timeOut, "Component Msg");

	}

	@FindBy(xpath = "//a[text()='Test']/ancestor::article//tbody/tr")
	private List<WebElement> showDrpDownList;

	public List<WebElement> showDrpDownList() {
		return FindElements(driver, showDrpDownList, "Show DropDown List");
	}

	public WebElement TooltipElement(String Title) {
		WebElement TooltipElement;

		return TooltipElement = FindElement(driver,
				"//a[text()='" + Title + "']/ancestor::article/preceding-sibling::lightning-icon", "Tooltip",
				action.SCROLLANDBOOLEAN, 20);
	}

	@FindBy(xpath = "//a[text()='Add Component(s) Here']")

	private WebElement AddComponentButton;

	public WebElement getAddComponentButton(int timeOut) {
		try {
			return isDisplayed(driver, AddComponentButton, "Visibility", timeOut, "AddComponentButton");
		}

		catch (Exception e) {
			return AddComponentLink;
		}

	}

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement editAppSaveButton;

	public WebElement getEditAppSaveButton(int timeOut) {
		return isDisplayed(driver, editAppSaveButton, "Visibility", timeOut, "Save Button");

	}

	@FindBy(xpath = "//div[@class='notification showMessage']")
	private WebElement saveConfirmationMessage;

	public WebElement getsaveConfirmationMessage(int timeOut) {
		return isDisplayed(driver, saveConfirmationMessage, "Visibility", timeOut, "Save confirmation Message");

	}

	@FindBy(xpath = "//lightning-icon[contains(@class,'slds-icon-utility-back')]")
	private WebElement backIcon;

	public WebElement getbBackIcon(int timeOut) {
		return isDisplayed(driver, backIcon, "Visibility", timeOut, "Back icon");

	}

	@FindBy(xpath = "//input[@name='label1']")
	private WebElement label1;

	public WebElement getlabel1(int timeOut) {
		return isDisplayed(driver, label1, "Visibility", timeOut, "Label 1");

	}

	@FindBy(xpath = "//a[text()='Lightning Record Pages']")
	private WebElement LightingRecordPage;

	public WebElement getLightingRecordPage(int timeOut) {
		return isDisplayed(driver, LightingRecordPage, "Visibility", timeOut,
				"Lighting Record Page");
	}
	
	@FindBy(xpath = "//input[@name='query1']")
	private WebElement query1;

	public WebElement getquery1(int timeOut) {
		return isDisplayed(driver, query1, "Visibility", timeOut, "Query 1");

	}

	@FindBy(xpath = "//input[@name='label2']")
	private WebElement label2;

	public WebElement getlabel2(int timeOut) {
		return isDisplayed(driver, label2, "Visibility", timeOut, "Label 2");

	}

	@FindBy(xpath = "//input[@name='query2']")
	private WebElement query2;

	public WebElement getquery2(int timeOut) {
		return isDisplayed(driver, query2, "Visibility", timeOut, "Query 2");

	}

	@FindBy(xpath = "//input[@name='label3']")
	private WebElement label3;

	public WebElement getlabel3(int timeOut) {
		return isDisplayed(driver, label3, "Visibility", timeOut, "Label 3");

	}

	@FindBy(xpath = "//input[@name='query3']")
	private WebElement query3;

	public WebElement getquery3(int timeOut) {
		return isDisplayed(driver, query3, "Visibility", timeOut, "Query 3");

	}

	@FindBy(xpath = "//h2[@class='slds-card__header-title']//a")
	private WebElement sldHeader;

	public WebElement getsldHeader(int timeOut) {
		return isDisplayed(driver, sldHeader, "Visibility", timeOut, "SLD Headers");

	}

	@FindBy(xpath = "//span[text()='Navatar Custom Filter For SDG']")
	private WebElement customFilterForSDGButton;

	public WebElement getcustomFilterForSDGButton(int timeOut) {
		return isDisplayed(driver, customFilterForSDGButton, "Visibility", timeOut, "Custon field for sdg");

	}

	@FindBy(xpath = "//div[@class='navpeIICustomFilterCompForSDG']")
	private WebElement customFilterComponent;

	public WebElement getcustomFilterComponent(int timeOut) {
		return isDisplayed(driver, customFilterComponent, "Visibility", timeOut, "Custom Filter Component");
	}

//	@FindBy(xpath="//span[text()='My Record filter']/preceding-sibling::span")
	@FindBy(xpath = "//span[text()='My Record filter']/parent::label/preceding-sibling::input")
	private WebElement myRecordFilterCheckbox;

	public WebElement getmyRecordFilterCheckbox(int timeOut) {
		return isDisplayed(driver, myRecordFilterCheckbox, "Visibility", timeOut, "My Record Filter Checkbox");
	}

	@FindBy(xpath = "//label[text()='Show']")
	private WebElement customFilterLabel;

	public WebElement getcustomFilterLabel(int timeOut) {
		return isDisplayed(driver, customFilterLabel, "Visibility", timeOut, "Custom Filter Label");
	}

	@FindBy(xpath = "//a[text()='Add Component(s) Here']")

	private WebElement AddComponentLink;

	public WebElement AddComponentLink(int timeOut) {
		try {
			return isDisplayed(driver, AddComponentLink, "Visibility", timeOut, "object manage");
		} catch (Exception e) {
			return AddComponentLink;
		}

	}

	public WebElement AddComponentLinkButton(int timeOut) {
		String xpath = "//a[text()='Add Component(s) Here']";
		try {

			return FindElement(driver, xpath, "AddComponentLink", action.SCROLLANDBOOLEAN, 50);
		}

		catch (Exception e) {
			return FindElement(driver, xpath, "AddComponentLink", action.SCROLLANDBOOLEAN, 50);
		}

	}

	public WebElement sdgHeaderElement(String HeaderSDG, int timeOut) {
		String xpath = "//a[text()='" + HeaderSDG + "']";
		try {

			return FindElement(driver, xpath, "sdgHeaderElement", action.SCROLLANDBOOLEAN, timeOut);
		}

		catch (Exception e) {
			return FindElement(driver, xpath, "sdgHeaderElement", action.SCROLLANDBOOLEAN, timeOut);
		}

	}

	public WebElement tabNameElement(String tabName, int timeOut) {

		try {
			return FindElement(driver, "//ul//li[contains(@class,'tab')]//a[text()='" + tabName + "']",
					"Tab Name: " + tabName, action.SCROLLANDBOOLEAN, timeOut);
		} catch (StaleElementReferenceException e) {
			return FindElement(driver, "//ul//li[contains(@class,'tab')]//a[text()='" + tabName + "']",
					"Tab Name: " + tabName, action.SCROLLANDBOOLEAN, timeOut);
		}

	}

	public WebElement tabNameElementInEditPageComponent(String tabName, int timeOut) {

		try {
			return FindElement(driver, "//ul//li[contains(@class,'tab')]//span[text()='" + tabName + "']/parent::a",
					"Tab Name: " + tabName, action.SCROLLANDBOOLEAN, timeOut);
		} catch (StaleElementReferenceException e) {
			return FindElement(driver, "//ul//li[contains(@class,'tab')]//span[text()='" + tabName + "']/parent::a",
					"Tab Name: " + tabName, action.SCROLLANDBOOLEAN, timeOut);
		}

	}

	public WebElement AlltabNameElementInEditPageComponent(int timeOut) {

		try {
			return FindElement(driver, "//ul[@class='tabs__nav']/li",
					"Tab Name: ", action.SCROLLANDBOOLEAN, timeOut);
		} catch (StaleElementReferenceException e) {
			return FindElement(driver, "//ul[@class='tabs__nav']/li",
					"Tab Name: ", action.SCROLLANDBOOLEAN, timeOut);
		}

	}

	
	public WebElement tabNameElementInEditPage(String tabName, int timeOut) {

		try {
			return FindElement(driver, "//span[text()='" + tabName + "' and (@class='uiOutputText' or @data-aura-class='uiOutputText')]/parent::a",
					"Tab Name: " + tabName, action.SCROLLANDBOOLEAN, timeOut);
		} catch (StaleElementReferenceException e) {
			return FindElement(driver, "//span[text()='" + tabName + "' and (@class='uiOutputText' or @data-aura-class='uiOutputText')]/parent::a",
					"Tab Name: " + tabName, action.SCROLLANDBOOLEAN, timeOut);
		}

	}
	
	public WebElement FirsttabNameElementInEditPage( int timeOut) {

		try {
			return FindElement(driver, "//span[@class='uiOutputText' or @data-aura-class='uiOutputText']//ancestor::fieldset/*[text()='Tabs']",
					"First Tab : ", action.SCROLLANDBOOLEAN, timeOut);
		} catch (StaleElementReferenceException e) {
			return FindElement(driver, "//span[@class='uiOutputText' or @data-aura-class='uiOutputText']//ancestor::fieldset/*[text()='Tabs']",
					" first Tab : ", action.SCROLLANDBOOLEAN, timeOut);
		}

	}

	@FindBy(xpath = "//button[text()='Add Tab']")
	private WebElement addTabButtonInEditPage;

	public WebElement addTabButtonInEditPage(int timeOut) {
		return isDisplayed(driver, addTabButtonInEditPage, "Visibility", timeOut, "addTabButtonInEditPage");

	}

	
	@FindBy(xpath = "//span[text()='Details' or text()='Related']//ancestor::div[@data-instance-type='COMPONENT' and @data-label='Tabs']")
	private WebElement recordDetailComponent;

	public WebElement recordDetailComponentComponent(int timeOut) {
		return isDisplayed(driver, recordDetailComponent, "Visibility", timeOut, "recordDetailComponent");

	}
	public WebElement highlightPanel(int timeOut) {
		String xpath ="//div[@data-label='Highlights Panel']";
		return FindElement(driver, xpath, "highlightPanelBottonInsertButton",action.BOOLEAN, timeOut);

	}
	
	
	public WebElement highlightPanelBottonInsertButton(int timeOut) {
		String xpath ="//div[@data-label='Highlights Panel']//ancestor::div[@class='sf-interactions-proxy sf-interactions-proxyAddComponent sf-interactions-proxyAddComponentAfter']";
		return FindElement(driver, xpath, "highlightPanelBottonInsertButton",action.BOOLEAN, timeOut);

	}
	
	@FindBy(xpath = "//div[@data-label='Record Detail']//ancestor::div[@data-instance-type='COMPONENT' and @data-label='Tabs']//div[@title='Move component']//lightning-primitive-icon")
	private WebElement recordDetailComponentMoveButton;

	public WebElement recordDetailComponentMoveButton(int timeOut) {
		return isDisplayed(driver, recordDetailComponentMoveButton, "Visibility", timeOut, "recordDetailComponentMoveButton");

	}

	@FindBy(xpath = "//div[@data-label='Activities']//ancestor::div[@data-instance-type='COMPONENT']")
	private WebElement activityTimelineComponent;

	public WebElement activityTimelineComponent(int timeOut) {
		return isDisplayed(driver, activityTimelineComponent, "Visibility", timeOut, "activityTimelineComponent");

	}
	
	@FindBy(xpath = "//div[@data-label=\"Today's Tasks\" and @data-instance-type='COMPONENT']")
	private WebElement TodaysTaskComponent;

	public WebElement TodaysTaskComponent(int timeOut) {
		return isDisplayed(driver, TodaysTaskComponent, "Visibility", timeOut, "TodaysTaskComponent");

	}
	
	@FindBy(xpath = "//div[@data-label=\"Today's Events\" and @data-instance-type='COMPONENT']")
	private WebElement TodaysEventComponent;

	public WebElement TodaysEventComponent(int timeOut) {
		return isDisplayed(driver, TodaysEventComponent, "Visibility", timeOut, "TodaysEventComponent");

	}
	
	@FindBy(xpath = "//div[@data-label='Activities']//ancestor::div[@data-instance-type='COMPONENT']//*[@title='Delete']")
	private WebElement activityTimelineComponentDeleteButton;

	public WebElement activityTimelineComponentDeleteButton(int timeOut) {
		return isDisplayed(driver, activityTimelineComponentDeleteButton, "Visibility", timeOut, "activityTimelineComponentDeleteButton");

	}
	
	@FindBy(xpath = "//div[@data-label=\"Today's Tasks\" and @data-instance-type='COMPONENT']//*[@title='Delete']")
	private WebElement TodaystaskComponentDeleteButton;

	public WebElement TodaystaskComponentDeleteButton(int timeOut) {
		return isDisplayed(driver, TodaystaskComponentDeleteButton, "Visibility", timeOut, "TodaystaskComponentDeleteButton");

	}
	
	@FindBy(xpath = "//div[@data-label=\"Today's Events\" and @data-instance-type='COMPONENT']//*[@title='Delete']")
	private WebElement TodaysEventComponentDeleteButton;

	public WebElement TodaysEventComponentDeleteButton(int timeOut) {
		return isDisplayed(driver, TodaysEventComponentDeleteButton, "Visibility", timeOut, "TodaysEventComponentDeleteButton");

	}

	@FindBy(xpath = "(//li[@role='option']//span[text()='Details'])[2]/parent::a")
	private WebElement detailTabCreatedAfterAddTab;

	public WebElement detailTabCreatedAfterAddTab(int timeOut) {
		return isDisplayed(driver, detailTabCreatedAfterAddTab, "Visibility", timeOut, "detailTabCreatedAfterAddTab");

	}

	@FindBy(xpath = "//span[text()='Tab Label']/parent::label/following-sibling::div//select")
	private WebElement tabLabelSelectElement;

	public WebElement tabLabelSelectElement(int timeOut) {
		return isDisplayed(driver, tabLabelSelectElement, "Visibility", timeOut, "tabLabelSelectElement");

	}
	
	@FindBy(xpath = "//select")
	private WebElement DefaultTab;

	public WebElement getDefaultTab(int timeOut) {
		return isDisplayed(driver, DefaultTab, "Visibility", timeOut, "Default Tab");

	}

	@FindBy(xpath = "//label[text()='Custom Label']/following-sibling::div//input")
	private WebElement customLabelInputBox;

	public WebElement customLabelInputBox(int timeOut) {
		return isDisplayed(driver, customLabelInputBox, "Visibility", timeOut, "customLabelInputBox");

	}

	@FindBy(xpath = "//button[text()='Done']/parent::div")
	private WebElement doneButtonDivInTabLabel;

	public WebElement doneButtonDivInTabLabel(int timeOut) {
		return isDisplayed(driver, doneButtonDivInTabLabel, "Visibility", timeOut, "doneButtonDivInTabLabel");

	}

	@FindBy(xpath = "//button[text()='Done']")
	private WebElement doneButtonInTabLabel;

	public WebElement doneButtonInTabLabel(int timeOut) {
		return isDisplayed(driver, doneButtonInTabLabel, "Visibility", timeOut, "doneButtonInTabLabel");

	}

	@FindBy(xpath = "//span[text()='Changes saved.']/parent::div[@class='notification showMessage']")
	private WebElement changesSavedMsg;

	public WebElement changesSavedMsg(int timeOut) {
		return isDisplayed(driver, changesSavedMsg, "Visibility", timeOut, "changesSavedMsg");

	}

	@FindBy(xpath = "//div[contains(@class,\"uiTabset--default\")]//a[text()=\"Add Component(s) Here\"]")
	private WebElement addComponentLinkInTabSection;

	public WebElement addComponentLinkInTabSection(int timeOut) {
		return isDisplayed(driver, addComponentLinkInTabSection, "Visibility", timeOut, "addComponentLinkInTabSection");
	}

	public WebElement customFilterComponent(int timeOut) {
		String xpath = "//div[@class='navpeIICustomFilterCompForSDG']";
		return FindElement(driver, xpath, "Custom Filter Component", action.SCROLLANDBOOLEAN, timeOut);

	}
	
	@FindBy(xpath = "//span[text()='Navatar Notification']")
	private WebElement NavatarNotificationBtn;

	public WebElement getNavatarNotificationBtn(int timeOut) {
		return isDisplayed(driver, NavatarNotificationBtn, "Visibility", timeOut, "Navatar Notification Component Link");

	}
	
	@FindBy(xpath = "//span[text()='Z (Do not use) Navatar Notification Popup']/..")
	private WebElement NavatarZNotificationBtn;

	public WebElement getNavatarZNotificationBtn(int timeOut) {
		return isDisplayed(driver, NavatarZNotificationBtn, "Visibility", timeOut, "Navatar Z (Do not use) Navatar Notification Popup Link");

	}

	@FindBy(xpath = "//button[@data-element-id='searchClear']")
	private WebElement searchClearBtn;

	public WebElement getSearchClearBtn(int timeOut) {
		return isDisplayed(driver, searchClearBtn, "Visibility", timeOut, "Search Clear Link");

	}
	
	@FindBy(xpath = "//div[@class='colSide']//div[contains(@data-instance-type,'COMPONENT')]")
	private WebElement firstComponent;

	public WebElement getFirstComponent(int timeOut) {
		return isDisplayed(driver, firstComponent, "Visibility", timeOut, "FirstComponent");
	}
	
	@FindBy(xpath = "//div[@class='top']//div[contains(@data-instance-type,'COMPONENT')]")
	private WebElement firstComponent1;

	public WebElement getFirstComponent1(int timeOut) {
		return isDisplayed(driver, firstComponent1, "Visibility", timeOut, "FirstComponent");
	}
	
	public List<WebElement> getLastComponent(int timeOut) {
		String xpath = "//div[contains(@data-instance-type,'COMPONENT')]";
		List<WebElement> list = new ArrayList<WebElement>();

		list = FindElements(driver, xpath, "Component");
		return list;
	}
}
