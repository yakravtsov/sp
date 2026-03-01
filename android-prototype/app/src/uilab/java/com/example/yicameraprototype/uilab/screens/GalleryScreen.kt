package com.example.yicameraprototype.uilab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import com.example.yicameraprototype.R
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class GalleryItem(
    val id: Int,
    val name: String,
    val downloadState: DownloadState = DownloadState.NotDownloaded
)

sealed class DownloadState {
    object NotDownloaded : DownloadState()
    data class Downloading(val progress: Int) : DownloadState()
    object Downloaded : DownloadState()
    object Error : DownloadState()
}

@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier
) {
    var currentPage by remember { mutableStateOf(1) }
    val totalPages = 10

    var items by remember {
        mutableStateOf(
            (1..8).map { index ->
                GalleryItem(
                    id = index,
                    name = "IMG_${String.format("%04d", index)}.jpg"
                )
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PaginationControls(
            currentPage = currentPage,
            totalPages = totalPages,
            onPrevious = { if (currentPage > 1) currentPage-- },
            onNext = { if (currentPage < totalPages) currentPage++ }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items) { item ->
                GalleryItemCard(
                    item = item,
                    onDownload = {
                        items = items.map {
                            if (it.id == item.id) {
                                when (it.downloadState) {
                                    is DownloadState.NotDownloaded -> it.copy(downloadState = DownloadState.Downloading(50))
                                    is DownloadState.Downloading -> it.copy(downloadState = DownloadState.Downloaded)
                                    is DownloadState.Downloaded -> it
                                    is DownloadState.Error -> it.copy(downloadState = DownloadState.Downloading(0))
                                }
                            } else {
                                it
                            }
                        }
                    }
                )
            }
        }

        PaginationControls(
            currentPage = currentPage,
            totalPages = totalPages,
            onPrevious = { if (currentPage > 1) currentPage-- },
            onNext = { if (currentPage < totalPages) currentPage++ }
        )
    }
}

@Composable
private fun GalleryItemCard(
    item: GalleryItem,
    onDownload: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.id.toString(),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = item.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            when (val state = item.downloadState) {
                is DownloadState.NotDownloaded -> {
                    IconButton(
                        onClick = onDownload,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.download_24px),
                            contentDescription = "Download"
                        )
                    }
                }
                is DownloadState.Downloading -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        LinearProgressIndicator(
                            progress = { state.progress / 100f },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "${state.progress}%",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                is DownloadState.Downloaded -> {
                    Text(
                        text = "Downloaded",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is DownloadState.Error -> {
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
private fun PaginationControls(
    currentPage: Int,
    totalPages: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onPrevious,
            enabled = currentPage > 1
        ) {
            Text("Previous")
        }

        Text(
            text = "Page $currentPage of $totalPages",
            style = MaterialTheme.typography.bodyMedium
        )

        TextButton(
            onClick = onNext,
            enabled = currentPage < totalPages
        ) {
            Text("Next")
        }
    }
}
