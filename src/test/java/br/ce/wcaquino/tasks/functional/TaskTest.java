package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.DESedeKeySpec;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {

	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Testando via Selenium");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("21/10/2023");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals("Success!", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Testando via Selenium");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("21/10/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals("Due date must not be in past", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Testando via Selenium");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals("Fill the due date", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("21/10/2023");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
		
	}
	//Due date must not be in past

	public WebDriver acessarAplicacao() throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
		URL url = null;
		url = new URL(" http://localhost:4444/wd/hub");
//		url = new URL(" http://192.168.15.30:4444/wd/hub");
	
		WebDriver driver = new RemoteWebDriver(url, capabilities);
		driver.navigate().to("http://192.168.32.1:8081/tasks");//IP - Local
//		driver.navigate().to("http://localhost:8081/tasks");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
}
