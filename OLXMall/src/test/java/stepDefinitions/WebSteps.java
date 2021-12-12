package stepDefinitions;

import helper.SeleniumHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class WebSteps {

//    private final WebDriver driver;

    public WebSteps() throws FileNotFoundException {

    }
    Properties obj = new Properties();
    FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "/Locators.properties");

    @Given("the user navigate to olx web page {string}")
    public void theUserNavigateToOlxWebPage(String url) throws Exception {
        try {
            if (url.equals("olx_url")) {
                url = "https://olx.com.pk/mall";
            }
            SeleniumHelper seleniumHelper = new SeleniumHelper();
            seleniumHelper.getURL(url);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @When("I am hovering my mouse to {string}")
    public void iAmHoveringMyMouseTo(String locator) throws Exception {
        try{
            obj.load(objfile);
            locator = obj.getProperty(locator);
            SeleniumHelper seleniumHelper = new SeleniumHelper();
            seleniumHelper.hovering(locator);
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @And("I am clicking on {string}")
    public void iAmClickingOn(String locator) throws Exception {
        try {
            obj.load(objfile);
            locator = obj.getProperty(locator);
            SeleniumHelper seleniumHelper = new SeleniumHelper();
            seleniumHelper.click(locator);
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @When("I have given {string} on {string}")
    public void iHaveGivenOn(String value, String locator) throws Exception {
        SeleniumHelper seleniumHelper = new SeleniumHelper();

        try{
            obj.load(objfile);
            locator = obj.getProperty(locator);
            seleniumHelper.SetTextBoxValue(value, locator);
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Then("I am verifying first ten items on screen")
    public void iAmVerifyingFirstTenItemsOnScreen() throws Exception {
        SeleniumHelper seleniumHelper = new SeleniumHelper();

        try{
            obj.load(objfile);
            String locator;
            String categoryText = obj.getProperty("Mobile_Category_Text");
            String MobileItemLocator = "mobile_item";
            String price_locator;
            int minPrice = 40000;
            int maxPrice = 120000;
            String priceLocatorValue;

            for (int i = 1; i<= 10; i++){
                locator = obj.getProperty(MobileItemLocator);
                locator = locator.replace("{x}", String.valueOf(i));
                seleniumHelper.scrollToElement(locator);
                seleniumHelper.click(locator);
                String CategoryTextValue = seleniumHelper.getText(categoryText);
                Assert.assertEquals("Mobiles", CategoryTextValue);
                price_locator = obj.getProperty("product_price");
                priceLocatorValue = seleniumHelper.getText(price_locator);
                priceLocatorValue = priceLocatorValue.replace("₨ ", "");
                priceLocatorValue = priceLocatorValue.replace(",", "");
                if (!(Integer.parseInt(priceLocatorValue) >= minPrice && Integer.parseInt(priceLocatorValue) <= maxPrice)){
                    throw new Exception();
                }
                seleniumHelper.goBackPreviousPage();
            }
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }


}