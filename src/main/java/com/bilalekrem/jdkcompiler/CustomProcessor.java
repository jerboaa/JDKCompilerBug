package com.bilalekrem.jdkcompiler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class CustomProcessor extends AbstractProcessor
{
    Element element;

    static final Set<String> supportedAnnotations = new HashSet<String>(Arrays.asList("*"));


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment)
    {
        if(roundEnvironment.getRootElements().size() > 0 )
        {
            TypeElement el = (TypeElement) roundEnvironment.getRootElements().iterator().next();
            element = el;
        }


        return true;
    }

    @Override
    public Set<String> getSupportedOptions()
    {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes()
    {
        return supportedAnnotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }
}
