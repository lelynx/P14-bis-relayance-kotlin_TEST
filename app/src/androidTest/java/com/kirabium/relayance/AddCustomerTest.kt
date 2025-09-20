package com.kirabium.relayance

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.ui.activity.addCustomer.AddCustomerActivity
import com.kirabium.relayance.ui.activity.main.MainActivity
import com.kirabium.relayance.util.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddCustomerTest {

    // createAndroidComposeRule permet d'interagir avec les vues classiques ET les composables.
    // Il lance l'Activity spécifiée.
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        Intents.init() // Initialiser Espresso Intents pour vérifier le lancement de AddCustomerActivity
    }

    @After
    fun tearDown() {
        Intents.release() // Libérer Espresso Intents
    }

    @Test
    fun ajoutClientDoitMettreAJourListe() {
        // --- Interagir avec MainActivity (Vues Classiques) ---
        // Vérifier qu'il y a N clients au départ (si vous gardez cette vérification)
         onView(withId(R.id.customerRecyclerView))
             .check(RecyclerViewItemCountAssertion.withItemCount(5)) // Adaptez le nombre attendu initial

        // Cliquer sur le bouton "+" FAB dans MainActivity
        onView(withId(R.id.addCustomerFab)).perform(click())

        // --- Vérifier la transition et interagir avec AddCustomerActivity (Jetpack Compose) ---
        // Vérifier que l'intent pour lancer AddCustomerActivity a été envoyé
        Intents.intended(hasComponent(AddCustomerActivity::class.java.name))


        // TODO: il faut avoit ajouter les testTag aux EditText
        // Champ "Nom"
        composeTestRule.onNodeWithTag("nameEDT")
            .performTextInput("John Doe")

        // Champ "Email"
        composeTestRule.onNodeWithTag("emailEDT")
            .performTextInput("john@email.com")
        // Espresso gère la fermeture du clavier pour les composables TextField en général.
        // closeSoftKeyboard() n'est pas directement applicable ici de la même manière qu'avec Espresso sur les EditText.

        // Cliquer sur le bouton "Enregistrer" dans AddCustomerScreen
        composeTestRule.onNodeWithText("Enregistrer") // Ou utilisez un testTag
            .performClick()

        // Vérifier que la liste contient maintenant N+1 clients
         onView(withId(R.id.customerRecyclerView))
            .check(RecyclerViewItemCountAssertion.withItemCount(6)) // Adaptez le nombre attendu final
    }



}