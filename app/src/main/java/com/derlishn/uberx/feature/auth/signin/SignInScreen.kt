package com.derlishn.uberx.feature.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.derlishn.uberx.R
import com.derlishn.uberx.ui.theme.ButtonDisabledColor
import com.derlishn.uberx.ui.theme.ButtonEnabledColor
import com.derlishn.uberx.ui.theme.ButtonTextColor
import com.derlishn.uberx.ui.theme.CursorColor
import com.derlishn.uberx.ui.theme.DarkBackgroundColor
import com.derlishn.uberx.ui.theme.FocusedBorderColor
import com.derlishn.uberx.ui.theme.LabelColor
import com.derlishn.uberx.ui.theme.TextColor
import com.derlishn.uberx.ui.theme.UnfocusedBorderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController) {

    val viewModel: SignInViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value) {

        when (uiState.value) {
            is SignInState.Success -> {
                navController.navigate("home")
            }

            is SignInState.Error -> {
                Toast.makeText(context, "Sign In failed", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(DarkBackgroundColor)
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .background(DarkBackgroundColor)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Welcome",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Login to your account",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.size(32.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Email") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FocusedBorderColor,
                    unfocusedBorderColor = UnfocusedBorderColor,
                    focusedLabelColor = LabelColor,
                    unfocusedLabelColor = LabelColor,
                    cursorColor = CursorColor,
                    disabledLabelColor = UnfocusedBorderColor
                ),
                textStyle = TextStyle(TextColor)
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FocusedBorderColor,
                    unfocusedBorderColor = UnfocusedBorderColor,
                    focusedLabelColor = LabelColor,
                    unfocusedLabelColor = LabelColor,
                    cursorColor = CursorColor,
                    disabledLabelColor = UnfocusedBorderColor
                ),
                textStyle = TextStyle(TextColor)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { navController.navigate("forgot_password") }) {
                    Text(text = "Forgot password?", color = Color.Red, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.size(32.dp))

            if (uiState.value == SignInState.Loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = { viewModel.signIn(email, password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonEnabledColor,
                        disabledContainerColor = ButtonDisabledColor
                    ),
                    enabled = email.isNotEmpty() && password.isNotEmpty() && (uiState.value == SignInState.Nothing || uiState.value == SignInState.Error)
                ) {
                    Text(text = "Login", color = ButtonTextColor)
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don't have an account?",
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    TextButton(onClick = { navController.navigate("signup") }) {
                        Text(text = "Create Now", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* TODO: Implement Google login */ }) {
                        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = null)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    IconButton(onClick = { /* TODO: Implement Facebook login */ }) {
                        Image(painter = painterResource(id = R.drawable.ic_facebook), contentDescription = null)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    IconButton(onClick = { /* TODO: Implement Instagram login */ }) {
                        Image(painter = painterResource(id = R.drawable.ic_instagram), contentDescription = null)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(navController = rememberNavController())
}
