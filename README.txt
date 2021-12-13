First choose the platform, for this:

Windows - set path in system environment or before start tests enter in console: set PLATFORM=android (platforms - android, ios, mobile_web)
Mac - before start tests enter in console: export PLATFORM=android (platforms - android, ios, mobile_web)



run testsuite: mvn -Dtest=TestSuite test

For up allure: allure serve target/allure-results --host localhost --port 9999

