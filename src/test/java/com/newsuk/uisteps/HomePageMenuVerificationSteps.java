package com.newsuk.uisteps;

import com.newsuk.model.web.HomePage;
import com.newsuk.model.web.SectionPage;
import com.newsuk.model.web.navigation.views.RSSFeedsViewPage;
import com.newsuk.model.web.navigation.views.SiteMapViewPage;
import com.newsuk.model.web.sections.MenuItem;
import com.newsuk.model.web.navigation.NavigationPanel;
import com.newsuk.model.web.navigation.views.ListViewPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;

import java.util.List;

/**
 * Created by ranjithmanyam on 29/08/2014.
 */
public class HomePageMenuVerificationSteps extends AbstractWebTest{

    static Logger log = LogManager.getLogger(HomePageMenuVerificationSteps.class.getName());
    private HomePage homePage;
    private NavigationPanel navigationPanel;
    private SectionPage sectionPage;
    private ListViewPage listViewPage;
    private SiteMapViewPage siteMapViewPage;
    private RSSFeedsViewPage rssFeedsViewPage;

    public HomePageMenuVerificationSteps() throws Exception {
        super();
    }

    @Given("^I am on home page$")
    public void loadHomePage() {
        this.homePage = new HomePage(driver).loadHomePage(baseUrl);
    }

    @Then("^I should see current date on the navigation bar$")
    public void verifyDisplayedDate(){
        DateTime dateTime = new DateTime();
        String expected = dateTime.toString(DateTimeFormat.forPattern("EEEE, MMMM d"));
        Assert.assertEquals(expected, homePage.getNavigation().getDate());
    }

    @Then("^I should be able see all the menus as follows$")
    public void I_should_be_able_see_all_the_menus_as_follows(List<MenuItem> menuItems) throws Throwable {
        for (MenuItem item: menuItems){
            Assert.assertTrue("Verifying Menu item " + item + " is displayed", homePage.getNavigation().isMenuItemDisplayed(item));
        }
    }

    @When("^I hover mouse pointer on menu \"([^\"]*)\"$")
    public void I_hover_mouse_pointer_on_menu(MenuItem menuItem) throws Throwable {
        navigationPanel = homePage.getNavigation().mouseOverOnMenuItem(menuItem);
    }

    @Then("^I the left panel heading should be \"([^\"]*)\"$")
    public void verifyLeftPanelHeading(String expectedHeading) throws Throwable {
        Assert.assertEquals("Verifying panel header", expectedHeading, navigationPanel.getLeftPanel().getHeader());
    }

    @Then("^the middle panel heading should be \"([^\"]*)\"$")
    public void verifyMiddlePanelHeading(String expectedHeading) throws Throwable {
        Assert.assertEquals("Verifying Middle panel header", expectedHeading, navigationPanel.getMiddlePanel().getHeader());
    }

    @Then("^the right panel heading should be \"([^\"]*)\"$")
    public void verifyRightPanelHeading(String expectedHeading) throws Throwable {
        Assert.assertEquals("Verifying Right panel header", expectedHeading, navigationPanel.getRightPanel().getHeader());
    }

    @Then("^I should be able navigate to \"([^\"]*)\" page$")
    public void navigateToSection(String sectionName) throws Throwable {
        sectionPage = navigationPanel.getLeftPanel().selectSection(sectionName);
    }

    @And("^I should see the logo$")
    public void verifyTheTimesLogo() throws Throwable {
        Assert.assertTrue("Verifying \"THE TIMES\" logo is displayed", sectionPage.getPageHeader().isLogoDisplayed());
    }

    @And("^The page header should be \"([^\"]*)\"$")
    public void verifyPageHeader(String header) throws Throwable {
        Assert.assertTrue("Verifying \"" + header + "\" is displayed at the top of the page", sectionPage.getPageHeader().isHeaderDisplayed(header));
    }

    @And("^The page title should be \"([^\"]*)\"$")
    public void verifyPageTitle(String pageTitle) throws Throwable {
        Assert.assertEquals("Verifying page title", pageTitle, sectionPage.getPageTitle());
    }

