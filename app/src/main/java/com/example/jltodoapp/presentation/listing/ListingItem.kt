package com.plcoding.cryptocurrencyappyt.presentation.coin_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jltodoapp.R
import com.example.jltodoapp.data.model.ListingItem

@Composable
fun ListingItem(
    listingItem: ListingItem
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.name_format, listingItem.name),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(R.string.price_format, listingItem.price),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(R.string.quantity_format, listingItem.quantity),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}