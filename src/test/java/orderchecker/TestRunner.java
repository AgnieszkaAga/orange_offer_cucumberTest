package orderchecker;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class )
@CucumberOptions(
        features = {"src/test/java/resources/orderchecker/order.feature"}
)
public class TestRunner {
}
