package com.example.facebook.ui.screens

// Elementos de Compose para mostrar imágenes, fondos y organizar la pantalla.
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape

// Botones, separadores y textos de Material.
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text

// Herramientas para crear componentes y vistas previas.
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// R permite acceder a las fotos guardadas en res/drawable-nodpi.
import com.example.facebook.R

// Colores y tema que ya utiliza el proyecto.
import com.example.facebook.ui.theme.FacebookPlaceholder
import com.example.facebook.ui.theme.FacebookSurface
import com.example.facebook.ui.theme.FacebookTextSecondary
import com.example.facebook.ui.theme.FacebookTheme

// Representa los datos que necesita cada persona de la pantalla.
private data class Persona(
    val nombre: String,  // Nombre que aparecerá junto a la foto.
    val foto: Int,       // Referencia a una imagen de drawable-nodpi.
    val detalle: String  // Texto pequeño debajo del nombre.
)

// Estas son las dos solicitudes de amistad.
// Cambia los nombres, fotos y detalles por los que tú quieras mostrar.
private val solicitudes = listOf(
    Persona("Ana Pérez", R.drawable.foto_solicitud_1, "2 amigos en común"),
    Persona("Luis Rojas", R.drawable.foto_solicitud_2, "3 amigos en común")
)

// Estas son las dos personas sugeridas.
private val sugerencias = listOf(
    Persona("María López", R.drawable.foto_sugerencia_1, "Sugerencia de amistad"),
    Persona("Carlos Díaz", R.drawable.foto_sugerencia_2, "Sugerencia de amistad")
)

// Estos son los tres amigos que aparecen al final de la pantalla.
private val amigos = listOf(
    Persona("Sofía Vargas", R.drawable.foto_amigo_1, "Amigo desde 2024"),
    Persona("Diego Flores", R.drawable.foto_amigo_2, "Amigo desde 2024"),
    Persona("Valeria Cruz", R.drawable.foto_amigo_3, "Amigo desde 2024")
)

@Composable
fun FriendsScreen(modifier: Modifier = Modifier) {
    // LazyColumn muestra una lista vertical y crea las filas según se necesitan.
    LazyColumn(
        modifier = modifier
            .fillMaxSize()                 // La lista ocupa toda la pantalla disponible.
            .background(FacebookSurface) // Aplica el color de fondo del proyecto.
            .padding(16.dp),              // Deja espacio alrededor del contenido.
        verticalArrangement = Arrangement.spacedBy(16.dp) // Separa las filas.
    ) {
        // Encabezado principal.
        item {
            Text(
                text = "Amigos", // Título visible en la pantalla.
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // Título de la sección de solicitudes.
        item {
            SectionTitle(
                title = "Solicitudes de amistad",
                subtitle = "${solicitudes.size} nuevas"
            )
        }

        // Crea una fila por cada persona de la lista «solicitudes».
        items(solicitudes.size) { index ->
            val persona = solicitudes[index] // Obtiene la solicitud actual.

            FriendActionRow(
                persona = persona,                // Envía nombre, foto y detalle.
                primaryButtonText = "Confirmar", // Texto del primer botón.
                secondaryButtonText = "Eliminar" // Texto del segundo botón.
            )
        }

        // Línea que separa solicitudes y sugerencias.
        item {
            HorizontalDivider(color = FacebookPlaceholder)
        }

        // Título de la sección de sugerencias.
        item {
            SectionTitle(
                title = "Personas que quizá conozcas",
                subtitle = "Recomendaciones"
            )
        }

        // Crea una fila por cada persona sugerida.
        items(sugerencias.size) { index ->
            val persona = sugerencias[index] // Obtiene la sugerencia actual.

            FriendActionRow(
                persona = persona,
                primaryButtonText = "Agregar",
                secondaryButtonText = "Eliminar"
            )
        }

        // Línea que separa sugerencias y amigos.
        item {
            HorizontalDivider(color = FacebookPlaceholder)
        }

        // Título de la lista de amigos.
        item {
            SectionTitle(
                title = "Tus amigos",
                subtitle = "Lista de amigos agregados"
            )
        }

        // Crea una fila por cada amigo.
        items(amigos.size) { index ->
            val persona = amigos[index] // Obtiene el amigo actual.

            FriendListItem(persona = persona)
        }
    }
}

// Muestra el título y subtítulo de cada sección.
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

// Muestra una solicitud o una sugerencia con dos botones.
@Composable
private fun FriendActionRow(
    persona: Persona,
    primaryButtonText: String,
    secondaryButtonText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),       // La fila ocupa todo el ancho.
        verticalAlignment = Alignment.CenterVertically // Centra su contenido.
    ) {
        Image(
            painter = painterResource(id = persona.foto), // Carga la foto indicada.
            contentDescription = "Foto de ${persona.nombre}",
            modifier = Modifier
                .size(64.dp)       // Tamaño de la foto.
                .clip(CircleShape), // Recorta la foto en forma circular.
            contentScale = ContentScale.Crop // Rellena el círculo sin deformarla.
        )

        Spacer(modifier = Modifier.width(12.dp)) // Espacio después de la foto.

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = persona.nombre, // Muestra el nombre.
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = persona.detalle, // Muestra amigos en común o sugerencia.
                style = MaterialTheme.typography.bodySmall,
                color = FacebookTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espacio antes de los botones.

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { }, // El proyecto todavía no define esta acción.
                    modifier = Modifier.weight(1f)
                ) {
                    Text(primaryButtonText)
                }

                OutlinedButton(
                    onClick = { }, // El proyecto todavía no define esta acción.
                    modifier = Modifier.weight(1f)
                ) {
                    Text(secondaryButtonText)
                }
            }
        }
    }
}

// Muestra un amigo de la lista final.
@Composable
private fun FriendListItem(persona: Persona) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = persona.foto), // Carga la foto del amigo.
            contentDescription = "Foto de ${persona.nombre}",
            modifier = Modifier
                .size(52.dp)       // Esta foto es un poco más pequeña.
                .clip(CircleShape), // La muestra como círculo.
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = persona.nombre, // Nombre del amigo.
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = persona.detalle, // Por ejemplo: «Amigo desde 2024».
                style = MaterialTheme.typography.bodySmall,
                color = FacebookTextSecondary
            )
        }

        OutlinedButton(onClick = { }) { // Aún no abre un perfil.
            Text("Ver perfil")
        }
    }
}

// Permite ver esta pantalla en la vista previa de Android Studio.
@Preview(showBackground = true)
@Composable
private fun FriendsScreenPreview() {
    FacebookTheme {
        FriendsScreen()
    }
}