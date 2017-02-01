/*
 * Copyright 2017 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.authorization.skeleton.requester;

import cd.go.authorization.skeleton.Constants;
import cd.go.authorization.skeleton.model.RoleConfig;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.request.GoApiRequest;
import com.thoughtworks.go.plugin.api.response.GoApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.List;

import static cd.go.authorization.skeleton.util.Util.GSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RolesConfigRequesterTest {
    @Mock
    private GoApplicationAccessor applicationAccessor;
    @Mock
    private GoApiResponse response;
    private ArgumentCaptor<GoApiRequest> argumentCaptor;
    private String rolesConfigJSON;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        argumentCaptor = ArgumentCaptor.forClass(GoApiRequest.class);
        rolesConfigJSON = "[\n" +
                "  {\n" +
                "    \"configuration\": {\n" +
                "      \"memberOf\": \"ou=blackbird,ou=area51,dc=example,dc=com\"\n" +
                "    },\n" +
                "    \"name\": \"blackbird\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"configuration\": {\n" +
                "      \"group\": \"ou=dev,ou=area51,dc=example,dc=com\"\n" +
                "    },\n" +
                "    \"name\": \"redflower\"\n" +
                "  }\n" +
                "]";
    }

    @Test
    public void shouldGetRolesConfigFromServer() throws Exception {
        when(applicationAccessor.submit(argumentCaptor.capture())).thenReturn(response);
        when(response.responseBody()).thenReturn(rolesConfigJSON);
        when(response.responseCode()).thenReturn(200);

        RolesConfigRequester requester = new RolesConfigRequester(applicationAccessor);
        requester.setRequestBody("foo");
        List<RoleConfig> roleConfigs = requester.request();

        GoApiRequest request = argumentCaptor.getValue();
        assertThat(request.api(), is(RequestToServer.REQUEST_ROLE_CONFIGURATION_FROM_SERVER.requestName()));
        assertThat(request.apiVersion(), is(Constants.API_VERSION));

        assertThat(roleConfigs, hasSize(2));
        JSONAssert.assertEquals(GSON.toJson(roleConfigs), rolesConfigJSON, true);
    }
}