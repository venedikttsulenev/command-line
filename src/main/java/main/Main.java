package main;

import utils.Environment;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Environment environment = new Environment(
                System.console(),
                Paths.get(System.getProperty("user.dir"))
        );
    }
}
