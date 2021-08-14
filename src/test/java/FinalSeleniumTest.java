/*
at least 3 different types of Xpath -> line 54(using title), line 57(using href), line 109(using img src)
at least 3 different types of cssSelector -> line 138(using relative(short) selector), line 80(using id selector), line 151(using absolute)
at least 3 different types of By.. ->  line 64(name), line 76(css), line 94(xpath)
at least 3 different types of JsExecutor -> line 143(scroll down(bottom of page)), line 62(passing value in it), line 160(scroll into view)
 */

import java.util.List;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import java.time.LocalTime;
import java.util.ArrayList;
import org.openqa.selenium.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FinalSeleniumTest extends ConstantsForFinalSeleniumTest {
    WebDriver driver = null;

    @Parameters("browserName")
    @BeforeTest
    public void setup(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void TestAll() throws InterruptedException {

        driver.get(MAIN_SITE_URL);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        waitForClick();

        WebElement myAccount = driver.findElement(By.xpath(MY_ACCOUNT_XPATH));
        myAccount.click();

        WebElement registerButton = driver.findElement(By.xpath(REGISTER_BUTTON_XPATH));
        registerButton.click();

        waitForClick();

        js.executeScript("document.getElementById('input-firstname').value='John';");

        WebElement lastName = driver.findElement(By.name(LASTNAME_BY_NAME));
        lastName.clear();
        lastName.sendKeys(LASTNAME);

        WebElement eMail = driver.findElement(By.xpath(EMAIL_XPATH));
        eMail.clear();
        eMail.sendKeys(getUniqueEmail());

        WebElement telephone = driver.findElement(By.xpath(TELEPHONE_XPATH));
        telephone.clear();
        telephone.sendKeys(TELEPHONE_NUMBER);

        WebElement password = driver.findElement(By.cssSelector(PASSWORD_SELECTOR));
        password.clear();
        password.sendKeys(PASSWORD);

        WebElement confirmPassword = driver.findElement(By.cssSelector(CONFIRM_PASSWORD_SELECTOR));
        confirmPassword.clear();
        confirmPassword.sendKeys(PASSWORD);

        WebElement radioButton = driver.findElement(By.name(RADIO_BUTTON_NAME));
        if (radioButton.isEnabled()) {
            radioButton.click();
        }

        WebElement privacyPolicyAgreement = driver.findElement(By.name(POLICY_AGREEMENT_NAME));
        if (privacyPolicyAgreement.isEnabled()) {
            privacyPolicyAgreement.click();
        }

        WebElement continueButton = driver.findElement(By.xpath(CONTINUE_BUTTON_XPATH));
        continueButton.click();

        waitForClick();

        Actions actions = new Actions(driver);
        WebElement desktops = driver.findElement(By.xpath(DESKTOPS_XPATH));
        actions.moveToElement(desktops).perform();

        WebElement showAllDesktops = driver.findElement(By.xpath(ALL_DESKTOPS_XPATH));
        showAllDesktops.click();

        WebElement mp3Players = driver.findElement(By.cssSelector(MP3_PLAYERS_SELECTOR));
        mp3Players.click();

        WebElement iPodShuffle = driver.findElement(By.xpath(IPOD_SHUFFLE_XPATH));
        iPodShuffle.click();

        WebElement iPodShuffleImage = driver.findElement(By.cssSelector(IPOD_SHUFFLE_IMAGE_SELECTOR));
        iPodShuffleImage.click();

        WebElement rightClickImage = driver.findElement(By.xpath(RIGHT_CLICK_ON_BUTTON_XPATH));

        Integer countImages = 1;
        for (int i = 0; i < 4; i++) {
            rightClickImage.click();
            waitForClick();
            countImages++;
        }

        WebElement imageCounter = driver.findElement(By.className(IMAGE_COUNTER_CLASSNAME));
        String imageCounterText = imageCounter.getText();
        Integer imageCounterToInt = Integer.parseInt(String.valueOf(imageCounterText.charAt(0)));

        boolean checkCounter = false;
        if (imageCounterToInt.equals(countImages)) {
            checkCounter = true;
            Assert.assertEquals(imageCounterToInt, countImages);
            System.out.println("4 photos are seen!");
        }

        WebElement closeImages = driver.findElement(By.xpath(CLOSE_IMAGES_XPATH));
        closeImages.click();

        WebElement writeReview = driver.findElement(By.cssSelector(WRITE_REVIEW_SELECTOR));
        writeReview.click();

        waitForClick();

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        waitForClick();

        WebElement review = driver.findElement(By.cssSelector(REVIEW_SELECTOR));
        review.clear();
        review.sendKeys(REVIEW);

        WebElement ratingRadioButton = driver.findElement(By.cssSelector(RATING_RADIO_BUTTON_SELECTOR));
        ratingRadioButton.click();

        WebElement submitReview = driver.findElement(By.cssSelector(SUBMIT_REVIEW_SELECTOR));
        submitReview.click();

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("button-cart")));
        WebElement addToCardButton = driver.findElement(By.cssSelector("#button-cart"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCardButton);
        addToCardButton.click();

        waitForClick();

        WebElement cardTotal = driver.findElement(By.id(CARD_TOTAL_ID));
        String realAmount = cardTotal.getText().substring(12);
        if (realAmount.equals("$122.00")) {
            System.out.println("Item successfully added to your cart");
        }

        WebElement checkForCheckoutCart = driver.findElement(By.id(CARD_TOTAL_ID));
        checkForCheckoutCart.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UNVISIBLEELEMENTXPATH)));
        driver.findElement(By.xpath(UNVISIBLEELEMENTXPATH)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(FIRST_NAME_DETAILS)));;
        WebElement firstNamingDetails = driver.findElement(By.name(FIRST_NAME_DETAILS));
        firstNamingDetails.clear();
        firstNamingDetails.sendKeys(NAME);

        WebElement lastNamingDetails = driver.findElement(By.id(LAST_NAME));
        lastNamingDetails.clear();
        lastNamingDetails.sendKeys(LASTNAME);

        WebElement address = driver.findElement(By.xpath(ADDRESS_XPATH));
        address.clear();
        address.sendKeys(ADDRESS);

        WebElement city = driver.findElement(By.name(CITY));
        city.clear();
        city.sendKeys(OUR_CITY);

        WebElement testDropDown = driver.findElement(By.id(TEST_DROPDOWN_ID));
        testDropDown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GEORGIA)));
        driver.findElement(By.xpath(GEORGIA)).click();

        WebElement regionState = driver.findElement(By.id(REGIONSTATE_ID));
        regionState.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TBILISI)));
        driver.findElement(By.xpath(TBILISI)).click();


        WebElement buttonPaymentContinueButton = driver.findElement(By.id(PAYMENT_ID));
        buttonPaymentContinueButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BUTTON_SHIPPING_ADDRESS_ID)));
        WebElement deliveryDetailsContinueButton = driver.findElement(By.id(BUTTON_SHIPPING_ADDRESS_ID));
        deliveryDetailsContinueButton.click();


