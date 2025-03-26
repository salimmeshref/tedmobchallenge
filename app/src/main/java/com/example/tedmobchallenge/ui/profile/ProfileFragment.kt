package com.example.tedmobchallenge.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.tedmobchallenge.data.PreferenceManager

class ProfileFragment : Fragment() {

    private lateinit var preferencesManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferenceManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ProfileScreen(
                            email = preferencesManager.getUserEmail(),
                            firstName = preferencesManager.getUserFirstName(),
                            lastName = preferencesManager.getUserLastName()
                        )
                    }
                }
            }
        }
    }
}

/**
 * Composable function for the Profile screen UI.
 */
@Composable
fun ProfileScreen(
    email: String,
    firstName: String,
    lastName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileCard(
            email = email,
            firstName = firstName,
            lastName = lastName
        )
    }
}

@Composable
fun ProfileCard(
    email: String,
    firstName: String,
    lastName: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProfileField(label = "Email", value = email)

            Spacer(modifier = Modifier.height(16.dp))

            ProfileField(label = "First Name", value = firstName)

            Spacer(modifier = Modifier.height(16.dp))

            ProfileField(label = "Last Name", value = lastName)
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Preview for the Profile screen.
 */
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(
            email = "john.doe@example.com",
            firstName = "John",
            lastName = "Doe"
        )
    }
}