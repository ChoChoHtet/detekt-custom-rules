package com.android.customRules


import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtImportDirective

class NoSyntheticImportRule() : Rule() {
  override val issue = Issue("NoSyntheticImport",
      Severity.Maintainability,
      "Don’t import Kotlin Synthetics as it is already deprecated.",
      Debt.TWENTY_MINS)

  override fun visitImportDirective(importDirective: KtImportDirective) {
    val import = importDirective.importPath?.pathStr
    if (import?.contains("kotlinx.android.synthetic") == true) {
      report(CodeSmell(issue, Entity.from(importDirective),
          "Importing '$import' which is a Kotlin Synthetics import."))
    }
  }
}
