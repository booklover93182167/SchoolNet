web: sh "java -version"
web: sh "./mvnw com.github.eirslett:frontend-maven-plugin:install-node-and-yarn -DnodeVersion=v6.10.3 -DyarnVersion=v0.24.4"
web: sh "./mvnw com.github.eirslett:frontend-maven-plugin:yarn"
web: sh "./mvnw com.heroku.sdk:heroku-maven-plugin:1.1.1:deploy -DskipTests -Pprod -Dheroku.appName=school-net"
