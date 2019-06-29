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
package tech.pegasys.pantheon.ethereum.p2p.discovery.internal;

import tech.pegasys.pantheon.ethereum.p2p.peers.Peer;
import tech.pegasys.pantheon.metrics.Counter;
import tech.pegasys.pantheon.metrics.LabelledMetric;
import tech.pegasys.pantheon.metrics.MetricsSystem;
import tech.pegasys.pantheon.metrics.PantheonMetricCategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscoveryProtocolLogger {

  private static final Logger LOG = LogManager.getLogger();
  private final LabelledMetric<Counter> outgoingMessageCounter;
  private final LabelledMetric<Counter> incomingMessageCounter;

  public DiscoveryProtocolLogger(final MetricsSystem metricsSystem) {
    outgoingMessageCounter =
        metricsSystem.createLabelledCounter(
            PantheonMetricCategory.NETWORK,
            "discovery_messages_outbound",
            "Total number of P2P discovery messages sent",
            "name");
    incomingMessageCounter =
        metricsSystem.createLabelledCounter(
            PantheonMetricCategory.NETWORK,
            "discovery_messages_inbound",
            "Total number of P2P discovery messages received",
            "name");
  }

  void logSendingPacket(final Peer peer, final Packet packet) {
    outgoingMessageCounter.labels(packet.getType().name()).inc();
    LOG.trace(
        "<<< Sending  {} packet to peer {} ({}): {}",
        shortenPacketType(packet),
        peer.getId().slice(0, 16),
        peer.getEnodeURL(),
        packet);
  }

  void logReceivedPacket(final Peer peer, final Packet packet) {
    incomingMessageCounter.labels(packet.getType().name()).inc();
    LOG.trace(
        ">>> Received {} packet from peer {} ({}): {}",
        shortenPacketType(packet),
        peer.getId().slice(0, 16),
        peer.getEnodeURL(),
        packet);
  }

  private String shortenPacketType(final Packet packet) {
    switch (packet.getType()) {
      case PING:
        return "PING ";
      case PONG:
        return "PONG ";
      case FIND_NEIGHBORS:
        return "FINDN";
      case NEIGHBORS:
        return "NEIGH";
    }
    return null;
  }
}
