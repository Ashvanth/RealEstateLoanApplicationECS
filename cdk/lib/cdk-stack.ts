import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { aws_ec2 as ec2, aws_ecs as ecs, aws_logs as logs, aws_ssm as ssm , aws_ecr as ecr,aws_iam as iam } from 'aws-cdk-lib';


export class RealEstateLoanAppFargateStack extends cdk.Stack {
constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);
    const vpc = new ec2.Vpc(this, 'MyVpc', {
      maxAzs: 2,
    });
    const cluster = new ecs.Cluster(this, 'MyCluster', {
      vpc: vpc,
    });

    const executionRole = new iam.Role(this, 'ExecutionRole', {
      assumedBy: new iam.ServicePrincipal('ecs-tasks.amazonaws.com'),
    });

    const ecrRepository = ecr.Repository.fromRepositoryName(
      this,
      'MyEcrRepository',
      'ashvanth'
    );

    ecrRepository.grantPull(executionRole);



    const taskDefinition = new ecs.FargateTaskDefinition(this, 'MyTaskDefinition', {
      memoryLimitMiB: 512,
      cpu: 256,
      executionRole: executionRole,
    });

    const container = taskDefinition.addContainer('pmilabsContainer', {
      image: ecs.ContainerImage.fromRegistry('250876169825.dkr.ecr.eu-west-1.amazonaws.com/ashvanth:latest'),
      cpu: 0,
      essential: true,
    });

    container.addPortMappings(
      { containerPort: 80, hostPort: 80, protocol: ecs.Protocol.TCP },
      { containerPort: 8080, hostPort: 8080, protocol: ecs.Protocol.TCP },
      { containerPort: 443, hostPort: 443, protocol: ecs.Protocol.TCP },
    );

   

    

    const service = new ecs.FargateService(this, 'MyFargateServiceDemo', {
      cluster: cluster,
      taskDefinition: taskDefinition,
      desiredCount: 1, // Specify the desired number of tasks
    });
  }
}
