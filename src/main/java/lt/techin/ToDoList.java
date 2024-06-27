package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ToDoList {

    protected WebDriver driver;

    private By addButton = By.cssSelector("#plus-icon");
    private By inputField = By.cssSelector("input");
    private By toDoTask = By.cssSelector("ul > li");
    private By firstTask = By.cssSelector("ul li:first-of-type");
    private By lastTask = By.cssSelector("ul li:last-of-type");
    private By completedTask = By.cssSelector("ul > li.completed");
    private By deleteIcon = By.cssSelector(".fa.fa-trash");


    public ToDoList(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToList() {
        driver.findElement(addButton).click();
    }

    public void addTaskToList(String task) {
        driver.findElement(inputField).sendKeys(task);
    }

    public void confirmAddTask() {
        driver.findElement(inputField).sendKeys(Keys.ENTER);
    }

    public boolean inputFieldIsVisible() {
        return driver.findElement(inputField).isDisplayed();
    }

    public String lastAddedTask() {
        return driver.findElement(lastTask).getText();
    }

    public List<WebElement> listOfTasks() {
        return driver.findElements(toDoTask);
    }

    public boolean isTaskCopleted() {
        return driver.findElement(completedTask).isDisplayed();
    }

    public void clickAllTasksToComplete() {
        List<WebElement> tasks = listOfTasks();
        for (WebElement task : tasks) {
            task.click();
        }
    }

    public boolean areAllTasksCompleted() {
        List<WebElement> tasks = listOfTasks();
        for (WebElement task : tasks) {
            if (!isTaskCopleted()) return false;
        }
        return true;
    }

    public void clickDeleteFirst() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(firstTask)).perform();
        driver.findElement(deleteIcon).click();
    }

    public void clickDeleteLast() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(lastTask)).perform();
        driver.findElement(deleteIcon).click();
    }

    public boolean isListOfTaskEmpty() {
        return listOfTasks().isEmpty();
    }

    public void deleteAllToDoItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100));
        Actions action = new Actions(driver);
        List<WebElement> task;
        while (true) {

            //renews list of task elements after deletion
            task = listOfTasks();
            if (task.isEmpty()) break;

            action.moveToElement(driver.findElement(firstTask)).perform();
            driver.findElement(deleteIcon).click();

            //Wait until first element is no longer present (ChatGPT helped)
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(firstTask)));
        }
    }


}
