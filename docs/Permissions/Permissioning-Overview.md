description: Pantheon Permissioning feature
<!--- END of page meta data -->

# Permissioning 

A permissioned network allows only specified nodes and accounts to participate by enabling node permissioning and/or account permissioning on the network.

!!! note
    In peer-to-peer networks, node-level permissions can be used to enforce rules on nodes you control. 
    With node-level permissions only, it is still possible a bad actor could violate network governance 
    and act as a proxy to other nodes.  
    
Permissioning is not privacy - 
    
![Node Permissioning](../images/node-permissioning-bad-actor.png)

![Account Permissioning](../images/account-permissioning.png)

## Local 

[Local permissioning](Local-Permissioning.md) are specified at the node level. Each node in the network has a [permissions configuration file](#permissions-configuration-file).
Updates to local permissioning must be made to the configuration file for each node. 

Affects your node not the rest of the network. Restrict use of your node - either resources or customers that can access your node. 

Doesn't require co-ordintion- you can act immediately in reponse to changing business conditons and protect your node. 
New blocks won't abide by your rules - it's about restricting the use of your node (ie, the resources under your control). 

## Onchain 

[Onchain permissioning](Onchain-Permissioning.md) is specified in a smart contract on the network. Specifying permissioning onchain
enables all nodes to read and update permissioning in one location. 

Requires co-ordination to add rules to contract. Can't react immediatly (may need a quorum) but when you do react, whole network
is reacts at once. New blocks will abide by the rules (eg, blocked accounts can no longer add transactions to the chain)

!!! note
    Onchain permissioning for accounts will be available in a future Pantheon release. 


## Nodes 

Can remove badly behaved nodes - ones that advertise bad blocks. can restrict to known parties only. 

## Accounts 

enforce onboarding or identity requirements 

suspend accounts 

blacklist broken contracts 

restrict actions accounts can take 