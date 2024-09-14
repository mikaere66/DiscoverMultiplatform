package com.michaelrmossman.multiplatform.discover.collections.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertiesCycleLane(

    /* Only 4943/4947 items have a value */
    @SerialName("popup")
    val popup: String? = null,

    @SerialName("id")
    val id: Long,

    /* Only 4886/4947 items have a value */
    @SerialName("Type")
    val type: String? = null,

    /* Only 570/4947 items have a value */
    @SerialName("Name")
    val name: String? = null,

    @SerialName("ServiceStatus")
    val status: String, // ? = null,

    @SerialName("StrategicNetworkCategory")
    val category: String, // ? = null,

    @SerialName("PublicRelevance")
    val relevance: String, // ? = null,

    @SerialName("Ownership")
    val ownership: String, // ? = null,

    /* Only 55/4947 items have a value */
    @SerialName("TrafficDirection")
    val direction: String? = null,

    @SerialName("LastEditDate")
    val edited: String // ? = null
)