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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static cd.go.authorization.skeleton.util.Util.GSON;

public class RoleConfig {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("configuration")
    private Map<String, String> configuration;

    public static List<RoleConfig> fromJSONList(String json) {
        Type type = new TypeToken<List<RoleConfig>>() {
        }.getType();
        return GSON.fromJson(json, type);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleConfig that = (RoleConfig) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return configuration != null ? configuration.equals(that.configuration) : that.configuration == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }
}
