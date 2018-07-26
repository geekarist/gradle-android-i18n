package com.github.gradle.android.i18n.di

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.gradle.android.i18n.util.Lf4SpacesIndenter
import org.koin.dsl.module.applicationContext

object AppModule {

    val applicationModule = applicationContext {

        /**
         * [XmlMapper] singleton.
         */
        bean {
            XmlMapper().apply {
                registerKotlinModule()

                val indenter = Lf4SpacesIndenter()
                val printer = DefaultXmlPrettyPrinter()
                printer.indentObjectsWith(indenter)
                printer.indentArraysWith(indenter)

                setDefaultPrettyPrinter(printer)
                configure(SerializationFeature.INDENT_OUTPUT, true)
                configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
            }
        }
    }
}