/*
 * Copyright 2018 ThoughtWorks, Inc.
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

package cd.go.artifact.webdav.annotation;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cd.go.artifact.webdav.utils.Util;

public interface Validatable {

  default String toJSON() {
    return Util.GSON.toJson(this);
  }

  default Map<String, String> toProperties() {
    return Util.GSON.fromJson(toJSON(), new TypeToken<Map<String, String>>() {}.getType());
  }

  default List<ValidationError> validateAllFieldsAsRequired() {
    return toProperties().entrySet().stream()
        .filter(entry -> StringUtils.isBlank(entry.getValue()) && !entry.getKey().equals("Destination")
            && !entry.getKey().equals("Username") && !entry.getKey().equals("Password"))
        .map(entry -> new ValidationError(entry.getKey(), entry.getKey() + " must not be blank."))
        .collect(Collectors.toList());
  }

  default ValidationResult validate() {
    return new ValidationResult(validateAllFieldsAsRequired());
  }
}
