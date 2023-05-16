package ru.tinkoff.edu.java;
public sealed abstract class AbstractParser implements Parser permits GitHubParser, StackOverflowParser {
    protected Parser nextParser;
    public void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}