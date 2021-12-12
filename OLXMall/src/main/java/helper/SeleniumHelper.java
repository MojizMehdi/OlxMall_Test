package helper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Hooks;

public class SeleniumHelper {

    private WebDriverWait wait;
    WebDriver driver = Hooks.getDriver();

    public SeleniumHelper() {
        if (driver != null) {
            wait = new WebDriverWait(driver, 60);
        }
    }
    public void getURL(String url) throws Exception {
        try {
            driver.get(url);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
    public void hovering (String keyword) throws Exception {
        try{
            WebElement hover_locator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword)));

            Actions builder = new Actions(driver);
            builder.moveToElement(hover_locator).perform();
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    public void click (String keyword) throws Exception{
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(keyword)));
            {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword)));
                {
                    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(keyword)));
                    link.click();
                }
            }
        }
        catch (ElementNotVisibleException e)
        {
            throw new Exception(String.format("The element provided %s is not on screen", keyword));
        }
        catch (StaleElementReferenceException e)
        {
            throw new Exception(String.format("The element provided %s is Stale", keyword));
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    public void SetTextBoxValue(String value, String keyword) throws Exception {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword)));
            {
                WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(keyword)));
                Thread.sleep(100);
                link.click();
                link.clear();
                link.sendKeys(value);
            }
        } catch (ElementNotVisibleException e) {
            throw new Exception(String.format("The element provided %s is not on screen", keyword));
        } catch (StaleElementReferenceException e) {
            throw new Exception(String.format("The element provided %s is Stale", keyword));
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
    public void scrollToElement(String keyword) throws Exception {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword)));
            {
                WebElement scroll_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword)));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", scroll_element);
            }
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
// This method takes a custom value and a screen locator then verify both by getting text from locator
    public String getText(String keyword) throws Exception {
        try
        {
            WebElement Control;
            String screen_value;

            Control = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword)));

            assert Control != null;
            if (keyword.contains("//input["))
            {
                screen_value = Control.getAttribute("value");
            }
            else
            {
                screen_value = Control.getText();
            }
            return screen_value;
        }
        catch (ElementNotVisibleException ex)
        {
            throw new Exception(String.format("The element provided %s is invalid", keyword));
        }
        catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    public void goBackPreviousPage(){
        driver.navigate().back();
    }
}


