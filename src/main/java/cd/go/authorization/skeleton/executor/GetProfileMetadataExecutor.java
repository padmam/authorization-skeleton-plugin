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

import cd.go.authorization.skeleton.model.Metadata;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.ArrayList;
import java.util.List;

import static cd.go.authorization.skeleton.util.Util.GSON;

// TODO: Provide metadata of your plugin profile
public class GetProfileMetadataExecutor implements RequestExecutor {
    static final List<Metadata> FIELDS = new ArrayList<>();

    private static final Metadata URL = new Metadata("Url", true, false);
    private static final Metadata MANAGER_DN = new Metadata("ManagerDN", true, false);
    private static final Metadata PASSWORD = new Metadata("Password", true, true);

    static {
        FIELDS.add(URL);
        FIELDS.add(MANAGER_DN);
        FIELDS.add(PASSWORD);
    }

    public GoPluginApiResponse execute() throws Exception {
        return new DefaultGoPluginApiResponse(200, GSON.toJson(FIELDS));
    }
}
