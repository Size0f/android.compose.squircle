# Android LightWeight Squircle Library for JetPack Compose

<img src="https://user-images.githubusercontent.com/8309945/151852180-f6f6acc1-fd09-46e9-8d3b-31297854eec9.gif" width="20%"/>

## Usage

Based on Compose Box and custom clip path
```kotlin
Squircle(
    sizeInDp = 48.dp, // squircle size
    smoothing = 4.0, // squircle radius
    backgroundColor = Color.Cyan // squircle background
) {
    Text(text = "42")
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.{your_resource_here}),
        contentDescription = "some image"
    )
    // or any view you want to be wrapped inside Squircle
}
```

## Installation
- Add jitpack.io to your project
```
repositories {
    ...
    maven { url "https://jitpack.io" }
}
```
- Add squircle library dependency
``` 
dependencies { 
  ...
  implementation 'com.github.Size0f:android.compose.squircle:1.0.7'
}
```
