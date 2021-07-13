import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculatorTest
{
    WebDriver driver;
    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.gecko.driver","C:\\geckodriver-v0.29.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.calculator.net/bmi-calculator.html");
    }

    public String BMIcalculator(String age, String gender, String height, String weight)
    {
        driver.findElement(By.xpath("//input[@id='cage']")).clear();
        driver.findElement(By.xpath("//input[@id='cage']")).sendKeys(age);//label[contains(text(),'Female')]
        driver.findElement(By.xpath("//label[contains(text(),'"+gender+"')]")).click();
        driver.findElement(By.xpath("//input[@id='cheightmeter']")).clear();
        driver.findElement(By.xpath("//input[@id='cheightmeter']")).sendKeys(height);
        driver.findElement(By.xpath("//input[@id='ckg']")).clear();
        driver.findElement(By.xpath("//input[@id='ckg']")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@type='image']")).click();
        String actual_result = driver.findElement(By.cssSelector("div.bigtext > b:nth-child(1)")).getText();
        //System.out.println("Result: " +actual_result);
        return actual_result;
    }

    @Test
    public void test1()
    {
        BMIcalculator("20","Male","180","60");
        Assert.assertEquals(BMIcalculator("20","Male","180","60"),"BMI = 18.5 kg/m2");

    }

    @Test
    public void test2()
    {
        BMIcalculator("35","Female","160","55");
        Assert.assertEquals(BMIcalculator("35","Female","160","55"),"BMI = 21.5 kg/m2");

    }
    @Test
    public void test3()
    {
        BMIcalculator("50","Male","175","65");
        Assert.assertEquals(BMIcalculator("50","Male","175","65"),"BMI = 21.2 kg/m2");

    }
    @Test
    public void test4()
    {
        BMIcalculator("45","Female","150","52");
        Assert.assertEquals(BMIcalculator("45","Female","150","52"),"BMI = 23.1 kg/m2");

    }

    @AfterTest
    public void close()
    {
        driver.quit();
    }



}
