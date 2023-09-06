package com.myorg;

import com.google.common.collect.Lists;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.SubnetConfiguration;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

import java.util.List;

public class IniStack extends Stack {
    public IniStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public IniStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "IniQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
        // final Queue queue = Queue.Builder.create(this, "IniQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();

        // init VPC
        List<SubnetConfiguration> subnetConfigurations = Lists.newArrayList();
        subnetConfigurations.add(SubnetConfiguration.builder()
                .name("public-subnet")
                .cidrMask(24)
                .subnetType(SubnetType.PUBLIC)
                .build());
        Vpc.Builder.create(this, "mainVPC")
                .maxAzs(2)
                .subnetConfiguration(subnetConfigurations)
                .build();
    }
}
