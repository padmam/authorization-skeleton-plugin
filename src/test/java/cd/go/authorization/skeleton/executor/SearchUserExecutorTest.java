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
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchUserExecutorTest extends BaseTest {

    private GoPluginApiRequest request;

    @Test
    public void setup() throws ServerRequestFailedException {
        super.setup();
        request = mock(GoPluginApiRequest.class);
    }

    @Before

    @Test
    public void shouldAbleToSearchUser() throws Exception {
        when(request.requestBody()).thenReturn(Collections.singletonMap(SearchUserExecutor.SEARCH_TERM, "bford").toString());

        GoPluginApiResponse response = new SearchUserExecutor(request).execute();

        assertThat(response.responseCode(), is(200));
        List<User> users = new Gson().fromJson(response.responseBody(), new TypeToken<List<User>>() {
        }.getType());

        assertThat(users, contains(new User("bford", "Bob Ford", "bford@example.com")));
    }


    @Test
    public void shouldReturnEmptySearchResultIfUserNotExist() throws Exception {
        when(request.requestBody()).thenReturn(Collections.singletonMap(SearchUserExecutor.SEARCH_TERM, "jdoe").toString());

        GoPluginApiResponse response = new SearchUserExecutor(request).execute();

        assertThat(response.responseCode(), is(200));
        List<User> users = new Gson().fromJson(response.responseBody(), new TypeToken<List<User>>() {
        }.getType());

        assertTrue(users.isEmpty());
    }
}