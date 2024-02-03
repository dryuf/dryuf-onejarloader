package net.dryuf.onejarloader.it.exec_main;

import net.dryuf.onejarloader.OneJarLoader;


public class JarExecuteCallable
{
	public static void main(String[] args) throws Exception
	{
		OneJarLoader cl = new OneJarLoader();
		System.exit(cl.invokeCallable(() -> ExecuteCallable.wrapper(args)));
	}
}
