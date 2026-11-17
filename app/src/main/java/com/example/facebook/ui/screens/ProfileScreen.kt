package com.example.facebook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebook.R
import com.example.facebook.ui.theme.FacebookPlaceholder
import com.example.facebook.ui.theme.FacebookSurface
import com.example.facebook.ui.theme.FacebookTextSecondary
import com.example.facebook.ui.theme.FacebookTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.marcottt)
    val imagen = painterResource(R.drawable.familiattt)
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 1. Portada con Foto de Perfil circular superpuesta + Botones de acción
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FacebookSurface)
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Contenedor de portada y foto de perfil encima
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                ) {
                    // Portada
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(FacebookPlaceholder),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = imagen,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Foto de perfil en círculo encima de la portada
                    Box(
                        modifier = Modifier
                            .size(128.dp)
                            .align(Alignment.BottomCenter)
                            .clip(CircleShape)
                            .background(FacebookPlaceholder)
                            .border(4.dp, FacebookSurface, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop

                        )

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Marco Avila Sahur",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Integrante de fusion andina y pasante de Inti",
                    style = MaterialTheme.typography.bodyMedium,
                    color = FacebookTextSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botones de Agregar a historia y Editar perfil
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("+ Agregar a historia")
                    }
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Editar perfil")
                    }
                }
            }
        }

        // 2. Sección: Información de contacto
        item {
            ProfileDataSectionCard(
                title = "Información de contacto",
                items = listOf(
                    "Correo electrónico" to "qmcfarmaceutica@gmail.com",
                    "Teléfono" to "+6767 73522437",
                    "Dirección / Ciudad" to "Palca - Bolivia"
                )
            )
        }

        // 3. Sección: Pasatiempos
        item {
            ProfileDataSectionCard(
                title = "Pasatiempos",
                items = listOf(
                    "Deportes" to "Tocar la Guitarra",
                    "Entretenimiento" to "Viedeojuegos y Brainrot",
                    "Otros intereses" to "Viajes a Peru"
                )
            )
        }

        // 4. Sección: Datos personales
        item {
            ProfileDataSectionCard(
                title = "Datos personales",
                items = listOf(
                    "Fecha de nacimiento" to "67 de enero de 2067",
                    "Situación sentimental" to "Soltero/a",
                    "Formación académica" to "UPEA",
                    "Lugar de origen" to "PeruKistan"
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ProfileDataSectionCard(
    title: String,
    items: List<Pair<String, String>>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = FacebookSurface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            items.forEachIndexed { index, (label, value) ->
                Column(modifier = Modifier.padding(vertical = 6.dp)) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        color = FacebookTextSecondary
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
                if (index < items.lastIndex) {
                    HorizontalDivider(color = FacebookPlaceholder)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    FacebookTheme {
        ProfileScreen()
    }
}
