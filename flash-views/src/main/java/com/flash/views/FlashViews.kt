package com.flash.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.flash.domain.flow.model.Data
import com.flash.domain.flow.model.Type

@Composable
fun SetView(
    data: ArrayList<Data>,
    applicationContext: Context
) {
    data.forEach { value ->
        CheckUiType(value, applicationContext)
    }
}

//TODO Build Generic Error Screen or give option to override from json
@Composable
fun ShowError() {
    Text(text = "There is a Problem")
}

//TODO Build Generic Loading Screen or give option to override from json
@Composable
fun ShowLoading() {
    Text(
        text = "Loading Please wait",
        textAlign = TextAlign.Center
    )
}

@Composable
fun CheckUiType(
    value: Data,
    applicationContext: Context
) {
    when (value.type) {
        Type.SCAFFOLD -> ShowScaffold(value = value, applicationContext)
        Type.TEXT -> ShowText(value = value, applicationContext)
        Type.APP_BAR -> ShowAppBar(value = value, applicationContext)
        Type.IMAGE -> ImageView(value = value, applicationContext)
        Type.VERTICAL_LIST -> VerticalList(value = value, applicationContext)
        Type.HORIZONTAL_LIST -> HorizontalList(value = value, applicationContext)
        Type.ROW -> RowView(value = value, applicationContext)
        Type.COLUMN -> ColumnView(value = value, applicationContext)
        Type.EDIT_TEXT -> EditText(value = value, applicationContext)
        Type.UNKNOWN -> Spacer(modifier = Modifier.requiredSize(0.dp))
    }
}

@Composable
fun ShowText(
    value: Data, applicationContext: Context
) {
    Text(
        text = "${value.value}",
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAppBar(
    value: Data, applicationContext: Context
) {
    TopAppBar(
        title = {
            SetView(
                data = value.children,
                applicationContext = applicationContext
            )
        },
        modifier = Modifier.background(Color.White)
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowScaffold(
    value: Data, applicationContext: Context
) {
    Scaffold(
        topBar = { SetView(value.topBar, applicationContext) },

    ) {
        Spacer(Modifier.height(58.dp))
        SetView(
            data = value.children,
            applicationContext = applicationContext
        )
    }
}

@Composable
fun VerticalList(
    value: Data, applicationContext: Context
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SetView(
                data = value.children,
                applicationContext = applicationContext
            )
        }
    }
}

@Composable
fun HorizontalList(
    value: Data, applicationContext: Context
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SetView(
                data = value.children,
                applicationContext = applicationContext
            )
        }
    }
}

@Composable
fun RowView(
    value: Data, applicationContext: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SetView(
            data = value.children,
            applicationContext = applicationContext
        )
    }
}

@Composable
fun ColumnView(
    value: Data, applicationContext: Context
) {
    Column {
        SetView(
            data = value.children,
            applicationContext = applicationContext
        )
    }
}

@Composable
fun ImageView(
    value: Data, applicationContext: Context
) {
    Image(
        painter = rememberAsyncImagePainter(value.value),
        contentDescription = null,
        modifier = Modifier
            .size(56.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    value: Data,
    applicationContext: Context
) {
    var textFieldState by remember { mutableStateOf(value.value) }

    TextField(
        value = textFieldState,
        onValueChange = { textFieldState = it}
    )
}
