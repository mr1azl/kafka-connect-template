package org.apache.kafka.connect.salesforce;

import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrea Patelli on 04/02/2016.
 */
public class SalesforceSourceConnector extends SourceConnector {
    public static final String EXAMPLE_CONFIG = "example.config";

    private String exampleConfig;

    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }

    @Override
    public void start(Map<String, String> props) {
        exampleConfig = props.get(EXAMPLE_CONFIG);
        if(exampleConfig == null || exampleConfig.isEmpty())
            throw new ConnectException("missing config");
    }

    @Override
    public Class<? extends Task> taskClass() {
        return SalesforceSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        ArrayList<Map<String, String>> configs = new ArrayList<>();
        for(int i = 0; i < maxTasks; i++) {
            Map<String, String> config = new HashMap<>();
            config.put(EXAMPLE_CONFIG, exampleConfig);
            configs.add(config);
        }
        return configs;
    }

    @Override
    public void stop() {

    }
}
