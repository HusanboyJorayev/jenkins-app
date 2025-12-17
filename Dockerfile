# 1️⃣ Bosqich: Build stage (Maven bilan .jar faylni yig‘ish)
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /app

# faqat dependency kechiktirilmasin deb, pom.xml avvaldan copy qilamiz
COPY pom.xml .
RUN mvn dependency:go-offline

# endi butun source kodni copy qilamiz
COPY src ./src

# .jar faylni build qilamiz
RUN mvn clean package -DskipTests

# 2️⃣ Bosqich: Run stage (yengil JRE image)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# build bosqichidan .jar faylni ko‘chirib olamiz
COPY --from=build /app/target/*.jar app.jar

# 8765 portni ochamiz
EXPOSE 8765

# JVM uchun ba’zi optimizatsiyalar (ixtiyoriy, lekin tavsiya etiladi)
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# .jar faylni ishga tushiramiz
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
