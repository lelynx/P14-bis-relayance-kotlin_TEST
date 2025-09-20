package com.kirabium.relayance.cucumber.steps

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.activity.main.MainActivity
import com.kirabium.relayance.util.RecyclerViewItemCountAssertion
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then

class AddCustomerSteps {

    @Given("I launch the application")
    fun iLaunchApplication() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Given("I am on the customer list")
    fun iAmOnCustomerList() {
        onView(withId(R.id.customerRecyclerView))
            .check(RecyclerViewItemCountAssertion.withItemCount(5))
    }

    @When("I click on the {string} button")
    fun iClickOnButton(label: String) {
        // Ici on suppose que c'est le FloatingActionButton
        onView(withId(R.id.addCustomerFab)).perform(click())
    }

    @When("I enter the name {string}")
    fun iEnterName(name: String) {
        onView(withId(R.id.nameEDT))
            .perform(typeText(name), closeSoftKeyboard())
    }

    @When("I enter the email {string}")
    fun iEnterEmail(email: String) {
        onView(withId(R.id.emailEditText))
            .perform(typeText(email), closeSoftKeyboard())
    }

    @When("I submit the form")
    fun iSubmitForm() {
        onView(withId(R.id.saveFab)).perform(click())
    }

    @Then("I should see {string} in the customer list")
    fun iShouldSeeCustomerInList(name: String) {
        onView(withText(name)).check(matches(isDisplayed()))
    }

    @Then("the list should contain {int} customers")
    fun listShouldContainCustomers(count: Int) {
        onView(withId(R.id.customerRecyclerView))
            .check(RecyclerViewItemCountAssertion.withItemCount(count))
    }
}