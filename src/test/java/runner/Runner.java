package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json"}, //this plugin will create execution report
        features = "src/test/resources/features", // here we specify where do we store our feature files
        glue = "steps", // here we letting Runner class know where do we store our step definition code
        tags = "@Oppfi_Api" // we can use tags to specify which feature/scenario to run


)
public class Runner {
}
 