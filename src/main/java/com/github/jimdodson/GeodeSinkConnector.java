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

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeodeSinkConnector extends SinkConnector {
  private static Logger log = LoggerFactory.getLogger(GeodeSinkConnector.class);
  private Map<String, String> configProps;

  @Override
  public String version() {
    log.debug("GeodeSinkConnector.version()");
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    log.debug("GeodeSinkConnector.start()");
    configProps = map;
  }

  @Override
  public Class<? extends Task> taskClass() {
    log.debug("GeodeSinkConnector.taskClass()");
    return GeodeSinkTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {
    log.debug("GeodeSinkConnector.taskConfigs()");
    final List<Map<String, String>> configs = new ArrayList<>(maxTasks);
    for (int i = 0; i < maxTasks; ++i) {
      configs.add(configProps);
    }
    return configs;
  }

  @Override
  public void stop() {
    log.debug("GeodeSinkConnector.stop()");
  }

  @Override
  public ConfigDef config() {
    log.debug("GeodeSinkConnector.config()");
    return GeodeSinkConnectorConfig.conf();
  }
}