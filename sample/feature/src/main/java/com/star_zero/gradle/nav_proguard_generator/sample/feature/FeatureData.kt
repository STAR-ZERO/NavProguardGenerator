package com.star_zero.gradle.nav_proguard_generator.sample.feature

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeatureData(
    val id: Int
) : Parcelable
