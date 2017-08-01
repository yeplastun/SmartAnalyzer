FROM maven:3.5.0-jdk-8
CMD ["mvn", "clean", "install", "-B"]