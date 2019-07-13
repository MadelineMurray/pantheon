description: Creating and sending private transactions
<!--- END of page meta data -->

# Creating and Sending Private Transactions 

To create and send private transactions use one of: 

* [web3.js-eea client library](eeajs.md) or [web3j client library](https://github.com/web3j/web3j)
* [`eea_sendTransaction` with EthSigner](https://docs.ethsigner.pegasys.tech/en/latest/Using-EthSigner/Using-EthSigner/) 
* [`eea_sendRawTransaction`](../../Reference/Pantheon-API-Methods.md#eea_sendrawtransaction) 

!!! note
    Private transactions either deploy contracts or call contract functions. 
    Ether transfer transactions cannot be private. 
    
## Accessing Private Transactions using JSON-RPC

A private transaction creates a Privacy Marker Transaction (PMT) that is included in a block and 
propagated using devP2P in the same way as a public Ethereum transaction. JSON-RPC API methods are 
provided to access private transaction receipts and counts: 

* [`eea_getTransactionCount`]
* [`eea_getTransactionReceipt`]   
* [`eea_getPrivateTransaction`]
* [`eth_getTransactionReceipt`]

!!! note
    If sending a large number of private transactions, the nonce for the account and privacy group must
    be maintained outside the client. Unlike [`eth_getTransaction Count`], [`eea_getTransactionCount`] does 
    not include the block parameter to specify the pending block to obtain the account nonce. 
    

## Methods for Privacy Groups 

* [`eea_createPrivacyGroup`]
* [`eea_deletePrivacyGroup`]
* [`eea_findPrivacyGroup`]
* [`eea_sendRawTransaction`] specifying a privacy group ID 
* [`eea_sendTransaction`] specifying a privacy group

## Example

Create Privacy Group containing A & B 

Send transaction using eea_sendTransaction specifying privacy group A 

eth_getTransactionReceipt (one example privy and one not)

eea_getPrivateTransaction (one example privy and one not)

eea_getTransaction Count  