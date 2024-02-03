package net.dryuf.onejarloader.it.exec_main;


import org.apache.commons.io.IOUtils;

import java.io.InputStream;


public class ExecuteResource
{
	public static int wrapper(String[] args) throws Exception
	{
		try (InputStream resource = ExecuteResource.class.getResourceAsStream("TheResource.txt")) {
			IOUtils.copy(resource, System.out);
		}
		return 44;
	}
}
