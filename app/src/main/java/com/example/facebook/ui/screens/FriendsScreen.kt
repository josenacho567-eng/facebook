package com.example.facebook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebook.ui.theme.FacebookPlaceholder
import com.example.facebook.ui.theme.FacebookSurface
import com.example.facebook.ui.theme.FacebookTextSecondary
import com.example.facebook.ui.theme.FacebookTheme

@Composable
fun FriendsScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(FacebookSurface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título principal
        item {
            Text(
                text = "Amigos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // 1. Solicitudes de amistad
        item {
            SectionTitle(title = "Solicitudes de amistad", subtitle = "2 nuevas")
        }
        items(2) { index ->
            FriendActionRow(
                name = "Solicitud ${index + 1}",
                subtitle = "${index + 2} amigos en común",
                primaryButtonText = "Confirmar",
                secondaryButtonText = "Eliminar"
            )
        }

        item {
            HorizontalDivider(color = FacebookPlaceholder)
        }

        // 2. Recomendaciones de amistad
        item {
            SectionTitle(title = "Personas que quizá conozcas", subtitle = "Recomendaciones")
        }
        items(2) { index ->
            FriendActionRow(
                name = "Sugerencia ${index + 1}",
                subtitle = "Sugerencia de amistad",
                primaryButtonText = "Agregar",
                secondaryButtonText = "Eliminar"
            )
        }

        item {
            HorizontalDivider(color = FacebookPlaceholder)
        }

        // 3. Visualización de amigos
        item {
            SectionTitle(title = "Tus amigos", subtitle = "Lista de amigos agregados")
        }
        items(3) { index ->
            FriendListItem(
                name = "Amigo ${index + 1}",
                subtitle = "Amigo desde 2024"
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String, subtitle: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = FacebookTextSecondary
        )
    }
}

@Composable
private fun FriendActionRow(
    name: String,
    subtitle: String,
    primaryButtonText: String,
    secondaryButtonText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(FacebookPlaceholder)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = FacebookTextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(primaryButtonText)
                }
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(secondaryButtonText)
                }
            }
        }
    }
}

@Composable
private fun FriendListItem(
    name: String,
    subtitle: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(FacebookPlaceholder)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = FacebookTextSecondary
            )
        }
        OutlinedButton(onClick = { }) {
            Text("Ver perfil")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendsScreenPreview() {
    FacebookTheme {
        FriendsScreen()
    }
}
