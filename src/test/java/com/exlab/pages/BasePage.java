package com.exlab.pages;

import com.exlab.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    //Page object model(POM)
    //Page object model(POM) is a design pattern in Selenium that creates an object repository for storing all web elements and methods.
    //It helps reduce code duplication and improves test case maintenance.
    //In Page Object Model, consider each web page of an application as a class file.

    //sitelerde farklı sayfalar açılsa da değişmeyen web elementler var. Bu elementleri bu classta locate edeceğiz.
    //BasePage abstract olmalı. Neden?
        //bu classtan obje üretilmeyecek.
        //Parent class olabilmesi için.

    //bunu( PageFactory.initElements(Driver.get(),this);) LoginPage classından alıp buraya kopyaladım. Artık LoginPage deki LoginPage constructerını kullanmama gerek kalmadı.
    //Çünkü LoginPage i BasePage classına extent ettim. Artık örn Loginpage den bir locator çağırdığımda ilk BasePage deki constructor çalışacak; extent ettğimden dolayı.

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//span[.='Dashboard']/../../../li")
    public List<WebElement> menuList;
    @FindBy(css = ".d-none.d-md-block.dropdown-toggle.ps-2")
    public WebElement userAccountName;
    @FindBy(xpath = "//span[.='Developers']")
    public WebElement developersMenu;
    @FindBy(xpath = "//span[.='Dashboard']")
    public WebElement dashboardMenu;


    public void navigateToModule(String tab){
        Driver.get().findElement(By.xpath("//span[.='" + tab + "']")).click();
    }


    public void navigateToModule(String tab, String module) throws InterruptedException {

        String tabLoc = "//span[.='" + tab + "']";
        String moduleLoc = "//span[.='" + module + "']";

        Thread.sleep(2000);
        WebElement tabElement = Driver.get().findElement(By.xpath(tabLoc));
        tabElement.click();

        Thread.sleep(2000);
        WebElement moduleElement= Driver.get().findElement(By.xpath(moduleLoc));
        moduleElement.click();

    }

    public String getAccountName(String accountName){
        return Driver.get().findElement(By.xpath("//span[.='"+accountName+"']")).getText();
    }

    public String getAccountName(){
        return userAccountName.getText();
    }

}

/**
 * Abstract class:
 * * is a restricted class that cannot be used to create objects
 * * (to access it, it must be inherited from another class).
 * Abstract method:
 * * can only be used in an abstract class,
 * * and it does not have a body.
 * * The body is provided by the subclass (inherited from).
 */