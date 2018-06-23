package com.github.hanavan99.traincontroller.client;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.github.hanavan99.traincontroller.core.Switch;
import com.github.hanavan99.traincontroller.core.enums.CommandSet;

public class RabbitMQTestClient {

	public static void main(String[] args) throws Exception {
		RabbitMQCommandBase base = new RabbitMQCommandBase("192.168.1.147", "ttyS0", CommandSet.TMCC);
		base.start();
		base.switchThrowOut(new Switch(1, null));
		sleep(1000);
		base.switchThrowThrough(new Switch(1, null));
		base.stop();
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {

		}
	}

}
