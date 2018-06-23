package com.github.hanavan99.controller.net.packets;

import java.io.Serializable;

import com.github.hanavan99.controller.core.Resource;
import com.github.hanavan99.controller.core.enums.RequestType;

public class ResponsePacket extends Packet implements Serializable {

	private static final long serialVersionUID = -12834691872649812L;

	private RequestType requestType;
	private Resource[] args;

	public ResponsePacket(RequestType requestType, Resource[] args) {
		this.requestType = requestType;
		this.args = args;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public Resource[] getArgs() {
		return args;
	}

}
