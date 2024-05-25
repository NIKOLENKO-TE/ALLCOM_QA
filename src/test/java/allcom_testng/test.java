package allcom_testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class test extends TestBaseSE {
    public final String HOME_PAGE_URL2 = "https://nikolenko-te.github.io/PHOTOGRAPHER_BERLIN/";

    @Test
    public void testHomePage() {
        app.driver.get(HOME_PAGE_URL2);
        WebElement test1 = app.driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[3]/div[1]/button[1]/*[1]"));
        test1.click();
        WebElement test2 = app.driver.findElement(By.xpath("//div[@id='file_input_container']"));
        test2.click();
        uploadFile("C:\\Users\\PORTISHEAD\\Downloads\\111111\\photo.jpeg");
    }
    public void uploadFile(String filePath) {
        try {
            // Скопировать путь к файлу в буфер обмена
            StringSelection stringSelection = new StringSelection(filePath);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            // Создать объект Robot
            Robot robot = new Robot();

            // Добавить задержку
            robot.delay(1000);

            // Нажать комбинацию клавиш для вставки текста из буфера обмена
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Добавить задержку
            robot.delay(1000);

            // Нажать Enter для подтверждения выбора файла
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}