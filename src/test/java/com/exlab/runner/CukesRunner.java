package com.exlab.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin ={"json:target/cucumber.json",
                "html:target/default-html-reports.html",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "com/exlab/stepDefs",
        dryRun = true,
        tags = "@wip"
)
public class CukesRunner {

  /*
  * Cucumber framework oluşturmak için; iki dependecy indirdik; cucumber java, cucumber junit
  * Üçlü bir yapıdır. Bu framworkun en büyük özelliği dışarıdan bakan birinin(techicla or nontechinal person) bu frameworku çok rahat
  * okuabilmesi ve görebilmesidir.
  * Bu frameworkte DDB yaklaşımı kullanılmaktadır. İdeal bir iş ortamında PO, QA ve developer bir araya gelip user storylerden scenarioları çıakrdığını,
  * bu senoryaların TestNG de her bir teste karşılık gelmektedir.Ve bunları basit bir structer ile yazıyoruz; ve yazarken ghörkin dili kullanıyoruz.
  * Given - when -then key wordleri ile yazılıyor. Scenarioları her bir stepte şuna git, şuna tıkla şeklinde basit bir dille yazıyoruz.
  * Scenariolarda her bir stebe karşılık gelen bir metod var ve bu metodlar step definitions package ın altında bulunuyor.
  * Yukarıda da Runner class var ve bu komutan. Runner class üzerinden feature filesdaki senaryoların ve step defs. daki metodların birbiriyle eşleşerekten
  * testlerimizi koşturuyoruz.
  * TestNG deki POM(herbir webpage e karşılık gelen bir page classım olsun), Cucumberda da var.Utilities de aynı şekilde.
  *
  *
  * *CukesRunner testlerimi koşturacağım, scenariolarımı çalıştıracağım merkez class.
    *CukesRunner scenarioları yakalayabilmesi için scenarioları yazdığım classın pathini verdim; fatures = "..";
    *Ve scenarioları yazdım, ancak bu scenariolarda herbir stepe/satıra karşılık gelen kod blokları yok(scenariolar undefine!!!);
        -Cukes Runnerı çalıştırdığımda bize bu scenariolar ile ilgili hazır taslaklar(kod bloklarını yazacağım) veriyor.
        -Bu taslakları alıp Login_StepDefs e yapıştırıyorum --> scenariolar ile ilgili metodları buraya yazıyorum(scenariolar define!!!).
    *Cukes Runner ın scenariolarda her bir stepe/satıra karşılık gelen metodları yakalayabilmesi için steps_defs package nın pathını aldım; glue = ".."
        -Cukes Runner artık bu scenariolara karşılık gelen metodları bulabilir.
    *Tüm bunları yaptıktan sonra her bir testin steplerini ayrı ayrı görebiliyorum.--> konsolda right ticke tıkla.
    *Kısaca Cucumber temelde 3 parçalı bir yapıdan oluşuyor;
        -CukesRunner - steps_defs - features

    *tagslar --> İlgili tag lar ile istediğimiz scenarioyu(TestNG de test caseidi) koşturabiliyoruz.
             --> cloudda/jenkins de @smoke tagıyla smoke test koşturabiliriz her gün.
             --> JIRA otomasyon için cucumber seçeneği var; bu framework da yazdığımız her bir scenarionun steplerini JIRA ya aktarabiliyoruz.
                 JIRA her bir test scenarioları için ID numarası üretiyor; bu ID numaralarını bu frameworkteki scenariolara @ID tag ile ekliyebiliyoruz.
                 Framework bittikten sonra(tüm testler koşuldu) jenkins file çıkıyor; bu çıkan file'ı JIRA ya aktardığımızda, JIRA @ID ile eşleştirmesini
                 yapıyor. Böylece jenkins raporu JIRA ya eklenmiş oluyor. O yüzden bu taglar önemli.
    *rapor --> 1-Rapor oluşturmak için ilk plugin ={"json:target/cucumber.json"}, yazıyoruz CucumberOption ın içerisine.
                ->> CukesRunner da koşturduğunda önceden gözükmeyen target klasöründe cucumber.json file dosyası oluşacak.
                ->> .json file dosyası nedir? --> içerisinde List(ArrayList) ve map in içi içe olduğu yapıdır. [{key,value}{key,value}]
           --> 2-Rapor oluşturmak için ikinci olarak plugin i dependincies in dışına copy-past yaptık. plugini githubdan aldık; https://github.com/damianszczepanik/cucumber-reporting --> pom xml den aldık.
           --> 3-Üçüncü olarak: Daha sonra Maven barına tıkla - Lifecycle - clean - verify --> maven html raporu oluşturuyor. Kısayolu; ctrl + ctrl'a bas. mvn verify yaz
                ->> maven barı gözükmüyorsa; View - Appearance - Tool Windows Bar'a tıkla.
           --> Sonuç : verify'a bastıktan sonra target yeniden gözükecek.
                ->>Tekrar target'e tıkla - 'cucumber-html-reports' - .html uzantılı filelar senin raporların; explorerdan görebilirsin.
                ->>Her rapor oluşturulduğunda üstüne yazar. O yüzden  explorerdan aldığın raporları saklamak istiyorsan
                   başka bir dosyaya copy-past yap.

    *TestNG de browserlarla Selenium ile konuşuyorduk, testleri de testNG yardımıyla koşturuyorduk.
    *Cucumber da browserlarla yine Selenium konuşuyoruz, fakat testlerimizi cucumber - JUnit(unit testleri annotationları ile) ile testleri koşturuyor.
    * Cucumberın kendine ait bir structorı/yapısı var; 3 lü bir yapısı var. Runner class - future files ve steps definition
    * future file da --> test scenarioları var; her bir scenario, TestNG deki bir teste karşılık geliyor.
                      --> ghörkin dili ile yazıldığı için daha anlaşılır; given-when-then
                      --> scenariolardaki her bir step bir metoda karşılık geliyor. Bu metodları da step definitions package na yerleştiriyoruz;
                          @when @then @given tagları ile bağlantı sağlanıyor
    * Step definition class --> bu classrlarda da actionlarım gerçekleşiyor; driverlar, click, send keys, aasertions,..
    * CooksRunner feature a nasıl erişiyor; @CucumberOptions ın içine yazdığımız pathi ile.
    * CooksRunnera step defs deki metodlara nasıl erişiyor; @CucumberOptions ın içine yazdığımız pathi ile.
    * tags; istediğimiz testlerimizi koşturmamızı, idtediğimiz test suitleini koşturmamızı sağlayan bir özellik.
    * rapor; 1-Cucumberın kendine ait raporu var; ama çok gelişmiş bir rapor değil, tercih edilmiyor;
    *          Rapor oluşması için pluginin sonuna "html:target/default-html-reports" ekliyoruz. -->  CukesRunnerı çalıştırınca rapor oluşur.
    *            rapor --> target - 'default-html-reports' klasörünün altında; index.html
    *        2-plugin ={"json:target/cucumber.json", bunu ekledikten sonra testi koşturduğumuzda, o teste ait datalar
    *        cucumber.json file nın içiersine json formatında atılıyor.Fakat bu raporumun oluşması için yeterli değil.
    *        Maven ile rapor oluşturma; iki plugin dependency indirdik; ilk plugin mavenı bulup çalıştırmasını sağlıyorken
    *           ikinci plugin mavenın testler koşulduktan sonra o raporu üretmesini sağlıyor.
    *           O yüzden CukesRunner ismi, plugin de ve projede aynı olmalı, deyse çalışmaz.
    *        maven dan vrify ettiğimizde maven cucumber.json filendaki dataları alarak bir html rapora dönüştürüyor.
    *Hooks; Before ve After annotationlarını Seleniumdan indirmelisin; JUnit den değil, deyse hata alırsın.
    *
    * CookRunnerdan çalıştırırsak sadece html report alırız; cucumber raporu için mavenı çalıştırman lazım.
    *
    *
    *

*
   */
}


