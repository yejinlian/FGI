package deepthinking.fgi.util.amq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description ActiveMQ主题消息订阅者
 * @author WANGJIHONG
 * @date 2018年3月25日 下午11:22:50
 * @Copyright 版权所有 (c) www.javalsj.com
 * @memo 无备注说明
 */
@Component
public class ActiveMQTopicSubscriber {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMQTopicSubscriber.class);

    @JmsListener(destination = ActiveMQTopicConst.TOPIC_NAME_WEBSOCKET_SYSTEM_NOTICE, containerFactory = ActiveMQTopicConst.BEAN_NAME_JMSLISTENERCONTAINERFACTORY)
    public void subscribeTopicWebsocketSystemNoticeMsg(String message) {
        logger.info("消费了一条主题{}消息{}。", ActiveMQTopicConst.TOPIC_NAME_WEBSOCKET_SYSTEM_NOTICE, message);
    }
}
