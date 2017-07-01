package com.hps.july.terrabyte.imp.processor;

import com.hps.july.core.process.Process;
import com.hps.july.terrabyte.imp.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vglushkov
 * Date: 30.11.2005
 * Time: 17:06:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractProcessor extends Process implements Processor {

	protected int port;
	protected String host;
	protected ArrayList errors = new ArrayList();

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List infoList() {
		return errors;
	}

	public void addInfo(Info info) {
		errors.add(info);
	}

	public boolean isValid(String s) {
		return (s != null && s.trim().length() > 0);
	}
}
