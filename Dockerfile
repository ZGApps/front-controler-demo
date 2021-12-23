# Here we call the base image, a tomcat server compatible with JDK 8
FROM tomcat:8.0-jre8

LABEL maintaner="Zak Appleton"

# Copy the .war file to the tomcat webapps directory
ADD target/FrontControllerDemo.war /usr/local/tomcat/webapps/

# EXPOSE informs Docker that the container listens on the specified port at runtime
EXPOSE 8080

# The CMD instruction specifies what to run when the containter is run.
# In this case, the tomcat server is started by running the following slell script:
CMD ["catalina.sh". "run"]