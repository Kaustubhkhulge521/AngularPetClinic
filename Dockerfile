FROM java:8
EXPOSE 8080
COPY . /opt
WORKDIR /opt
ENTRYPOINT [ "java","-war","./target/maheshPro-0.0.1-SNAPSHOT.war" ]