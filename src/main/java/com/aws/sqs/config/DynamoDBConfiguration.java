package com.aws.sqs.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    private AmazonDynamoDB amazonDynamoDBConfig() {

        return  AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4567", "us-east-2"))
//                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("test", "test")))
                .build()
                ;
    }
}
