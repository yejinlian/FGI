package deepthinking.fgi.util.amq;

public class ActiveMQTopicConst {
	 /** 
     * 在Topic模式中，对消息的监听需要对containerFactory进行配置，工厂标识
     */ 
    public static final String BEAN_NAME_JMSLISTENERCONTAINERFACTORY = "topicJmsListenerContainerFactory";    
    
    /**
     * 主题消息标识_WebSocket的系统公告
     */
    public static final String TOPIC_NAME_WEBSOCKET_SYSTEM_NOTICE = "topic.websocket.system.notice";
}
