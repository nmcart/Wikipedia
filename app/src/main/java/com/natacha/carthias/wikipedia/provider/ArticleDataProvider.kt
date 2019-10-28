package com.natacha.carthias.wikipedia.provider

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.natacha.carthias.wikipedia.models.Urls
import com.natacha.carthias.wikipedia.models.WikiResult
import java.io.Reader
import java.lang.Exception

/**
 * Created by Natacha Carthias on 28/10/2019
 */

class ArticleDataProvider {

    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "PluralSight Wikipedia")
    }

    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getSearchUrl(term, skip, take).httpGet().responseObject(WikipediaDataDeserializer()){
            _, response, result ->

            if(response.statusCode != 200) {
                throw Exception("Unable to get articles")
            }
            val(data, _) = result
            responseHandler.invoke(data as @kotlin.ParameterName(name = "result") WikiResult)
        }

        fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?) {
            Urls.getRandomUrl(take).httpGet()
                .responseObject(WikipediaDataDeserializer()){_, response, result ->

                    if(response.statusCode != 200) {
                        throw Exception("Unable to get articles")
                    }
                    val(data, _) = result
                    responseHandler.invoke(data as @kotlin.ParameterName(name = "result") WikiResult)
                }
        }
    }

    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, WikiResult::class.java)
        }
    }
