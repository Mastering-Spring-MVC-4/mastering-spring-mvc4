import masterSpringMvc.MasterSpringMvcApplication;
import masterSpringMvc.auth.StubSocialSigninConfig;
import masterSpringMvc.search.StubTwitterSearchConfig;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pages.LoginPage;
import pages.ProfilePage;
import pages.SearchResultPage;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        MasterSpringMvcApplication.class,
        StubTwitterSearchConfig.class,
        StubSocialSigninConfig.class
})
@WebIntegrationTest(randomPort = true)
public class FluentIntegrationTest extends FluentTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Override
    public WebDriver getDefaultDriver() {
        return new PhantomJSDriver();
    }

    public String getDefaultBaseUrl() {
        return "http://localhost:" + serverPort;
    }

    @Test
    public void hasPageTitle() {
        goTo("/");
        assertThat(findFirst("h2").getText()).isEqualTo("Login");
    }

    @Page
    private LoginPage loginPage;
    @Page
    private ProfilePage profilePage;
    @Page
    private SearchResultPage searchResultPage;

    @Test
    public void should_be_redirected_after_filling_form() {
        goTo("/");
        loginPage.isAt();

        loginPage.login();
        profilePage.isAt();

        profilePage.fillInfos("geowarin", "geowarin@mymail.com", "03/19/1987");
        profilePage.addTaste("spring");

        profilePage.saveProfile();

        takeScreenShot();
        searchResultPage.isAt();
        assertThat(searchResultPage.getNumberOfResults()).isEqualTo(2);
    }

}
