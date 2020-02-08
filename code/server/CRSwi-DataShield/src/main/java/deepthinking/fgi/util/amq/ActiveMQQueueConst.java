package deepthinking.fgi.util.amq;

public class ActiveMQQueueConst {
	  /** 
     * 在Queue模式中，对消息的监听需要对containerFactory进行配置，工厂标识
     */ 
    public static final String BEAN_NAME_JMSLISTENERCONTAINERFACTORY = "queueJmsListenerContainerFactory";    
    
    /**
     * 队列消息标识_WebSocket的Java老司机聊天室
     */
    public static final String QUEUE_NAME_WEBSOCKET_CHATROOM_JAVALSJ = "queue.websocket.chatroom.javalsj";
}
