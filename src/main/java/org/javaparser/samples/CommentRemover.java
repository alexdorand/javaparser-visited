package org.javaparser.samples;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class CommentRemover {

    private static final String FILE_PATH = "src/main/java/org/javaparser/samples/ReversePolishNotation.java";

    public static void main(String[] args) throws Exception {
        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));

        List<Comment> comments = cu.getAllContainedComments();
        List<Comment> unwantedComments = comments
                .stream()
                .filter(p -> !p.getCommentedNode().isPresent() || p instanceof LineComment)
                .collect(Collectors.toList());
        unwantedComments.forEach(Node::remove);

        System.out.println(cu.toString());

    }

}
