package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.Repository
import com.nodes.plugin.utils.TextUtils
import java.util.Properties

class RepositoryImplGenerator : BaseGenerator<Repository>() {

    override val getTemplate = TemplateMap.REPOSITORY_IMPL

    override fun getClassName(modelObject: Repository) = "${modelObject.name}RepositoryImpl"
    override fun getPackageName(modelObject: Repository) = modelObject.name

    override fun additionalProperties(modelObject: Repository, properties: Properties?): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.REPOSITORY_CLASS, "${modelObject.name}Repository")
            setProperty(TemplateProperties.RETURN_TYPE, modelObject.returnType)
            setProperty(TemplateProperties.METHOD_NAME, TextUtils.textForMethodName(modelObject.returnType))
            setProperty(TemplateProperties.PARAM_NAME, TextUtils.textForParam(modelObject.returnType))
            val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
            if (pack != null && pack.contains(".repositories.")) {
                val subPack = pack.substring(0, pack.indexOf(".repositories."))
                setProperty(TemplateProperties.PACKAGE_REPOSITORY_NAME, subPack)
            }
        }
    }
}
