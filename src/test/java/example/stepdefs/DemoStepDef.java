package example.stepdefs;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * This class is the glue of demo.feature
 *
 * @author Di
 * @since 23/03/18
 */
public class DemoStepDef {

    private RequestSpecification request;
    private Response response;
    private ValidatableResponse validatableResponse;
    private List<ExpectedResult> expectedResults;

    @Given("^I have a request$")
    public void iHaveARequest() {
        request = given();
    }

    @When("^I send it$")
    public void iSendIt() {
        response = request.when().get("http://localhost:8090/lotto");
    }

    @Then("^the status code is (\\d+)$")
    public void theStatusCodeIs(int statusCode) {
        validatableResponse = response.then().statusCode(statusCode);
    }

    @And("^response equals to the following$")
    public void responseEqualsToTheFollowing(DataTable table) {
        expectedResults = table.asList(ExpectedResult.class);

        for (ExpectedResult expectedResult : expectedResults) {
            if (StringUtils.isNumeric(expectedResult.value)) {
                validatableResponse.body(expectedResult.attribute, equalTo(Ints.tryParse(expectedResult.value)));
            } else {
                validatableResponse.body(expectedResult.attribute, equalTo(expectedResult.value));
            }
        }
    }

    @And("^response contains the following$")
    public void responseContainsTheFollowing(DataTable table) {
        expectedResults = table.asList(ExpectedResult.class);

        for (ExpectedResult expectedResult : expectedResults) {
            Iterable<String> iterable = Splitter.on(",").trimResults().omitEmptyStrings().split(expectedResult.value);
            ArrayList<String> arr = Lists.newArrayList(iterable);
            arr.forEach(element -> validatableResponse.body(expectedResult.attribute, hasItems(Integer.parseInt(element))));
        }
    }

    @Getter
    @Setter
    private class ExpectedResult {
        private String attribute;
        private String value;
    }
}
