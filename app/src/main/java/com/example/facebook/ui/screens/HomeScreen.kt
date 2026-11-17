package com.example.facebook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun HomeScreen(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 1. Buscador y barra de estado superior
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FacebookSurface)
                    .padding(12.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar en Facebook...") },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Caja rápida para crear publicación
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(FacebookPlaceholder)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .border(1.dp, FacebookPlaceholder, RoundedCornerShape(20.dp))
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "¿Qué estás pensando?",
                            color = FacebookTextSecondary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        // 2. Sección de Historias
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FacebookSurface)
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = "Historias",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        StoryCardPlaceholder(label = "Crear historia", isCreate = true)
                    }
                    items(5) { index ->
                        StoryCardPlaceholder(label = "Historia ${index + 1}")
                    }
                }
            }
        }

        // 3. Lista de Publicaciones
        items(3) { index ->
            PostItemPlaceholder(postNumber = index + 1)
        }
    }
}

@Composable
private fun StoryCardPlaceholder(
    label: String,
    isCreate: Boolean = false
) {
    Card(
        modifier = Modifier
            .width(105.dp)
            .height(175.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = FacebookPlaceholder)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Avatar o ícono superior
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        if (isCreate) MaterialTheme.colorScheme.primary else FacebookSurface
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCreate) {
                    Text(
                        text = "+",
                        color = FacebookSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun PostItemPlaceholder(postNumber: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = FacebookSurface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Encabezado de la publicación
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(FacebookPlaceholder)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Usuario $postNumber",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Hace ${postNumber} h",
                        style = MaterialTheme.typography.bodySmall,
                        color = FacebookTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Texto de ejemplo para la publicación #$postNumber...",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Contenedor de imagen de la publicación
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(FacebookPlaceholder),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Contenido / Imagen de publicación $postNumber",
                    color = FacebookTextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = FacebookPlaceholder)

            // Botones de interacción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(onClick = { }) { Text("Me gusta") }
                TextButton(onClick = { }) { Text("Comentar") }
                TextButton(onClick = { }) { Text("Compartir") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FacebookTheme {
        HomeScreen()
    }
}
