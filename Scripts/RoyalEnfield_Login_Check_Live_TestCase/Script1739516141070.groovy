import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil

'Login Page'
WebUI.openBrowser(GlobalVariable.LiveUrl)

WebUI.maximizeWindow()

WebUI.setText(findTestObject('RE Login Page/input_Username_email'), userNameLive)

WebUI.setText(findTestObject('RE Login Page/input_Password_password'), passwordLive)

WebUI.click(findTestObject('RE Login Page/button_Log In'))

'Check if login failed'
boolean isErrorPresent = WebUI.verifyElementPresent(findTestObject('RE Login Page/strong_These credentials do not match our records'), 
    1, FailureHandling.OPTIONAL)

if (isErrorPresent) {
    WebUI.comment((('Login failed for Username: ' + userNameLive) + ' | Password: ') + passwordLive)

    //WebUI.takeScreenshot() // Takes a screenshot for debugging
    KeywordUtil.markFailed((('❌ Login failed for Username: ' + userNameLive) + ' | Password: ') + passwordLive)
	WebUI.closeBrowser()
} else {
    'Home Page\r\n'
    WebUI.verifyElementPresent(findTestObject('RE_Home Page/span_Analytics'), 2)

    WebUI.click(findTestObject('RE_Home Page/a_ProfileDrop'))

    WebUI.click(findTestObject('RE_Home Page/a_Logout'))

    WebUI.closeBrowser()
}

