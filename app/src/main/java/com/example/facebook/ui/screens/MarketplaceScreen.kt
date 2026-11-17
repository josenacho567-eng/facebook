package com.example.facebook.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun MarketplaceScreen(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(FacebookSurface),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Encabezado y Buscador
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = "Marketplace",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar en Marketplace...") },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Vender")
                    }
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Categorías")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Selección de hoy",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Publicaciones de Marketplace
        items(6) { index ->
            MarketplaceItemCard(index = index + 1)
        }
    }
}

@Composable
private fun MarketplaceItemCard(index: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = FacebookSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(FacebookPlaceholder),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Foto $index",
                    color = FacebookTextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "$${index * 100}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Artículo en venta $index",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Ciudad, País",
                    style = MaterialTheme.typography.bodySmall,
                    color = FacebookTextSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketplaceScreenPreview() {
    FacebookTheme {
        MarketplaceScreen()
    }
}
