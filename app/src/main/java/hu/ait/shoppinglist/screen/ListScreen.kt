package hu.ait.shoppinglist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.shoppinglist.data.Item
import hu.ait.shoppinglist.data.ItemCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier, viewModel: ListViewModel = hiltViewModel()) {
    
    val coroutineScope = rememberCoroutineScope()
    
    val shoppingList by viewModel.getAllItems().collectAsState(initial = emptyList())
    
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    
    var shoppingItemName: Item? by rememberSaveable {
        mutableStateOf(null)
    }
    
    Box() {
        Column() {
            Column() {
                
                TopAppBar(
                    title = {
                        Text("Shopping List")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    actions = {
                    
                    })
            }
            
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(shoppingList) { item ->
                    Text(text = item.name)
                }
            }
            
            
        }
        // floating action button
        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .padding(24.dp)
                .size(96.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
    if (showDialog) {
        AddEditItemDialog(
            onDismiss = {
                showDialog = false
                shoppingItemName = null
            },
            modifier = Modifier,
            itemViewModel = viewModel
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditItemDialog(onDismiss: () -> Unit, modifier: Modifier, itemViewModel: ListViewModel) {
    Dialog(onDismissRequest = onDismiss) {
        
        var itemTitle by rememberSaveable {
            mutableStateOf("Milk")
        }
        var itemDesc by rememberSaveable {
            mutableStateOf("2%")
        }
        var itemPrice by rememberSaveable {
            mutableStateOf(300)
        }
        var itemCat by rememberSaveable {
            mutableStateOf(ItemCategory.FOOD)
        }
        
        Column(
            Modifier.background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {
            Text("Add new item")
            OutlinedTextField(modifier = modifier
                .padding(5.dp)
                .fillMaxWidth(),
                value = itemTitle,
                onValueChange = {
                    itemTitle = it
                },
                label = {
                    Text("Title")
                }
            )
            OutlinedTextField(modifier = modifier
                .padding(5.dp)
                .fillMaxWidth(),
                value = itemDesc,
                onValueChange = {
                    itemDesc = it
                },
                label = {
                    Text("Description")
                }
            )
            OutlinedTextField(modifier = modifier
                .padding(5.dp)
                .fillMaxWidth(),
                value = itemPrice.toString(),
                onValueChange = {
                    val parsedAmount = it.toIntOrNull()
                    if (parsedAmount != null) {
                        itemPrice = parsedAmount
                    }
                },
                label = {
                    Text("Price")
                }
            )
            Button(onClick = {
                if (itemTitle != "" && itemPrice > 0) {
                    itemViewModel.insert(
                        Item(
                            name = itemTitle,
                            category = itemCat,
                            estPrice = itemPrice,
                            description = itemDesc,
                            isBought = false
                        )
                    )
                }
                onDismiss()
            }) {
                Text("Add")
            }
        }
        
        
    }
}
