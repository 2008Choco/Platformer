package me.choco.game.utils.general;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.println("----------------------------------------------------");
		System.err.println("Error Detected: ");
		System.err.println(e.toString());
		System.err.println("----------------------------------------------------");
		System.err.println("Thread:");
		System.err.println("    Name: " + t.getName());
		System.err.println("    Id: " + t.getId());
		System.err.println("    Is Running: " + t.isAlive());
		System.err.println("----------------------------------------------------");
		System.err.println("Stacktrace:");
		e.printStackTrace();
		System.err.println("----------------------------------------------------");
	}
}