package net.dryuf.onejarloader.it.exec_main;

import net.dryuf.onejarloader.OneJarLoader;


public class JarExecuteNative
{
	public static void main(String[] args) throws Exception
	{
		OneJarLoader cl = new OneJarLoader();
		System.exit(cl.invokeStatic(JarExecuteNative.class.getPackage().getName() + ".ExecuteNative", "wrapper", new Class[]{ String[].class }, args));
	}
}
