package com.nuncamaria.navigation.viewloaderpath

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ProviderInfo
import android.os.Bundle
import java.lang.ref.WeakReference

class NavigationRouteLoaderPaths(context: Context) {

    private val mContext: WeakReference<Context> = WeakReference(context)

    companion object {
        private var sInstance: NavigationRouteLoaderPaths? = null
        private val sLock = Any()

        fun getInstance(context: Context): NavigationRouteLoaderPaths? {
            if (sInstance == null) {
                synchronized(sLock) {
                    if (sInstance == null) {
                        sInstance = NavigationRouteLoaderPaths(context)
                    }
                }
            }
            return sInstance
        }
    }

    fun discoverAndInitialize(moduleName: String? = null) {
        try {
            mContext.get()?.let { context ->
                val provider = ComponentName(
                    context.packageName,
                    NavigationRouteProvider::class.java.name
                )
                val providerInfo: ProviderInfo = context.packageManager
                    .getProviderInfo(provider, PackageManager.GET_META_DATA)
                val metadata = providerInfo.metaData
                discoverAndInitialize(metadata, moduleName)
            }
        } catch (exception: PackageManager.NameNotFoundException) {
            throw exception
        }
    }

    private fun discoverAndInitialize(metadata: Bundle?, moduleName: String?) {
        try {
            metadata?.let { providerMetadata ->
                if (moduleName != null) {
                    val keys = providerMetadata.keySet()
                    val key = keys.filter { metadataKey ->
                        providerMetadata.getString(metadataKey) == moduleName
                    }
                    key.firstOrNull()?.let { moduleLoaderKey ->
                        instanceLoaderClass(moduleLoaderKey)
                    }
                } else {
                    val keys = providerMetadata.keySet()
                    for (key in keys) {
                        instanceLoaderClass(key)
                    }
                }
            }
        } catch (exception: ClassNotFoundException) {
            throw exception
        }
    }

    private fun instanceLoaderClass(loaderClassPath: String) {
        val clazz = Class.forName(loaderClassPath)
        if (NavigationRouteLoader::class.java.isAssignableFrom(clazz)) {
            clazz.getConstructor().newInstance()
        }
    }
}