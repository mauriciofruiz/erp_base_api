# Stage 1: Build the application
FROM amazoncorretto:20.0.2 AS builder

# Create and set the working directory inside the container
WORKDIR /app

# Copy the entire project directory to the container
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build -x test --continue

# Stage 2: Create a smaller runtime image
FROM amazoncorretto:20.0.2-alpine3.18

WORKDIR /app
COPY --from=builder /app/build/libs/erp_base_api-0.0.1-SNAPSHOT.jar .

# Install tzdata and set the timezone to America/Guayaquil
RUN apk update && \
    apk add --no-cache tzdata && \
    ln -snf /usr/share/zoneinfo/America/Guayaquil /etc/localtime && \
    echo "America/Guayaquil" > /etc/timezone

# Expose the port on which your application will run
EXPOSE 80

# Metadata as defined in suggestions
LABEL version="1.0" \
      description="erp_base_api Application" \
      maintainer="Sicon <programador1_ti@siconecuador.com>"

CMD ["java", "-jar", "erp_base_api-0.0.1-SNAPSHOT.jar"]