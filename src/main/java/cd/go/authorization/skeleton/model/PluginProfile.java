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

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import static cd.go.authorization.skeleton.util.Util.GSON;

//TODO: Change this , Profile must contains all fields which are declared in `GetProfileMetadataExecutor.java`
public class PluginProfile {
    @Expose
    @SerializedName("Url")
    private String url;
    @Expose
    @SerializedName("ManagerDN")
    private String managerDn;
    @Expose
    @SerializedName("Password")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getManagerDn() {
        return managerDn;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginProfile)) return false;

        PluginProfile that = (PluginProfile) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (managerDn != null ? !managerDn.equals(that.managerDn) : that.managerDn != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (managerDn != null ? managerDn.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public static PluginProfile fromJSON(String json) {
        return GSON.fromJson(json, PluginProfile.class);
    }

    public static Map<String, PluginProfile> fromJSONMap(String json) {
        JsonObject jsonObject = GSON.fromJson(json, JsonObject.class);
        Type type = new TypeToken<Map<String, PluginProfile>>() {
        }.getType();
        return GSON.fromJson(jsonObject.get("profiles").toString(), type);
    }
}
