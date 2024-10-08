package stepdefinitions;

import com.luizalabs.wishlist.WishlistApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = WishlistApplication.class)
public class StepDefsDefault {
}
