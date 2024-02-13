package com.example.composetutorial.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composetutorial.R
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(navController: NavController) {
    // Declare a variable to hold the selected image URI
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // ActivityResultLauncher for picking images
    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the selected image URI
            selectedImageUri = uri
        }

    ComposeTutorialTheme {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                // Back Button
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var text by remember { mutableStateOf("Antti") }

                Text(
                    text = text,
                    style = MaterialTheme.typography.titleLarge
                )
                // Profile Image
                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profilepic),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                val keyboardController = LocalSoftwareKeyboardController.current
                val focusManager = LocalFocusManager.current

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Username") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    })
                )

                // Image picker Button
                Button(onClick = {
                    // Launch the image picker
                    imagePickerLauncher.launch("image/*")
                }) {
                    Text(text = "Change picture")
                }
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreen() {
    // Declare a variable to hold the selected image URI
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // ActivityResultLauncher for picking images
    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the selected image URI
            selectedImageUri = uri
        }

    ComposeTutorialTheme {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                // Back Button
                IconButton(onClick = { /* Handle back button click */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Image
                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profilepic),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Image picker Button
                Button(onClick = {
                    // Launch the image picker
                    imagePickerLauncher.launch("image/*")
                }) {
                    Text(text = "Change picture")
                }
            }
        }
    }
}