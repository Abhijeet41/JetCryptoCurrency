package com.abhi41.cryptocurrency.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi41.cryptocurrency.data.remote.dto.TeamMember

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp, top = 6.dp),
            text = teamMember.name ?: "null",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position ?: "null",
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Preview
@Composable
fun Preview() {
    TeamListItem(
        teamMember = TeamMember(
        id = "1",
        name = "name",
        position = "position")
    )
}
