package net.dryuf.onejarloader.it.exec_main;

import net.dryuf.onejarloader.OneJarLoader;


public class JarExecuteStatic
{
	public static void main(String[] args) throws Exception
	{
		OneJarLoader cl = new OneJarLoader();
		System.exit(cl.invokeStatic(JarExecuteStatic.class.getPackage().getName() + ".ExecuteStatic", "wrapper", new Class[]{ String[].class }, args));
	}
}
