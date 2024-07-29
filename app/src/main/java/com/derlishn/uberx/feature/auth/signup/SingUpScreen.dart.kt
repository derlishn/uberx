package com.derlishn.uberx.feature.auth.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.derlishn.uberx.ui.theme.*

@Composable
fun SignUpScreen(navController: NavController) {

    val viewModel: SignUpViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirm by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value) {

        when (uiState.value) {
            is SignUpState.Success -> {
                navController.navigate("home")
            }

            is SignUpState.Error -> {
                Toast.makeText(context, "Sign Up failed", Toast.LENGTH_SHORT).show()
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
                text = "Create your account",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Full Name") },
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
                    else
                        Icons.Filled.VisibilityOff

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
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = confirm,
                onValueChange = { confirm = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Confirm Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                isError = password.isNotEmpty() && confirm.isNotEmpty() && password != confirm,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FocusedBorderColor,
                    unfocusedBorderColor = UnfocusedBorderColor,
                    focusedLabelColor = LabelColor,
                    unfocusedLabelColor = LabelColor,
                    cursorColor = CursorColor,
                    disabledLabelColor = UnfocusedBorderColor,
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red
                ),
                textStyle = TextStyle(TextColor)
            )
            Spacer(modifier = Modifier.size(16.dp))
            if (uiState.value == SignUpState.Loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        viewModel.signUp(name, email, password)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonEnabledColor,
                        disabledContainerColor = ButtonDisabledColor
                    ),
                    enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirm.isNotEmpty() && password == confirm
                ) {
                    Text(text = "Sign Up", color = ButtonTextColor)
                }
                Spacer(modifier = Modifier.size(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text(text = "Sign In", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(navController = rememberNavController())
}
