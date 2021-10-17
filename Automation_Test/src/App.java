import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.interactions.Actions;



public class App {
    
    ChromeDriver driver;
    String url = "https://todomvc.com/examples/angular2";

    /* Save test result in the ReadMe file */
        public void savelogfile(String s) throws IOException
        {
            try {
                FileWriter fw = new FileWriter("../Automation_Test/README.md", true);
                fw.write(s);
                fw.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }


    /* Open browser and intialization */

    public String invokeBrowser() throws IOException
    {
        System.setProperty("webdriver.chrome.driver", "../Automation_Test/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        String txt = LocalDateTime.now()+": Page loaded successfully!! (PASSED) \n";
        driver.manage().window().maximize();
        driver.get(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return(txt);
    }

    /* Check if page title is defined correctlly */

    public String verifyPageTitle() throws IOException
    {
        String exoectedtitle = "Angular2 • TodoMVC";
        String actualtitle = driver.getTitle();
        String txt = "";
        if(actualtitle.equals(exoectedtitle))
        {
            System.out.println("Page title. (PASSED)");
            txt = LocalDateTime.now()+": Page title value is correct. (PASSED) \n";
        } else{
            System.out.println("Page title match Failed!");
            txt = LocalDateTime.now()+": Page title match Failed! \n";
        }
        return(txt);
    }

    /* Check if page header name is defined correctlly */

    public String pageHeader() throws IOException
    {
        String txt = "";
        WebElement pageheader = driver.findElement(By.xpath("//h1[text()='todos']"));
        if(pageheader.isDisplayed()){
            System.out.println("TODOS exists on the page. (PASSED)");
            txt = LocalDateTime.now()+": TODOS exists on the page. (PASSED) \n";
        } else{
            System.out.println("Page Header Failed!");
            txt = LocalDateTime.now()+": Page Header Failed! \n";
        }
        return(txt);
    }

    /* Check if the URL is Correct. */

    public String tasks() throws IOException
    {
        String txt ="";
        //driver.navigate().refresh();
        String cur_url= driver.getCurrentUrl();
        System.out.println("Current URL is: "+cur_url+" (PASSED)");
        txt = LocalDateTime.now()+": Current URL is: "+cur_url+" (PASSED) \n";
        return(txt);
    } 

    /* Add new tasks and check if adding an empty task is possible. */

    public String addNewTask() throws IOException
    {
        String txt = "";
        try {
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).clear();
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("Fisrt task that must be done");
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("Second task that must be done");
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("Third task that must be done");
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            txt = driver.findElement(By.xpath("//footer/span/strong")).getText();
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            String tmp1 = driver.findElement(By.xpath("//footer/span/strong")).getText();
            if (txt.equals(tmp1)) {
                tmp1="";
                System.out.println("Add new task scenario has done successfully. (PASSED)");
                txt = LocalDateTime.now()+": Add new task scenario has done successfully, and ("+ txt+") items added. (PASSED) \n";
                txt = txt+LocalDateTime.now()+": Add empty task scenario has been done successfully, and an empty item was not added. (PASSED) \n";
            }else
            {
                txt = LocalDateTime.now()+": Add new task scenario has been done successfully, and ("+ txt+") items added, but adding an empty value failed.\n";
            }

        } catch (Exception e) {
            System.out.println("Add new task scenario Failed!");
            txt = LocalDateTime.now()+": Add new task scenario Failed! \n";
        }
        return(txt);
    }

    /* Delete a tasks by click on the delete button */

    public String deleteTasks() throws IOException
    {
        String txt = "";
        try {
            Actions actions = new Actions(driver);
            WebElement deletebutton = driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li:nth-child(1) > div > input"));
            actions.moveToElement(deletebutton).build().perform();
            //Thread.sleep(5000);
            driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li:nth-child(1) > div > button")).click();
            txt = driver.findElement(By.xpath("//footer/span/strong")).getText();
            System.out.println("Delete task scenario has been done successfully. (PASSED)");
            txt = LocalDateTime.now()+": Delete task scenario has been done successfully, and ("+ txt+") items have remained. (PASSED) \n";
        } catch (Exception e) {
            System.out.println("Delete task scenario Failed!");
            txt = LocalDateTime.now()+": Delete task scenario Failed! \n";
        }
        return(txt);
    }

    /* Check afew radio buttons and check clear slected tasks. */

    public String deletemultipleTasks() throws IOException
    {
        String txt = "";
        try {
            driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[1]/div/input")).click();
            driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[2]/div/input")).click();
            Thread.sleep(1000);
            txt = driver.findElement(By.xpath("//footer/span/strong")).getText();
            driver.findElement(By.className("clear-completed")).click();
            System.out.println("Delete Multiple tasks scenario has been done successfully. (PASSED)");
            txt = LocalDateTime.now()+": Delete Multiple tasks scenario has been done successfully, and ("+ txt+") items have remained. (PASSED) \n";
        } catch (Exception e) {
            System.out.println("Delete Multiple tasks scenario Failed!");
            txt = LocalDateTime.now()+": Delete Multiple tasks scenario Failed! \n";
        }
        return(txt);
    }

    /* Editing a tasks. */

    public String editTask() throws IOException
    {
        String txt = "";
        try {
            Actions actions = new Actions(driver);
            WebElement edittask = driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li:nth-child(1) > div > label"));
            actions.doubleClick(edittask).perform();
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li.editing > input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li.editing > input")).sendKeys(" Task1 Changed successfully....");
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li.editing > input")).sendKeys(Keys.ENTER);
            Thread.sleep(500);
            System.out.println("Edit task scenario has been done successfully. (PASSED)");
            txt = LocalDateTime.now()+": Edit task scenario has been done successfully. (PASSED) \n";
        } catch (Exception e) {
            System.out.println("Edit task scenario Failed!");
            txt = LocalDateTime.now()+": Edit task scenario Failed! \n";
        }
        return(txt);
    }

    /* Adding tasksnegative tests. */

    public String addTaskNegativeTest() throws IOException
    {
        String txt = "";
        String tmp = "";
        try {
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).clear();
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("This task has been added to test if the web application accepts a long text task without any problem and according to acceptance criteria.");
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            tmp = driver.findElement(By.xpath("//footer/span/strong")).getText();
            txt =txt+ LocalDateTime.now()+": There are no limitations for the number of entered characters and ("+ tmp+") items added. (Need a review) \n";
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("1234567890");
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            tmp = driver.findElement(By.xpath("//footer/span/strong")).getText();
            txt =txt+ LocalDateTime.now()+": It is possible to add numbers only, and ("+ tmp+") items added. (Need a review) \n";
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("!@#$%^&*()_+=-|}{//..,");
            driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            tmp = driver.findElement(By.xpath("//footer/span/strong")).getText();
            txt =txt+ LocalDateTime.now()+": It is possible to add signs only, and ("+ tmp+") items added. (Need a review) \n";
            
            if (tmp.equals("3")) {
                System.out.println("Negative Adding a task scenario has been done successfully and need a review.");
                txt =txt+ LocalDateTime.now()+": Negative Adding a task scenario has been done successfully and need a review. (NOT PASSED) \n";
            }else
            {
                txt = LocalDateTime.now()+": Negative Adding a task scenario has been done, and ("+ tmp+") items added, but adding some items failed. (Need a riview) \n";
            }

        } catch (Exception e) {
            System.out.println("Add new task scenario Failed!");
            txt = LocalDateTime.now()+": Add new task scenario Failed! \n";
        }
        return(txt);
    }

    /* Negative editing a task with empty text. */

    public String negativeeditTask() throws IOException
    {
        String txt = "";
        try {
            Actions actions = new Actions(driver);
            WebElement edittask = driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li:nth-child(1) > div > label"));
            actions.doubleClick(edittask).perform();
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li.editing > input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body > todo-app > section > section > ul > li.editing > input")).sendKeys(Keys.ENTER);
            Thread.sleep(500);
            System.out.println("Edit task with empty text scenario has been done successfully. (PASSED)");
            txt = LocalDateTime.now()+": Negative Edit task with empty text scenario has been done successfully. (PASSED) \n";
        } catch (Exception e) {
            System.out.println("Edit task scenario Failed!");
            txt = LocalDateTime.now()+": Edit task scenario Failed! \n";
        }
        return(txt);
    }

    /* Check Clear completed button link is shown correctly. */

    public String checkclearbutton() throws IOException
    {
        String txt = "";
        try {
            driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[1]/div/input")).click();
            driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[2]/div/input")).click();
            driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[3]/div/input")).click();
            Thread.sleep(1000);
            txt = driver.findElement(By.className("clear-completed")).getText();
            System.out.println(txt+" button link is shown correctly. (PASSED)");
            txt = LocalDateTime.now()+": Clear completed button link is shown correctly. (PASSED) \n";
        } catch (Exception e) {
            System.out.println("Delete Multiple tasks scenario Failed!");
            txt = LocalDateTime.now()+": Showing clear completed button link scenario Failed! \n";
        }
        return(txt);
    }

    /* Negative test add many tasks. */

    public String addManytasks() throws IOException
    {
        String txt = "";
        try {
            for (int i = 1; i <= 100; i++) {
                driver.findElement(By.cssSelector("body > todo-app > section > header > input")).clear();
                driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys("Task"+i+" that must be done");
                driver.findElement(By.cssSelector("body > todo-app > section > header > input")).sendKeys(Keys.ENTER);
            }
            driver.findElement(By.xpath("/html/body")).sendKeys(Keys.END);
            txt = LocalDateTime.now()+": 100 tasks have been added successfully. (PASSED) \n";
            System.out.println("100 tasks have been added successfully. (PASSED)");
        } catch (Exception e) {
            System.out.println("100 task scenario Failed!");
            txt = LocalDateTime.now()+": 100 task scenario Failed! \n";
        }
        return(txt);
    }



    /* Close the browser and finish the test. */

    public void closebrowser()
    {
        driver.close();
    }
    
    public static void main(String args[]) throws InterruptedException, IOException
    {
        
        App todolist = new App();
        todolist.savelogfile("---------------- Test execution "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+"--------------- \n");
        todolist.savelogfile(todolist.invokeBrowser());
        todolist.savelogfile(todolist.verifyPageTitle());
        todolist.savelogfile(todolist.tasks());
        todolist.savelogfile(todolist.pageHeader());
        todolist.savelogfile(todolist.addNewTask());
        todolist.savelogfile(todolist.editTask());
        todolist.savelogfile(todolist.deleteTasks());
        todolist.savelogfile(todolist.deletemultipleTasks());
        todolist.savelogfile(todolist.addTaskNegativeTest());
        todolist.savelogfile(todolist.negativeeditTask());
        todolist.savelogfile(todolist.checkclearbutton());
        todolist.savelogfile(todolist.addManytasks());
        
        Thread.sleep(2000);
        todolist.closebrowser();


        try {
            Runtime.getRuntime().exec(new String[] { "c:\\windows\\notepad.exe", "..\\Automation_Test\\README.md" });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
