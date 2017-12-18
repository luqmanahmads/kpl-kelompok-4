package com.study.dwika.kplchat.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
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
                        Connection connection = factory.newConnection();
                        Channel channel = connection.createChannel();
                        channel.basicQos(1);
                        Envelope envelope = new Envelope(11, false, "user.1", "");
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        channel.queueBind("tmp-1", "user.1", "");
                        QueueingConsumer consumer = new QueueingConsumer(channel);
                        channel.basicConsume("tmp-1", false, consumer);

                        while (true){
                            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

                            final String message = new String(delivery.getBody());
                            Log.d("Debug","[r] " + message);

                            Messages messages = new Messages(0,0,message);
                            baseDataManager.saveMessages(messages);
                        }

                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e){
                        Log.d("debug", "Connection broken: " + e.getClass().getName());
                        try {
                            //sleep and then try again
                            Thread.sleep(4000);
//                        setupConnectionFactory();
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
            Connection conn = factory.newConnection();
        } catch (Exception el) {
            Log.d("Debug", "Connection " + el.toString());
            el.printStackTrace();
        }
    }

}
