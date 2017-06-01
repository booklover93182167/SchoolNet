web: java -version
web: ./mvnw com.github.eirslett:frontend-maven-plugin:install-node-and-yarn -DnodeVersion=v6.10.3 -DyarnVersion=v0.24.4
web: ./mvnw com.github.eirslett:frontend-maven-plugin:yarn
web: ./mvnw com.heroku.sdk:heroku-maven-plugin:1.1.1:deploy -DskipTests -Pprod -Dheroku.appName=school-net
