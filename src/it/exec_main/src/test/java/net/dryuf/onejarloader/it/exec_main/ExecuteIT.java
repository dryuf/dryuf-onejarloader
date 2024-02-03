package net.dryuf.onejarloader.it.exec_main;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;


public class ExecuteIT
{
	@Test
	public void executeMain() throws Exception
	{
		executeGeneric(new String[]{ "java", "-jar", "target/dryuf-onejarloader-it-exec_main.jar" }, "I_DID_SUCCEED_MAIN\n");
	}

	@Test
	public void executeStatic() throws Exception
	{
		executeGeneric(new String[]{ "java", "-cp", "target/dryuf-onejarloader-it-exec_main.jar", "net.dryuf.onejarloader.it.exec_main.JarExecuteStatic" }, "I_DID_SUCCEED_ANY\n");
	}

	@Ignore("This does not work as the ClassLoader comes from wrapping class instead of context")
	@Test
	public void executeCallable() throws Exception
	{
		executeGeneric(new String[]{ "java", "-cp", "target/dryuf-onejarloader-it-exec_main.jar", "net.dryuf.onejarloader.it.exec_main.JarExecuteCallable" }, "I_DID_SUCCEED_CALLABLE\n");
	}

	@Test
	public void executeResource() throws Exception
	{
		executeGeneric(new String[]{ "java", "-cp", "target/dryuf-onejarloader-it-exec_main.jar", "net.dryuf.onejarloader.it.exec_main.JarExecuteResource" }, "I_DID_SUCCEED_RESOURCE\n");
	}

	public void executeGeneric(String[] args, String expected) throws Exception
	{
		Process process = new ProcessBuilder(args)
			.redirectOutput(ProcessBuilder.Redirect.PIPE)
			.redirectError(ProcessBuilder.Redirect.INHERIT)
			.start();

		String output = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

		assertEquals(process.waitFor(), 44);
		assertEquals(output, expected);
	}
}
