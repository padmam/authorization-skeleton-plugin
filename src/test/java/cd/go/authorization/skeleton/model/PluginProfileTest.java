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

package cd.go.authorization.skeleton.model;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PluginProfileTest {

    @Test
    public void shouldAbleToDeserializeToPluginProfile() throws Exception {
        String json = "{\n" +
                "    \"ManagerDN\": \"manger-cred\",\n" +
                "    \"Url\": \"url1\",\n" +
                "    \"Password\": \"secret\"\n" +
                "}";

        PluginProfile pluginProfile = PluginProfile.fromJSON(json);

        assertNotNull(pluginProfile);
        assertThat(pluginProfile.getUrl(), is("url1"));
        assertThat(pluginProfile.getManagerDn(), is("manger-cred"));
        assertThat(pluginProfile.getPassword(), is("secret"));
    }

    @Test
    public void shouldAbleToDeserializeToPluginProfilesMap() throws Exception {
        String json = "{\n" +
                "  \"plugin_profile_1\": {\n" +
                "    \"ManagerDN\": \"manger-cred\",\n" +
                "    \"Url\": \"url1\",\n" +
                "    \"Password\": \"secret\"\n" +
                "  },\n" +
                "  \"profile_profile_2\": {\n" +
                "    \"ManagerDN\": \"manger-cred\",\n" +
                "    \"Url\": \"url2\",\n" +
                "    \"Password\": \"secret\"\n" +
                "  }\n" +
                "}";

        Map<String, PluginProfile> pluginProfileMap = PluginProfile.fromJSONMap(json);
        PluginProfile pluginProfile1 = pluginProfileMap.get("plugin_profile_1");
        PluginProfile pluginProfile2 = pluginProfileMap.get("profile_profile_2");

        assertThat(pluginProfileMap.keySet(), contains("plugin_profile_1", "profile_profile_2"));

        assertThat(pluginProfile1.getUrl(), is("url1"));
        assertThat(pluginProfile1.getManagerDn(), is("manger-cred"));
        assertThat(pluginProfile1.getPassword(), is("secret"));

        assertThat(pluginProfile2.getUrl(), is("url2"));
        assertThat(pluginProfile2.getManagerDn(), is("manger-cred"));
        assertThat(pluginProfile2.getPassword(), is("secret"));
    }
}