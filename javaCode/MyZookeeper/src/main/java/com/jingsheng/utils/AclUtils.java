package com.jingsheng.utils;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 *
 */
public class AclUtils {

    public static String getDigestUserPwd(String id) throws Exception {
        return DigestAuthenticationProvider.generateDigest(id);
    }

    public static void main(String[] args) throws Exception {
        String id = "jingsheng:jingsheng";
        String idDigested = getDigestUserPwd(id);
        System.out.println(idDigested);
    }

}
