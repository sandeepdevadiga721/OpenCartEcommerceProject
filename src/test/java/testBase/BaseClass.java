package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Smoke","Datadriven"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		// loading config.Properites file
		FileReader file = new FileReader("./src//test//resources//config.Properties");
		p = new Properties();
		p.load(file);

		switch (br.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid browser name...");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL"));// reading URL from property files
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity","Regression","Smoke","Datadriven"})
	public void teardown() {
		driver.quit();
	}

	// Method to generate a random first name
	public static String generateFirstName() {
		String[] firstNames = { "John", "Jane", "Alex", "Emily", "Chris", "Katie", "Michael", "Sarah" };
		Random random = new Random();
		return firstNames[random.nextInt(firstNames.length)];
	}

	// Method to generate a random last name
	public static String generateLastName() {
		String[] lastNames = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis" };
		Random random = new Random();
		return lastNames[random.nextInt(lastNames.length)];
	}

	public static String generateEmail(String firstName, String lastName) {
		String domain = "@gmail.com";
		String uniqueID = UUID.randomUUID().toString().substring(0, 5); // Generate a unique identifier
		return firstName.toLowerCase() + "." + lastName.toLowerCase() + uniqueID + domain;
	}

	// Method to generate a random password based on first name
	public static String generatePassword(String firstName) {
		return firstName.toLowerCase() + "@123";
	}

	public static Object[] extractQuantityAndProductName(String input) {
		// Split the input string by spaces
		String[] parts = input.split(" ");

		// Extract the quantity part (e.g., "1x") and product name (e.g., "iPhone")
		String quantity = parts[0];
		String productName = parts[1];

		// Strip 'x' from quantity to get numeric value
		int numericQuantity = Integer.parseInt(quantity.replaceAll("[^0-9]", ""));

		// Return the values in an Object array
		return new Object[] { numericQuantity, productName };
	}

	public String captureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

}
