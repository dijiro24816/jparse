package jparse;

import java.io.Serializable;
import java.util.List;

public record SyntaxNode(Rule rule, List<Token> tokens) implements Serializable {
}