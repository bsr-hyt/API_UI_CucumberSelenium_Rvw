package com.exlab.stepDefs;

import com.exlab.pages.LoginPage;
import com.exlab.request.ExlabRequest;
import com.exlab.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class SignUpStepDef {


    @When("The user create a POST request with {string} and {string} and {string} and {string} and {string}")
    public void the_user_create_a_POST_request_with_and_and_and_and(String name, String email, String password, String about, String terms) {
        ExlabRequest.registerNewUser(name, email, password, about, terms);
        ExlabRequest.response.prettyPrint();
    }

    @Then("The user verifies that the status code is {int}")
    public void the_user_verifies_that_the_status_code_is(int expectedStatusCode) {
        assertEquals(expectedStatusCode,ExlabRequest.response.statusCode());
    }

    @Then("The user verifies that body contains {string}")
    public void the_user_verifies_that_body_contains(String token) {
        assertTrue(ExlabRequest.response.body().asString().contains(token));

    }

    @Then("The compiler gets the token")
    public void the_compiler_gets_the_token() {
        ConfigurationReader.set("newUserToken",ExlabRequest.token);

    }
    @When("The user creates a PATCH request and send the token {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string}")
    public void the_user_creates_a_PATCH_request_and_send_the_token_name_and_and_and_and_and_and_and_and_and(String name,String password,String admin,String about, String terms,
                                                                                                             String job,String company,String website,String location, String skills) {
        ExlabRequest.patchSaveProfile(name,password,admin,about,terms,job,company,website,location,skills);
    }

    @Then("The user creates a GET request to get user own profile with token")
    public void the_user_creates_a_GET_request_to_get_user_own_profile_with_token() {
        ExlabRequest.getOwnProfile();
    }

    @Then("The user verifies that name as {string} and email as {string}")
    public void the_user_verifies_that_name_as_and_email_as(String expectedName, String expectedEmail) {
        assertEquals(expectedName,ExlabRequest.name);
        assertEquals(expectedEmail,ExlabRequest.email);
    }

    @When("The user creates a POST request for add a new experience with {string} and {string} and {string} and {string} and {string} and {string} and {string}")
    public void the_user_creates_a_POST_request_for_add_a_new_experience_with_and_and_and_and_and_and(String job, String company, String location, String fromdate,
                                                                                                      String todate, String current, String description) {
        ExlabRequest.postExperience(job, company, location, fromdate, todate, current, description);
    }

    @Then("The user is on the Dashboard page")
    public void the_user_is_on_the_Dashboard_page() throws InterruptedException {
        new LoginPage().setup();
    }

    @Then("The user verifies that UI experience amd API experience must be match job is {string}")
    public void the_user_verifies_that_UI_experience_amd_API_experience_must_be_match_job_is(String expectedJob) throws InterruptedException {
        //from ui
        String actualJobFromUI = new LoginPage().getExperienceJob(expectedJob);
        //from api
        String actualJobFromApi = ExlabRequest.experience_Job;
        //compare
        assertEquals(actualJobFromApi,actualJobFromUI);
    }



}