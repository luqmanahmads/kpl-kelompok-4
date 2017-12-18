package com.study.dwika.kplchat.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.study.dwika.kplchat.model.Messages;

/**
 * Created by A.I on 18/12/2017.
 */

public class RabbitMQSender {

    private ConnectionFactory factory;
    private Thread publishThread;

    public RabbitMQSender() {
        super();
        factory = new ConnectionFactory();
        setupConnectionFactory();
    }

    public void publishToAMQP()
    {
//        setupConnectionFactory();
        Log.d("debug", "publish");
        publishThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Connection connection = factory.newConnection();
                    Channel ch = connection.createChannel();
                    ch.confirmSelect();
                    
//                    ch.exchangeDeclare("user.1","fanout",true,false, null);
                    Messages messages = new Messages(1, "android");
                    Gson gson = new Gson();
                    String json = gson.toJson(messages);
                    Log.d("debug", "json " + json);
                    try{
                        ch.basicPublish("conversation.outgoing", "1", MessageProperties.PERSISTENT_BASIC, json.getBytes());
                        Log.d("debug", "[s] " + messages.toString());
                        ch.waitForConfirmsOrDie();
                    } catch (Exception e){
                        Log.d("debug","[f] " + messages);
                        throw e;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.d("debug", "Connection broken: " + e.getClass().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
//                            el.printStackTrace();
                    }
                }

            }
        });
        publishThread.start();
    }

    private void setupConnectionFactory() {

        try {

            factory.setUsername("chat-kpl");
            factory.setPassword("chat-kpl");
            factory.setVirtualHost("/");
            factory.setHost("35.198.231.167");
            factory.setPort(5672);
            factory.setAutomaticRecoveryEnabled(true);

            factory.setConnectionTimeout( 30000 );

            factory.setAutomaticRecoveryEnabled( true );

            factory.setNetworkRecoveryInterval( 5000 );

            factory.setTopologyRecoveryEnabled( true );

        } catch (Exception el) {
            Log.d("Debug", "Connection " + el.toString());
            el.printStackTrace();
        }
    }

}
