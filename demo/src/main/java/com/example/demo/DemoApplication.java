package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "first") String firstNumber, @RequestParam(value = "second") String secondNumber,@RequestParam(value = "operation") String op){
        switch (op){
            case "add":
                return add(firstNumber,secondNumber);
            case "subtract":
                return subtract(firstNumber,secondNumber);
            case "multiply":
                return multiply(firstNumber,secondNumber);
            case "divide":
                return divide(firstNumber,secondNumber);
            default:
                return "E";
        }
    }

    @GetMapping("/click")
    public String click (@RequestParam(value = "number") String x, @RequestParam(value = "operation") String op){
        switch (op){
            case "inverse":
                return inverse(x);
            case "square":
                return square(x);
            case "sqrt":
                return sqrt(x);
            case "percent":
                return percent(x);
            default:
                return "E";
        }
    }


    public String add( String firstNumber,String secondNumber){
        double first=Double.parseDouble(firstNumber);
        double second=Double.parseDouble(secondNumber);
        return String.format((first + second )+"");
    }

    public String subtract(String firstNumber, String secondNumber){
        double first=Double.parseDouble(firstNumber);
        double second=Double.parseDouble(secondNumber);
        return String.format((first - second )+"");
    }
    public String multiply(String firstNumber, String secondNumber){
        double first=Double.parseDouble(firstNumber);
        double second=Double.parseDouble(secondNumber);
        return String.format((first * second )+"");
    }
    public String divide(String firstNumber,String  secondNumber){
        double first=Double.parseDouble(firstNumber);
        double second=Double.parseDouble(secondNumber);
        if(second==0)
            return "E";
        return String.format((first / second )+"");
    }
    public String inverse(String x){
        double number=Double.parseDouble(x);
        if(number==0)
            return "E";
        return String.format("%.16f",(1/number));
    }
    public String square(String x){
        double number=Double.parseDouble(x);
        return String.format((number*number )+"");
    }

    public String sqrt(String x){
        double number=Double.parseDouble(x);
        if(number<0)
            return "E";
        return String.format((Math.sqrt(number))+"");
    }

    public String percent(String number){
        double x=Double.parseDouble(number);
        return String.format((x/100)+"");
    }


}
