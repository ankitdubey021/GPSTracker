# GPSTracker (out of maintenance)
Very easy and user friendly library for getting current location, address and many more thing with a few line of code.
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

## Requirement

**Add permission in manifest file as-**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

**Step 1. implement MyTracker.ADLocationListener interface for getting location as callback**
<br>
**Step 2. override whereIAm() method**
       
    @Override
    public void whereIAM(ADLocation loc) {
        System.out.println(loc);
    }
 
<br>



**Step 3. get location**

	new MyTracker(getApplicationContext(),this).track();



<h2>Sample code </h2>
        
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        else{
            findLoc();
        }
    }

    private void findLoc(){
        new MyTracker(getApplicationContext(),this).track();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            findLoc();
        }
    }
    @Override
    public void whereIAM(ADLocation loc) {
        System.out.println(loc);
    }


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


    
