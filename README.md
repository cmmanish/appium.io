Sample Appium JUnit project
---
# brew install node      # get node.js
# npm install -g appium  # get appium
# npm install wd         # get appium client

You will then need to start appium, eg:

# appium &               # start appium


To compile and run all tests, run:

    gradlew
    gradlew clean assemble
    gradle test

To run a single test, run:

    gradle -Dtest=ccom.lyve.android.AndroidBasicTest test
