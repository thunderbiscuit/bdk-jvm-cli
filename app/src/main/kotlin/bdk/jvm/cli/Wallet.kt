package bdk.jvm.cli

import org.bitcoindevkit.bdkjni.*

class CliWallet {
    private val lib: Lib
    private lateinit var walletPtr: WalletPtr

    init {
        Lib.load()
        lib = Lib()
    }

    public fun createWallet(): Unit {
        initialize(
            descriptor = "wpkh(tprv8ZgxMBicQKsPexGYyaFwnAsCXCjmz2FaTm6LtesyyihjbQE3gRMfXqQBXKM43DvC1UgRVv1qom1qFxNMSqVAs88qx9PhgFnfGVUdiiDf6j4/0/*)",
            changeDescriptor = "wpkh(tprv8ZgxMBicQKsPexGYyaFwnAsCXCjmz2FaTm6LtesyyihjbQE3gRMfXqQBXKM43DvC1UgRVv1qom1qFxNMSqVAs88qx9PhgFnfGVUdiiDf6j4/2/*)",
        )
    }

    private fun initialize(
        descriptor: String,
        changeDescriptor: String,
    ): Unit {
        walletPtr = lib.constructor(
            WalletConstructor(
                name = "cli-wallet",
                network = Network.testnet,
                path = createTempDir().toString(),
                descriptor = descriptor,
                change_descriptor = changeDescriptor,
                electrum_url = "tcp://electrum.blockstream.info:60001",
                electrum_proxy = null,
            )
        )
    }

    fun newAddress(): String {
        return lib.get_new_address(walletPtr)
    }

//    fun constructor() {
//        val dir = createTempDir()
//        val descriptor = "wpkh(tprv8ZgxMBicQKsPexGYyaFwnAsCXCjmz2FaTm6LtesyyihjbQE3gRMfXqQBXKM43DvC1UgRVv1qom1qFxNMSqVAs88qx9PhgFnfGVUdiiDf6j4/0/*)"
//        val electrum = "tcp://electrum.blockstream.info:60001"
//        wallet = lib.constructor(WalletConstructor("testnet", Network.regtest, dir.toString(), descriptor, null, electrum, null))
//        lib.sync(wallet)
//    }
}