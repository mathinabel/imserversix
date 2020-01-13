package com.quyuanjin.imserver;

import com.quyuanjin.imserver.utils.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Slf4j
@SpringBootApplication
public class ImserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImserverApplication.class, args);
        new ChatServer().start(8089);
    }

}
