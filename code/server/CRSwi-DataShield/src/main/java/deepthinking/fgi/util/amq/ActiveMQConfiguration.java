package deepthinking.fgi.util.amq;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

/**
 * @description ActiveMQ消息队列配置类
 * @author WANGJIHONG
 * @date 2018年3月25日 下午10:52:26 
 * @Copyright 版权所有 (c) www.javalsj.com
 * @memo 无备注说明
 */
@Configuration
public class ActiveMQConfiguration {
    
    /** 
     * 在Queue模式中，对消息的监听需要对containerFactory进行配置
     */ 
    @Bean(ActiveMQQueueConst.BEAN_NAME_JMSLISTENERCONTAINERFACTORY)
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }
    
    /** 
     * 在Topic模式中，对消息的监听需要对containerFactory进行配置
     */ 
    @Bean(ActiveMQTopicConst.BEAN_NAME_JMSLISTENERCONTAINERFACTORY)
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
