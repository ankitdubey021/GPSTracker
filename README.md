# GPSTracker
A light weight library for getting current user location, latitude, longitude, address, ip address and many more.

Step 1) Add library to your app level build file.<br>

```diff
- compile'com.github.ankitdubey021:GPSTracker:1.1'

```

Step 2) In project level build file, add the jitpack repository
```diff
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
  }

```

Step 3)
Add permission in manifest file as-
```diff
+ <uses-permission android:name="android.permission.INTERNET"/>
+ <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```
   
Step 4)
create object of MyTracker class
```diff
- MyTracker tracker=new MyTracker(this);

```

<h2>Sample Code</h2>

private static final int REQUEST_CODE_PERMISSION = 2;<br>
String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;<br>
@Override<br>
protected void onCreate(Bundle savedInstanceState) {<br>
        super.onCreate(savedInstanceState);<br>
        setContentView(R.layout.activity_main);<br>
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_PERMISSION);
            }else{
                //read location
                getLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    System.out.println("permission denied!");
                }
                break;
        }
    }
    void getLocation(){
        MyTracker tracker=new MyTracker(this);
        System.out.println(tracker.getLatitude());
        System.out.println(tracker.getLongitude());
        System.out.println(tracker.getLocation());
        System.out.println(tracker.address);
        System.out.println(tracker.cityName);
        System.out.println(tracker.state);
        System.out.println(tracker.countryName);
        System.out.println(tracker.countryCode);
        System.out.println(tracker.ipAddress);
        System.out.println(tracker.macAddress);
    }


<h2>MIT License</h2>

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


    
