package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestCases {
    ChromeDriver driver;
   // WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(TestCases.class.getName());
    
    @Test
    public void testCase01() throws InterruptedException{ 

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        logger.info("Navigated to Google Forms URL.");
        Thread.sleep(3000);
      
        WebElement nameInputBox = driver.findElement(By.xpath("//div[@class='Xb9hP']/input[@type='text']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Xb9hP']/input[@type='text']")));
        System.out.println("Enter your name");
        Wrappers.enterText (nameInputBox,"Crio Learner");
        logger.info("Name entered: Crio Learner");
        Thread.sleep(2000);
        
        WebElement practicingAutomationTextArea = driver.findElement(By.xpath("//textarea[contains(@class,'tL9Q4c')]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@class,'tL9Q4c')]")));
        String practisingAutomationText = "I want to be the best QA Engineer!";
        logger.info("Text entered into textarea.");
        

        Thread.sleep(30000);

        long epochTime=System.currentTimeMillis()/1000;
        
        String epochTimeString = String.valueOf(epochTime);
        Wrappers.enterText (practicingAutomationTextArea, practisingAutomationText+" "+epochTimeString);
        Thread.sleep(3000); 
        
        // Select radio button a/c to automation experience
        System.out.println("Select your experience in Automation");
        Wrappers.radioButton (driver,"0 - 2");
        logger.info("Radio button '0 - 2' selected.");

        
        //Select checkbox for skillsets
        System.out.println("Select three skills");
        Thread.sleep(3000);
        Wrappers.checkBox(driver,"Java");
        Wrappers.checkBox(driver,"Selenium");
        Wrappers.checkBox(driver,"TestNG");
        logger.info("Checkboxes selected: Java, Selenium, TestNG.");
        
        // Clicking on dropdown to select Salutation
        
        WebElement dropDoWebElement = driver.findElement(By.xpath("//div[@jsname='LgbsSe']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@jsname='LgbsSe']")));
        Wrappers.clickOnElement (driver, dropDoWebElement);
        Thread.sleep(2000);
        Wrappers.dropDownClick(driver,"Mrs");
        Thread.sleep(3000);
        logger.info("Dropdown option 'Mrs' selected.");
        
        //Enter 7 days ago date
        
        WebElement dateInputBox = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysAgoDate = Wrappers.getdateSevenDaysAgo();
        Wrappers.enterText (dateInputBox, sevenDaysAgoDate);
        Thread.sleep(30000);
        logger.info("Date entered: " + sevenDaysAgoDate);

        WebElement hourInput = driver.findElement(By.xpath("//input[@aria-label='Hour']")); // Update the XPath accordingly
        WebElement minuteInput = driver.findElement(By.xpath("//input[@aria-label='Minute']")); // Update the XPath accordingly
    
        String currentTime = Wrappers.getCurrentTime(); // Ensure this returns time in "HH:MM" format
        String[] currentTimeInHHMM = currentTime.split(":");
        String HH = currentTimeInHHMM[0];
        String MM = currentTimeInHHMM[1];

        hourInput.clear();
        hourInput.sendKeys(HH);
    
        minuteInput.clear();
        minuteInput.sendKeys(MM);
        System.out.println("Time is Entered");
        logger.info("Time entered: " + HH + ":" + MM);

         // Click the submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='NPEfkd RveJvd snByac']")));
        submitButton.click();
        System.out.println("Submit button clicked.");

        // Optional: Add additional wait if necessary to ensure submission completes
         Thread.sleep(5000); 

         WebElement finalText = driver.findElement(By.xpath("//div[@class='vHW8K']"));
         String text= finalText.getText();
         System.out.println(text);

         Thread.sleep(5000);
         logger.info("Waited for submission to complete.");
    }


    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        //driver = new ChromeDriver(options);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}