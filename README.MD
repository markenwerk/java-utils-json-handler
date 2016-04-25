# Common handler for JSON libraries

[![Build Status](https://travis-ci.org/markenwerk/java-utils-json-handler.svg?branch=master)](https://travis-ci.org/markenwerk/java-utils-json-handler)
[![Coverage Status](https://coveralls.io/repos/github/markenwerk/java-utils-json-handler/badge.svg?branch=master)](https://coveralls.io/github/markenwerk/java-utils-json-handler?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/571e4392fcd19a00415b27a7/badge.svg)](https://www.versioneye.com/user/projects/571e4392fcd19a00415b27a7)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler)
[![Issues](https://img.shields.io/github/issues/markenwerk/java-utils-json-handler.svg)](https://github.com/markenwerk/java-utils-json-handler/issues)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-utils-json-handler/blob/master/LICENSE)

## Overview

This library provides a common handler interface to describe JSON documents for other JSON processing libraries.

Consult the [documentation](http://markenwerk.github.io/java-utils-json-handler/javadoc/index.html) and the [usage description](#usage) for further information:

- [Handling JSON documents](#handling-json-documents)
- [Available JSON handlers](#available-json-handlers)
- [Available users of JSON handlers](#available-users-of-json-handlers)
- [Custom JSON handlers](#custom-json-handlers)
- [Using JSON handlers](#using-json-handlers)

## Maven

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>utils-json-handler</artifactId>
	<version>1.0.0</version>
</dependency>
```
 
## Usage

### Handling JSON documents

This library provides the [`JsonHandler`][JsonHandler] interface which provides a collection of callback methods that can be called (e.g. by a JSON parser or by domain model for JSON documents) to describe a JSON document and calculates a result for the described JSON document.

#### Available JSON handlers

- A [`AppendingJavaTextJsonHandler`][AppendingJavaTextJsonHandler] from the JSON [text handler library](https://github.com/markenwerk/java-utils-json-handler-text) appends a text that mimics the `toString()` behavior of Java collection classes and represents the described JSON document to an [`Appendable`][Appendable]. 
- A [`AppendingJsonTextJsonHandler`][AppendingJsonTextJsonHandler] from the JSON [text handler library](https://github.com/markenwerk/java-utils-json-handler-text) appends a JSON text that represents the described JSON document to an [`Appendable`][Appendable]. 
- A [`JavaTextJsonHandler`][JavaTextJsonHandler] from the JSON [text handler library](https://github.com/markenwerk/java-utils-json-handler-text) creates a [`String`][String] containing a text that mimics the `toString()` behavior of Java collection classes and represents the described JSON document. 
- A [`JsonTextJsonHandler`][JsonTextJsonHandler] from the JSON [text handler library](https://github.com/markenwerk/java-utils-json-handler-text) creates a [`String`][String] containing a JSON text that represents the described JSON document. 
- A [`JsonValueJsonHandler`][JsonValueJsonHandler] creates a [`JsonValue`] that represents the described JSON document. 
- A [`NullJsonHandler`][NullJsonHandler] from this library is an empty implementation that does nothing.
- A [`XmlDocumentJsonHandler`][XmlDocumentJsonHandler] from the JSON [XML handler library](https://github.com/markenwerk/java-utils-json-handler-xml) creates a XML [`Document`][Document] that represents the described JSON document.

#### Available used of JSON handlers

- A [`JsonDocument`][JsonDocument] from the JSON [domain model library](https://github.com/markenwerk/java-utils-json-model) uses a [`JsonHandler`][JsonHandler] to describe its root [`JsonValue`][JsonValue].
- A [`JsonPushParser`][JsonPushParser] from the fast JSON [parser library](https://github.com/markenwerk/java-utils-json-parser) uses a [`JsonHandler`][JsonHandler] to describe the parsed JSON socument. 

#### Custom JSON handlers

A JSON document will be described to a [`JsonHandler`][JsonHandler] by calls to the appropriate callback methods using the following rules:

- A JSON document will begin with `onDocumentBegin()` and end with `onDocumentEnd()`
- A JSON array will begin with `onArrayBegin()` and end with `onObjectEnd()`
- Elements of a JSON array will be added with either `onArrayBegin()`, `onObjectBegin()`, `onNull()` `onBoolean(boolean)`, `onLong(long)`, `onDouble(double)` or `onString(String)`.
- If a JSON array has more than one element, `onNext()` will be called between each two elements.
- A JSON object will bebegin with `onObjectBegin()` and end with `onObjectEnd()`
- Entries of a JSON object will be added with `onName(String)` followed by eiter `onArrayBegin()`, `onObjectBegin()`, `onNull()` `onBoolean(boolean)`, `onLong(long)`, `onDouble(double)` or `onString(String)`.
- If a JSON object has more than one entry, `onNext()` will be called between each two entries.

All methods sholdn't throw any exceptions other than [`JsonHandlingException`][JsonHandlingException], except `onDouble(double)` and `onString(String)` which need to check the passed argument and therefore may throw a [`InvalidJsonValueException`][InvalidJsonValueException].

This library provides the [`IdleJsonhandler`][IdleJsonhandler] with empty callback methods and check methods to be used in `onDouble(double)` or `onString(String)`, which simplifies the creation of custom implementations.

The following example [`JsonHandler`][JsonHandler] counts the number of JSON literals in the described JSON document:

```java
JsonHandler<Integer> jsonHandler = new IdleJsonHandler<Integer>(){

	int result;

	@Override
	public void onNull() throws JsonHandlingException {
		result++;
	}

	@Override
	public void onBoolean(boolean value) throws JsonHandlingException {
		result++;
	}

	@Override
	public void onLong(long value) throws JsonHandlingException {
		result++;
	}

	@Override
	public void onDouble(double value) throws InvalidJsonValueException, JsonHandlingException {
		checkDoubleValue(value);
		result++;
	}

	@Override
	public void onString(String value) throws InvalidJsonValueException, JsonHandlingException {
		checkStringValue(value);
		result++;
	}

	@Override
	public Integer getResult() throws JsonHandlingException {
		return result;
	}

};
```

#### Using JSON handlers

To describe a JSON document it is necessary to call the appropriate callback methods using the following rules:

- A JSON document must be begun with `onDocumentBegin()` and ended with `onDocumentEnd()`
- A JSON array must be gegun with `onArrayBegin()` and ended with `onObjectEnd()`
- Elements of a JSON array must be added with either `onArrayBegin()`, `onObjectBegin()`, `onNull()` `onBoolean(boolean)`, `onLong(long)`, `onDouble(double)` or `onString(String)`.
- If a JSON array has more than one element, `onNext()` must be called between each two elements.
- A JSON object must be gegun with `onObjectBegin()` and ended with `onObjectEnd()`
- Entries of a JSON object must be added with `onName(String)` followed by eiter `onArrayBegin()`, `onObjectBegin()`, `onNull()` `onBoolean(boolean)`, `onLong(long)`, `onDouble(double)` or `onString(String)`.
- If a JSON object has more than one entry, `onNext()` must be called between each two entries.

The following sequence of callback methods describes the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26).

```java
// a generic JsonHandler
JsonHandler<?> handler = ...

// begin a document
handler.onDocumentBegin();

	// begin an object
	handler.onObjectBegin();
	
		// inside the object: set a name followed by a value to describe an entry
		handler.onName("null");
		handler.onNull();
	
		// inside the object: call next between two entries
		handler.onNext();
		
		handler.onName("boolean");
		handler.onBoolean(true);
		
		handler.onNext();
		
		handler.onName("long");
		handler.onLong(-42);
		
		handler.onNext();
		
		handler.onName("double");
		handler.onDouble(-23.42);
		
		handler.onNext();
		
		// begin an array
		handler.onName("array");
		handler.onArrayBegin();
		
			// inside the object: just a value to describe an entry
			handler.onString("foo");
		
			// inside the object: call next between two entries
			handler.onNext();
		
			handler.onString("bar");
		
		// end the array
		handler.onArrayEnd();
	
	// end the object
	handler.onObjectEnd();

// end the document
handler.onDocumentEnd();
```

[IdleJsonHandler]: http://markenwerk.github.io/java-utils-json-handler/index.html?net/markenwerk/utils/json/handler/IdleJsonHandler.html
[JsonHandler]: http://markenwerk.github.io/java-utils-json-handler/index.html?net/markenwerk/utils/json/handler/JsonHandler.html
[JsonHandlingException]: http://markenwerk.github.io/java-utils-json-handler/index.html?net/markenwerk/utils/json/handler/JsonHandlingException.html
[NullJsonHandler]: http://markenwerk.github.io/java-utils-json-handler/index.html?net/markenwerk/utils/json/handler/NullJsonHandler.html

[AppendingJavaTextJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/handler/text/AppendingJavaTextJsonHandler.html
[AppendingJsonTextJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/handler/text/AppendingJsonTextJsonHandler.html
[JavaTextJsonHandler]: http://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/handler/text/JavaTextJsonHandler.html
[JsonTextJsonHandler]: http://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/handler/text/JsonTextJsonHandler.html

[XmlDocumentJsonHandler]: http://markenwerk.github.io/java-utils-json-handler-xml/index.html?net/markenwerk/utils/json/handler/xml/XmlDocumentJsonHandler.html

[InvalidJsonValueException]: http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/InvalidJsonValueException.html

[JsonPushParser]: http://markenwerk.github.io/java-utils-json-parser/index.html?net/markenwerk/utils/json/common/JsonPushParser.html

[JsonDocument]: https://markenwerk.github.io/java-utils-json-model/index.html?net/markenwerk/utils/json/model/JsonDocument.html
[JsonValue]: https://markenwerk.github.io/java-utils-json-model/index.html?net/markenwerk/utils/json/model/JsonValue.html
[JsonValueJsonHandler]: https://markenwerk.github.io/java-utils-json-model/index.html?net/markenwerk/utils/json/model/processing/JsonValueJsonHandler.html

[Appendable]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Appendable.html
[Document]: https://docs.oracle.com/javase/8/docs/api/index.html?org/w3c/dom/Document.html
[String]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/String.html

