package com.example.facebook.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.items
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

/**
 * Modelo de datos para representar individualmente cada producto en Marketplace.
 * Puedes personalizar el título, precio, ubicación e imagen (R.drawable) para cada uno.
 */
data class MarketplaceProduct(
    val id: Int,
    val title: String,
    val price: String,
    val location: String,
    @DrawableRes val imageRes: Int? = null,
    val placeholderText: String = "Foto $id"
)

/**
 * Lista de productos individuales.
 * Modifica o agrega elementos aquí para cambiar el texto o la imagen de cada producto por separado.
 */
val sampleMarketplaceProducts = listOf(
    MarketplaceProduct(
        id = 1,
        title = "Guitarra Gibson E-355",
        price = "280Bs",
        location = "La Paz, Bolivia",
        imageRes = R.drawable.product1
    ),
    MarketplaceProduct(
        id = 2,
        title = "Disfraz Tung Tung 67",
        price = "670Bs",
        location = "La Paz, Bolivia",
        imageRes = R.drawable.product2
    ),
    MarketplaceProduct(
        id = 3,
        title = "Juegos Ps4",
        price = "100Bs",
        location = "La Paz, Bolivia",
        imageRes = R.drawable.product3
    ),
    MarketplaceProduct(
        id = 4,
        title = "Chaquta de cuero",
        price = "280Bs",
        location = "La Paz, Bolivia",
        imageRes = R.drawable.product4
    ),
    MarketplaceProduct(
        id = 5,
        title = "Alvin",
        price = "0.50Bs",
        location = "Tropico, Bolivia",
        imageRes = R.drawable.product5
    ),
    MarketplaceProduct(
        id = 6,
        title = "Espada Sagrada Legion Ultimate Definitiva",
        price = "300Bs",
        location = "Santa Cruz, Bolivia",
        imageRes = R.drawable.product6
    )
)

@Composable
fun MarketplaceScreen(
    modifier: Modifier = Modifier,
    products: List<MarketplaceProduct> = sampleMarketplaceProducts
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts = remember(searchQuery, products) {
        if (searchQuery.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.title.contains(searchQuery, ignoreCase = true) ||
                product.location.contains(searchQuery, ignoreCase = true) ||
                product.price.contains(searchQuery, ignoreCase = true)
            }
        }
    }

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

        // Publicaciones individuales de Marketplace
        items(
            items = filteredProducts,
            key = { it.id }
        ) { product ->
            MarketplaceItemCard(product = product)
        }
    }
}

@Composable
private fun MarketplaceItemCard(
    product: MarketplaceProduct,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                if (product.imageRes != null) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = product.placeholderText,
                        color = FacebookTextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = product.location,
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
