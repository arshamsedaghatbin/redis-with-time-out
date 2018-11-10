package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/")
public class AccountResource {



 @Autowired private RedisTemplate< String, Object > template;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount() throws InterruptedException {
        String sample= "sample";
        setValue("arsham",sample);Thread.sleep(1000);

        System.out.println(getValue("arsham"));
        System.out.println(getValue("arsham"));
    }

    public Object getValue( final String key ) {
        return template.opsForValue().get( key );
    }

    public void setValue( final String key, final String value ) {
        template.opsForValue().set( key, value );
        template.expire( key, 2, TimeUnit.SECONDS );
    }

}
