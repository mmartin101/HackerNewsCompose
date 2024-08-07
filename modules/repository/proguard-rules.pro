# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.google.firebase.database.GenericTypeIndicator{*;}
-keepattributes Signature
-dontwarn java.lang.invoke.StringConcatFactory

#-dontwarn com.mmartin.hackernewscompose.repository.NewsFeedRepository
#-dontwarn com.mmartin.hackernewscompose.repository.RepositoryModule
#-dontwarn com.mmartin.hackernewscompose.repository.RepositoryModule_ProvideCoroutineDispatcherFactory
#-dontwarn com.mmartin.hackernewscompose.repository.RepositoryModule_ProvideNewsFeedDatabaseRepositoryFactory
#-dontwarn com.mmartin.hackernewscompose.repository.RepositoryModule_ProvideNewsFeedFirebaseRepositoryFactory
#-dontwarn com.mmartin.hackernewscompose.repository.RepositoryModule_ProvideNewsFeedRemoteRepositoryFactory
#-dontwarn com.mmartin.hackernewscompose.repository.RepositoryModule_ProvideNewsItemDaoFactory
#-dontwarn com.mmartin.hackernewscompose.repository.db.NewsItemDao
#-dontwarn com.mmartin.hackernewscompose.repository.db.models.StoriesList