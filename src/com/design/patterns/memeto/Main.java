package com.design.patterns.memeto;

public class Main {

    public static void main(String[] args) {

        Document doc = new Document("Hello", 5, "consolata");
        History docHistory = new History();

        doc.setContent("New Hello");
        docHistory.push(doc.createContentState());

        doc.setContent("Ola pola");
        docHistory.push(doc.createContentState());

        doc.setFontName("incosolata");
        docHistory.push(doc.createFontNameState());


        doc.setFontName("robota");
        System.out.println(doc);

        doc.restoreState(docHistory.pop());

        System.out.println(doc);

        doc.restoreState(docHistory.pop());
        doc.restoreState(docHistory.pop());
        System.out.println(doc);
    }
}
