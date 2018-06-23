package controller.net.packets;

import java.io.Serializable;

import controller.core.Resource;
import controller.core.User;
import controller.core.enums.RequestType;

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
