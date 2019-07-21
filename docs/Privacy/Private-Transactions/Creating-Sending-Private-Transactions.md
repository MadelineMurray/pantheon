description: Creating and sending private transactions
<!--- END of page meta data -->

# Creating and Sending Private Transactions 

To create and send private transactions using: 

* [web3.js-eea client library](eeajs.md) or [web3j client library](https://github.com/web3j/web3j)
* [`eea_sendTransaction` with EthSigner](https://docs.ethsigner.pegasys.tech/en/latest/Using-EthSigner/Using-EthSigner/) 
* [`eea_sendRawTransaction`](../../Reference/Pantheon-API-Methods.md#eea_sendrawtransaction) 

!!! note
    Private transactions either deploy contracts or call contract functions. 
    Ether transfer transactions cannot be private. 
    
## Methods for Private Transactions

A private transaction creates a Privacy Marker Transaction (PMT) as well the private transaction itself. 
Use [`eth_getTransactionReceipt`](../../Reference/Pantheon-API-Methods.md#eth_gettransactionreceipt) to 
get the transaction receipt for the Privacy Maker Transaction and [`eea_getTransactionReceipt`](../../Reference/Pantheon-API-Methods.md#eea_gettransactionreceipt) 
to get the transaction receipt for the private transaction. 

Use [`eth_getTransactionByHash`](../../Reference/Pantheon-API-Methods.md#eth_gettransactionbyhash) to 
get the Privacy Marker Transaction using the transaction hash returned when submitting the private transaction. 
Use [`eea_getPrivateTransacton`] to get the private transaction using the `input` value from Privacy Marker Transaction. 

Separate private states are maintained for each [privacy group](../Privacy-Overview.md#privacy-groups) so 
the account nonce for an account is specific to the privacy group. That is, the nonce for account A for
privacy group ABC is different to the account nonce for account A for privacy group AB. Use 
[`eea_getTransactionCount`](../../Reference/Pantheon-API-Methods.md#eea_gettransactioncount) to get 
the account nonce for an account for the specified privacy group. 

!!! note
    If sending a large number of private transactions, you may need to calculate the nonce for the account 
    and privacy group outside the client. 