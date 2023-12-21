package compro2.ex;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Host2Ip {
    public static void main(String[] args) {
        String hostname = "www.naver.com";

        try {
            InetAddress myAdd = InetAddress.getLocalHost();
            System.out.println("LocalHost Name : " + myAdd.getHostName());
            System.out.println("LocalHost Address : " + myAdd.getHostAddress());

            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress s : addresses)
                System.out.println(s);
        } catch ( UnknownHostException e ) {
            System.out.println(hostname + "의 IP 주소를 찾을 수 없습니다.");
        }
    }
}
