# Utils

Set of utils developed for d2day use and/or supporting ongoing projects

P2Y - convertion of properties from properties to YAML for SpringBoot application properties migration to YAML. Has special handling for the following:

~~~
  logging.level.*=INFO 
~~~
becomes:
~~~
  logging:
    level:
      ROOT: "INFO"
~~~      
And
~~~      
  logging.level.my.package.foo=ERROR
  logging.level.my.package.bar=WARN
~~~
becomes:
~~~
  logging:
    level:
      my.package.foo: "ERROR"
      my.package.bar: "WARN" 
~~~

Usage:
~~~
  java pvi.utils.P2Y <existing properties file>
~~~
