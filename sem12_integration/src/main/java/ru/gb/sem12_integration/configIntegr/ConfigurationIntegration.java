package ru.gb.sem12_integration.configIntegr;

import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class ConfigurationIntegration
{
    @Bean
    public MessageChannel textInputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriteChannel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriteChannel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {
            text = text.toUpperCase();
            return text;
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriteChannel")
    public FileWritingMessageHandler customHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("./"));
        handler.setExpectReply(false);                      //
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler; // кому возвращаем ?
    }
}
