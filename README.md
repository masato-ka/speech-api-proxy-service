speech-api-service
====

Overview

## Description

 This service is proxy for Bing Speech API. You only upload wave(PCM) file as multipart form data.
 
  See in below swagger document after running this service.

  [Swagger document](http://localhost:8080/swagger-ui.html)


## Build

~~~~
$ git clone https://github.com/masato-ka/speech-api-proxy-service.git
$ cd speech-api-proxy-service
$ mvn package -DskipTests=true
~~~~

And create jar file in speech-api-proxy-service/target folder.

## Run the service.

~~~
$java -Dbing.recog.api.subscription=Your Azure subscription -jar target/speech-api-service-0.0.1-SNAPSHOT.jar 
~~~

## Request example

Get the result of speech recognition from Azure service.

for example (PCM file and sampling rate is 16kHz)
~~~
curl -X POST --header 'Content-Type: multipart/form-data' 'http://localhost:8080/api/v1/speech/recognizer?formatType=audio%2Fpcm&sampleRate=16000&lang=en-US&mode=conversation' -F "audio=@Your file path" 
~~~

Now, this service suported below parameters

|param name  | value       |description   |
|:-----------|------------:|:------------:|
|formatType(must)  |audio/pcm    |encoding format for uploading audio file.|
|sampleRate(must)  |16000        |sample rate for uploading audio file |
|lang(optional)    |en-US        |Define language. This proxy Default is en-US, and support ja-JP|
|mode(optional)    |conversation |Define recognition mode. Other support values are interactive and dictation |

And other options and value is see in

[Convert speech to text with Microsoft APIs](https://docs.microsoft.com/ja-jp/azure/cognitive-services/speech/api-reference-rest/bingvoicerecognition)

## Licence

[MIT LICENCE](https://github.com/masato-ka/geo-hash-potate/blob/master/LICENSE.txt)


## Author

[masato-ka](https://twitter.com/masato_ka)