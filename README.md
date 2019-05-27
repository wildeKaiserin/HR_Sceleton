# HR_Sceleton

* Project compiled successfully in earlier commit, with a string sample.

* Current version is untested because there is no backend to test against.

* cpp/native-lib.cpp seems to be the interface to send C return values via JNI

* because of the lack of PNG support right now, we're typecasting the return value of _getPage_ to string and writing it to a file, to decode into a bitmap.

* the following native methods are called:

  * getPage(int height, int width);

  * setResolution(float dpi);

  * openFile(DUMMY FILE - IGNORE);

  * mvFirstPage();

  * closeFile();