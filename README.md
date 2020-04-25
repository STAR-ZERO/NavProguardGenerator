NavProguardGenerator
===

Auto generate proguard rule file for Jetpack Navigation Component and apply the file to configuration.

## Description

If using own class in Navigation Component `argType` , you need to add own class names to prguard rule. Otherwise, you will get crash with R8/Proguard applied your app.

This plugin will generate prouard rule file for `argType` and apply to your `proguardFiles` or `consumerProguardFiles`.

## Install

TODO

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

