package com.generation.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import com.generation.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Iniciando o ViewModel
    private val model: MainViewModel by activityViewModels()
    private lateinit var binding :ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lista de botões da calculadora
        val btnNum0 = binding.btnNum0
        val btnNum1 = binding.btnNum1
        val btnNum2 = binding.btnNum2
        val btnNum3 = binding.btnNum3
        val btnNum4 = binding.btnNum4
        val btnNum5 = binding.btnNum5
        val btnNum6 = binding.btnNum6
        val btnNum7 = binding.btnNum7
        val btnNum8 = binding.btnNum8
        val btnNum9 = binding.btnNum9
        val btnPonto = binding.btnPonto
        val btnAC = binding.btnAC
        val btnLimpa = binding.btnLimpa
        val btnMais = binding.btnMais
        val btnMenos = binding.btnMenos
        val btnMultiplica = binding.btnMultiplica
        val btnDivide = binding.btnDivide
        val btnIgual = binding.btnIgual
        val btnSinal = binding.btnSinal

        val listButton = listOf<View>(
            btnNum0, btnNum1, btnNum2, btnNum3,
            btnNum4, btnNum5, btnNum6, btnNum7,
            btnNum8, btnNum9, btnPonto, btnAC,
            btnLimpa, btnMais, btnMenos, btnMultiplica,
            btnDivide, btnIgual, btnSinal
        )

        // Seta o nome dos botões de acordo com as constantes pela função
        for (x in listButton) {
            if(x is Button){
                x.text = getBtnConstant(x).toString()
            }
        }

        // Setando função de clique para todos os botões
        for (x in listButton) {
            x.click()
        }

        // Setando viewmodel para receber as Strings
        // de cálculo e números
        model.getNumbers().observe(this, Observer<CalcVisor> {
            tvNumbers.text = it.number
            tvCalc.text = it.calc
        })

    }

    // Pega o valor da constante do botão
    private fun getBtnConstant(any: Any): Any? {
        return when(any) {
            btnNum0 -> N0
            btnNum1 -> N1
            btnNum2 -> N2
            btnNum3 -> N3
            btnNum4 -> N4
            btnNum5 -> N5
            btnNum6 -> N6
            btnNum7 -> N7
            btnNum8 -> N8
            btnNum9 -> N9
            btnPonto -> POINT
            btnSinal -> SIGNAL
            btnLimpa -> BACK
            btnAC -> AC
            btnMais -> PLUS
            btnMenos -> MINUS
            btnMultiplica -> MULTIPLY
            btnDivide -> DIVIDE
            btnIgual -> EQUAL
            else -> null
        }
    }

    // Inserindo o Listener no Botão
    private fun View.click() {
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