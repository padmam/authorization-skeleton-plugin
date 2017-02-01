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
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.List;
import java.util.Map;

public class VerifyConnectionRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;
    private PluginProfile pluginProfile;

    public VerifyConnectionRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
        pluginProfile = PluginProfile.fromJSON(request.requestBody());
    }

    @Override
    public GoPluginApiResponse execute() {
        List<Map<String, String>> result = verifyConnection();
        return DefaultGoPluginApiResponse.success(GSON.toJson(result));
    }

    /**
     * @return List of errors,
     * where key is a field name and value is an error message
     */
    private List<Map<String, String>> verifyConnection() {
        // TODO: Implement me!
        throw new UnsupportedOperationException("Implement me!");
    }
}
