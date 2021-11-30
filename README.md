
#AWS-SQS-DynamoDB

### AWS demo project using spring boot and AWS services like dynamodb and sqs


### Endpoints 

* SQS

       POST - http://localhost:8080/sqs/send-message

       GET - http://localhost:8080/sqs/get-messages



* Dynamo 

        GET - http://localhost:8080/dynamo/test

        POST - http://localhost:8080/dynamo/add-event
    
        GET - https://localhost:8080/dynamo/get-event/{messageId}


### Commands

* SQS


         - aws sqs create-queue --queue-name first-queue --endpoint-url http://localhost:4566



*  DynamoDB
  
  

         - aws dynamodb create-table --table-name Event --attribute-definitions AttributeName=eventId,AttributeType=S AttributeName=message,AttributeType=S --key-schema AttributeName=eventId,KeyType=HASH AttributeName=mssage,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url=http://localhost:4566
    
         - aws dynamodb create-table --cli-input-json file://create-table-event.json --endpoint-url http://localhost:4566


* Docker

  

  
        - docker run --rm -it -p 4566:4566 -p 4571:4571 localstack/localstack -e "SERVICES=dynamodb,sqs"

        - docker ps

        - docker rm <container id>


* Windows

        - npx kill-port <port>


* AWS

        - aws configure
