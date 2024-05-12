package com.codeliner.simplecalc

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val resultValueLiveData = MutableLiveData<String>()
    private val formulaValueLiveData = MutableLiveData<String>()


    lateinit var buttont2: Button
    var currentNumber = ""
    var currentOperator = ""
    var result = ""
    var firstNumber = ""

    fun getResultValueLiveData(): LiveData<String> = resultValueLiveData
    fun getFormulaValueLiveData(): LiveData<String> = formulaValueLiveData
    fun onButtonClick(buttonId: Int) {
        when (buttonId) {
            R.id.layout_main -> buttonValue()
        }
    }


    fun buttonValue() {

        val buttonText = buttont2.text.toString()

        when {
            buttonText.matches(Regex("[0-9]")) -> {

                if (currentOperator.isEmpty()) {
                    firstNumber += buttonText
                    resultValueLiveData.value = firstNumber
                } else {
                    currentNumber += buttonText

                    resultValueLiveData.value = currentNumber
                }
            }
            buttonText.matches(Regex("[+\\-*/]")) -> {
                currentNumber = ""
                if (resultValueLiveData.value.toString().isNotEmpty()) {
                    currentOperator = buttonText
                    resultValueLiveData.value = "0"
                }
            }
            buttonText == "=" -> {
                if (currentNumber.isNotEmpty() && currentOperator.isNotEmpty()) {
                    formulaValueLiveData.value = "$firstNumber$currentOperator$currentNumber"
                    result = evaluaterExpression(firstNumber, currentNumber, currentOperator)
                    firstNumber = result
                    resultValueLiveData.value = result
                }
            }
            buttonText == "." -> {
                if (currentOperator.isEmpty()) {
                    if (!firstNumber.contains(".")) {
                        if (firstNumber.isEmpty()) firstNumber += "0$buttonText"
                        else firstNumber += buttonText
                        resultValueLiveData.value = firstNumber
                    }
                } else {
                    if (!currentNumber.contains(".")) {
                        if (currentNumber.isEmpty()) currentNumber += "0$buttonText"
                        else currentNumber += buttonText
                        resultValueLiveData.value = currentNumber
                    }
                }
            }
            buttonText == "c" -> {
                currentNumber = ""
                firstNumber = ""
                currentOperator = ""
                resultValueLiveData.value = "0"
                formulaValueLiveData.value = ""
            }
        }

    }

    private fun evaluaterExpression(
        firstNumber: String,
        secondNumber: String,
        operator: String
    ): String {
        val num1 = firstNumber.toDouble()
        val num2 = secondNumber.toDouble()
        return when (operator) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> (num1 / num2).toString()
            else -> ""
        }
    }

}
