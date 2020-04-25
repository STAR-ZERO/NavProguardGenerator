NavProguardGenerator
===

Auto generate proguard rule file for Jetpack Navigation Component and apply the file to configuration.

## Description

If using own class in Navigation Component `argType` , you need to add own class names to prguard rule. Otherwise, you will get crash with R8/Proguard applied your app.

This plugin will generate prouard rule file for `argType` and apply to your `proguardFiles` or `consumerProguardFiles`.

## Install

Add below to top level `build.gradle`

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.star-zero.gradle:nav-proguard-generator:1.0.0"
  }
}

subprojects {
    // apply all sub projects
    apply plugin: 'com.star-zero.gradle.nav-proguard-generator'
}

```

## Usage

No configuration, you just build your app. This plugin will create `nav-proguard-rule.pro`.

## License

```
Copyright 2020 Kenji Abe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

