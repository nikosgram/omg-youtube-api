Youtube API [![Build Status](https://travis-ci.org/nikosgram13/youtube-api.svg?branch=master)](https://travis-ci.org/nikosgram13/youtube-api)
=======

This project is a API that provides access to YouTube data, such as videos, playlists, and channels, the best way for the Android Applications.

Examples
--------
```java
YoutubeAPI api = YoutubeAPI.create("Google API Key").channel("channel username");

System.out.println(api.channel().getId()); //print the channel's ID.
```
**For more examples [click here][2]**

Download
--------
Download [the latest JAR][1]

License
--------

```
The MIT License (MIT)

Copyright (c) 2015 Nikos Grammatikos

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
```

[1]: https://github.com/nikosgram13/youtube-api/releases
[2]: https://github.com/nikosgram13/youtube-api/wiki/API-Examples
