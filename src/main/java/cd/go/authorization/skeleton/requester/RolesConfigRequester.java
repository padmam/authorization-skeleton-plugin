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

import cd.go.authorization.skeleton.exception.ServerRequestFailedException;
import cd.go.authorization.skeleton.model.RoleConfig;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.request.DefaultGoApiRequest;
import com.thoughtworks.go.plugin.api.response.GoApiResponse;

import java.util.List;

import static cd.go.authorization.skeleton.Constants.API_VERSION;
import static cd.go.authorization.skeleton.Constants.PLUGIN_IDENTIFIER;

public class RolesConfigRequester implements  Requester<List<RoleConfig>> {

    private GoApplicationAccessor accessor;
    private String requestBody;

    public RolesConfigRequester(GoApplicationAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public List<RoleConfig> request() throws ServerRequestFailedException {
        DefaultGoApiRequest request = new DefaultGoApiRequest(RequestToServer.REQUEST_ROLE_CONFIGURATION_FROM_SERVER.requestName(), API_VERSION, PLUGIN_IDENTIFIER);
        request.setRequestBody(this.requestBody);
        GoApiResponse response = accessor.submit(request);

        if (response.responseCode() != 200) {
            throw ServerRequestFailedException.getPluginProfiles(response);
        }

        return RoleConfig.fromJSONList(response.responseBody());
    }

    @Override
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
