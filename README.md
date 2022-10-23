# XAUFEHole Backend Software

*This is the main README of the whole project,to see the readme of the frontend, please
click [here](https://github.com/shaokeyibb/XAUFEHoleFrontend).*

The XAUFEHole Project by HikariLan, this is the backend of the full project.

## Build

1. run `gradle build`
2. the artifacts will be in `build/libs/`, you should use the one without the `-plain` suffix.

## Configuration

The configuration file is `src/main/resources/application.yml`, and you should (can) change these properties:

```yaml
# your JDBC datasource driver name, default is the MySQL driver
spring.datasource.driver-class-name: "com.mysql.cj.jdbc.Driver"
# Your database configuration
spring.datasource.url: "jdbc:mysql://localhost:3306/hole_xctra_temp_?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai"
spring.datasource.username: "hole_xctra_temp_"
spring.datasource.password: ""
# Your redis configuration
spring.redis.host: "localhost"
spring.redis.port: 6379
spring.redis.password:
# Your mail service configuration
spring.mail.host: "smtp.ym.163.com"
spring.mail.port: "994"
spring.mail.username: "noreply@xctc.club"
spring.mail.password: ""
spring.mail.protocol: "smtp"
# The account token of the SM.MS image uploader service
xaufe-hole.image-uploader.token: ""
```

All the properties can be also set by environment variables or the application arguments like `-Dspring.redis.port=6379`
or `--spring.redis.port=6379`.

## Deploy the whole Project

To deploy the whole project, you need install these software first:

- Nginx (or Apache, whatever)
- MySQL (maybe other database software like MariaDB, if so, please import the driver in the `build.gradle` file, and set
  the correct driver name in the configuration)
- Redis
- Java 8

You can OPTIONALLY install these software, if you want to use the music player:

- Node.js

Now, follow these steps to deploy the project:

1. Configuration and run the backend software by using Java like `java -jar XAUFEHoleBackend.jar`
2. Put the compile files of frontend software to a directory
3. Configure the Nginx to serve the files, an example here:

```lua
server
{
    listen 80;
	# your server name here
    server_name hole.xctra.cn;
    index index.html;
    # the root of your frontend files here
    root /www/wwwroot/hole.xctra.temp.minecraft.kim;

    location / {
        try_files $uri $uri/ /index.html;
    }

    # the backend endpoint, you can change the port here
    location /api {
      proxy_pass http://127.0.0.1:5000/api;
    }

    access_log  /www/wwwlogs/hole.xctra.temp.minecraft.kim.log;
    error_log  /www/wwwlogs/hole.xctra.temp.minecraft.kim.error.log;
}
```

Now the project should work properly. But for the full experience, please continue to the next steps:

1. Install and run [Binaryify/NeteaseCloudMusicApi](https://github.com/Binaryify/NeteaseCloudMusicApi)
2. Install and run [jsososo/QQMusicApi](https://github.com/jsososo/QQMusicApi)
3. append these configurations to your nginx configuration, change the port if needed:

```lua
    location /api/qqmusic/song/ {
      proxy_pass http://127.0.0.1:3300/song/;
    }

    location /api/netease/song/ {
      proxy_pass http://127.0.0.1:3000/song/;
    }
```

Well done! Now you can enjoy the full experience of the XAUFEHole Project!

## License

This software is licensed under [AGPLv3](LICENSE)
