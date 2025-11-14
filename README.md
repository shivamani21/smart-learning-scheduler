
# Smart Learning Scheduler - Backend

Environment variables used (set these in your environment or in Render):
- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD
- APP_JWT_SECRET
- APP_JWT_EXPIRATION_MS
- TWILIO_ACCOUNT_SID
- TWILIO_AUTH_TOKEN
- TWILIO_FROM_NUMBER

Build & run:
- mvn -DskipTests clean package
- java -jar target/smart-learning-scheduler-0.0.1-SNAPSHOT.jar

Docker:
- Build: docker build -t sls-backend .
- Run: docker run -e SPRING_DATASOURCE_URL=... -e SPRING_DATASOURCE_USERNAME=... -e SPRING_DATASOURCE_PASSWORD=... -e APP_JWT_SECRET=... -e TWILIO_ACCOUNT_SID=... -e TWILIO_AUTH_TOKEN=... -e TWILIO_FROM_NUMBER=... -p 8080:8080 sls-backend
