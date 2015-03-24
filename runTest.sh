#!/bin/sh

#adb devices
#adb kill-server

./gradlew clean assemble

#launch Nexus 5 Emulator

/Users/mmadhusoodan/Library/Android/sdk/tools/emulator -netdelay none -netspeed full -avd Nexus_5_API_21_x86

./gradlew -Dtest.single=SignInAveryAppTest test --rerun-tasks