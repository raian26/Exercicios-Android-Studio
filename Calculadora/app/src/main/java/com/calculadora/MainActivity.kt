package com.calculadora

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.calculadora.databinding.ActivityMainBinding

import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
private lateinit var binding:ActivityMainBinding

    // Iniciando o ViewModel

private var  mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }

    // Pega o valor da constante do botão
    private fun getBtnConstant(any: Any): Any? {
        return when(any) {
             binding.numero0-> N0
           binding.numero1 -> N1
            binding.numero2 -> N2
            binding.numero3 -> N3
            binding.numero4 -> N4
            binding.numero5 -> N5
            binding.numero6 -> N6
            binding.numero7 -> N7
            binding.numero8 -> N8
            binding.numero9-> N9
            binding.ponto-> ponto
            binding.backspace -> back
            binding.limpa -> c
            binding.adicao -> adicao
            binding.subtracao -> subtracao
            binding.multiplicacao-> multiplicacao
            binding.divisao -> divisao
            binding.igual -> igual
            else -> null
        }
    }

    // Inserindo o Listener no Botão
    click {
        this.setOnClickListener {
            send(it)
        }
    }

    // Função que envia o botão clicado
    private fun send(v: View) {
        // Seta o valor da variável c de acordo com a tecla clicada
        val c = getBtnConstant(v)
        if (c != null) {
            model.setClick(c)
        }
    }
}