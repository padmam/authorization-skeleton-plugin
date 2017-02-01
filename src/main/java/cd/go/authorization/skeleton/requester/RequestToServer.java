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

public enum RequestToServer {
    REQUEST_ROLE_CONFIGURATION_FROM_SERVER(RequestToServer.Constants.AUTHORIZATION_PROCESSOR_PREFIX + ".get-role-config");

    private final String requestName;

    RequestToServer(String requestName) {
        this.requestName = requestName;
    }

    public String requestName() {
        return requestName;
    }

    private interface Constants {
        String AUTHORIZATION_PROCESSOR_PREFIX = "go.processor.authorization";
    }
}
