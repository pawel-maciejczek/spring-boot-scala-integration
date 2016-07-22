# Spring MVC + Spring Boot + scala + gradle POC
There are 3 modules:

* core - app core services written in scala
* web-java - application layer with controllers written in java, service methods return java.util.concurrent.Future
* web-scala - application layer controllers written in java, service results return scala.concurrent.Future

To switch the application layer module, just comment/uncomment appropriate module in settings.gradle in the main project and rebuild the app.

Working:
* case classes marshalling
* scala collections marshalling
* optional marshalling (None is null at the moment, but it's configurable according to jackson doc)

Not working:
* scala futures marshalling in web-java