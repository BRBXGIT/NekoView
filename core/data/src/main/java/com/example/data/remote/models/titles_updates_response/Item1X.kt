package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item1X(
    @SerialName("downloads")
    val downloads: Int = 0,
    @SerialName("episodes")
    val episodes: Episodes = Episodes(),
    @SerialName("hash")
    val hash: String = "",
    @SerialName("leechers")
    val leechers: Int = 0,
    @SerialName("magnet")
    val magnet: String = "",
    @SerialName("metadata")
    val metadata: String? = null,
    @SerialName("quality")
    val quality: Quality = Quality(),
    @SerialName("raw_base64_file")
    val rawBase64File: String? = null,
    @SerialName("seeders")
    val seeders: Int = 0,
    @SerialName("size_string")
    val sizeString: String = "",
    @SerialName("torrent_id")
    val torrentId: Int = 0,
    @SerialName("total_size")
    val totalSize: Long = 0,
    @SerialName("uploaded_timestamp")
    val uploadedTimestamp: Int = 0,
    @SerialName("url")
    val url: String = ""
)