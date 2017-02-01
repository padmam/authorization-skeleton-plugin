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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.validation.ValidationError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cd.go.authorization.skeleton.util.Util.GSON;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class VerifyConnectionRequestFromServerExecutorTest {

    @Mock
    private GoPluginApiRequest request;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldReturnEmptyValidationResultIfProfileIsValid() throws Exception {
        Map<String, String> profileMap = new HashMap<>();
        profileMap.put("Url", "foo-url");
        profileMap.put("SearchBase", "ou=users,ou=system");
        profileMap.put("ManagerDN", "uid=admin,ou=system");
        profileMap.put("Password", "secret");
        profileMap.put("SearchFilter", "uid");
        profileMap.put("DisplayNameAttribute", "displayName");

        when(request.requestBody()).thenReturn(GSON.toJson(profileMap));

        GoPluginApiResponse response = new VerifyConnectionRequestExecutor(request).execute();

        assertThat(response.responseCode(), is(200));

        List<ValidationError> result = new Gson().fromJson(response.responseBody(), new TypeToken<List<ValidationError>>() {
        }.getType());

        assertThat(result, hasSize(0));
    }

    @Test
    public void shouldReturnNonEmptyValidationResultIfProfileIsInvalid() throws Exception {
        Map<String, String> profileMap = new HashMap<>();
        profileMap.put("Url", "foo-url");
        profileMap.put("SearchBase", "ou=users,ou=system");
        profileMap.put("ManagerDN", "uid=no-such-user,ou=system");
        profileMap.put("Password", "secret");
        profileMap.put("SearchFilter", "uid");
        profileMap.put("DisplayNameAttribute", "displayName");

        when(request.requestBody()).thenReturn(GSON.toJson(profileMap));

        GoPluginApiResponse response = new VerifyConnectionRequestExecutor(request).execute();
        assertThat(response.responseCode(), is(200));

        List<ValidationError> result = new Gson().fromJson(response.responseBody(), new TypeToken<List<ValidationError>>() {
        }.getType());

        assertThat(result, contains(new ValidationError("Failed to authenticate `ManagerDN`")));
    }
}