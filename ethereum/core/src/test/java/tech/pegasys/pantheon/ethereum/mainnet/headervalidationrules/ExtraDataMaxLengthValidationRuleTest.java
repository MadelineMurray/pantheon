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
package tech.pegasys.pantheon.ethereum.mainnet.headervalidationrules;

import static org.assertj.core.api.Assertions.assertThat;

import tech.pegasys.pantheon.ethereum.core.BlockHeader;
import tech.pegasys.pantheon.ethereum.core.BlockHeaderTestFixture;
import tech.pegasys.pantheon.util.bytes.BytesValue;

import org.junit.Test;

public class ExtraDataMaxLengthValidationRuleTest {

  @Test
  public void sufficientlySmallExtraDataBlockValidateSuccessfully() {
    final ExtraDataMaxLengthValidationRule uut = new ExtraDataMaxLengthValidationRule(1);
    final BlockHeaderTestFixture builder = new BlockHeaderTestFixture();
    builder.extraData(BytesValue.wrap(new byte[1]));

    final BlockHeader header = builder.buildHeader();

    // Note: The parentHeader is not required for this validator.
    assertThat(uut.validate(header, null)).isTrue();
  }

  @Test
  public void tooLargeExtraDataCausesValidationFailure() {
    final ExtraDataMaxLengthValidationRule uut = new ExtraDataMaxLengthValidationRule(1);
    final BlockHeaderTestFixture builder = new BlockHeaderTestFixture();
    builder.extraData(BytesValue.wrap(new byte[2]));

    final BlockHeader header = builder.buildHeader();

    // Note: The parentHeader is not required for this validator.
    assertThat(uut.validate(header, null)).isFalse();
  }
}
