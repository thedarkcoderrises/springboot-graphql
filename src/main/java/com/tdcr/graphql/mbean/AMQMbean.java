package com.tdcr.graphql.mbean;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.*;

@Component
@ManagedResource
public class AMQMbean {

    @Autowired
    private MBeanServerConnection amqConnection;

    @ManagedAttribute
    public String getUpTime()throws Exception  {
        ObjectName activeMQ = new ObjectName("org.apache.activemq:type=Broker,brokerName=D-AMQ");
        BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(amqConnection,
                activeMQ, BrokerViewMBean.class, true);
        return mbean.getUptime();
    }

    @ManagedOperation
    public String addQueue(String queueName) throws Exception {
        ObjectName activeMQ = new ObjectName("org.apache.activemq:type=Broker,brokerName=D-AMQ");
        Object[] params = {queueName};
        String[] sig = {"java.lang.String"};
        BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(amqConnection,
                activeMQ, BrokerViewMBean.class, true);
        amqConnection.invoke(activeMQ,"addQueue",params,sig);
        for (ObjectName name : mbean.getQueues()) {
            QueueViewMBean queueMbean = (QueueViewMBean)
                    MBeanServerInvocationHandler.newProxyInstance(amqConnection, name,
                            QueueViewMBean.class, true);
            return queueMbean.getName();
        }
        return null;
    }
}
