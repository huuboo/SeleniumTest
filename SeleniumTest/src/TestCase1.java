import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; 
import java.lang.Thread;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestCase1 {
	public static void main(String[] args) throws InterruptedException {
		
		/* Launch ToDoApp Page */
		String userName = "username";
		String password = "password";
		String driverPath = "chromedriverpath";
		System.setProperty("webdriver.chrome.driver",driverPath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://todo-list-login.firebaseapp.com/");
		
		/* Choose Github Login Method */
		driver.findElement(By.cssSelector(".btn.btn-social.btn-github")).click();
		Thread.sleep(4000);
		
		/* Enter user name and password to login */
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator(); 
		String parentWindow = iterator.next();
		String childWindow = iterator.next();
		driver.switchTo().window(childWindow);
		driver.findElement(By.id("login_field")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("commit")).click();
		Thread.sleep(4000);
		
		/* Create 10 to do list with random strings */
		System.out.println(driver.switchTo().window(parentWindow).getTitle());
		driver.switchTo().window(parentWindow);
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.cssSelector("input[ng-model=\"home.list\"]")).sendKeys(String.valueOf(i));
			driver.findElement(By.cssSelector("button[ng-click=\"home.list && home.add()\"]")).click();
			}
		Thread.sleep(4000);
		
		/* Upon completion log out */
		driver.findElement(By.cssSelector(".btn.btn-default")).click();
		Thread.sleep(4000);
		
		/*  Login again with the same account */
		driver.findElement(By.cssSelector(".btn.btn-social.btn-github")).click();
		Thread.sleep(4000);
		
		
		/* Delete list from 5 - 10 */
		List<WebElement> we = driver.findElements(By.cssSelector(".btn.btn-danger.btn-block.glyphicon.glyphicon-remove"));
		for (int i = 4; i<10; i++) {
			we.get(i).click();
		}
		Thread.sleep(4000);
		
		/* Upon completion log out again */
		driver.findElement(By.cssSelector(".btn.btn-default")).click();
		Thread.sleep(4000);
		
		//driver.close();
	}

}
