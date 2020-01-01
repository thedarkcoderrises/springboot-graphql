package com.tdcr.graphql.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.metadata.JmxAttributeSource;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableMBeanExport
public class AMQConfig {

/*
    @Bean
    public JmxAttributeSource attributeSource(){
        return new AnnotationJmxAttributeSource();
    }


    @Bean
    public MBeanInfoAssembler assembler(JmxAttributeSource attributeSource){
        MetadataMBeanInfoAssembler assembler = new MetadataMBeanInfoAssembler();
        assembler.setAttributeSource(attributeSource);
        return assembler;
    }*/

    /*@Bean
    public MBeanExporter mbeanExporter() {
        MBeanExporter exporter = new AnnotationMBeanExporter();
        exporter.setAssembler(assembler);
        exporter.setAutodetect(true);
        return exporter;
    }

    @Bean
    public MBeanServerConnection amqConnection() throws IOException {
        Map<String, String[]> environment = null;
        JMXServiceURL url =
                new JMXServiceURL("service:jmx:rmi:///jndi/rmi://0.0.0.0:1099/jmxrmi");
        String[] credentials = new String[2];
        credentials[0] = "admin";
        credentials[1] = "activemq";
        environment = new HashMap<String, String[]>();
        environment.put(JMXConnector.CREDENTIALS, credentials);

        MBeanServerConnection conn = JMXConnectorFactory.connect(url,
                environment).getMBeanServerConnection();
        return conn;
    }
*/


}
