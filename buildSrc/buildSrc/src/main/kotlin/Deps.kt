object Deps {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.android.gradlePlugin}"

    const val bintrayGradlePlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"

    object androidx {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidx.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidx.core}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidx.fragment}"
        const val material = "com.google.android.material:material:${Versions.androidx.material}"
        const val preference = "androidx.preference:preference-ktx:${Versions.androidx.preference}"
    }

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
}
