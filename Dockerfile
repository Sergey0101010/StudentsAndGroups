FROM openjdk:17
ADD /target/studentsAndGroups-0.0.1-SNAPSHOT.jar studentsAndGroups-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "studentsAndGroups-0.0.1-SNAPSHOT.jar"]