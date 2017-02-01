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

package cd.go.authorization.skeleton;

import cd.go.authorization.skeleton.exception.ServerRequestFailedException;
import cd.go.authorization.skeleton.model.PluginProfile;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseTest {
    protected GoPluginApiRequest request;
    protected PluginProfile pluginProfile;

    @Before
    public void setup() throws ServerRequestFailedException {
        request = mock(GoPluginApiRequest.class);

        pluginProfile = mock(PluginProfile.class);
        when(pluginProfile.getUrl()).thenReturn("url");
        when(pluginProfile.getManagerDn()).thenReturn("manager-dn");
        when(pluginProfile.getPassword()).thenReturn("secret");
    }
}
