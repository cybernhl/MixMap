checkstyle:
	./gradlew checkstyle

graph:
	./gradlew generateDependencyGraphMapboxLibraries
	./gradlew :libcore:generateDependencyGraphMapboxLibraries
    ./gradlew :libtelemetry:generateDependencyGraphMapboxLibraries

sanity-test-example-activities:
	node scripts/generate-test-code.js

test:
    ./gradlew :libcore:test
    ./gradlew :libtelemetry:downloadSchema
    ./gradlew :libtelemetry:test

release:
    ./gradlew :libcore:assembleRelease
    ./gradlew :libtelemetry:assembleRelease

javadoc:
    ./gradlew :libcore:javadocrelease
    ./gradlew :libtelemetry:javadocrelease

publish-core:
    export IS_LOCAL_DEVELOPMENT=false; ./gradlew :libcore:uploadArchives

publish-telem:
    export IS_LOCAL_DEVELOPMENT=false; ./gradlew :libtelemetry:uploadArchives

publish-local:
    # This publishes to ~/.m2/repository/com/mapbox/mapboxsdk
    export IS_LOCAL_DEVELOPMENT=true; ./gradlew :libcore:uploadArchives
    export IS_LOCAL_DEVELOPMENT=true; ./gradlew :libtelemetry:uploadArchives