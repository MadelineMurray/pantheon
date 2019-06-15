description: web3.js-eea methods reference
<!--- END of page meta data -->

M

## Options Parameter 

The Options parameter is used by: 

* [`generatePrivacyGroup`](#generateprivacygroup)
* [`sendRawParameter`](#sendrawtransaction)
* [`getTransactionCount`](#gettransactioncount)

The Options parameter has the following properties: 

* `privateKey`: Ethereum private key with which to sign the transaction
* `privateFrom` : Orion public key
* `privateFor` : Orion public keys of recipients
* `nonce` : Optional. If not provided, is calculated using `eea_getTransctionCount`.
* `to` : Optional. Contract address to which to send the transaction. If not provided, the transaction 
is a contract deployment transaction
* `data` : Transaction data

## getMakerTransaction

Gets the [privacy marker transaction](../Privacy/Private-Transaction-Processing.md) transaction receipt.

**Parameters**

`txHash` - `string` : Transaction hash of the private transaction
`retries` - `int` : Number of attempts to make to get the private marker transaction receipts 
`delay` - `int` : Delay between retries (in seconds?)

**Returns**

`result` : Privacy marker transaction receipt (promise?)

!!! example
    ```bash tab="JS request"
    eea.getMakerTransaction("tx hash -add", 5, 2)
    ```
    
    ```bash tab="Result"
    Add this
    ```
    
## generatePrivacyGroup
    
Generates the privacy group ID. The privacy group ID is the RLP-encoded `privateFor` and `privateFrom` keys.
    
**Parameters**
    
[Transaction options](#options-parameter)
    
**Returns**
    
`string` : Privacy marker transaction receipt  
    
    !!! example
        ```bash tab="JS request"
        add
        ```
        
        ```bash tab="Result"
        add
        ```
        
## sendRawTransaction 

Signs and sends a RLP-encoded private transaction to Pantheon using `eea_sendRawTransaction`. 

**Parameters**

[Transaction options](#options-parameter)

**Returns**

`string` : Transaction hash of the [`privacy marker transaction`](../Privacy/Private-Transaction-Processing.md)   
    
    !!! example
        ```bash tab="JS request"
        add
        ```
        
        ```bash tab="Result"
        add
        ```

## getTransactionCount 

Returns the number of transactions sent from the specified address for the privacy group (add link to privacy group content).

**Parameters**

[Transaction options](#options-parameter)

**Returns**

`int` : Transaction count that address and privacy group

    !!! example
        ```bash tab="JS request"
        add
        ```
        
        ```bash tab="Result"
        add
        ```

## getTransactionReceipt 

Gets the private transaction receipt.

**Parameters**

`txHash` - `string` : Transaction hash of the private transaction
`enclavePublicKey` - `string` : Orion public key (same as privateFrom?  what about privateFor?)
`retries` - `int` : Number of attempts to make to get the private marker transaction receipts 
`delay` - `int` : Delay between retries (in seconds?)

**Returns**

Private transaction receipt 

    !!! example
        ```bash tab="JS request"
        add
        ```
        
        ```bash tab="Result"
        add
        ```