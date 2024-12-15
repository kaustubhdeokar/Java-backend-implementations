> mvn clean
- deletes target directory where all files and jars are located.

> mvn package
- The mvn package command performs several steps as part of the build lifecycle. Here is a step-by-step breakdown:  
- Validate: Ensures that the project is correct and all necessary information is available.
- Compile: Compiles the source code of the project.
- Test: Runs the tests using a suitable unit testing framework. These tests should not require the code to be packaged or deployed.
- Package: Takes the compiled code and packages it into a JAR file (or other specified package format).
Integration Test: Processes and deploys the package if necessary into an environment where integration tests can be run.
- Verify: Runs any checks to verify the package is valid and meets quality criteria.
- Install: Installs the package into the local repository, for use as a dependency in other projects locally.
- Deploy: Copies the final package to the remote repository for sharing with other developers and projects.
These steps ensure that the project is built, tested, and packaged correctly.

- <strong>Summary: compilation, packaging, installing in local maven repo.</strong>

