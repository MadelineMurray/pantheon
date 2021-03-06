/*
 * Copyright 2018 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.pantheon.ethereum.p2p.rlpx.handshake.ecies;

import tech.pegasys.pantheon.crypto.SECP256K1;
import tech.pegasys.pantheon.util.bytes.Bytes32;
import tech.pegasys.pantheon.util.bytes.BytesValue;

public interface InitiatorHandshakeMessage {

  BytesValue encode();

  Bytes32 getNonce();

  SECP256K1.PublicKey getPubKey();

  SECP256K1.PublicKey getEphPubKey();

  Bytes32 getEphPubKeyHash();
}
