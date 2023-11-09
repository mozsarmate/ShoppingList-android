package hu.ait.shoppinglist.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import hu.ait.shoppinglist.data.Item


@Composable
fun ItemCard(
    curItem: Item,
    onDelete: () -> Unit = {},
    icon: ImageVector
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }
        
        Column(
            modifier = Modifier
                .padding(10.dp)
                .animateContentSize()
        ) {
            
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row() {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon",
                    )
                    Column {
                        Text(text = curItem.name, modifier = Modifier.fillMaxWidth(0.5f))
                        Text(
                            text = curItem.estPrice.toString() + " Ft",
                        )
                    }
                }
                Row() {
                    IconButton(onClick = {
                        onDelete()
                    }) {
                        Icon(Icons.Filled.Delete, null)
                    }
                    
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded)
                                Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = if (expanded) {
                                "Less"
                            } else {
                                "More"
                            }
                        )
                    }
                }
            }
            if (expanded) {
                Text(text = curItem.id.toString())
            }
        }
    }
}