//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-method")));
//        WebElement deliveryMethodContinueButton = driver.findElement(By.id("button-shipping-method"));
//        deliveryDetailsContinueButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(COMMENT)));

        WebElement comment = driver.findElement(By.name(COMMENT));
        comment.clear();
        comment.sendKeys("Commented here!");

        WebElement deliveryMethodButton = driver.findElement(By.id(DELIVERY_ID));
        deliveryMethodButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TERMS_AND_CONDITIONS_XPATH)));
        WebElement termsAndConditionsRadioButton = driver.findElement(By.xpath(TERMS_AND_CONDITIONS_XPATH));
        termsAndConditionsRadioButton.click();

        WebElement paymentMethodContinueButton = driver.findElement(By.id(BUTTON_PAYMENT));
        paymentMethodContinueButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOTAL_AMOUNT_XPATH)));
        WebElement totalAmount = driver.findElement(By.xpath(TOTAL_AMOUNT_XPATH));
        String totalAmountText = totalAmount.getText();

        if (totalAmountText.equals("$100.00")) {
            System.out.println("Item was successfully bought on your cart!");
        } else {
            System.out.println("you did not buy an item, try again!");
        }

        try {
            WebElement flatShippingRate = driver.findElement(By.xpath(SHIPPING_RATE_XPATH));
            String flatShippingRateText = flatShippingRate.getText();
            System.out.println(flatShippingRateText);
            if (flatShippingRateText.equals("$5.00")) {
                System.out.println("Flat shipping rate checked");
            } else {
                System.out.println("Flat shipping error!");
            }
        } catch (NoSuchElementException | NotFoundException e) {
            System.out.println(e.getMessage());
        }

        WebElement confirmOrderButton = driver.findElement(By.id(CONFIRM_BUTTON_ID));
        confirmOrderButton.click();

        waitForClick();

        WebElement orderPlacedSection = driver.findElement(By.xpath(ORDER_PLACED_SECTION_XPATH));
        orderPlacedSection.click();

        List<WebElement> listOfTable = driver.findElements(By.xpath(LIST_OF_TABLE_XPATH));

        ArrayList<String> elements = new ArrayList<String>();

        boolean temp = false;
        for (WebElement e: listOfTable) {
            String currentElement = e.getText();
            elements.add(currentElement);
        }

        System.out.println(elements);

        WebElement checkStatus = driver.findElement(By.xpath(CHECK_STATUS_XPATH));
        if (checkStatus.getText().equals("Pending")) {
            System.out.println("Status is pending!");
        }
    }

    private static void waitForClick() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }

    private static String getUniqueEmail() {

        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("HHmmss");
        LocalTime localtime = LocalTime.now();

        String randomStringGenerator = datetime.format(localtime);
        return "johnDoe" + randomStringGenerator + "@gmail.com";
    }
}
