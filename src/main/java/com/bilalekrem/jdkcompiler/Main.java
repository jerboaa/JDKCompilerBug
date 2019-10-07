package com.bilalekrem.jdkcompiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Main
{
    public static void main(String[] args)
    {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        ArrayList<String> customClasses = new ArrayList<>();
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("-proc:only");
        arguments.add("-classpath");
        arguments.add("src/main/java/com/bilalekrem/jdkcompiler");

        customClasses.add("src/main/java/com/bilalekrem/jdkcompiler/PojoClass.java");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(customClasses);

        JavaCompiler.CompilationTask task = compiler.getTask(new PrintWriter(baos), fileManager, diagnostics,
                arguments, null, compilationUnits);

        ArrayList<Processor> processors = new ArrayList<>();
        CustomProcessor processor = new CustomProcessor();
        processors.add(processor);
        task.setProcessors(processors);
        task.call();

        for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics())
        {
            System.err.format("Error on line %d in %s\n", diagnostic.getLineNumber(), diagnostic);
        }

        boolean singleResult = getKind(processor.element);
        boolean result = tryToAttemptGetKind(processor.element);
        System.out.println("Multiple: -- resulted: " + result);
        System.out.println("Single: -- resulted: " + singleResult);
	if (!singleResult) {
           throw new RuntimeException("Test failed!");
	}
        System.out.println("Test passed!");
    }

    private static boolean tryToAttemptGetKind(Element element) {
        int remainAttempt = 3;

        System.out.println("Printing element kind");
        while(remainAttempt-- > 0) {
            try {
                ElementKind kind = element.getKind();
                return true;
            } catch (AssertionError error) {
                System.err.println("Attempt failed, remaining attempt: " + remainAttempt);
            }
        }
        return false;
    }

    private static boolean getKind(Element element) {
            try {
                ElementKind kind = element.getKind();
                return true;
            } catch (AssertionError error) {
		error.printStackTrace();
		return false;
            }
    }
}
