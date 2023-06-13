package com.example.appfirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
        setContent {
            MaterialTheme {
                Formulario()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun Formulario() {
        val dataContato = remember { mutableStateOf("") }
        val endereco = remember { mutableStateOf("") }
        val nome = remember { mutableStateOf("") }
        val observacao = remember { mutableStateOf("") }
        val origem = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "CADASTRANDO DADOS",
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
            value = data_contato.value,
            onValueChange = { data_contato.value = it },
            label = { Text("Data_contato") },
            modifier = Modifier.testTag("data_contatoTextField")
        )
         TextField(
            value = endereco.value,
            onValueChange = { endereco.value = it },
            label = { Text("Endereço") },
            modifier = Modifier.testTag("enderecoTextField")
        )
        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text("Nome") },
            modifier = Modifier.testTag("nomeTextField")
        )
        TextField(
            value = observacao.value,
            onValueChange = { observacao.value = it },
            label = { Text("Observação") },
            modifier = Modifier.testTag("observacaoTextField")
        )
        TextField(
            value = origem.value,
            onValueChange = { origem.value = it },
            label = { Text("Origem") },
            modifier = Modifier.testTag("origemTextField")
        )

            Button(
                onClick = {
                    val data = hashMapOf(
                         "data_contato" to dataContato.value,
                    "endereco" to endereco.value,
                    "nome" to nome.value,
                    "observacao" to observacao.value,
                    "origem" to origem.value    
                    )

                    firestore.collection("agendamentos")
                        .add(data)
                        .addOnSuccessListener {
                             // Limpar os campos após o envio
                        dataContato.value = ""
                        endereco.value = ""
                        nome.value = ""
                        observacao.value = ""
                        origem.value = ""   
                        }
                        .addOnFailureListener { e ->
                            // Handle failure
                        }
                },
                modifier = Modifier.padding(bottom = 150.dp)
            ) {
                Text("Cadastrar Dados")
            }
        }
    }
}
