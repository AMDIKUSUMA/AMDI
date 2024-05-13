import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugas.R

// Model for item
data class Item(val id: Int, val name: String, val imageUrl: Int, val detail: String)

val itemList = List(10) { index ->
    Item(index, "Item ${index + 1}", R.drawable.anime, "Detail of item ${index + 1}")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAppBar(navController)}


}

@Composable
fun ListScreen(navController: NavController) {
    LazyColumn {
        item { Text(text = "Lazy Column") }
        items(itemList) { item ->
            ListItem(item = item) {
                navController.navigate("detail/${item.id}")
            }
        }
        item { Text(text = "Lazy Row") }
        itemsIndexed(itemList) { index, item ->
            ListItem(item = item) {
                navController.navigate("detail/${item.id}")
            }
        }
        item { Text(text = "Lazy Grid") }
        items(itemList.chunked(2)) { rowItems ->
            LazyRow {
                items(rowItems) { item ->
                    GridItem(item = item) {
                        navController.navigate("detail/${item.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(item: Item, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.name)
    }
}

@Composable
fun GridItem(item: Item, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.name)
    }
}

@Composable
fun DetailScreen(itemId: Int, navController: NavController) {
    val selectedItem = itemList.find { it.id == itemId }
    selectedItem?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = it.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it.detail,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    } ?: run {
        Text(text = "Item not found!")
    }
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter: Painter = painterResource(id = R.drawable.dua)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "amdi kusuma")
        Text(text = "amdy@gmail.com")
        Text(text = "univerity bumigora")
        Text(text = "Your Major")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    App()
}
