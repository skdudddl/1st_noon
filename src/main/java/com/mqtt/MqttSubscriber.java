package com.mqtt;



import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;



public class MqttSubscriber implements MqttCallback {

    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId();
    private static final String TOPIC_FILTER = "topic";
    private static final String MQTT_USERNAME = "username";
    private static final String MQTT_PASSWORD = "pwd";

    public static void main(String[] args) {
        try {
            // MQTT 연결 옵션 설정
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(MQTT_USERNAME);
            options.setPassword(MQTT_PASSWORD.toCharArray());

            // MQTT 클라이언트 생성
            MqttClient client = new MqttClient(BROKER_URL, MQTT_CLIENT_ID);
            client.setCallback(new MqttSubscriber());

            // 연결
            client.connect(options);

            // 구독
            client.subscribe(TOPIC_FILTER);

            System.out.println("MQTT Subscriber가 시작되었습니다. 토픽: " + TOPIC_FILTER);


        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("연결이 끊겼습니다.");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("=====================메세지 도착=================");
        System.out.println("Topic: " + topic);
        System.out.println("Message: " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("메시지 전달 완료.");
    }
}
