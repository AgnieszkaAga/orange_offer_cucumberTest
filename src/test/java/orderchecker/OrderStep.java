package orderchecker;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderStep {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win10\\IdeaProjects\\CucumberTest\\src\\test\\java\\resources\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
        this.driver = null;
    }


    @Given("^User navigates to Orange website$")
    public void user_navigates_to_Orange_website() throws Throwable {
        driver.get("https://www.orange.pl/");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button[title='Zamknij informację o cookies']")).click();
    }

    @And("^User clicks on the mobiles offers$")
    public void user_clicks_on_the_mobiles_offers() throws Throwable {
        Thread.sleep(3000);
        boolean advertIsVisible = driver.findElements(By.cssSelector("button[title='Zamknij szufladę']")).size() !=0;
        if (advertIsVisible == true) {
            driver.findElement(By.cssSelector("button[title='Zamknij szufladę']")).click();
        }
        driver.findElement(By.id("Menu_B2C_Telefony_i_urządzenia")).click();
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Menu_B2C_Telefony_i_urządzenia_Telefony")));
        driver.findElement(By.id("Menu_B2C_Telefony_i_urządzenia_Telefony")).click();
        Thread.sleep(6000);
    }

    @And("^User clicks on first offer with subscription' details button$")
    public void user_clicks_on_first_offer_with_subscription_details_button() throws Throwable {
        boolean advertIsVisible = driver.findElements(By.cssSelector("button[title='Zamknij szufladę']")).size() !=0;
        if (advertIsVisible == true) {
            driver.findElement(By.cssSelector("button[title='Zamknij szufladę']")).click();
        }
        List<WebElement> offerts = new ArrayList<>();
        offerts = driver.findElements(By.cssSelector("a[title*='Sprawd']"));
        offerts.get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title*='Przejd']")));
        driver.findElement(By.cssSelector("button[title*='Przejd']")).click();
    }

    @And("^User matches resign from additions option$")
    public void user_matches_resign_from_additions_option() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".css-wzdbp7")));
        driver.findElement(By.cssSelector(".css-wzdbp7")).click();
        Thread.sleep(6000);
    }

    @When("^User adds offer to basked$")
    public void user_adds_offer_to_basked() throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement addToBaskedButton = driver.findElement(By.cssSelector(".css-9as7mc"));
        js.executeScript("arguments[0].scrollIntoView();", addToBaskedButton);
        addToBaskedButton.click();
    }

    @And("^User confirms the order$")
    public void user_confirms_the_order() throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#navigation-next-ora-button")));
        WebElement goFurtherButton = driver.findElement(By.cssSelector("#navigation-next-ora-button"));
        js.executeScript("arguments[0].scrollIntoView();",goFurtherButton);
        js.executeScript("window.scrollBy(0,-250)");
        driver.findElement(By.cssSelector("#navigation-next-ora-button")).click();
    }

    @And("^User enters incorrect personal details$")
    public void user_enters_incorrect_personal_details() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='firstName']")));
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("Adam");
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys("Mickiewicz");
        driver.findElement(By.cssSelector("input[name='pesel']")).sendKeys("73022631389");
        driver.findElement(By.cssSelector("input[name='idNumber']")).sendKeys("AEL047132");
        driver.findElement(By.cssSelector("input[id='postalCode_main']")).sendKeys("43-300");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[id='streetName_main']")).sendKeys("Warszawska");
        driver.findElement(By.cssSelector("input[id='streetNumber_main']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='emailAddress']")).sendKeys("abd@gmail.com");
        driver.findElement(By.cssSelector("input[name='phoneNumber']")).sendKeys("531555556");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"tooltip-8821095695420\"]/div/div[1]/label/span[1]")).click();
    }

    @And("^User approves all conditions$")
    public void user_approves_all_conditions() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"consents-component-expander\"]/div[1]/label/span[1]")).click();
    }

    @And("^User submits the order$")
    public void user_submits_the_order() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[id='navigation-next-ora-button']")));
        Thread.sleep(6000); //Sleep after select all approves
        //Find chat and close if exists
        boolean confirmationButton = driver.findElements(By.cssSelector("button[id='navigation-next-ora-button']")).size() !=0;
        if (confirmationButton == true) {
            driver.findElement(By.cssSelector("button[id='navigation-next-ora-button']")).click();
        } else {
        driver.switchTo().frame(1);
        WebElement chatClose = driver.findElement(By.id("jw_icon"));
        wait.until(ExpectedConditions.elementToBeClickable(chatClose));
        chatClose.click();
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector(".opl-cookie-control__close")).click();
        driver.findElement(By.cssSelector("button[id='navigation-next-ora-button']")).click();
        }
    }

    @Then("^User is informed that order can not be realized$")
    public void user_is_informed_that_order_can_not_be_realized() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement element = driver.findElement(By.cssSelector("[id='react-modal-5']"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertTrue(driver.findElement(By.cssSelector("[id='react-modal-5']")).isDisplayed());
    }

    @And("^User cancels the order$")
    public void user_cancels_the_order() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"react-modal-5\"]/div/div/div/div[1]/button")).click();
    }

    @And("^User is informed that my basket is empty$")
    public void user_is_informed_that_my_basket_is_empty() throws Throwable {
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Twój koszyk jest pusty')]"));
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Twój koszyk jest pusty')]")).isDisplayed());
    }
}
