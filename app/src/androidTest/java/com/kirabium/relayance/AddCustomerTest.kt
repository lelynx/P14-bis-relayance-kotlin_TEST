package com.kirabium.relayance

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.activity.main.MainActivity
import com.kirabium.relayance.util.RecyclerViewItemCountAssertion
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddCustomerTest {

    @Test
    fun ajoutClientDoitMettreAJourListe() {
        // Lancer l'application
        ActivityScenario.launch(MainActivity::class.java)

        // Vérifier qu'il y a 5 clients au départ
        onView(withId(R.id.customerRecyclerView))
            .check(RecyclerViewItemCountAssertion.withItemCount(5))

        // Cliquer sur le bouton "+"
        onView(withId(R.id.addCustomerFab)).perform(click())

        // Remplir le formulaire
        onView(withId(R.id.nameEditText))
            .perform(typeText("John Doe"), closeSoftKeyboard())

        onView(withId(R.id.emailEditText))
            .perform(typeText("john@email.com"), closeSoftKeyboard())

        // Sauvegarder
        onView(withId(R.id.saveFab)).perform(click())

        // Vérifier que "Jean Dupont" apparaît dans la liste
        onView(withText("John Doe")).check(matches(isDisplayed()))

        // Vérifier que la liste contient maintenant 6 clients
        onView(withId(R.id.customerRecyclerView))
            .check(RecyclerViewItemCountAssertion.withItemCount(6))
    }
}