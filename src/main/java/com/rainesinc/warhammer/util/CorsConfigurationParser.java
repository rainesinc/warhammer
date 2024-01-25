package com.rainesinc.warhammer.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CorsConfigurationParser {

    public static List<String> getAllowedHeaders(){

        return getFromFile("cors.allowed.headers");
    }

    public static List<String> getAllowedMethods(){
        return getFromFile("cors.allowed.methods");
    }

    public static List<String> getAllowedOrigins(){

        return getFromFile("cors.allowed.origins");
    }

    public static List<String> getExposedHeaders(){

        return getFromFile("cors.exposed.headers");
    }

    private static List<String> getFromFile(String fileName){
        List<String> list = new ArrayList<>();
        InputStream is = CorsConfigurationParser.class
                .getClassLoader().getResourceAsStream(fileName);
        assert is != null;
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine())
            list.add(sc.nextLine());
        return list;
    }


}
