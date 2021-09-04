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

