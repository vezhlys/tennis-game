package com.github.vezhlys.game.timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.github.vezhlys.game.exceptions.NoInputException;

public class InputTimer implements Callable<String> {
	private final long timeout;

	public InputTimer(final long timeout) {
		this.timeout = timeout;
	}

	@Override
	public String call() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (!br.ready()) {
				Thread.sleep(200);
			}
			return br.readLine();
		} catch (IOException | InterruptedException e1) {
		}
		return null;
	}

	public String readLine() throws InterruptedException, ExecutionException {
		ExecutorService ex = Executors.newSingleThreadExecutor();
		Future<String> result = ex.submit(this);
		try {
			return result.get(timeout, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			result.cancel(true);
			throw new NoInputException();
		} finally {
			ex.shutdownNow();
		}
	}
}
