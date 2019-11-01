/*
  Copyright 2019 James Dodson

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
 */
package com.github.jimdodson;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

class GeodeSinkConnectorConfig extends AbstractConfig {

  static final String REGION_CONFIG = "geode.region";
  private static final String REGION_DOC = "Geode region name";

  static final String GEODE_HOST_CONFIG = "geode.host";
  private static final String GEODE_HOST_DOC = "Geode host";

  static final String GEODE_PORT_CONFIG = "geode.port";
  private static final String GEODE_PORT_DOC = "Geode port";

  private GeodeSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  GeodeSinkConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  static ConfigDef conf() {
    return new ConfigDef()
            .define(REGION_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, REGION_DOC)
            .define(GEODE_HOST_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, GEODE_HOST_DOC)
            .define(GEODE_PORT_CONFIG, ConfigDef.Type.INT, ConfigDef.Importance.HIGH, GEODE_PORT_DOC)
            ;

  }

}