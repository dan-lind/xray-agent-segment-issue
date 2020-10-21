package com.byteslice.common.unlock.api.controller;

import com.amazonaws.xray.AWSXRay;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/xray")
public class UnlockController {

    @GetMapping("/withIssue")
    public ResponseEntity<?> withoutIssue() {
        CompletableFuture.runAsync(() -> {
            try {
                AWSXRay.beginSubsegment("Sugsegment");
                Thread.sleep(3000);
                AWSXRay.endSubsegment();
            } catch (InterruptedException e) {
                //NOOP
            }
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/withoutIssue")
    public ResponseEntity<?> withIssue() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            try {
                AWSXRay.beginSubsegment("Sugsegment");
                Thread.sleep(3000);
                AWSXRay.endSubsegment();
            } catch (InterruptedException e) {
                //NOOP
            }
        });
        future.get();
        return ResponseEntity.ok().build();
    }
}