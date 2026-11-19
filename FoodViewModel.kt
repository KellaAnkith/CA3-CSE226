package com.example.ca3



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel

class FoodViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var quantity by mutableIntStateOf(
        savedStateHandle["quantity"] ?: 1
    )
        private set

    fun increaseQuantity() {
        quantity++
        savedStateHandle["quantity"] = quantity
    }

    fun decreaseQuantity() {
        if (quantity > 1) {
            quantity--
            savedStateHandle["quantity"] = quantity
        }
    }
}

@Composable
fun FoodOrderScreen(
    viewModel: FoodViewModel = viewModel()
) {

    val quantity = viewModel.quantity

    val itemName = "Pizza"
    val price = 150
    val totalPrice = price * quantity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "🍕 $itemName",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Price: ₹$price",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                QuantitySelector(
                    quantity = quantity,
                    onIncrease = {
                        viewModel.increaseQuantity()
                    },
                    onDecrease = {
                        viewModel.decreaseQuantity()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Quantity: $quantity",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Total Price: ₹$totalPrice",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Place Order")
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = onDecrease,
            enabled = quantity > 1
        ) {
            Text(
                text = "−",
                fontSize = 22.sp
            )
        }

        Text(
            text = "$quantity",
            modifier = Modifier.padding(horizontal = 25.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = onIncrease
        ) {
            Text(
                text = "+",
                fontSize = 22.sp
            )
        }
    }
}
