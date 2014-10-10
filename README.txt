Running Cucumber test suite:
To run Cucumber tests run a Cucumber Runner class (e.g SportsAppNavigationTest) with system parameter -DENV={env}
where env is the environment name. Available environment values are si, uat, staging.
Example: -DENV=si

Running all acceptance tests from maven:
mvn clean install -DENV=si