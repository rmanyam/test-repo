package com.newsuk.model.web;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.newsuk.common.utilities.DriverProvider;

public class PageBase {
	private String currentWindowHandleName;
	protected final WebDriver driver;
	private String popUpWindowHandleName;

	/**
	 * Constructs an object and assigns a web driver object
	 * 
	 * @param driver
	 *            the driver to be assigned
	 */
	public PageBase() {
		this.driver = DriverProvider.getDriver();
	}

	/**
	 * @see Page#close()k Closes the page.
	 */
	public void close() {
		driver.close();
	}

	/**
	 * Returns <code>true</code> if the page contains the given text, and
	 * <code>false</code> otherwise.
	 * 
	 * @param text
	 * @return
	 */
	public boolean containsText(String text) {
		return driver.getPageSource().contains(text);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getSelectedValueFromListBox(WebElement listBox) {
		Select select = new Select(listBox);
		WebElement element = select.getFirstSelectedOption();
		return element.getText();
	}

	public String getText(By by) {
		return findElement(by).getText();

	}

	/**
	 * Returns title of the current browser
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	public boolean isElementExist(WebElement element) {
		boolean bFlag = false;
		try {
			if (element.isDisplayed()) {
				bFlag = true;
			}
		} catch (Exception e) {
			return false;
		}
		return bFlag;
	}

	public boolean isElementExists(By by) {
		List<WebElement> elements = driver.findElements(by);
		if (elements.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void selectCheckBox(By by) {
		WebElement checkBoxElement = findElement(by);
		if (!checkBoxElement.isSelected()) {
			checkBoxElement.click();
		}
	}

	public void selectListByText(WebElement webElement, String text) {
		Select select = new Select(webElement);
		select.selectByVisibleText(text);
	}

	public void typeElement(By by, String value) {
		WebElement locator = findElement(by);
		locator.clear();
		locator.sendKeys(value);
	}

	/**
	 * verifying <web element> does not exists for given element by the
	 * id/name/x-path
	 */
	public void verifyElementNotExists(By by) {
		List<WebElement> elements = driver.findElements(by);
		if (elements.size() > 0) {
			throw new RuntimeException(
					"Expected: element does not exist.\n Got: element exists.");
		}
	}

	protected void closePopUpWindow() {
		getDriver().switchTo().window(popUpWindowHandleName).close();
		getDriver().switchTo().window(currentWindowHandleName);
	}

	/**
	 * Returns <code>true<code> if the element found by the given id/name/x-path
	 * <code>false</code> otherwise.
	 * 
	 * @param By
	 *            (id/name/x-path)
	 */
	protected boolean exists(By by) {
		try {
			return driver.findElement(by) != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Returns <web element> of the driver given by the id/name/x-path
	 */
	protected WebElement findElement(By by) {
		return driver.findElement(by);
	}

	/**
	 * Returns list of <web elements> of the driver given by the id/name/x-path
	 */
	protected List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	/**
	 * Instantiate and return an instance of the given class, and set a lazy
	 * proxy for each of the WebElement and List fields that have been declared.
	 * 
	 * @param <T>
	 *            the type of class to be instantiated
	 * @param pageClass
	 *            the page class to be instantiated
	 * @return an instance of a page class
	 */
	protected <T> T getCurrentPageAs(Class<T> pageClass) {
		return PageFactory.initElements(getDriver(), pageClass);
	}

	/**
	 * Returns the assigned driver.
	 * 
	 * @return the assigned driver
	 */
	protected WebDriver getDriver() {
		return driver;
	}

	protected WebDriver getPopUpWindow() {
		currentWindowHandleName = getDriver().getWindowHandle();
		Set<String> childWindows = getDriver().getWindowHandles();

		for (String windowHandle : childWindows) {
			if (!windowHandle.equals(currentWindowHandleName)) {
				popUpWindowHandleName = windowHandle;
			}
		}

		return getDriver().switchTo().window(popUpWindowHandleName);
	}

	protected void selectCheckBox(By by, boolean expectedState) {
		WebElement checkBoxElement = findElement(by);
		if (expectedState != checkBoxElement.isSelected()) {
			checkBoxElement.click();
		}
	}

	/**
	 * clicking on <web element> given by the id/name/x-path
	 */
	protected void selectElement(By by) {
		findElement(by).click();
	}

	public void click(By by) {
		WebElement element = findElement(by);
		element.click();
	}

	public List<WebElement> getAllElements(By elementsPath) {
		return driver.findElements(elementsPath);
	}

	public void mouseOverToElement(By elementsPath) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(elementsPath));
		action.build();
	}

}
