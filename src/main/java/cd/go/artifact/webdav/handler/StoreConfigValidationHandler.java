/*
 * Copyright 2018 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cd.go.artifact.webdav.handler;

import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import cd.go.artifact.RequestHandler;
import cd.go.artifact.webdav.annotation.ValidationResult;
import cd.go.artifact.webdav.model.WebDavStoreConfig;

public class StoreConfigValidationHandler implements RequestHandler {

  private final WebDavStoreConfig storeConfig;

  public StoreConfigValidationHandler(GoPluginApiRequest request) {
    storeConfig = WebDavStoreConfig.fromJSON(request.requestBody());
  }

  @Override
  public GoPluginApiResponse execute() {
    ValidationResult result = storeConfig.validate();
    return DefaultGoPluginApiResponse.success(result.toJSON());
  }
}
