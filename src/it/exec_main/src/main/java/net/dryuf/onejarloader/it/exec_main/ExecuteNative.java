package net.dryuf.onejarloader.it.exec_main;


public class ExecuteNative
{
	public static int wrapper(String[] args) throws Exception
	{
		System.loadLibrary("zstd-jni-1.5.5-11");
		System.out.println("I_DID_SUCCEED_NATIVE");
		return 44;
	}
}
