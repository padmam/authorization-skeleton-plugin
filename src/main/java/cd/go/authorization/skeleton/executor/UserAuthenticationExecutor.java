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

import cd.go.authorization.skeleton.model.Authentication;
import cd.go.authorization.skeleton.model.AuthenticationResponse;
import cd.go.authorization.skeleton.model.PluginProfile;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse.SUCCESS_RESPONSE_CODE;

public class UserAuthenticationExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;
    private GoApplicationAccessor accessor;

    public UserAuthenticationExecutor(GoPluginApiRequest request, GoApplicationAccessor accessor) {
        this.request = request;
        this.accessor = accessor;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {
        Authentication authentication = Authentication.fromJSON(request.requestBody());
        Map<String, PluginProfile> profiles = PluginProfile.fromJSONMap(request.requestBody());
        AuthenticationResponse authenticationResponse = authenticate(authentication, profiles);

        Map<String, Object> userMap = new HashMap<>();
        if (authenticationResponse != null) {
            userMap.put("user", authenticationResponse.getUser());
            userMap.put("roles", authorizeUser(authenticationResponse));
        }

        DefaultGoPluginApiResponse response = new DefaultGoPluginApiResponse(SUCCESS_RESPONSE_CODE, GSON.toJson(userMap));
        return response;
    }

    private AuthenticationResponse authenticate(Authentication authentication, Map<String, PluginProfile> profiles) {
        for (Map.Entry<String, PluginProfile> entry : profiles.entrySet()) {
            AuthenticationResponse authenticationResponse = authenticateWithProfile(authentication, entry.getKey(), entry.getValue());
            if (authenticationResponse != null)
                return authenticationResponse;
        }
        return null;
    }

    private AuthenticationResponse authenticateWithProfile(Authentication authentication, String profileId, PluginProfile profile) {
        // TODO: Implement me!
        throw new UnsupportedOperationException("Implement me!");
    }

    private List<String> authorizeUser(AuthenticationResponse authenticationResponse) {
        // TODO: Implement me!
        throw new UnsupportedOperationException("Implement me!");
    }
}
