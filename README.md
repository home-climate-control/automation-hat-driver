Pimoroni Automation HAT Java Driver
==

[![Build Status](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/gradle.yml/badge.svg)](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/gradle.yml)
[![Build Status](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/codeql-analysis.yml)
[![SonarCloud](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/sonarcloud.yml/badge.svg)](https://github.com/home-climate-control/automation-hat-driver/actions/workflows/sonarcloud.yml)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=home-climate-control_automation-hat-driver&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=home-climate-control_automation-hat-driver)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=home-climate-control_automation-hat-driver&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=home-climate-control_automation-hat-driver)

## What?

This is the Java driver for the [Pimoroni Automation HAT](https://learn.pimoroni.com/tutorial/sandyj/getting-started-with-automation-hat-and-phat).

## What for?

The primary driver behind this project (and its predecessor, [mqtt-automation-hat-go](https://github.com//climategadgets/mqtt-automation-hat-go) is the [Home Climate Control](https://github.com/home-climate-control/dz) project.

## How?

### Prerequisites

* Run `raspi-config` and enable `I2C` in Interfacing Options;
* Run `sudo apt install wiringpi`

### Build
```
git clone https://github.com/home-climate-control/automation-hat-driver.git && \
cd automation-hat-driver && \
./gradlew build
```
### Test

Tests are configured in such a way that they will not run on any platform other than Raspberry Pi. Moreover, they have to be explicitly enabled (you don't want tests to fiddle with expensive hardware that the HAT controls).
There is a [bug in Gradle](https://github.com/gradle/gradle/issues/17461) that requires special treatment - Gradle daemon has to be disabled for running tests: 

```
TEST_AUTOMATION_HAT=safe ./gradlew --no-daemon cleanTest test
```

## Enjoy

If in doubt, drop a note to [Home Climate Control user forum](http://groups.google.com/group/home-climate-control), help will come fast.
