package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;

import java.util.List;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:22:30
 */
public interface Processor {

	public void execute(Command command) throws LoaderException, BaseException;

	public void setHost(String host);
	public void setPort(int port);
	public String getHost();
	public int getPort();

	public List infoList();
}
