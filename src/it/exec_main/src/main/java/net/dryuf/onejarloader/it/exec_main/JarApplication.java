package net.dryuf.onejarloader.it.exec_main;

import net.dryuf.onejarloader.OneJarLoader;


public class JarApplication
{
	public static void main(String[] args) throws Exception
	{
		OneJarLoader cl = new OneJarLoader();
		cl.invokeMain(JarApplication.class.getPackage().getName() + ".Application", args);
	}
}
