package com.tdcr.graphql.mbean;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.util.ArrayList;
import java.util.List;

//@Component
//@ManagedResource(objectName="com.tdcr:name=AMQ-Mbean",description = "AMQ Manager")
public class AMQMbean {

 /*   @Autowired
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

    @ManagedOperation
    public List<TabularData> browseAsTable() throws Exception {
        List<TabularData> msgStat = new ArrayList<>();
        ObjectName activeMQ = new ObjectName("org.apache.activemq:type=Broker,brokerName=D-AMQ");
        BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(amqConnection,
                activeMQ, BrokerViewMBean.class, true);
        for (ObjectName name : mbean.getQueues()) {
            QueueViewMBean queueMbean = (QueueViewMBean)
                    MBeanServerInvocationHandler.newProxyInstance(amqConnection, name,
                            QueueViewMBean.class, true);
             msgStat.add(queueMbean.browseAsTable());
        }
        return msgStat;
    }

    @ManagedOperation
    public List<String> getStats() throws Exception {
        List<String> stats = new ArrayList<>();
        String tabSpace= "  ";
        String gap= "             ";
        ObjectName activeMQ = new ObjectName("org.apache.activemq:type=Broker,brokerName=D-AMQ");
        BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(amqConnection,
                activeMQ, BrokerViewMBean.class, true);

        StringBuilder header = new StringBuilder("QueueName")
                .append(tabSpace)
                .append("QueueSize")
                .append(tabSpace)
                .append("ConsumerCount")
                .append(tabSpace)
                .append("EnqueueCount")
                .append(tabSpace)
                .append("DequeueCount")
                .append(tabSpace)
                .append("MemPercentUsage")
                .append(tabSpace)
                .append("CursorSize");
        stats.add(header.toString());
        for (ObjectName name : mbean.getQueues()) {
            QueueViewMBean queueMbean = (QueueViewMBean)
                    MBeanServerInvocationHandler.newProxyInstance(amqConnection, name,
                            QueueViewMBean.class, true);
            StringBuilder stat = new StringBuilder(queueMbean.getName())
                    .append(gap)
                    .append(queueMbean.getQueueSize())
                    .append(gap)
                    .append(queueMbean.getConsumerCount())
                    .append(gap)
                    .append(queueMbean.getEnqueueCount())
                    .append(gap)
                    .append(queueMbean.getDequeueCount())
                    .append(gap)
                    .append(queueMbean.getMemoryPercentUsage())
                    .append(gap)
                    .append(queueMbean.cursorSize());
            stats.add(stat.toString());
        }
        return stats;
    }


    @ManagedOperation
    public void moveMsgTo (String source,String dest) throws Exception{
        ObjectName srcON = new ObjectName("org.apache.activemq:type=Broker,brokerName=D-AMQ,destinationType=Queue,destinationName="+source);

        QueueViewMBean srcQueueMbean = (QueueViewMBean)
                MBeanServerInvocationHandler.newProxyInstance(amqConnection, srcON,
                        QueueViewMBean.class, true);
        for ( CompositeData cd:
        srcQueueMbean.browse()) {
            String msgId = (String) cd.get("JMSMessageID");
            srcQueueMbean.moveMessageTo(msgId,dest);
        }
    }*/
}
