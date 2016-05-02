/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.utils.json.handler;

import net.markenwerk.utils.json.common.FailedJsonOperationException;
import net.markenwerk.utils.json.common.InvalidJsonNameException;
import net.markenwerk.utils.json.common.InvalidJsonValueException;

/**
 * An {@link IdleJsonHandler} is a {@link JsonHandler} with empty methods. It is
 * intended a base for custom {@link JsonHandler} implementations, that don't
 * need to implement all methods.
 * 
 * <p>
 * An {@link IdleJsonHandler} also provides methods to
 * {@link IdleJsonHandler#checkDouble(double) check} whether a double value is a
 * valid JSON number and to {@link IdleJsonHandler#checkString(String) check}
 * whether a {@link String} value is a valid JSON string.
 * 
 * @param <Result>
 *            The result type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public abstract class IdleJsonHandler<Result> implements JsonHandler<Result> {

	@Override
	public void onDocumentBegin() throws FailedJsonOperationException {
	}

	@Override
	public void onDocumentEnd() throws FailedJsonOperationException {
	}

	@Override
	public void onArrayBegin() throws FailedJsonOperationException {
	}

	@Override
	public void onArrayEnd() throws FailedJsonOperationException {
	}

	@Override
	public void onObjectBegin() throws FailedJsonOperationException {
	}

	@Override
	public void onObjectEnd() throws FailedJsonOperationException {
	}

	@Override
	public void onName(String name) throws FailedJsonOperationException {
		checkName(name);
	}

	/**
	 * Checks that a {@link String} value is a valid JSON name.
	 * 
	 * @param name
	 *            The name to be checked.
	 * @return Always {@literal true}, if no {@link InvalidJsonNameException} is
	 *         thrown.
	 * 
	 * @throws InvalidJsonNameException
	 *             If the given name is {@literal null}.
	 */
	public static final boolean checkName(String name) throws InvalidJsonNameException {
		if (null == name) {
			throw new InvalidJsonNameException("name is null");
		}
		return true;
	}

	@Override
	public void onNext() throws FailedJsonOperationException {
	}

	@Override
	public void onNull() throws FailedJsonOperationException {
	}

	@Override
	public void onBoolean(boolean value) throws FailedJsonOperationException {
	}

	@Override
	public void onLong(long value) throws FailedJsonOperationException {
	}

	@Override
	public void onDouble(double value) throws InvalidJsonValueException, FailedJsonOperationException {
		checkDouble(value);
	}

	/**
	 * Checks that a double value is a valid JSON number.
	 * 
	 * @param value
	 *            The value to be checked.
	 * @return Always {@literal true}, if no {@link InvalidJsonValueException}
	 *         is thrown.
	 * 
	 * @throws InvalidJsonValueException
	 *             If the given value is {@link Double#isInfinite(double)
	 *             infinite} or {@link Double#isNaN(double) not a number}.
	 */
	public static final boolean checkDouble(double value) throws InvalidJsonValueException {
		if (Double.isNaN(value)) {
			throw new InvalidJsonValueException("value is not a number");
		}
		if (Double.isInfinite(value)) {
			throw new InvalidJsonValueException("value is infinite");
		}
		return true;
	}

	@Override
	public void onString(String value) throws InvalidJsonValueException, FailedJsonOperationException {
		checkString(value);
	}

	/**
	 * Checks that a {@link String} value is a valid JSON string.
	 * 
	 * @param value
	 *            The value to be checked.
	 * @return Always {@literal true}, if no {@link InvalidJsonValueException}
	 *         is thrown.
	 * 
	 * @throws InvalidJsonValueException
	 *             If the given value is {@literal null}.
	 */
	public static final boolean checkString(String value) throws InvalidJsonValueException {
		if (null == value) {
			throw new InvalidJsonValueException("value is null");
		}
		return true;
	}

	@Override
	public Result getResult() throws FailedJsonOperationException {
		return null;
	}

}
