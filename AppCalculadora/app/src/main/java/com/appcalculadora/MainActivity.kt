package com.appcalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        //evento de clique dos numeros
        numero_zero.setOnClickListener{acresExpressao("0",true)}
        numero_um.setOnClickListener{acresExpressao("1",true)}
        numero_dois.setOnClickListener{acresExpressao("2",true)}
        numero_tres.setOnClickListener{acresExpressao("3",true)}
        numero_quatro.setOnClickListener{acresExpressao("4",true)}
        numero_cinco.setOnClickListener{acresExpressao("5",true)}
        numero_seis.setOnClickListener{acresExpressao("6",true)}
        numero_sete.setOnClickListener{acresExpressao("7",true)}
        numero_oito.setOnClickListener{acresExpressao("8",true)}
        numero_nove.setOnClickListener{acresExpressao("9",true)}
        numero_sete.setOnClickListener{acresExpressao("7",true)}
        ponto.setOnClickListener { acresExpressao(".", true) }

        //evento de clique dos operadores

        soma.setOnClickListener { acresExpressao("+",false) }
        subtracao.setOnClickListener { acresExpressao("-",false) }
        multiplicacao.setOnClickListener { acresExpressao("*",false) }
        divisao.setOnClickListener { acresExpressao("/",false) }

        //Limpar tudo com o botão "C"
        limpar.setOnClickListener {
            expressao.text = ""
            resultado.text = ""
        }

        //Apagar usando o backspace
        backspace.setOnClickListener {
            val string = expressao.text.toString()
            if (string.isNotBlank()){
                expressao.text = string.substring(0,string.length-1)
            //vai verificar o comprimento dos caracters e subtrair um
            }
            resultado.text = ""
        }

        igual.setOnClickListener {
            try {
                val expressaoresult = ExpressionBuilder(expressao.text.toString()).build()

                val resultadofinal = expressaoresult.evaluate()
                val longResultado = resultadofinal.toLong()

                if(resultadofinal==longResultado.toDouble())
                    resultado.text = longResultado.toString()
                else
                    resultado.text = resultadofinal.toString()


            } catch (e: Exception){


            }
        }
    }


    fun acresExpressao(string:String, limpardados:Boolean){
if (resultado.text.isNotEmpty()){
    expressao.text = ""
}
        if(limpardados){
            resultado.text = ""
            expressao.append(string)
        } else{
            expressao.append(resultado.text)
            expressao.append(string)
            resultado.text = ""
        }
    }
}