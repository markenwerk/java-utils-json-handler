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

import net.markenwerk.utils.json.common.JsonException;
import net.markenwerk.utils.json.common.JsonIndexException;
import net.markenwerk.utils.json.common.JsonValueException;

/**
 * A {@link JsonHandler} provides a collection of callback methods that can be
 * called for a sequence of events that describe a JSON document. A
 * {@link JsonHandler} can calculate a result for the described JSON document.
 * 
 * @param <Result>
 *            The result type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public interface JsonHandler<Result> {

	/**
	 * Called when the parsing of a JSON document started.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onDocumentBegin() throws JsonException;

	/**
	 * Called when the parsing of a JSON document ended.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onDocumentEnd() throws JsonException;

	/**
	 * Called when the parsing of a JSON array started. A corresponding call for
	 * the {@link JsonHandler#onArrayEnd() end} must eventually be called.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onArrayBegin() throws JsonException;

	/**
	 * Called when the parsing of a JSON array ended.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onArrayEnd() throws JsonException;

	/**
	 * Called when the parsing of a JSON object started. A corresponding call
	 * for the {@link JsonHandler#onObjectEnd() end} must eventually be called.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onObjectBegin() throws JsonException;

	/**
	 * Called when the parsing of a JSON object ended.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onObjectEnd() throws JsonException;

	/**
	 * Called when the parsing of a JSON object encountered the name for the
	 * next JSON value.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @param name
	 *            The name for the next JSON value.
	 * 
	 * @throws JsonIndexException
	 *             If the given value is {@literal null}.
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onName(String name) throws JsonIndexException, JsonException;

	/**
	 * Called when the parsing of a JSON array or JSON object encountered
	 * another element.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onNext() throws JsonException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON null.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onNull() throws JsonException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON boolean.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @param value
	 *            The value of the JSON boolean.
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onBoolean(boolean value) throws JsonException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON number, that
	 * represents an integer value.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @param value
	 *            The value of the JSON number.
	 * 
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onLong(long value) throws JsonException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON number, that
	 * represents a decimal value.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @param value
	 *            The value of the JSON number.
	 * 
	 * @throws JsonValueException
	 *             If the given value is {@link Double#isNaN(double) not a
	 *             number} or {@link Double#isInfinite(double) infinite}.
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onDouble(double value) throws JsonValueException, JsonException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON string.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @param value
	 *            The value of the JSON string.
	 * 
	 * @throws JsonValueException
	 *             If the given value is {@literal null}.
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public void onString(String value) throws JsonValueException, JsonException;

	/**
	 * Returns the calculated result.
	 * 
	 * <p>
	 * Implementers should catch any exception that isn't a
	 * {@link JsonException} and wrap them in an appropriate
	 * {@link JsonException} (e.g. a {@link JsonHandlingException}).
	 * 
	 * @return The calculated result.
	 * 
	 * @throws JsonException
	 *             If the handling failed.
	 */
	public Result getResult() throws JsonException;

}
