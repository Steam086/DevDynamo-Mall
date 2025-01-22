package com.devdynamo.service;


// rpc DeliverTokenByRPC(DeliverTokenReq) returns (DeliveryResp) {}
// rpc VerifyTokenByRPC(VerifyTokenReq) returns (VerifyResp) {}
public interface AuthService {
    String deliverToken(Integer userId );
    Boolean verifyToken(String token);
    default String renewToken(Integer userId){return "";};
}
