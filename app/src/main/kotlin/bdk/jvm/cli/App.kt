package bdk.jvm.cli

import bdk.jvm.cli.CliWallet

fun main() {
    println("Hello, BITCOINDEVKIT!")

    val wallet: CliWallet = CliWallet()
    wallet.createWallet()
    println(wallet.newAddress())
}
