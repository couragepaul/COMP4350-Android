package softeng.apartmentapp;

import android.app.Application;
import android.app.Instrumentation;
import android.test.ApplicationTestCase;
import com.robotium.solo.Solo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private static  String username = "Note 1";
    private static  String password = "Note 2";

    private Solo solo = new Solo(null);
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.

    }



    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    public void test_login() {
        solo.enterText(0, username);
        solo.enterText(1, password);
        solo.clickOnButton("Login");
        assertTrue(solo.searchText("Please wait. Logging in."));
        solo.waitForActivity("com.pointabout.personal.MainTabActivity", 3000);
        solo.assertCurrentActivity("The activity should be Main Tab", "MainTabActivity");
        solo.sendKey(Solo.MENU);
        solo.clickOnText("Logout");
        solo.waitForText("Are you sure you want to log out");
        solo.clickOnButton("Logout");
        solo.waitForText("You have been logged out of Personal.");

    }


}