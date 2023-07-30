import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.*

@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf(TextFieldValue()) }
    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Header
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace with your actual logo.png resource
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                //.padding(bottom=20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .background(GreyGreen),
                 contentAlignment = Alignment.Center// Replace with your desired background color
        ) {
            Text(
                text = "Let's get to know you",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        // Content of the Box, if any
            // For example, you can add Text or other composables here
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Personal information",
            style = MaterialTheme.typography.titleMedium,
            color = PurpleGrey40,
            modifier=Modifier.padding(start=10.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "First name",
            color = PurpleGrey40,
            modifier=Modifier.padding(start=10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TransparentTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First Name"
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Last name",
            color = PurpleGrey40,
            modifier=Modifier.padding(start=10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TransparentTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = "Last Name"
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Email",
            color = PurpleGrey40,
            modifier=Modifier.padding(start=10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TransparentTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address"
        )
        // TODO: Add user input fields and register button here
        Button(
            onClick = { /* Handle register button click here */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
        ) {
            Text(text = "Register")
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Surface {
        Onboarding()
    }
}
@Composable
fun TransparentTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String
) {
    val shape: Shape = RoundedCornerShape(4.dp)

    Surface(
        color = Color.Transparent,
        contentColor = Color.Black,
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start=12.dp, end=12.dp)
            .border(1.dp, SolidColor(NewAlmostTransparent), shape) // Add a small black border
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth().padding(start=10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
            singleLine = true,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(25.dp))
    }
}