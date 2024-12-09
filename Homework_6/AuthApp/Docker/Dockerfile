FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./auth-0.0.1-SNAPSHOT.jar .
ENV DB_LOGIN John

EXPOSE 8000/tcp
CMD ["java", "-jar", "auth-0.0.1-SNAPSHOT.jar"]