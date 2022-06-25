package ru.netology

import kotlin.math.roundToInt

const val MIN_TRANSFER_FEE = 35_00
const val EXTRA_TRANSFER_FEE = 20_00
const val LIMIT_TRANSFER_FEE = 75_000_00
const val STANDARD_PERCENT_FEE = 0.0075
const val LIMIT_PERCENT_FEE = 0.006

enum class TypeAccount {
    Mastercard, Maestro, Visa, Mir, VKPay
}

fun main() {
    val amountTransfer = 5000_00
    val amountTransfersMonth = 80_000_00
    val typeAccount = TypeAccount.Mastercard

    val result = calculateTransferFee(amountTransfer, amountTransfersMonth, typeAccount)
    printResult(result)
}

fun calculateTransferFee(
    amountTransfer: Int,
    amountTransfersMonth: Int = 0,
    typeAccount: TypeAccount = TypeAccount.VKPay
): Int {
    return when (typeAccount) {
        TypeAccount.Maestro, TypeAccount.Mastercard -> {
            if (amountTransfersMonth < LIMIT_TRANSFER_FEE) 0
            else (amountTransfer * LIMIT_PERCENT_FEE + EXTRA_TRANSFER_FEE).roundToInt()
        }
        TypeAccount.Visa, TypeAccount.Mir -> {
            val transferFee = (amountTransfer * STANDARD_PERCENT_FEE).roundToInt()
            if (transferFee > MIN_TRANSFER_FEE) transferFee else MIN_TRANSFER_FEE
        }
        TypeAccount.VKPay -> 0
    }
}

fun printResult(result: Int) {
    if (result == 0) {
        println("Без комиссии")
    } else {
        println("Комиссия составит ${result / 100} руб. ${result % 100} коп.")
    }
}