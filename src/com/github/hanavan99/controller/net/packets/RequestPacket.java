package com.github.hanavan99.controller.net.packets;

import java.io.Serializable;

import com.github.hanavan99.controller.core.Resource;
import com.github.hanavan99.controller.core.User;
import com.github.hanavan99.controller.core.enums.RequestType;

public class RequestPacket extends Packet implements Serializable {

	private static final long serialVersionUID = -5622123607220265197L;

	private RequestType requestType;
	private Resource[] args;
	private User user;

	public RequestPacket(RequestType requestType, Resource[] args, User user) {
		this.requestType = requestType;
		this.args = args;
		this.user = user;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public Resource[] getArgs() {
		return args;
	}

	public User getUser() {
		return user;
	}

}
