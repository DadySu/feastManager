package com.up.feast.config;

import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 *
 * @author liub
 * @date 2019-03-19 15:15
 */
@Configuration
public class AnimalConfig {

    @Bean(name = "animal1")
    public Animal getAnimal1() {
        return new Animal("小白", 2);
    }

    @Bean(name = "animal1")
    @ConditionalOnMissingBean
    public Animal getAnimal2() {
        return new Animal("小黑", 3);
    }


    class Animal {

        private String name;

        private Integer age;

        public Animal() {

        }

        public Animal(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    class Dog extends Animal {
        public Dog(String name, Integer age) {
            super(name, age);
        }
    }

    class Cat extends Animal {

    }
}
