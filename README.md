# GPSTracker 
A light weight library for getting current user location, latitude, longitude, address, ip address and many more.
​
## Installation
<!-- TODO: add package -->
In project level build file, add the jitpack repository inside repositories of allprojects  as follows:
```groovy
allprojects {
   repositories {
        ...	
	maven { url 'https://jitpack.io' }
   }
}

```	

Gradle:
```groovy
implementation 'com.github.ankitdubey021:GPSTracker:2.0'
```



Maven:
```xml
<dependency>
	    <groupId>com.github.ankitdubey021</groupId>
	    <artifactId>GPSTracker</artifactId>
	    <version>2.0</version>
</dependency>
```

## Requirement

**Add permission in manifest file as-**
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```
​

```java


<h2> Step 1. implement MyTracker.ADLocationListener interface for getting location as callback </h2>


<h2> Step 2. override whereIAm() method </h2>

 @Override
    public void whereIAM(ADLocation loc) {
        System.out.println(loc);
    }
    
<h4> Step 3. get location </h4>

new MyTracker(getApplicationContext(),this).track();

```

​

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)


Copyright (c) 2017 Ankit Dubey

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


    
