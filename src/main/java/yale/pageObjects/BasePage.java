package yale.pageObjects;

import framework.BaseElement;
import framework.Browser;
import framework.Config;
import org.openqa.selenium.By;

public class BasePage  {

    protected Browser browser =Browser.getInstance();
    protected BaseElement baseElement;

    public BasePage(By by) {
        baseElement= new BaseElement();
        BaseElement.getElement(by);
    }
}
