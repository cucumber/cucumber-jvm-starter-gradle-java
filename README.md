# Cucumber-JVM Starter: Java with Gradle

This is the simplest possible setup for Cucumber using Java with Gradle.
There is nothing fancy like a webapp or browser testing. All this does is to
show you how to set up and run Cucumber! If this is your first time using
Cucumber have a look at the [10-minute tutorial](https://cucumber.io/docs/guides/10-minute-tutorial)
first.

To write assertions the project comes with [AssertJ](https://assertj.github.io/doc/#assertj-core-assertions-guide)
included. 

## Get the code

Git:

    git clone https://github.com/cucumber/cucumber-jvm-starter-gradle-java.git
    cd cucumber-jvm-starter-gradle-java

Or [download a zip](https://github.com/cucumber/cucumber-jvm-starter-gradle-java/archive/main.zip) file.

## Run the tests

Open a command window and run:

On macOS/Linux:

```shell
./gradlew test --rerun-tasks --info
```

On Windows PowerShell:

```powershell
.\gradlew test --rerun-tasks --info
```

This runs Cucumber features using Cucumber's JUnit Platform Engine. The `Suite`
annotation on the `RunCucumberTest` class tells JUnit to kick off Cucumber.

```text
RunCucumberTest > Belly > Belly - a few cukes STANDARD_OUT

    Scenario: a few cukes                 # classpath:com/example/project/belly.feature:3
      ✔ Given I have 42 cukes in my belly # com.example.project.StepDefinitions.I_have_cukes_in_my_belly(int)
      ↷ When I wait 1 hour                # com.example.project.StepDefinitions.i_wait_seconds(java.lang.Integer)
            org.opentest4j.TestAbortedException: TODO: Implement me
                   at com.example.project.StepDefinitions.i_wait_seconds(StepDefinitions.java:19)
                   at ✽.I wait 1 hour(classpath:com/example/project/belly.feature:5)
      ↷ Then my belly should growl
```

The output show that there is a single feature file with one scenario. The
scenario has three steps, one passing, one pending, and one undefined. See if
you make can each step pass.

## Configuration

The [Cucumber JUnit Platform Engine](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine) uses configuration parameters to know
what features to run, where the glue code lives, what plugins to use, etc.

For available parameters see [Cucumber JUnit Platform Engine - Configuration Options](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine#configuration-options).

## Run a subset of Features or Scenarios

You can run an individual feature from the CLI by selecting its file name without extension.  

```
./gradlew test --rerun-tasks --info --tests "belly"
```

For more information see [Testing in Java & JVM projects - Non-class based testing - Filtering](https://docs.gradle.org/current/userguide/java_testing.html#sec:non_class_based_testing_filtering).

Tags can also be selected from the CLI using the `cucumber.filter.tags`
parameter.
This takes a [Cucumber Expression](https://github.com/cucumber/cucumber-expressions).

```
./gradlew test --rerun-tasks --info -Dcucumber.filter.tags="not @Haricots and (@Zucchini or @Gherkin)" 
```

Note: Add `-Dcucumber.plugin=pretty` to get a more detailed output during test
execution.

### Running a single scenario or feature

Gradle does not (yet) support selecting single scenarios  with JUnit selectors.
As a work around the `cucumber.features` property can be  used. Because this
property will cause Cucumber to ignore any other selectors from JUnit it is
prudent to only execute the Cucumber engine.

To select the scenario on line 3 of the `belly.feature` file use:

```
./gradlew test --rerun-tasks --info -Dcucumber.features=src/test/features/belly.feature:3
```

Note: Add `-Dcucumber.plugin=pretty` to get a more detailed output during test
execution.
