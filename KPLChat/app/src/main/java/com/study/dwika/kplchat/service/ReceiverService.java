package com.study.dwika.kplchat.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.model.Messages;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by A.I on 17/12/2017.
 */

public class ReceiverService extends Service {

    private PowerManager.WakeLock wakeLock;
    private ConnectionFactory factory;
    private BaseDataManager baseDataManager;
    private Thread listenThread;
    private Messages messages;
    private Gson gson;
    private Connection connection;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager pm = (PowerManager) getSystemService(this.POWER_SERVICE);
        factory = new ConnectionFactory();
        Log.d("Debug", "Service Created");

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(this), new SharedPreferenceHelper(this));
        setupConnectionFactory();

        messages = new Messages();
        gson = new Gson();

        listenMessage();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        wakeLock.release();
    }

    public void listenMessage(){

        listenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                    try{
                        connection = factory.newConnection();
                        final Channel channel = connection.createChannel();
                        channel.basicQos(1);

                        channel.queueBind("tmp-" + baseDataManager.getId(), "user." + baseDataManager.getId(), "");
//
                        channel.basicConsume("tmp-" + baseDataManager.getId(), false, new Consumer() {
                            @Override
                            public void handleConsumeOk(String consumerTag) {

                            }

                            @Override
                            public void handleCancelOk(String consumerTag) {

                            }

                            @Override
                            public void handleCancel(String consumerTag) throws IOException {

                            }

                            @Override
                            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

                            }

                            @Override
                            public void handleRecoverOk(String consumerTag) {

                            }

                            @Override
                            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                                String routingKey = envelope.getRoutingKey();
                                String contentType = properties.getContentType();
                                long deliveryTag = envelope.getDeliveryTag();

                                final String incomingChat = new String(body);

                                Log.d("Debug", "Incoming chat " + incomingChat);

                                messages = gson.fromJson(incomingChat, Messages.class);

                                baseDataManager.saveMessages(messages);

                                channel.basicAck(deliveryTag, false);
                                connection.close();
                            }
                        });
                        connection.close();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//
                    catch (Exception e){
                        Log.d("debug", "Connection broken: " + e.getClass().getName());
                        try {
                            //sleep and then try again
                            Thread.sleep(4000);
                        } catch (InterruptedException el) {
                            el.printStackTrace();
                        }
                    }
                }

            }
            });
        listenThread.start();
    }

    private void setupConnectionFactory() {

        try {
            factory.setAutomaticRecoveryEnabled(true);
            factory.setUsername("chat-kpl");
            factory.setPassword("chat-kpl");
            factory.setVirtualHost("/");
            factory.setHost("35.198.231.167");
            factory.setPort(5672);
//            Connection conn = factory.newConnection();
        } catch (Exception el) {
            Log.d("Debug", "Connection " + el.toString());
            el.printStackTrace();
        }
    }

}
