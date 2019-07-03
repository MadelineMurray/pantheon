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
package tech.pegasys.pantheon.cli.options;

import static com.google.common.base.Preconditions.checkArgument;

import tech.pegasys.pantheon.util.uint.UInt256;

import java.math.BigInteger;
import java.util.Iterator;

import com.google.common.base.Splitter;
import com.google.common.collect.Range;

public class OptionParser {

  public static Range<Long> parseLongRange(final String arg) {
    checkArgument(arg.matches("-?\\d+\\.\\.-?\\d+"));
    final Iterator<String> ends = Splitter.on("..").split(arg).iterator();
    return Range.closed(parseLong(ends.next()), parseLong(ends.next()));
  }

  public static long parseLong(final String arg) {
    return Long.parseLong(arg, 10);
  }

  public static String format(final Range<Long> range) {
    return format(range.lowerEndpoint()) + ".." + format(range.upperEndpoint());
  }

  public static String format(final int value) {
    return Integer.toString(value, 10);
  }

  public static String format(final long value) {
    return Long.toString(value, 10);
  }

  public static String format(final float value) {
    return Float.toString(value);
  }

  public static String format(final UInt256 value) {
    return new BigInteger(value.toUnprefixedHexString(), 16).toString(10);
  }
}
