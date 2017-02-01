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

import java.util.*;


public class FieldValidator {
    private final List<Metadata> fields;

    public FieldValidator(List<Metadata> fields) {
        this.fields = fields;
    }

    public List<Map<String, String>> validate(Map<String, String> properties) {
        List<Map<String, String>> result = new ArrayList<>();
        List<String> knownFields = new ArrayList<>();

        for (Metadata field : fields) {
            knownFields.add(field.getKey());
            Map<String, String> validationError = field.validate(properties.get(field.getKey()));

            if (!validationError.isEmpty()) {
                result.add(validationError);
            }
        }


        Set<String> set = new HashSet<>(properties.keySet());
        set.removeAll(knownFields);

        if (!set.isEmpty()) {
            for (String key : set) {
                LinkedHashMap<String, String> validationError = new LinkedHashMap<>();
                validationError.put("key", key);
                validationError.put("message", "Is an unknown property");
                result.add(validationError);
            }
        }
        return result;
    }

}
