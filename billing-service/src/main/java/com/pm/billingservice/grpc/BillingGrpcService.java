package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import com.google.api.Billing;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
            StreamObserver<BillingResponse> responseObserver){ //back and forth two-way communication with stream observer is more powerful
        log.info("createBillingAccount request received {}", billingRequest.toString());

        //business logic - e.g. save to database, perform calculations stc

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response); //used to send our response from our gRPC service back to client
        responseObserver.onCompleted();   // response is completed
    }

}
