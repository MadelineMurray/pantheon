description: Creating and sending private transactions
<!--- END of page meta data -->

# Creating and Sending Private Transactions 

To create and send private transactions use one of: 

* [web3.js-eea client library](eeajs.md)
* [`eea_sendTransaction` with EthSigner](https://docs.ethsigner.pegasys.tech/en/latest/Using-EthSigner/Using-EthSigner/) 

!!! note
    Private transactions either deploy contracts or call contract functions. 
    Ether transfer transactions cannot be private. 
    
## Accessing Private Transactions using JSON-RPC

A private transaction creates a Privacy Marker Transaction (PMT) that is included in a block and 
propagated using devP2P in the same way as a public Ethereum transaction. 