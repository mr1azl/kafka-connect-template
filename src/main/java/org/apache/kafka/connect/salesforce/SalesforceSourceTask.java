package org.apache.kafka.connect.salesforce;

import org.apache.kafka.common.errors.InterruptException;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Andrea Patelli on 04/02/2016.
 */
public class SalesforceSourceTask extends SourceTask {
    private final static Logger log = LoggerFactory.getLogger(SalesforceSourceTask.class);

    private String exampleConfig;

    private Map<String, Object> offsets = new HashMap<>(0);

    @Override
    public String version() {
        return new SalesforceSourceConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
        exampleConfig = props.get(SalesforceSourceConnector.EXAMPLE_CONFIG);
        if(exampleConfig == null)
            throw new ConnectException("config null");

        loadOffsets("connectorname", "partitionname");
    }

    @Override
    public List<SourceRecord> poll() throws InterruptException {
        List<SourceRecord> records = new ArrayList<>();
        return records;
    }

    @Override
    public void stop() {
    }

    private void loadOffsets(String connector, String partition) {
        offsets.putAll(context.offsetStorageReader().offset(Collections.singletonMap(connector, partition)));
    }
}
