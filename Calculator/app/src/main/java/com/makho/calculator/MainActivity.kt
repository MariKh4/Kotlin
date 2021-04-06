package com.makho.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import android.view.View
import android.content.ClipData
import android.content.ClipboardManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonclick(view: View) {

        Zero.setOnClickListener { evaluateExpression("0") }
        One.setOnClickListener { evaluateExpression("1") }
        Two.setOnClickListener { evaluateExpression("2") }
        Three.setOnClickListener { evaluateExpression("3") }
        Four.setOnClickListener { evaluateExpression("4") }
        Five.setOnClickListener { evaluateExpression("5") }
        Six.setOnClickListener { evaluateExpression("6") }
        Seven.setOnClickListener { evaluateExpression("7") }
        Eight.setOnClickListener { evaluateExpression("8") }
        Nine.setOnClickListener { evaluateExpression("9") }

        Plus.setOnClickListener { evaluateExpression("+") }
        Minus.setOnClickListener { evaluateExpression("-") }
        Multiplication.setOnClickListener { evaluateExpression("*") }
        Division.setOnClickListener { evaluateExpression("/") }

        Dot.setOnClickListener { evaluateExpression(".") }
        Rbrk.setOnClickListener { evaluateExpression("(") }
        Lbrk.setOnClickListener { evaluateExpression(")") }


        Clear.setOnClickListener {
            Expression.text = ""
            Result.text = ""
        }


        Back.setOnClickListener {
            Expression.text = Expression.text.substring(0, Expression.text.length-1)
            Result.text = ""
        }


        Equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(Expression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    Result.text = longResult.toString()
                } else {
                    Result.text = result.toString()
                }
            } catch (e:Exception) {
                Log.d("Expression", "message : " + e.message)

            }
        }

        Expression.movementMethod = ScrollingMovementMethod()
        Result.movementMethod = ScrollingMovementMethod()

    }

    fun evaluateExpression(string: String) {
        if (Result.text.isNotEmpty()){
            if (string == "+" || string =="-" || string =="*" || string =="/") {
                Expression.text = Result.text
                Result.text = ""
            } else {
                Expression.text = ""
                Result.text = ""
            }
        }

        Result.text = ""
        Expression.append(string)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("expression", Expression.text.toString())
        outState.putString("result", Result.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Expression.text = savedInstanceState.getString("expression")
        Result.text = savedInstanceState.getString("result")
    }


}
