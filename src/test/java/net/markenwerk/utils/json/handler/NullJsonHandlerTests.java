package net.markenwerk.utils.json.handler;

import org.junit.Assert;
import org.junit.Test;

import net.markenwerk.utils.json.common.InvalidJsonValueException;

@SuppressWarnings("javadoc")
public class NullJsonHandlerTests {

	@Test
	public void onNull() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onNull();
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onBoolean_true() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onBoolean(true);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onBoolean_false() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onBoolean(false);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onLong_zero() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onLong(0);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onLong_positive() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onLong(Long.MAX_VALUE);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onLong_negative() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onLong(Long.MIN_VALUE);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test(expected = InvalidJsonValueException.class)
	public void onDouble_infinite() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onDouble(Double.POSITIVE_INFINITY);
		handler.onDocumentEnd();

	}

	@Test(expected = InvalidJsonValueException.class)
	public void onDouble_notANumber() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onDouble(Double.NaN);
		handler.onDocumentEnd();

	}

	@Test
	public void onDouble_zero() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onDouble(0);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onDouble_positive() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onDouble(Double.MAX_VALUE);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onDouble_negative() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onDouble(Double.MIN_VALUE);
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test(expected = InvalidJsonValueException.class)
	public void onString_null() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onString(null);
		handler.onDocumentEnd();

	}

	@Test
	public void onString_empty() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onString("");
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onString_nonEmpty() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onString("foobar");
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onArray_empty() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onArrayBegin();
		handler.onArrayEnd();
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onArray_nonEmpty() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onArrayBegin();
		handler.onNull();
		handler.onArrayEnd();
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

	@Test
	public void onObject_empty() {

		JsonHandler<Void> handler = new NullJsonHandler();

		handler.onDocumentBegin();
		handler.onObjectBegin();
		handler.onObjectEnd();
		handler.onDocumentEnd();

		Void result = handler.getResult();

		Assert.assertNull(result);

	}

}
