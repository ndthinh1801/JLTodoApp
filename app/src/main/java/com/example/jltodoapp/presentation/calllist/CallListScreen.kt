package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jltodoapp.R
import com.example.jltodoapp.presentation.calllist.CallListViewModel
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.CallListItem

@Composable
fun CallListScreen(
    navController: NavController,
    viewModel: CallListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.label_call_list)) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        val state by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                state.calls?.let { calls ->
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(calls) { callItem ->
                            CallListItem(
                                callItem = callItem
                            )
                        }
                    }
                }
                state.userMessage?.let { message ->
                    val snackbarText = stringResource(message)
                    LaunchedEffect(scaffoldState, viewModel, message, snackbarText) {
                        scaffoldState.snackbarHostState.showSnackbar(snackbarText)
                        viewModel.snackbarMessageShown()
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }

}