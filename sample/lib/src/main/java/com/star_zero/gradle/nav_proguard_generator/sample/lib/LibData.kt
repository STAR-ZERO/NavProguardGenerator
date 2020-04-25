package com.star_zero.gradle.nav_proguard_generator.sample.lib

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LibData(
    val id: Int
) : Parcelable
