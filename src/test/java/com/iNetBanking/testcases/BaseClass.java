package com.iNetBanking.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;
import com.iNetBanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getApplicationUrl();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();

	public static WebDriver d;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		logger = LogManager.getLogger("ebanking");
		logger.info("We are in logger info");
		// PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
			d = new ChromeDriver();

		} else if (br.equals("edge")) {
			System.setProperty("webdriver.edge.driver", readconfig.getEdgepath());
			d = new EdgeDriver();

		}
		d.get(baseURL);

	}

	@AfterClass
	public void tearDown() {
		d.quit();
	}

	public void captureScreenhot(WebDriver d, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) d;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		Files.copy(src, dest);
		System.out.println("Screenshot Taken");
	}

}
