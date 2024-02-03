package net.dryuf.onejarloader;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.testng.Assert.expectThrows;


public class OneJarLoaderTest
{
	@Test
	public void sneakyFutureGet_whenThrows_throwOriginal()
	{
		expectThrows(NumberFormatException.class, () ->
			OneJarLoader.sneakyFutureGet(CompletableFuture.supplyAsync(()
				-> { throw new NumberFormatException(); })
			)
		);
	}

	@Test
	public void sneakySupplier_whenThrows_throwOriginal()
	{
		expectThrows(IOException.class, () ->
			OneJarLoader.sneakySupplier(() -> { throw new IOException(); }).get()
		);
	}
}
