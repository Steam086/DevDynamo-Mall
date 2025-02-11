package com.devdynamo.service;


/**
 * 这是认证服务的API接口，用于生成token和对token的验证
 * @author jjr
 * @version 1.0
 */
public interface AuthService {
    /**
     * 根据传入的userId生成一个对应的token
     *
     * @param userId 用户的id
     * @return 根据userId生成的token
     */
    String deliverToken(Integer userId );

    /**
     * 验证token
     * @param token 待验证的token，要去掉前缀 'bearer '
     * @return true 如果验证通过，否则返回false
     */
    boolean verifyToken(String token);

    default String renewToken(Integer userId){return "";};
}
// rpc DeliverTokenByRPC(DeliverTokenReq) returns (DeliveryResp) {}
// rpc VerifyTokenByRPC(VerifyTokenReq) returns (VerifyResp) {}
