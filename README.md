Pimoroni Automation HAT Java Driver
==

[![Build Status](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/gradle.yml/badge.svg)](https://github.com/home-climate-control/dz/actions/workflows/gradle.yml)
[![Build Status](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/home-climate-control/dz/actions/workflows/codeql-analysis.yml)
[![SonarCloud](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/sonarcloud.yml/badge.svg)](https://github.com/home-climate-control/dz/actions/workflows/sonarcloud.yml)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=home-climate-control_automation-hat-driver&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=home-climate-control_automation-hat-driver)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=home-climate-control_automation-hat-driver&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=home-climate-control_automation-hat-driver)

## What?

This is the Java driver for the [Pimoroni Automation HAT](https://learn.pimoroni.com/tutorial/sandyj/getting-started-with-automation-hat-and-phat).

## What for?

The primary driver behind this project (and its predecessor, [mqtt-automation-hat-go](github.com/climategadgets/mqtt-automation-hat-go) is the [Home Climate Control](https://github.com/home-climate-control/dz) project.

## How?

This will get you started:
```
git clone https://github.com/home-climate-control/automation-hat-driver.git && \
cd automation-hat-driver && \
./gradlew build
```

## Caveats
Tests will not run other than on Raspberry Pi, and some of them you need to explicitly enable (you don't want tests to fiddle with expensive hardware that the HAT controls). Moreover, looks like this project hit a [bug in Gradle](https://github.com/gradle/gradle/issues/17461) - to be determined.

## Enjoy

If in doubt, drop a note to [Home Climate Control user forum](http://groups.google.com/group/home-climate-control), help will come fast.
