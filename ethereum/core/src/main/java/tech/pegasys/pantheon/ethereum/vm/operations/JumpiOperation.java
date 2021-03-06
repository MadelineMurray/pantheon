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
package tech.pegasys.pantheon.ethereum.vm.operations;

import tech.pegasys.pantheon.ethereum.core.Gas;
import tech.pegasys.pantheon.ethereum.vm.AbstractOperation;
import tech.pegasys.pantheon.ethereum.vm.Code;
import tech.pegasys.pantheon.ethereum.vm.EVM;
import tech.pegasys.pantheon.ethereum.vm.ExceptionalHaltReason;
import tech.pegasys.pantheon.ethereum.vm.GasCalculator;
import tech.pegasys.pantheon.ethereum.vm.MessageFrame;
import tech.pegasys.pantheon.util.bytes.Bytes32;
import tech.pegasys.pantheon.util.uint.UInt256;

import java.util.EnumSet;
import java.util.Optional;

public class JumpiOperation extends AbstractOperation {

  public JumpiOperation(final GasCalculator gasCalculator) {
    super(0x57, "JUMPI", 2, 0, true, 1, gasCalculator);
  }

  @Override
  public Gas cost(final MessageFrame frame) {
    return gasCalculator().getHighTierGasCost();
  }

  @Override
  public void execute(final MessageFrame frame) {
    final UInt256 jumpDestination = frame.popStackItem().asUInt256();
    final Bytes32 condition = frame.popStackItem();

    if (!condition.isZero()) {
      frame.setPC(jumpDestination.toInt());
    } else {
      frame.setPC(frame.getPC() + getOpSize());
    }
  }

  @Override
  public Optional<ExceptionalHaltReason> exceptionalHaltCondition(
      final MessageFrame frame,
      final EnumSet<ExceptionalHaltReason> previousReasons,
      final EVM evm) {
    // If condition is zero (false), no jump is will be performed. Therefore skip the test.
    if (frame.getStackItem(1).isZero()) {
      return Optional.empty();
    }

    final Code code = frame.getCode();
    final UInt256 potentialJumpDestination = frame.getStackItem(0).asUInt256();
    return !code.isValidJumpDestination(evm, frame, potentialJumpDestination)
        ? Optional.of(ExceptionalHaltReason.INVALID_JUMP_DESTINATION)
        : Optional.empty();
  }
}
