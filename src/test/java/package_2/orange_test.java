package package_2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;

public class orange_test {

	WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			// driver = WebDriverManager.firefoxdriver().create();
			driver = new FirefoxDriver();
			System.out.println("Browser Started: " + browser);

		} else if (browser.equalsIgnoreCase("chrome")) {
			// driver = WebDriverManager.chromedriver().create();
			driver = new ChromeDriver();
			System.out.println("Browser Started: " + browser);

		} else {
			throw new Exception("Incorrect Browser");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(priority = 1)
	public void orangelogotest() {
		boolean flag = true;
		flag = driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
		Assert.assertFalse(flag);
	}

	@Test(priority = 2)
	public void getFooterLinksTest() {

		List<WebElement> footerlinkList = driver
				.findElements(By.xpath("//div[@class = 'orangehrm-copyright-wrapper']"));
		footerlinkList.forEach(ele -> System.out.println(ele.getText()));
		assertEquals(footerlinkList.size(), 1);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}
