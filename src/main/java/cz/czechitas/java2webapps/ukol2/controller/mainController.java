package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class mainController extends Throwable {

    private final Random random = new Random();
    private static List<String> readAllLines() throws mainController, java.io.IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("citaty.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/")
    public ModelAndView citat() throws mainController, java.io.IOException {

        int randomNumber = random.nextInt(4) + 1;


        int nahodnyCitat = random.nextInt(readAllLines().size());

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("quote", readAllLines().get(nahodnyCitat));
        modelAndView.addObject("pozadi", String.format("/images/obrazek-%d.jpg", randomNumber));

        return modelAndView;
    }
}
