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

package cd.go.authorization.skeleton.executor;

import cd.go.authorization.skeleton.BaseTest;
import cd.go.authorization.skeleton.exception.ServerRequestFailedException;
import cd.go.authorization.skeleton.model.User;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticationExecutorTest extends BaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private GoPluginApiRequest request;
    private GoApplicationAccessor accessor;

    @Test
    public void setup() throws ServerRequestFailedException {
        super.setup();
        accessor = mock(GoApplicationAccessor.class);
        when(accessor.submit(any())).thenReturn(new DefaultGoApiResponse(DefaultGoApiResponse.SUCCESS_RESPONSE_CODE));
        request = mock(GoPluginApiRequest.class);
    }

    @Test
    public void shouldAbleToAuthenticateUser() throws Exception {
        when(request.requestBody()).thenReturn(getUserDetailMap("bford", "bob").toString());

        GoPluginApiResponse response = new UserAuthenticationExecutor(request, accessor).execute();

        assertThat(response.responseCode(), is(200));
        User user = getUser(response);
        assertThat(user, is(new User("bford", "Bob Ford", "bford@example.com")));
    }

    private Map<String, String> getUserDetailMap(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return map;
    }

    private User getUser(GoPluginApiResponse response) {
        AuthResponse authResponse = new Gson().fromJson(response.responseBody(), AuthResponse.class);
        return authResponse.user;
    }

    private class AuthResponse {
        @SerializedName("user")
        public User user;
    }
}
