# Android STT Tutorial

Android application that streams audio from the microphone and transcribes it using STT.

## Prerequisites

#### Download model

Download the pre-trained English model and extract it:
```
curl -LO https://github.com/mozilla/DeepSpeech/releases/download/v0.7.4/deepspeech-0.7.4-models.tflite
curl -LO https://github.com/mozilla/DeepSpeech/releases/download/v0.7.4/deepspeech-0.7.4-models.scorer
```

Move the model files `deepspeech-0.7.4-models.tflite` and `deepspeech-0.7.4-models.scorer`, to the demo application's data directory on your android device.
Mind that the data directory will only be present after installing and launching the app once.

```
adb push deepspeech-0.7.4-models.tflite deepspeech-0.7.4-models.scorer /storage/emulated/0/Android/data/org.deepspeechdemo/files/
```

You can also copy the files from your file browser to the device.

#### Android device with USB Debugging

Connect an android device and make sure to enable USB-Debugging in the developer settings of the device.
If haven't already, you can activate your developer settings by following [this guide from android](https://developer.android.com/studio/debug/dev-options#enable).

## Installation

To install the example app on your connected android device you can either use the command line or Android Studio.

### Command Line

```
cd android-tflite-tutorial
./gradlew installDebug
``` 

### Android Studio

Open the `android-tflite-tutorial` directory in Android Studio.
Run the app and your connected android device.

## Usage

Start recording by pressing the button and the app will transcribe the spoken text.
