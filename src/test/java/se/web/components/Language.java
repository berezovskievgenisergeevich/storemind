package se.web.components;

public enum Language {
    English("[data-testid='language-en']"),
    Deutsch("[data-testid='language-de']"),
    Français("[data-testid='language-fr']");
    public final String selector;

    private Language(String selector) {
        this.selector = selector;
    }
}