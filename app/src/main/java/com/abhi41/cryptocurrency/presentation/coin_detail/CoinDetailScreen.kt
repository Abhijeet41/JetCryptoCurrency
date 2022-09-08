package com.abhi41.cryptocurrency.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhi41.cryptocurrency.common.CoinDetailNavArgs
import com.abhi41.cryptocurrency.presentation.coin_detail.components.CoinTag
import com.abhi41.cryptocurrency.presentation.coin_detail.components.TeamListItem
import com.google.accompanist.flowlayout.FlowRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = CoinDetailNavArgs::class
)
@Composable
fun CoinDetailScreen(
    navigator: DestinationsNavigator,
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        state.coin.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.weight(8f),
                            text = "${coin?.rank}. ${coin?.name} ${coin?.symbol}",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = if (coin?.isActive == true) "active" else "inactive",
                            color = if (coin?.isActive == true) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,

                            )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp
                    ) {
                        coin?.tags?.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }

                if (coin != null) {
                    items(coin.team) { teamMember ->
                        if (teamMember != null) {
                            TeamListItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                teamMember = teamMember,
                            )
                            Divider()
                        }
                    }
                }

            }

        }


        if (state.error.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp),
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}