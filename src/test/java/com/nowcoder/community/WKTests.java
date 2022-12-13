package com.nowcoder.community;

import java.io.IOException;

public class WKTests {

    public static void main(String[] args) {
        String cmd = "/usr/local/bin/wkhtmltoimage --quality 75 http://www.nowcoder.com /Users/vinci/coding/work/wk-images/3.png";
        try {
            Runtime.getRuntime().exec(cmd);
            Thread.sleep(3000);
            System.out.println("Done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
