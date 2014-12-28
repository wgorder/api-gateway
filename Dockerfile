FROM wgorder/java8-jce
MAINTAINER Bill Gorder <william.gorder>

# Add the application to the container
ADD build/libs/api-gateway.jar /data/api-gateway.jar

# Expose
EXPOSE  8080

# Run
CMD ["java", "-jar", "api-gateway.jar"]
