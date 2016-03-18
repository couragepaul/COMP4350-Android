package softeng.apartmentapp;

import android.app.Application;
import android.app.Instrumentation;
import android.test.ApplicationTestCase;
import com.robotium.solo.Solo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private static  String username = "username";
    private static  String password = "password";

    
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.

    }



    @Override
    public void tearDown() throws Exception
    {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
    }

    public void test_login() {}


}
