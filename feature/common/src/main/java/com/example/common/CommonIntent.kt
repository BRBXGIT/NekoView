package com.example.common

import com.example.data.remote.models.user_favorites_ids.Item0

sealed class CommonIntent {
    data class SetNavIndex(val index: Int): CommonIntent()
    data class GetUserToken(
        val email: String,
        val password: String
    ): CommonIntent()
    data class ChangeVideoQuality(val quality: Int): CommonIntent()
    data object FetchVideoQuality: CommonIntent()
    data object FetchShowSkipOpeningButton: CommonIntent()
    data object ChangeShowSkipOpeningButton: CommonIntent()
    data object FetchAutoSkipOpening: CommonIntent()
    data object ChangeAutoSkipOpening: CommonIntent()
    data object ChangeAutoplay: CommonIntent()
    data object FetchAutoPlay: CommonIntent()
    data class FavoritesNeedReload(val reload: Boolean): CommonIntent()
    data class ChangeFeatured(val featured: List<Item0>): CommonIntent()
    data class ChangeSessionToken(val token: String): CommonIntent()
}