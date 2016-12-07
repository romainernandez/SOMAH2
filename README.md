# [SOMAH2](https://github.com/RomainErnandez/SOMAH2)

> SOMAH2 is a utility tool designed to facilitate  communication for midwives and nurses about breastfeeding and infant feeding.

> It aims to be flexible and sustainable as the content will grow with time.

***

## Get started

+ **Documentation** - [![Inline docs](http://inch-ci.org/github/dwyl/hapi-auth-jwt2.svg?branch=master)](https://github.com/RomainErnandez/SOMAH2/blob/master/README.md)

+ **The [master](https://github.com/RomainErnandez/SOMAH2/tree/master/app/app) branch which contains the latest release apk.**

***

## Contribute
	
	# Windows Git Bash
	git clone https://github.com/RomainErnandez/SOMAH2.git

#### Web part (Server)

> An Apache/MySQL/PHP server is needed to provide a remote acess to the content.

+ **[WampServer 2.5](https://sourceforge.net/projects/wampserver/files/WampServer%202/Wampserver%202.5/) installs automatically everything you need to start working with the server. You might need to install [Visual C++ Redistributable Packages](https://www.microsoft.com/en-US/download/details.aspx?id=30679) before.**

:exclamation: You have to launch the installers as administrator.

> I would suggest to make a symbolic link from the wamp www/ folder to the web/ folder where you cloned the project.

	# Windows cmd.exe
	mklink /J C:\wamp\www\web C:\...\SOMAH2\web

> You can now start WampServer and insert some content in it.
> 
> In a browser, connect to [http://localhost/phpmyadmin](http://localhost/phpmyadmin) and click on import on the upper part.

![phpmyadminImage](/web/img/phpmyadmin.PNG)

> In the *File to Import:* section select the somah2_web.sql file located in the web/ folder where you cloned the project and click on *Go*.

![importImage](/web/img/import.PNG)

> :thumbsup: You have now a running server with the latest content in it.

#### App part (Client)

+ **The app has been developed with [Android Studio](https://developer.android.com/studio/index.html) and use the library [android-async-http](http://loopj.com/android-async-http/).**

> Simply open the project SOMA2/app with Android Studio. You might also need to download Android SDK Platforms 4.0.3 ([IceCreamSandwich](https://fr.wikipedia.org/wiki/Android_Ice_Cream_Sandwich)).

![IceCreamSandwichLogo](/web/img/IceCreamSandwichLogo.png)

***

## Screenshots

![Capture2](/app/app/src/main/res/drawable/welcome_menu_backgroun.png)

***
