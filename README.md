# Real-Time Data Processing and Analytics with Apache Flink, Kafka, Elasticsearch, and Docker

## Overview
This project guides you through building an end-to-end data engineering system using Apache Flink, Kafka, Elasticsearch, Kibana, and Docker. The system processes real-time data and generates analytics, demonstrating the power of these technologies in an industry-grade data pipeline.

## System Architecture
System Architecture

## Prerequisites
Before running this project, ensure you have the following installed:

JDK 11+
Apache Flink
Kafka
Elasticsearch
Docker and Docker Compose

## Installation
### Clone the Repository
Clone this repository to your local machine:

bash
```
git clone <repository-url>
cd <repository-folder>
```

### Install Dependencies
Install the required dependencies using Maven:

bash
```
mvn clean install
```

### Docker Services
Use Docker Compose to set up the required services:

bash
```
docker-compose up -d
```

This command will download necessary Docker images, create containers, and start services in detached mode.

### Verify Services
Check if all services are up and running:

bash
```
docker-compose ps
```
You should see all services listed as 'running'.


### Accessing the Services
Kafka Control Center: http://localhost:9021
Debezium UI: http://localhost:8080
Elasticsearch: Default port 9200

### Shutting Down
To stop and remove containers, networks, and volumes:

bash
```
docker-compose down
```

## Getting Started
Follow the video tutorial for a step-by-step guide on building and running the project.

## Customization
You can modify the Docker Compose file to suit your needs. For example, consider additional configurations for production environments.

⚠️ Note: This setup is intended for development and testing purposes. For production environments, consider additional factors like security, scalability, and data persistence.

## References
Apache Flink Documentation
Elasticsearch Documentation
Docker Documentation
- [Kafka Documentation](https://kafka.apache.org/documentation/)
- [Python Faker](https://faker.readthedocs.io/en/master/)
- [Inspired by a tutorial on real-time data processing.](https://www.youtube.com/watch?v=deepQRXnniM&t=384s)

## 📧Contact
Please feel free to contact me if you have any questions.
<a href="https://ducanh0285@gmail.com" target="blank"><img align="center" src="https://img.icons8.com/color/48/000000/gmail--v2.png" alt="ducanh0285@gmail.com" height="30" width="40" /></a><a href="https://www.facebook.com/ducanh.pp" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/facebook.svg" alt="1" height="30" width="40" /></a><a href="https://twitter.com/Ducann02Nguyen" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/twitter.svg" alt="1" height="30" width="40" /></a><a href="https://www.linkedin.com/in/ducanhnt/" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="1" height="30" width="40" /></a>
