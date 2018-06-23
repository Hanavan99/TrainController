package com.github.hanavan99.traincontroller.server;

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;

public class Main {
    public static void main(String[] args) {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().build();
        EmbeddedRabbitMq rabbitMq = new EmbeddedRabbitMq(config);
        rabbitMq.start();
        try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
            rabbitMq.stop();
		}
    }
}
