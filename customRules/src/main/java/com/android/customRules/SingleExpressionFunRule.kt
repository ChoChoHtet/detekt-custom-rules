package com.android.customRules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Location
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtNamedFunction

import io.gitlab.arturbosch.detekt.api.Entity

class SingleExpressionFunRule() : Rule() {
    override val issue: Issue
        get() = Issue(
           "SingleExpressionFunction" ,Severity.Style,
            "Please change to one line function",
            Debt.TWENTY_MINS
        )

    private var oneLine = 0 ;
    override fun visitNamedFunction(function: KtNamedFunction) {
        function.bodyBlockExpression?.let{
            val expressionLine = Location.startLineAndColumn(it).line
            println("equallines: ${ it.children.size}")
           val returnKeyword = it.children[0].text.contains("return")
            if (it.children.size == 1 && returnKeyword){
                report(CodeSmell(issue, Entity.from(it),"Change to single line"))
            }
            oneLine = it.children.size
        }
      /*  function.equalsToken?.let {element ->
           *//* function.bodyExpression?.let {
                val equalLines = Location.startLineAndColumn(element).line
                val expressionLine = Location.startLineAndColumn(it).line
                println("Expression line: $expressionLine, equallines: $equalLines")
                if (equalLines == 1){
                    report(CodeSmell(issue, Entity.from(element),"Change to single line"))
                }

            }*//*
            val equalLines = Location.startLineAndColumn(element).line
            println("equallines: $equalLines")
            function.bodyBlockExpression?.let {
                val expressionLine = Location.startLineAndColumn(it).line
                if (equalLines == 1){
                    report(CodeSmell(issue, Entity.from(element),"Change to single line"))
                }
            }
        }*/
       /* function.bodyExpression?.let {
            val equalLines = Location.startLineAndColumn(element).line
            val expressionLine = Location.startLineAndColumn(it).line
            if (equalLines != expressionLine){
                report(CodeSmell(issue, Entity.from(element),"Change to single line"))
            }
            *//* if (expressionLine == 1){
                 report(CodeSmell(issue, Entity.from(element),"Change to single line"))
             }*//*
        }*/

        //println(function.bodyExpression?.javaClass)
    }

    /*override fun visitReturnExpression(expression: KtReturnExpression) {
        super.visitReturnExpression(expression)
        println("Lines: $oneLine ")
        if (oneLine == 1 &&  expression.returnKeyword.isValid){
            report(CodeSmell(issue, Entity.from(expression),"Change to single line"))
        }
    }*/

}