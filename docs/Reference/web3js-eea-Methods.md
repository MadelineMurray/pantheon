description: web3js-eea methods reference
<!--- END of page meta data -->

For more examples, refer to the following in the [webjs-eea repository](https://github.com/PegaSysEng/web3js-eea):

* [`deployContract.js`](https://github.com/PegaSysEng/web3js-eea/blob/master/example/multiNodeExample/deployContract.js)
* [`storeValueFromNode1.js`](https://github.com/PegaSysEng/web3js-eea/blob/master/example/multiNodeExample/storeValueFromNode1.js)

## Options Parameter 

The Options parameter is used by: 

* [`generatePrivacyGroup`](#generateprivacygroup)
* [`getTransactionCount`](#gettransactioncount)
* [`sendRawParameter`](#sendrawtransaction)

The Options parameter has the following properties: 

* `privateKey`: Ethereum private key with which to sign the transaction
* `privateFrom` : Orion public key
* `privateFor` : Orion public keys of recipients
* `nonce` : Optional. If not provided, is calculated using `eea_getTransctionCount`.
* `to` : Optional. Contract address to which to send the transaction. Do not specify for contract deployment transactions
* `data` : Transaction data

## generatePrivacyGroup
    
Generates the privacy group ID. The privacy group ID is the RLP-encoded `privateFor` and `privateFrom` keys.
    
**Parameters**
    
[Transaction options](#options-parameter)
    
**Returns**
    
`string` : Privacy group ID 

!!! example
    ```bash"
    const privacyGroupId = web3.eea.generatePrivacyGroup({
      privateFrom: "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=",
      privateFor: ["Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs="]
    });
    ```

## getMarkerTransaction

Gets the [privacy marker transaction](../Privacy/Private-Transaction-Processing.md) transaction receipt.

**Parameters**

`txHash` - `string` : Transaction hash of the private transaction
`retries` - `int` : Number of attempts to make to get the private marker transaction receipts 
`delay` - `int` : Delay between retries in milliseconds

**Returns**

`result` : Privacy marker transaction receipt 

!!! example
    ```bash
    const privateMarkerTransacion = web3.eea.getMarkerTransaction("0x9c41b3d44ed73511c82a9e2b1ef581eb797475c82f318ca2802358d3ba4a8274", 5, 100);
    ```
        
## getTransactionCount 

Returns the number of transactions sent from the specified address for the privacy group.

**Parameters**

[Transaction options](#options-parameter)

**Returns**

`int` : Transaction count for that account (`privateKey`) and privacy group

    !!! example
        ```bash tab="JS request"
        add
        ```
        
## getTransactionReceipt 

Gets the private transaction receipt using `eea_getTransactionReceipt`.

**Parameters**

`txHash` - `string` : Transaction hash of the private transaction
`enclavePublicKey` - `string` : `privateFrom` key for the transaction 
`retries` - `int` : Number of attempts to make to get the private marker transaction receipts 
`delay` - `int` : Delay between retries in milliseconds

**Returns**

Private transaction receipt 

    !!! example
        ```bash
          const privateTxReceipt = web3.eea.getTransactionReceipt("0x9c41b3d44ed73511c82a9e2b1ef581eb797475c82f318ca2802358d3ba4a8274", "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=");
        ```
    
        
## sendRawTransaction 

Signs and sends a RLP-encoded private transaction to Pantheon using `eea_sendRawTransaction`. 

**Parameters**

[Transaction options](#options-parameter)

**Returns**

`string` : Transaction hash of the [`privacy marker transaction`](../Privacy/Private-Transaction-Processing.md)   
    
    !!! example
        ```bash tab="Contract Deployment Transaction"
        const createPrivateEmitterContract = () => {
          const contractOptions = {
            data: `0x${binary}`,
            privateFrom: orion.node1.publicKey,
            privateFor: [orion.node2.publicKey],
            privateKey: pantheon.node1.privateKey
          };
          return web3.eea.sendRawTransaction(contractOptions);
        };
        ```
        
        ```bash tab="Contract Invocation Transaction"
         const functionCall = {
           to: address,
           data: functionAbi.signature + functionArgs,
           privateFrom: orion.node1.publicKey,
           privateFor: [orion.node2.publicKey],
           privateKey: pantheon.node1.privateKey
         };
         return web3.eea.sendRawTransaction(functionCall);
         ```
        