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
package tech.pegasys.pantheon.ethereum.jsonrpc.internal.methods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import tech.pegasys.pantheon.ethereum.jsonrpc.internal.JsonRpcRequest;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.parameters.JsonRpcParameter;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcError;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcErrorResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcSuccessResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminChangeLogLevelTest {

  private final JsonRpcParameter parameters = new JsonRpcParameter();

  private AdminChangeLogLevel adminChangeLogLevel;

  @Before
  public void before() {
    adminChangeLogLevel = new AdminChangeLogLevel(parameters);
    Configurator.setAllLevels("", Level.INFO);
  }

  @Test
  public void shouldReturnExpectedMethodName() {
    assertThat(adminChangeLogLevel.getName()).isEqualTo("admin_changeLogLevel");
  }

  @Test
  public void shouldReturnCorrectResponseWhenRequestHasLogLevel() {
    final JsonRpcRequest request =
        new JsonRpcRequest("2.0", "admin_changeLogLevel", new Object[] {Level.DEBUG});
    final JsonRpcResponse expectedResponse = new JsonRpcSuccessResponse(request.getId());

    final Level levelBeforeJsonRpcRequest = LogManager.getLogger().getLevel();
    final JsonRpcSuccessResponse actualResponse =
        (JsonRpcSuccessResponse) adminChangeLogLevel.response(request);
    final Level levelAfterJsonRpcRequest = LogManager.getLogger().getLevel();

    assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
    assertEquals(Level.INFO, levelBeforeJsonRpcRequest);
    assertEquals(Level.DEBUG, levelAfterJsonRpcRequest);
  }

  @Test
  public void shouldReturnCorrectResponseWhenRequestHasLogLevelAndFilters() {
    final JsonRpcRequest request =
        new JsonRpcRequest(
            "2.0", "admin_changeLogLevel", new Object[] {Level.DEBUG, new String[] {"com"}});
    final JsonRpcResponse expectedResponse = new JsonRpcSuccessResponse(request.getId());

    final Level levelOfAllProjectBeforeJsonRpcRequest = LogManager.getLogger().getLevel();
    final Level levelWithSpecificPackageBeforeJsonRpcRequest =
        LogManager.getLogger("com").getLevel();
    final JsonRpcSuccessResponse actualResponse =
        (JsonRpcSuccessResponse) adminChangeLogLevel.response(request);
    final Level levelOfAllProjectAfterJsonRpcRequest = LogManager.getLogger().getLevel();
    final Level levelWithSpecificPackageAfterJsonRpcRequest =
        LogManager.getLogger("com").getLevel();

    assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
    assertEquals(Level.INFO, levelOfAllProjectBeforeJsonRpcRequest);
    assertEquals(Level.INFO, levelOfAllProjectAfterJsonRpcRequest);
    assertEquals(Level.INFO, levelWithSpecificPackageBeforeJsonRpcRequest);
    assertEquals(Level.DEBUG, levelWithSpecificPackageAfterJsonRpcRequest);
  }

  @Test
  public void requestHasValidStringLogLevelParameter() {
    final JsonRpcRequest request =
        new JsonRpcRequest("2.0", "admin_changeLogLevel", new String[] {"DEBUG"});
    final JsonRpcResponse expectedResponse = new JsonRpcSuccessResponse(request.getId());

    final Level levelBeforeJsonRpcRequest = LogManager.getLogger().getLevel();
    final JsonRpcSuccessResponse actualResponse =
        (JsonRpcSuccessResponse) adminChangeLogLevel.response(request);
    final Level levelAfterJsonRpcRequest = LogManager.getLogger().getLevel();

    assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
    assertEquals(Level.INFO, levelBeforeJsonRpcRequest);
    assertEquals(Level.DEBUG, levelAfterJsonRpcRequest);
  }

  @Test
  public void requestHasNullArrayParameter() {
    final JsonRpcRequest request =
        new JsonRpcRequest("2.0", "admin_changeLogLevel", new Object[] {null});
    final JsonRpcResponse expectedResponse =
        new JsonRpcErrorResponse(request.getId(), JsonRpcError.INVALID_PARAMS);

    final JsonRpcResponse actualResponse = adminChangeLogLevel.response(request);
    assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
  }

  @Test
  public void requestHasInvalidStringLogLevelParameter() {
    final JsonRpcRequest request =
        new JsonRpcRequest("2.0", "admin_changeLogLevel", new String[] {"INVALID"});
    final JsonRpcResponse expectedResponse =
        new JsonRpcErrorResponse(request.getId(), JsonRpcError.INVALID_PARAMS);

    final JsonRpcResponse actualResponse = adminChangeLogLevel.response(request);
    assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
  }

  @Test
  public void requestHasInvalidLogFilterParameter() {
    final JsonRpcRequest request =
        new JsonRpcRequest("2.0", "admin_changeLogLevel", new Object[] {"DEBUG", "INVALID"});
    final JsonRpcResponse expectedResponse =
        new JsonRpcErrorResponse(request.getId(), JsonRpcError.INVALID_PARAMS);

    final JsonRpcResponse actualResponse = adminChangeLogLevel.response(request);
    assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
  }
}
