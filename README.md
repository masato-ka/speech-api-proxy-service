speech-api-service
====

Overview

## Description

 This service is proxy for Bing Speech API. You only upload wave(PCM) file as multipart form data.
 
  See in below swagger document after running this service.
  http://localhost:8080/swagger-ui.html


## Build

~~~~
~~~~

## Run the service.

~~~
$java -Dbing.recog.api.subscription=Your Azure subscription -jar speech-api-service.jar 
~~~

## Request example

Get the result of speech recognition from Azure service.

for example (PCM file and sampling rate is 16kHz)
~~~
curl -v https://[your hosting domain]/api/v1/recognizer?formatType=audio/pcm?sampleRate=16000 --data-binary @wave file path
~~~

Now, this service suported below parameters

|param name| value |description|
|formatType|audio/pcm| encoding format for uploading audio file.| 
|sampleRate|16000|sample rate for uploading audio file |
 

## Licence

[MIT LICENCE](https://github.com/masato-ka/geo-hash-potate/blob/master/LICENSE.txt)

The library do not depends other library.

## Author

[masato-ka](https://twitter.com/masato_ka)