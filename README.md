# Kafka Connect Sink Connector for Apache Geode
Sink connector for putting Kafka messages into a Geode region.

#Local Env Test Steps:
### Run Geode in docker

    cd docker
    docker-compose up

### Build the connector

    ./gradlew clean build jar copyToLib

### Install connector artifacts

    mkdir $CONFLUENT_HOME/share/java/geode-sink
    cp build/libs/* $CONFLUENT_HOME/share/java/geode-sink

### Create `geode-sink.properties` containing the following

    topics=geode-sink
    key.converter=org.apache.kafka.connect.storage.StringConverter
    value.converter=org.apache.kafka.connect.storage.StringConverter
    key.converter.schemas.enable=false
    value.converter.schemas.enable=false
    tasks.max=1
    connector.class=GeodeSinkConnector
    confluent.topic.bootstrap.servers=localhost:9092
    confluent.topic.replication.factor=1
    name=geode-sink
    geode.region=hello
    geode.host=localhost
    geode.port=10334
    
### Start CP and run the connector
    
    confluent start
    confluent load geode-sink -d geode-sink.properties

### Send some data

    kafka-console-producer --broker-list localhost:9092 --topic geode-sink --property parse.key=true --property key.separator=,
    111,msg 1
    222,msg 2
    333,msg 3