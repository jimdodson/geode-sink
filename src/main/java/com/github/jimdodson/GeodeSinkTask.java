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

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

import static com.github.jimdodson.GeodeSinkConnectorConfig.*;

public class GeodeSinkTask extends SinkTask {
  private static Logger log = LoggerFactory.getLogger(GeodeSinkTask.class);
  private Region<String, String> region;

  @Override
  public String version() {
    log.info("GeodeSinkTask.version()");
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    log.debug("GeodeSinkTask.start()");
    GeodeSinkConnectorConfig config = new GeodeSinkConnectorConfig(map);
    if (region == null) {
      ClientCache cache = new ClientCacheFactory().addPoolLocator(config.getString(GEODE_HOST_CONFIG), config.getInt(GEODE_PORT_CONFIG)).create();
      region = cache.<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create(config.getString(REGION_CONFIG));
    }
  }

  @Override
  public void put(Collection<SinkRecord> collection) {
    log.debug("GeodeSinkTask.put()");
    for (SinkRecord sinkRecord : collection) {
      log.trace("sink record : " + sinkRecord.key() + " " + sinkRecord.value());
      region.put(sinkRecord.key().toString(), sinkRecord.value().toString());
    }

  }

  @Override
  public void flush(Map<TopicPartition, OffsetAndMetadata> map) {
    log.debug("GeodeSinkTask.flush()");
  }

  @Override
  public void stop() {
    log.debug("GeodeSinkTask.stop()");
  }

}