FROM openjdk:11
EXPOSE 8080
ADD target/StocksDataAnalytics.jar StocksDataAnalytics.jar
ENTRYPOINT ["java","-jar","/StocksDataAnalytics.jar"]