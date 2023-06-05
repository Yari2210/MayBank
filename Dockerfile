FROM openjdk:8
EXPOSE 8080
#COPY java-images-new.jar /java-images-new.jar
ADD target/java-images-new.jar java-images-new.jar
#CMD ["java", "-jar", "/java-images-new.jar"]
ENTRYPOINT ["java", "-jar", "/java-images-new.jar"]
#RUN apk add --no-cache tzdata
#ENV TZ=Asia/Jakarta
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone