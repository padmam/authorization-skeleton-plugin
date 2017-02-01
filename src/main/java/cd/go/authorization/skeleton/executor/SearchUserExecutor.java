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

import cd.go.authorization.skeleton.model.PluginProfile;
import cd.go.authorization.skeleton.model.User;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.Map;
import java.util.Set;

import static cd.go.authorization.skeleton.util.Util.GSON;

public class SearchUserExecutor implements RequestExecutor {
    public static final String SEARCH_TERM = "search_term";

    private final GoPluginApiRequest request;

    public SearchUserExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {
        Map<String, String> requestParam = GSON.fromJson(request.requestBody(), Map.class);
        Map<String, PluginProfile> profiles = PluginProfile.fromJSONMap(request.requestBody());
        String searchTerm = requestParam.get(SEARCH_TERM);

        Set<User> users = searchUsers(searchTerm, profiles);

        return new DefaultGoPluginApiResponse(200, GSON.toJson(users));
    }

    Set<User> searchUsers(String searchTerm, Map<String, PluginProfile> profiles) {
        // TODO: Implement me!
        throw new UnsupportedOperationException("Implement me!");
    }

}
