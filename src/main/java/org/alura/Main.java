package org.alura;

import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;

public class Main {
    public static void main(String[] args) {
        FlatMaterialDesignDarkIJTheme.setup();
        new MainFrame();
    }
}