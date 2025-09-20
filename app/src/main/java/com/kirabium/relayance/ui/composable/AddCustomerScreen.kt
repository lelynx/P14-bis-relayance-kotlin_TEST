package com.kirabium.relayance.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirabium.relayance.ui.activity.addCustomer.AddCustomerUiState

@Composable
fun AddCustomerScreen(

    uiState: AddCustomerUiState = AddCustomerUiState(),
    onNameChanged: (String) -> Unit={},
    onEmailChanged: (String) -> Unit = {},
    onSave: () -> Unit = {},
    onBack: () -> Unit={}
) {
    val context = LocalContext.current

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            Toast.makeText(context, "Client ajouté avec succès", Toast.LENGTH_SHORT).show()
            onBack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = uiState.name,
            onValueChange = onNameChanged,
            label = { Text("Nom") },
            isError = !uiState.isNameValid,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("nameEDT") // TODO: ajouter un TAG pour ref dans la classe de test
        )
        if (!uiState.isNameValid) {
            Text("Nom invalide", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChanged,
            label = { Text("Email") },
            isError = !uiState.isEmailValid,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("emailEDT") // TODO: ajouter un TAG pour ref dans la classe de test
        )
        if (!uiState.isEmailValid) {
            Text("Email invalide", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("saveFAB") // TODO: ajouter un TAG pour ref dans la classe de test
        ) {
            Text("Enregistrer")
        }
    }
}

@Preview
@Composable
private fun UIPreview() {
    AddCustomerScreen()

}

