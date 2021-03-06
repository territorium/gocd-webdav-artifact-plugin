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

package cd.go.artifact.handler;

import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import cd.go.artifact.RequestHandler;
import cd.go.artifact.util.Resources;

/**
 * The {@link GetViewHandler} is a message that the plugin should implement, to allow users to
 * configure artifact stores from the Artifact Stores View in GoCD.
 * 
 * The server will not provide a request body.
 */
public class GetViewHandler implements RequestHandler {

  private final String template;

  /**
   * Constructs an instance of {@link GetViewHandler}.
   *
   * @param template
   */
  public GetViewHandler(String template) {
    this.template = template;
  }

  /**
   * The plugin is expected to return status 200 if it can understand the request.
   * 
   * <pre>
   * {
   *  "template": "<div>some html</div>"
   *}
   * </pre>
   */
  @Override
  public final GoPluginApiResponse execute() {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    builder.add("template", Resources.asString(template));
    return DefaultGoPluginApiResponse.success(builder.build().toString());
  }
}