    @When("^I click on List View icon$")
    public void clickOnListViewIcon() throws Throwable {
        listViewPage = homePage.getNavigation().clickOnListView();
    }

    @Then("^I should see list of articles with article number, date & time and article title$")
    public void I_should_see_list_of_articles_with_article_number_date_time_and_article_title() throws Throwable {
        Assert.assertTrue("Verifying articles are displayed correctly", listViewPage.isArticlesDisplayedCorrectly());
    }

    @And("^I should see Filter headlines search box$")
    public void verifySearchBoxIsDisplayedOn() throws Throwable {
        Assert.assertTrue(listViewPage.isSearchBoxDisplayed());
    }

    @And("^I should see an ad on List View page$")
    public void verifyAnAdIsDisplayedOnListViewpage() throws Throwable {
        Assert.assertTrue(listViewPage.isAdDisplayed());
    }

    @When("^I click on SiteMap icon$")
    public void clickOnSiteMapIcon() throws Throwable {
        siteMapViewPage = homePage.getNavigation().clickOnSiteMapView();
    }

    @Then("^I should see all sections in SiteMap view page$")
    public void I_should_see_all_sections_in_SiteMap_view_page() throws Throwable {
        Assert.assertTrue("Verifying all sections are displayed under Site Map page", siteMapViewPage.isAllSectionsDisplayed());
    }

    @And("^I should see the corresponding sub-titles$")
    public void I_should_see_the_corresponding_sub_titles() throws Throwable {
        Assert.assertTrue("Verifying section Sub Titles are displayed", siteMapViewPage.isSectionSubTitlesDisplayed());
    }

    @When("^I click on \"([^\"]*)\" section from site map page$")
    public void clicinOnSection(String sectionName) throws Throwable {
        sectionPage = siteMapViewPage.clickOnSection(sectionName);
    }

    @And("^I click on \"([^\"]*)\" sub-title from site map page$")
    public void I_click_on_sub_title_from_site_map_page(String subTitle) throws Throwable {
        sectionPage = siteMapViewPage.clickOnSubTitle(subTitle);
    }

    @And("^I click on \"([^\"]*)\" sub-section from site map page$")
    public void I_click_on_sub_section_from_site_map_page(String subSection) throws Throwable {
        sectionPage = siteMapViewPage.clickOnSubSection(subSection);
    }

    @Then("^I search for \"([^\"]*)\" on site map page$")
    public void I_search_for_on_site_map_page(String searchText) throws Throwable {
        siteMapViewPage = siteMapViewPage.search(searchText);
    }

    @And("^I should be able to find the search string \"([^\"]*)\" in all results returned$")
    public void I_should_be_able_to_find_the_search_string_in_all_results_returned(String searchText) throws Throwable {
        Assert.assertTrue("Verifying search term displayed in all search results", siteMapViewPage.isSearchResultsDisplayedCorrectly(searchText));
    }

    @And("^I should be able to navigate to \"([^\"]*)\" section by clicking on it$")
    public void I_should_be_able_to_navigate_to_section_by_clicking_on_it(String searchResult) throws Throwable {
        sectionPage = siteMapViewPage.clickOnSearchResult(searchResult);
    }

    @When("^I click on RSS Feeds icon$")
    public void I_click_on_RSS_Feeds_icon() throws Throwable {
        rssFeedsViewPage = homePage.getNavigation().clickOnRSSFeedsView();
    }

    @Then("^The RSS Feeds header should be \"([^\"]*)\"$")
    public void The_RSS_Feeds_header_should_be(String expectedHeader) throws Throwable {
        Assert.assertEquals("Verifying RSS Feeds page header", expectedHeader, rssFeedsViewPage.getHeading());
    }

    @And("^The RSS feeds are displayed correctly$")
    public void The_RSS_feeds_are_displayed_correctly() throws Throwable {
        Assert.assertTrue("Verifying the feeds are displayed correctly (Title, Content and Updated date)", rssFeedsViewPage.isFeedsDisplayedCorrectly());
    }
}
