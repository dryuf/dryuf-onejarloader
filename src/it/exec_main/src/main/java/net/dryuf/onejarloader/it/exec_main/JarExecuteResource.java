package net.dryuf.onejarloader.it.exec_main;

import net.dryuf.onejarloader.OneJarLoader;


public class JarExecuteResource
{
	public static void main(String[] args) throws Exception
	{
		OneJarLoader cl = new OneJarLoader();
		System.exit(cl.invokeStatic(JarExecuteResource.class.getPackage().getName() + ".ExecuteResource", "wrapper", new Class[]{ String[].class }, args));
	}
}
