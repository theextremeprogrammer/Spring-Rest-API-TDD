tests:
	@/usr/bin/time ./gradlew clean build test

integrationtests:
	@/usr/bin/time ./gradlew clean integrationTest
