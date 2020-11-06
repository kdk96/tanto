object Deps {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.android.gradlePlugin}"

    const val bintrayGradlePlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"

    object androidx {
        const val fragment = "androidx.fragment:fragment:${Versions.androidx.fragment}"
    }

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"

    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
}
