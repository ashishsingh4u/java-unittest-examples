## Unit testing - is an approach of testing where the unit(class, method) is under control.

### Test double - is not a primary object(from OOP world). It is a realisation which is created temporary to test, check or during development. Test doubles types:

**_fake object_** is a real implementation of interface(protocol) or an extent which is using an inheritance or other
approaches which can be used to create - is dependency. Usually it is created by developer as the simplest solution to
substitute some dependency

**_stub object_** is a bare object(0, nil and methods without logic) with extra state which is predefined(by developer)
to define returned values. Usually it is created by framework

**_mock object_** is very similar to stub object but the extra state is changed during program execution to check if
something happened(method was called, arguments, when, how often...).

**_spy object_** is a real object with a "partial mocking". It means that you work with a non-double object except
mocked behavior

**_dummy object_** is object which is necessary to run a test but no one variable or method of this object is not
called.

**_Martin Fowler said_**

> _There is a difference in that the stub uses state verification while the mock uses' behavior verification._

## Java UnitTest Example

This project is to demonstrate how we can test our implementation using different ways. JUnit5 and Mockito will be used
for this demonstration

### Using Fake Test Doubles (Without Mockito)

#### This is used to fake the external dependencies

    FakeMovieServiceTest

### Using Dummy Test Doubles (Without Mockito)

#### This is used to replace the dependencies which are not required during the testing, but we need to provide it to avoid compilation errors

    DummyNotificationService

### Using Stub Test Doubles (Without Mockito)

#### This is used to return hard-coded answers/returns. Stubs is used to replace external dependencies that we do not want to test

    MovieService2Test

### Using Spy Test Doubles (Without Mockito)

#### This is used to record interactions with external dependencies

    MovieServiceSpyTest

### Using Mock Test Doubles (Without Mockito)

#### This is used for assertion and behavior verification through mock object

    MovieServiceMockTest

### Using Fake Test Doubles (With Mockito)

#### This is used to fake the external dependencies

    MovieServiceTest::testGetAllOnMovieServiceWithMockito

### Using Dummy Test Doubles (With Mockito)

#### This is used to replace the dependencies which are not required during the testing, but we need to provide it to avoid compilation errors

    MovieServiceTest::testGetAllOnMovieServiceWithMockito

### Using Stub Test Doubles (With Mockito)

#### This is used to return hard-coded answers/returns. Stubs is used to replace external dependencies that we do not want to test

    MovieService2Test::testLatestMoviesByStarsWithMockito

### Using Mock Test Doubles (With Mockito)

#### This is used for assertion and behavior verification through mock object

    MovieServiceMockTest::testSaveOnMockedMovieServiceWithMockito

### Using Spy Test Doubles (With Mockito)

#### This is used to record interactions with external dependencies

    MovieServiceSpyTest::testSaveOnMovieServiceWithSpy