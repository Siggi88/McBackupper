package io.siggi.mcbackupper.util;

@FunctionalInterface
public interface FunctionWithThrowable<I, O, E extends Throwable> {

	public O apply(I i) throws E;
}
