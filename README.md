# QUEX

#### Crypto interpretation application

## Project explanation

This project, you can follow the coins, comment and reply to the comments, as well as add your favorite coins with everyone's own profile pages.

Thanks to its open source structure, you can examine this project and learn where and how the data is kept.

## Project features
 - Jetpack
     - [Flow][1] : Flow is conceptually a stream of data that can be computed asynchronously.
     - [View Binding][2]: View binding is a feature that allows us to more easily write code that interacts with views.
 - MVVM with Clean architecture
 - [Hilt][3] for dependency injection
 - [Navigation Components][4]
 - [Firebase][5]
 - [Retrofit][6]


## Project structure

This project was written with MVVM architecture using multi-module.

While the data in this project is stored in the AWS server with retrofit, the visual and account data are stored in the firebase database.

## Screens

| Sign In | Sign Up | Home |
| ------- | ------- | ---- |
<img src="https://user-images.githubusercontent.com/97980164/204120172-138804a7-4145-491a-aa54-2bf742f133c2.jpg" width="250" height="500"/>|<img src="https://user-images.githubusercontent.com/97980164/204120239-7f2ee6ca-f822-430c-a482-6756b1a1501b.jpg" width="250" height="500"/>|<img src="https://user-images.githubusercontent.com/97980164/204120272-cfe245dc-f58b-4d0d-9154-df48ff7f6121.jpg" width="250" height="500"/>|

</br>

| Profile | Comments | Notifications |
| ------- | -------- | ------------- |
<img src="https://user-images.githubusercontent.com/97980164/204120247-4b647e32-8d07-463f-a626-f0589056eb26.jpg" width="250" height="500"/>|<img src="https://user-images.githubusercontent.com/97980164/204120303-4072636d-9c01-4702-bdbd-81ab1b3a3739.jpg" width= "250" height="500"/>|<img src="https://user-images.githubusercontent.com/97980164/204120331-ffcb1db7-0e2a-4d9e-b3c2-fb48d4b2ba95.jpg" width="250" height="500"/>|

</br>

| Answers | 
| ------- |
|<img src="https://user-images.githubusercontent.com/97980164/204120353-54672a90-1988-4ed7-b4fa-96f3e31fd2e7.jpg" width="250" height="500"/>


[1]: https://developer.android.com/kotlin/flow
[2]: https://developer.android.com/topic/libraries/view-binding
[3]: https://developer.android.com/training/dependency-injection/hilt-android
[4]: https://developer.android.com/guide/navigation/navigation-navigate
[5]: https://firebase.google.com
[6]: https://square.github.io/retrofit
