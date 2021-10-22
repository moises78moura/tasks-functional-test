package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TaskTest {

	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
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
	public void naoDeveSalvarTarefaComDataPassada() {
		
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
	public void naoDeveSalvarTarefaSemData() {
		
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
	public void naoDeveSalvarTarefaSemDescricao() {
		
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

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("http://localhost:8081/tasks");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
}
