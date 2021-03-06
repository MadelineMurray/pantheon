/*
 * Copyright 2019 ConsenSys AG.
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
package tech.pegasys.pantheon.crypto;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

public final class PantheonProvider extends Provider {

  private static final String info = "Pantheon Security Provider v1.0";

  public static final String PROVIDER_NAME = "Pantheon";

  @SuppressWarnings("unchecked")
  public PantheonProvider() {
    super(PROVIDER_NAME, "1.0", info);
    AccessController.doPrivileged(
        (PrivilegedAction)
            () -> {
              put("MessageDigest.Blake2bf", "tech.pegasys.pantheon.crypto.Blake2bfMessageDigest");
              return null;
            });
  }
}
