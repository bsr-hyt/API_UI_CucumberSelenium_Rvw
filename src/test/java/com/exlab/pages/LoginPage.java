package com.exlab.pages;

import com.exlab.request.ExlabRequest;
import com.exlab.utilities.ConfigurationReader;
import com.exlab.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(css ="#email" )
    public WebElement userEmailInput_loc;//bu ne demek: locatorı css = "#email" olan web elementi userEmailInput_loc nın içine attım.
                                        // Neden böyle yaptık? Neden page object model?
                                        //1.her şeyi düzenli, nizamlı bir biçimde yazmamızı sağlıyor.
                                        //2.her şeyi tekrar tekrar locate etmemize gerek kalmıyor; Farklı yerlerde(classlarda) de kullanmama imkan veriyor.
                                        //3. web elementinin locati değiştiğinde ayrı ayrı düzeltmemize gerek kalmıyor.
    @FindBy(css = "#yourPassword")
    public WebElement passwordInput_loc;

    @FindBy(xpath = "//*[.='Login']")
    public  WebElement loginBtn_loc;

    @FindBy(xpath ="//*[contains(text(),'Password is incorrect. Please check')]" )
    public WebElement passwordWarningMessage_loc;

    @FindBy(xpath = "//*[contains(text(),'Email address is incorrect. Please check')]")
    public WebElement emailWarningMessage_loc;



    public void login(){
        String userEmail= ConfigurationReader.get("userEmail");
        String password= ConfigurationReader.get("password");

        userEmailInput_loc.sendKeys(userEmail);
        passwordInput_loc.sendKeys(password);
        loginBtn_loc.click();
    }


    public void login(String userEmail, String password){
        userEmailInput_loc.sendKeys(userEmail);
        passwordInput_loc.sendKeys(password);
        loginBtn_loc.click();
    }

    public void setup() throws InterruptedException {
        Driver.get().get(ConfigurationReader.get("url"));
//        login(ExlabRequest.email,ConfigurationReader.get("password"));
        login(ConfigurationReader.get("email"),ConfigurationReader.get("password"));
        Thread.sleep(2000);
    }

    public String getExperienceJob(String job) throws InterruptedException {
        navigateToModule(ConfigurationReader.get("name"),"My Profile");
        return Driver.get().findElement(By.xpath("(//span[.='"+job+"'])[1]")).getText();

    }



}
