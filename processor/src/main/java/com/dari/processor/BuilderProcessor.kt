package com.dari.processor

import com.dari.annotation.Builder
import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * Created By David Odari
 * On 30/11/19
 *
 **/
@AutoService(Processor::class)
class BuilderProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return linkedSetOf(Builder::class.java.canonicalName)
    }

    //Source versions of the Java™ programming language
    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        roundEnv.getElementsAnnotatedWith(Builder::class.java)
            .forEach {
                if (it.kind != ElementKind.CLASS) {

                    // Returns the messager used to report errors, warnings, and other notices.
                    processingEnv.messager.printMessage(
                        Diagnostic.Kind.ERROR,
                        "Annotate Classes Only"
                    )

                    //exit
                    return true
                }
                processAnnotation(it)
            }
        return false
    }

    private fun processAnnotation(element: Element?) {
        TODO("Implementation")
    }

}