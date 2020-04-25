package com.star_zero.gradle.nav_proguard_generator.sample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val name: String
) : Parcelable
