package com.calculadora

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calculadora.model.CalcVisor
import net.objecthunter.exp4j.ExpressionBuilder

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val visor = CalcVisor()
    private var equalPress = false
    private var hasError = false

    private val calcVisor: MutableLiveData<CalcVisor> by lazy {
        MutableLiveData<CalcVisor>()
    }

    // Envia a variável calcVisor
    fun getNumbers(): LiveData<CalcVisor> {
        return calcVisor
    }

    // Recebe o click da tela
    fun setClick(c: Any?) {
        if (hasError) {
            if (c == com.calculadora.c) processClick(com.calculadora.c)
        } else processClick(c.toString())
    }

    // Processa informação do click
    private fun processClick(c: String) {
        when (c) {
            com.calculadora.c -> resetCalc()
            back -> {
                if (visor.number.length > 1) {
                    visor.number = visor.number.substring(0, visor.number.length - 1)
                } else {
                    visor.number = N0
                }
            }
            ponto -> {
                if (!visor.number.contains(ponto)) {
                    if (visor.number == "") visor.number = "$N0." else visor.number += "$c"
                }
            }
            sinal -> {
                visor.number =
                    if (visor.number.isNotEmpty() && visor.number.first() == subtracao.first()) {
                        visor.number.substring(1, visor.number.length)
                    } else {
                        "$subtracao${visor.number}"
                    }
            }
            adicao -> setCalc(adicao)
            subtracao -> setCalc(subtracao)
            multiplicacao -> setCalc(multiplicacao)
            divisao -> setCalc(divisao)
            igual -> {
                visor.calc += visor.number
                makeCalc(visor.calc)
            }
            else -> {
                try {
                    c.toInt()
                    when(visor.number){
                        N0 -> visor.number = ""
                        "$subtracao$N0" -> visor.number = "$subtracao"
                    }
                    visor.number += "$c"
                } catch (e: Exception) {
                    hasError = true
                    visor.number = error
                }
            }
        }
        calcVisor.value = visor
    }

    // Função AC da calculadora
    private fun resetCalc() {
        hasError = false
        equalPress = false
        visor.calc = ""
        visor.number = N0
    }

    // Adiciona o cálculo a ser feito no visor.calc
    private fun setCalc(str: String) {
        if (equalPress) {
            visor.calc = ""
            equalPress = false
        }
        visor.calc += "${visor.number}${str}"
        visor.number = N0
    }

    // Mostra o resultado apertando " = "
    private fun makeCalc(str: String) {
        var result: String
        try {
            val replaceStr = str.replace(adicao, "+")
                .replace(subtracao, "-")
                .replace(divisao, "/")
                .replace(multiplicacao, "*")
            val calc = ExpressionBuilder(replaceStr).build()
            result = calc.evaluate().toString()
            visor.calc += igual
        } catch (e: Exception) {
            hasError = true
            result = error
        }

        visor.number =
            if (result.substring(
                    result.length - 2,
                    result.length
                ) == "$ponto$N0"
            ) result.substring(
                0,
                result.length - 2
            ) else result
        equalPress = true
    }

}